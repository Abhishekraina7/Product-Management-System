# Setup and Development Guide

## 🛠 Development Environment Setup

### Prerequisites
Before setting up the Product Management System, ensure you have the following installed:

1. **Java Development Kit (JDK)**
   - Version: JDK 8 or higher
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version` and `javac -version`

2. **MySQL Server**
   - Version: MySQL 5.7 or higher
   - Download: [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
   - Alternative: [XAMPP](https://www.apachefriends.org/) for integrated Apache-MySQL-PHP solution

3. **IDE (Optional but Recommended)**
   - **NetBeans IDE**: Project is configured for NetBeans
   - **IntelliJ IDEA**: Universal Java IDE
   - **Eclipse**: Alternative Java IDE

### Database Configuration

#### 1. MySQL Server Setup
1. Start MySQL service:
   ```bash
   # Windows (Command Prompt as Administrator)
   net start mysql
   
   # Linux/macOS
   sudo systemctl start mysql
   # or
   sudo service mysql start
   ```

2. Login to MySQL:
   ```bash
   mysql -u root -p
   ```

#### 2. Database and Table Creation
Execute the following SQL commands:

```sql
-- Create database
CREATE DATABASE productmanagementsystem;
USE productmanagementsystem;

-- Create login table
CREATE TABLE login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Insert default admin credentials
INSERT INTO login VALUES ('admin', '12345');

-- Create product table
CREATE TABLE product (
    proid VARCHAR(20) PRIMARY KEY,
    pname VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    cost DECIMAL(10,2) NOT NULL,
    noorder INT NOT NULL,
    cname VARCHAR(50) NOT NULL,
    discount VARCHAR(10)
);

-- Optional: Insert sample data
INSERT INTO product VALUES 
('1001', 'Sample Product 1', 100, 25.50, 10, 'Britania', '5%'),
('1002', 'Sample Product 2', 200, 15.75, 25, 'Sunsilk', '10%'),
('1003', 'Sample Product 3', 150, 45.00, 5, 'Ponds', '15%');

-- Verify data
SELECT * FROM login;
SELECT * FROM product;
```

#### 3. Database Configuration in Application
Update the database connection details in `src/product_management_system/Conn.java`:

```java
// Current configuration (modify as needed)
c = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagementsystem", "root", "your_password");
```

**Connection String Components:**
- **Host**: `localhost` (change if MySQL is on different server)
- **Port**: `3306` (default MySQL port)
- **Database**: `productmanagementsystem`
- **Username**: `root` (or your MySQL username)
- **Password**: Update with your MySQL password

### Application Setup

#### Option 1: Using NetBeans IDE
1. **Clone Repository**:
   ```bash
   git clone https://github.com/Abhishekraina7/Product-Management-System.git
   cd Product-Management-System
   ```

2. **Open Project**:
   - Launch NetBeans IDE
   - File → Open Project
   - Navigate to cloned repository folder
   - Select the project and open

3. **Configure Libraries**:
   - Right-click project → Properties
   - Libraries → Add JAR/Folder
   - Add MySQL JDBC driver (mysql-connector-java.jar)

4. **Build and Run**:
   - Build → Clean and Build Project
   - Run → Run Project
   - Or press F6 to run

#### Option 2: Command Line Setup
1. **Download MySQL JDBC Driver**:
   ```bash
   # Download MySQL Connector/J
   wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.33.jar
   # Or download from: https://dev.mysql.com/downloads/connector/j/
   ```

2. **Set Classpath**:
   ```bash
   export CLASSPATH=$CLASSPATH:path/to/mysql-connector-java-8.0.33.jar
   ```

3. **Compile Project**:
   ```bash
   cd src
   javac -cp ".:mysql-connector-java-8.0.33.jar" product_management_system/*.java
   ```

4. **Run Application**:
   ```bash
   java -cp ".:mysql-connector-java-8.0.33.jar" product_management_system.Login
   ```

#### Option 3: Using Ant Build Tool
1. **Build with Ant**:
   ```bash
   # Clean previous builds
   ant clean
   
   # Compile source code
   ant compile
   
   # Create JAR file
   ant jar
   
   # Run application
   java -jar dist/Product_Management_System.jar
   ```

## 🚀 Running the Application

### Startup Sequence
1. **Launch Application**: Run `Login.java` main method
2. **Login Screen**: Enter credentials (admin/12345)
3. **Main Dashboard**: Navigate to desired functionality
4. **Product Operations**: Add, View, Update products as needed

### Default Login Credentials
- **Username**: `admin`
- **Password**: `12345`

**Note**: Change default credentials for security in production environments.

## 🔧 Development Workflow

### Project Structure
```
Product-Management-System/
├── src/
│   └── product_management_system/
│       ├── AddProduct.java      # Product creation module
│       ├── Conn.java           # Database connection
│       ├── Login.java          # Authentication module
│       ├── Main.java           # Main dashboard
│       ├── UpdateProduct.java  # Product modification module
│       ├── ViewDetail.java     # Product display module
│       └── icons/              # UI resources
├── build/                      # Compiled classes
├── dist/                       # Distribution files
├── nbproject/                  # NetBeans configuration
├── build.xml                   # Ant build configuration
└── README.md                   # Project documentation
```

### Adding New Features

#### 1. Database Changes
```sql
-- Example: Adding new column to product table
ALTER TABLE product ADD COLUMN category VARCHAR(50);

-- Update existing records
UPDATE product SET category = 'General' WHERE category IS NULL;
```

#### 2. Code Modifications
- **Add UI Components**: Follow existing pattern in other classes
- **Database Operations**: Extend `Conn.java` or create new DAO classes
- **Event Handling**: Implement `ActionListener` for new buttons
- **Form Validation**: Add input validation before database operations

#### 3. Best Practices for Extensions
```java
// Example: Adding input validation
public boolean validateProductData(String name, String cost) {
    if (name == null || name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Product name cannot be empty");
        return false;
    }
    
    try {
        double costValue = Double.parseDouble(cost);
        if (costValue < 0) {
            JOptionPane.showMessageDialog(this, "Cost cannot be negative");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid cost format");
        return false;
    }
    
    return true;
}
```

## 🐛 Debugging and Troubleshooting

### Common Issues and Solutions

#### 1. Database Connection Problems
**Error**: `SQLException: Access denied for user 'root'@'localhost'`
**Solution**: 
- Verify MySQL username/password
- Check if MySQL service is running
- Update credentials in `Conn.java`

#### 2. JDBC Driver Not Found
**Error**: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`
**Solution**:
- Add MySQL JDBC driver to classpath
- Download from: https://dev.mysql.com/downloads/connector/j/
- Add JAR file to project libraries

#### 3. Table/Database Not Found
**Error**: `SQLSyntaxErrorException: Table 'productmanagementsystem.product' doesn't exist`
**Solution**:
- Run database creation script
- Verify database and table names match code
- Check MySQL connection string

#### 4. UI Component Issues
**Error**: Images not loading or UI appears broken
**Solution**:
- Verify image files exist in `src/product_management_system/icons/`
- Check file paths in code
- Ensure proper case sensitivity (Linux/macOS)

### Debugging Techniques

#### 1. Enable Database Logging
```java
// Add to Conn.java constructor for debug information
System.out.println("Attempting database connection...");
System.out.println("Connection successful: " + c.isValid(5));
```

#### 2. SQL Query Debugging
```java
// Print SQL queries before execution
System.out.println("Executing query: " + query);
ResultSet rs = s.executeQuery(query);
```

#### 3. Exception Handling Enhancement
```java
// Replace generic printStackTrace with detailed logging
catch (SQLException e) {
    System.err.println("SQL Error Code: " + e.getErrorCode());
    System.err.println("SQL State: " + e.getSQLState());
    System.err.println("Error Message: " + e.getMessage());
    e.printStackTrace();
}
```

## 📊 Performance Optimization

### Database Performance
1. **Connection Pooling**: Implement connection pooling for production
2. **Prepared Statements**: Replace string concatenation with prepared statements
3. **Indexing**: Add indexes on frequently queried columns
4. **Connection Management**: Properly close connections and statements

### Application Performance
1. **Memory Management**: Dispose unused frames and components
2. **Event Threading**: Use SwingUtilities.invokeLater for UI updates
3. **Data Caching**: Cache frequently accessed data
4. **Lazy Loading**: Load data only when needed

## 🔒 Security Enhancements

### Immediate Improvements
1. **Input Validation**: Sanitize all user inputs
2. **Prepared Statements**: Prevent SQL injection attacks
3. **Password Hashing**: Hash passwords before database storage
4. **Configuration Files**: Move credentials to external config files

### Example Security Implementation
```java
// Secure password handling
public boolean authenticateUser(String username, char[] password) {
    String hashedPassword = hashPassword(new String(password));
    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, username);
        pstmt.setString(2, hashedPassword);
        
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        logger.error("Authentication error", e);
        return false;
    } finally {
        // Clear password from memory
        Arrays.fill(password, ' ');
    }
}
```

## 📱 Deployment Options

### Standalone JAR Deployment
```bash
# Create runnable JAR with dependencies
ant clean jar

# Create distribution with dependencies
mkdir product_management_dist
cp dist/Product_Management_System.jar product_management_dist/
cp mysql-connector-java-8.0.33.jar product_management_dist/

# Create startup script
echo "java -cp Product_Management_System.jar:mysql-connector-java-8.0.33.jar product_management_system.Login" > product_management_dist/start.sh
chmod +x product_management_dist/start.sh
```

### Web Application Migration
For converting to web application:
1. **Spring Boot**: Migrate to REST API backend
2. **Frontend**: Develop React/Angular frontend
3. **Database**: Continue with MySQL or migrate to PostgreSQL
4. **Security**: Implement JWT authentication
5. **Deployment**: Use Docker containers and cloud platforms

## 🧪 Testing Strategy

### Manual Testing Checklist
- [ ] **Authentication**: Test valid/invalid login credentials
- [ ] **Add Product**: Test with various input combinations
- [ ] **View Products**: Verify data display and search functionality
- [ ] **Update Product**: Test data modification and persistence
- [ ] **Database**: Test connection recovery and error handling
- [ ] **UI**: Test window navigation and button responses

### Automated Testing Setup
```java
// JUnit test example
@Test
public void testProductAddition() {
    AddProduct addProduct = new AddProduct();
    Product testProduct = new Product("TEST001", "Test Product", 100, 25.0, 5, "TestCorp", "10%");
    
    boolean result = addProduct.saveProduct(testProduct);
    assertTrue("Product should be added successfully", result);
}
```

## 📚 Learning Resources

### Java Swing Development
- [Oracle Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Java Swing Documentation](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)

### JDBC and Database
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

### Best Practices
- [Java Coding Standards](https://www.oracle.com/java/technologies/javase/codeconventions-introduction.html)
- [Clean Code Principles](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

This setup guide provides comprehensive instructions for getting the Product Management System running in various environments and development scenarios.