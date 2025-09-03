import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database configuration - Update password to match your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/Library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SREEHARI@123"; // Change to your MySQL password
    
    // Connection properties for MySQL Connector/J 9.4.0
    private static final String FULL_URL = URL + 
        "?useSSL=false" +
        "&allowPublicKeyRetrieval=true" +
        "&serverTimezone=UTC" +
        "&useUnicode=true" +
        "&characterEncoding=utf8";
    
    /**
     * Get database connection using MySQL Connector/J 9.4.0
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(FULL_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test database connection
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Database connected successfully!");
            System.out.println("MySQL Connector/J 9.4.0 is working correctly!");
            return true;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.err.println("Check if MySQL server is running and credentials are correct");
            return false;
        }
    }
}
