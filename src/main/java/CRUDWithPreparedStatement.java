import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDWithPreparedStatement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // 1. CREATE
            String insertSQL = "INSERT INTO clients (first_name, last_name, date_of_birth, phone_number) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setString(1, "Jane");
                insertStmt.setString(2, "Smith");
                insertStmt.setDate(3, java.sql.Date.valueOf("1990-05-15"));
                insertStmt.setString(4, "+380671234567");
                int rowsInserted = insertStmt.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);
            }

            // 2. READ
            String selectSQL = "SELECT * FROM clients";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSQL)) {
                ResultSet resultSet = selectStmt.executeQuery();
                System.out.println("Clients in the database:");
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("client_id") +
                            ", Name: " + resultSet.getString("first_name") +
                            " " + resultSet.getString("last_name") +
                            ", Phone: " + resultSet.getString("phone_number"));
                }
            }

            // 3. UPDATE
            String updateSQL = "UPDATE clients SET phone_number = ? WHERE first_name = ? AND last_name = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
                updateStmt.setString(1, "+380931234567");
                updateStmt.setString(2, "Jane");
                updateStmt.setString(3, "Smith");
                int rowsUpdated = updateStmt.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);
            }

            // 4. DELETE
            String deleteSQL = "DELETE FROM clients WHERE first_name = ? AND last_name = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
                deleteStmt.setString(1, "Jane");
                deleteStmt.setString(2, "Smith");
                int rowsDeleted = deleteStmt.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}