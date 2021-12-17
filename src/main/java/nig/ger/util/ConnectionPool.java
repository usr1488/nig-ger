package nig.ger.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final int connectionThreshold;
    private final String url;
    private final String login;
    private final String password;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();
    private final Queue<Connection> weakConnections = new ConcurrentLinkedQueue<>();
    private final Lock lock = new ReentrantLock(true);

    public ConnectionPool(String url, String login, String password) {
        this(url, login, password, 10);
    }

    public ConnectionPool(String url, String login, String password, int connectionThreshold) {
        this.url = url;
        this.login = login;
        this.password = password;
        this.connectionThreshold = connectionThreshold;

        Runtime.getRuntime().addShutdownHook(new Thread(scheduledExecutorService::shutdownNow));
    }

    public Connection getConnection() {
        Connection connection = connections.poll();

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Connection finalConnection = connection; //lambda effectively final

        return (Connection) Proxy.newProxyInstance(
                connection.getClass().getClassLoader(),
                new Class[] { Connection.class },
                (proxy, method, args) -> {
                    if (method.getName().equals("close")) {
                        releaseConnection(finalConnection);
                        return null;
                    } else {
                        return method.invoke(finalConnection, args);
                    }
                }
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
                    weakConnections.remove().close();
                } catch (NoSuchElementException ignored) {
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            },30, TimeUnit.SECONDS);
        }
    }
}
