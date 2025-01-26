import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetadataExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/travel_agency";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Database: " + metaData.getDatabaseProductName());
            System.out.println("Database Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver: " + metaData.getDriverName());

            ResultSet tables = metaData.getTables(null, null, "%", null);
            while (tables.next()) {
                System.out.println("Table: " + tables.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}