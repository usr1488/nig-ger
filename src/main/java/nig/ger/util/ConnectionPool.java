package nig.ger.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ConnectionPool implements DataSource {
    private final int connectionThreshold;
    private final String url;
    private final String username;
    private final String password;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final Map<Connection, Connection> connectionToProxyMap = new ConcurrentHashMap<>();
    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();
    private final Queue<Connection> weakConnections = new ConcurrentLinkedQueue<>();
    private final Lock lock = new ReentrantLock(true);

    public ConnectionPool(String url, String username, String password) {
        this(url, username, password, 10);
    }

    public ConnectionPool(String url, String username, String password, int connectionThreshold) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connectionThreshold = connectionThreshold;

        Runtime.getRuntime().addShutdownHook(new Thread(scheduledExecutorService::shutdownNow));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        System.out.printf("connections: %d\nweak connections: %d\n\n", connections.size(), weakConnections.size());

        return connectionToProxyMap.computeIfAbsent(
                Optional.ofNullable(connections.poll()).orElse(DriverManager.getConnection(url, username, password)),
                key -> (Connection) Proxy.newProxyInstance(
                        key.getClass().getClassLoader(),
                        new Class[] { Connection.class },
                        (proxy, method, args) -> {
                            if (method.getName().equals("close")) {
                                releaseConnection(key);
                                return null;
                            } else {
                                return method.invoke(key, args);
                            }
                        }
                )
        );
    }

    private void releaseConnection(Connection connection) {
        lock.lock();

        Optional.ofNullable(weakConnections.poll()).ifPresent(connections::add);

        if (connections.size() < connectionThreshold) {
            connections.add(connection);
            lock.unlock();
        } else {
            lock.unlock();
            weakConnections.add(connection);
            scheduledExecutorService.schedule(() -> {
                try {
                    connectionToProxyMap.remove(weakConnections.remove()).close();
                } catch (NoSuchElementException ignored) { // used remove() instead of poll() to prevent NPE on close()
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }, 30, TimeUnit.SECONDS);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return (T) getConnection();
        }

        throw new SQLException("Wrapping type not supported: " + iface.getName());
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return Connection.class.isAssignableFrom(iface);
    }

    @Override
    public PrintWriter getLogWriter() {
        return new PrintWriter(System.out);
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }
}
