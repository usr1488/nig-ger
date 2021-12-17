package nig.ger;

import nig.ger.util.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public ConnectionPool getConnectionPool() {
        return new ConnectionPool("jdbc:h2:mem:", "root", "root");
    }
}
