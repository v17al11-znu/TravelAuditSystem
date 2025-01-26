import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDWithStatement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // 1. CREATE
            String insertSQL = "INSERT INTO clients (first_name, last_name, date_of_birth, phone_number) " +
                    "VALUES ('John', 'Doe', '1980-01-01', '+380501234567')";
            int rowsInserted = statement.executeUpdate(insertSQL);
            System.out.println("Rows inserted: " + rowsInserted);

            // 2. READ
            String selectSQL = "SELECT * FROM clients";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            System.out.println("Clients in the database:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("client_id") +
                        ", Name: " + resultSet.getString("first_name") +
                        " " + resultSet.getString("last_name") +
                        ", Phone: " + resultSet.getString("phone_number"));
            }

            // 3. UPDATE
            String updateSQL = "UPDATE clients SET phone_number = '+380931234567' WHERE first_name = 'John' AND last_name = 'Doe'";
            int rowsUpdated = statement.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowsUpdated);

            // 4. DELETE
            String deleteSQL = "DELETE FROM clients WHERE first_name = 'John' AND last_name = 'Doe'";
            int rowsDeleted = statement.executeUpdate(deleteSQL);
            System.out.println("Rows deleted: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}