import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesConnectionExample {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("db.properties")) {
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connected to the database!");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}