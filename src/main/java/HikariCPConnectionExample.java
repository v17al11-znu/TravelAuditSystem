import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPConnectionExample {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/travel_agency");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10);

        try (HikariDataSource dataSource = new HikariDataSource(config);
            Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database using HikariCP!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}