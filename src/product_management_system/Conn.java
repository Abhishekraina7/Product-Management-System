
package product_management_system;
import java.sql.*;

/**
 * Database Connection Manager Class
 * Handles MySQL database connectivity for the Product Management System
 * 
 * Technical Details:
 * - Uses MySQL JDBC Driver for database connectivity
 * - Establishes connection to 'productmanagementsystem' database
 * - Creates Statement object for SQL execution
 * 
 * Security Note: Database credentials are hardcoded (not recommended for production)
 * Improvement: Use configuration files or environment variables
 */
public class Conn {
    
    // Database connection object
    Connection c;
    // SQL statement execution object
    Statement s;
    
    /**
     * Constructor: Establishes database connection
     * Loads MySQL JDBC driver and creates connection to database
     * 
     * Connection Details:
     * - Database: productmanagementsystem
     * - User: root
     * - Password: SS30@krhps (hardcoded - security risk)
     * 
     * @throws Exception if database connection fails
     */
    public Conn(){
        try {
            // Load MySQL JDBC driver (required for older Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection to MySQL database
            // Format: jdbc:mysql://[host]:[port]/[database_name]
            c = DriverManager.getConnection("jdbc:mysql:///productmanagementsystem", "root", "SS30@krhps");
            
            // Create statement object for SQL execution
            s = c.createStatement();
            
        } catch (Exception e) {
            // Generic exception handling - logs error to console
            // Production improvement: Use proper logging framework
            e.printStackTrace();
        }
    }
    
}
