import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/travel_agency";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO tours (name, description, price, duration_days) VALUES ('Italy', 'Rome Tour', 1500.00, 7)");
                Savepoint savepoint = connection.setSavepoint();

                statement.executeUpdate("UPDATE clients SET phone_number = NULL WHERE client_id = 1");
                connection.rollback(savepoint); // Відкат до savepoint

                connection.commit();
                System.out.println("Transaction committed!");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction rolled back!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}