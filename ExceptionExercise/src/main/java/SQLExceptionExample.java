import java.sql.*;

public class SQLExceptionExample {

    public static void main(String[] args) {
        try {
            runQuery();
        } catch (SQLException e) {
            System.out.println("Caught SQLException in main:");
            System.out.println("Message: " + e.getMessage());
        }
    }

    static void runQuery() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM non_existing_table";
            stmt.executeQuery(sql);

        } catch (SQLException e) {
            throw e;
        }
    }
}