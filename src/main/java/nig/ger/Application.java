package nig.ger;

import nig.ger.util.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public DataSource connectionPool() {
        return new ConnectionPool("jdbc:h2:mem:", "root", "root");
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(connectionPool());
    }
}
