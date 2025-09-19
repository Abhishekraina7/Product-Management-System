# Product Management System

A comprehensive **Java-based desktop application** for managing product inventory with a user-friendly GUI interface and MySQL database integration.

## 📋 Project Overview

The Product Management System is a complete CRUD (Create, Read, Update, Delete) application designed for small to medium businesses to manage their product inventory efficiently. Built using Java Swing framework with MySQL database backend, it provides an intuitive interface for product management operations.

### 🛠 Technology Stack
- **Programming Language**: Java SE
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **IDE**: NetBeans
- **Build Tool**: Apache Ant

### 📁 Project Structure
<img width="505" height="496" alt="image" src="https://github.com/user-attachments/assets/dbb86286-9328-4449-a36b-0f263cc64cdd" />

```
src/product_management_system/
├── Login.java          # User authentication
├── Main.java           # Main menu navigation
├── AddProduct.java     # Add new products
├── ViewDetail.java     # View and search products
├── UpdateProduct.java  # Update existing products
├── Conn.java          # Database connection
└── icons/             # UI images and icons
```

## 🚀 Features

### 1. **Secure Authentication System**
- Admin login with username/password verification
- Session-based navigation
- **Default Credentials**: 
  - Username: `admin`
  - Password: `12345`

![Admin Login](https://github.com/user-attachments/assets/6620a586-fb90-40c5-a044-77580ab5fcf2)

### 2. **Intuitive Main Dashboard**
- Clean, user-friendly interface
- Quick access to all major functions
- Visual button-based navigation

![Main Dashboard](https://github.com/user-attachments/assets/ef0037f8-b9b7-4e0a-9047-65d046ae0497)

### 3. **Product Management**

#### ➕ Add Products
- Auto-generated 4-digit Product IDs
- Comprehensive product information form
- Predefined company dropdown list
- Real-time data validation

![Add Product](https://github.com/user-attachments/assets/884136e8-948b-4a67-9e90-2a88353bab67)

#### 👁 View Products
- Tabular display of all products
- Real-time data from database
- Professional table formatting

![View Products](https://github.com/user-attachments/assets/9d737c29-e489-44b9-9599-1664f9196b22)

#### 🔍 Search Functionality
- Search by Product ID
- Instant results filtering
- Dynamic table updates

![Search Products](https://github.com/user-attachments/assets/13a4c1ab-2085-4b8f-bcb5-cab627db6ae7)

#### ✏️ Update Products
- Select products from dropdown
- Real-time data population
- Editable fields for modification

![Update Products](https://github.com/user-attachments/assets/90dbaecc-451e-4d8b-a739-1f5443ea5034)

## 🗃 Database Schema

### Tables Structure

#### `login` Table
| Column | Type | Constraints |
|--------|------|-------------|
| username | VARCHAR(50) | PRIMARY KEY |
| password | VARCHAR(50) | NOT NULL |

#### `product` Table
| Column | Type | Description |
|--------|------|-------------|
| proid | VARCHAR(20) | Product ID (Primary Key) |
| pname | VARCHAR(100) | Product Name |
| stock | INT | Stock Level |
| cost | DECIMAL(10,2) | Product Cost |
| noorder | INT | Number of Orders |
| cname | VARCHAR(50) | Company Name |
| discount | VARCHAR(10) | Discount Percentage |

## 🔧 Installation & Setup

### Prerequisites
- **Java Development Kit (JDK)** 8 or higher
- **MySQL Server** 5.7 or higher
- **NetBeans IDE** (optional, for development)

### Database Setup
1. Install and start MySQL Server
2. Create the database and tables:

```sql
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
```

### Application Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/Abhishekraina7/Product-Management-System.git
   ```

2. Open the project in NetBeans IDE

3. Update database credentials in `Conn.java` if needed

4. Build and run the project:
   ```bash
   # Using NetBeans: Build → Clean and Build
   # Or using Ant from command line:
   ant clean compile jar
   ```

5. Run the application:
   ```bash
   java -jar dist/Product_Management_System.jar
   ```

## 🏗 Architecture & Design

### Design Patterns Used
- **Observer Pattern**: ActionListener implementations
- **Factory Pattern**: Database connection creation
- **Template Method**: Consistent window setup across classes

### Key Classes
- **`Conn.java`**: Database connectivity and connection management
- **`Login.java`**: Authentication and user verification
- **`Main.java`**: Central navigation hub
- **`AddProduct.java`**: Product creation functionality
- **`ViewDetail.java`**: Product display and search operations
- **`UpdateProduct.java`**: Product modification capabilities

## 📈 Future Enhancements

### Technical Improvements
- [ ] **Security Enhancements**: Implement PreparedStatements to prevent SQL injection
- [ ] **Connection Pooling**: Add database connection pooling for better performance
- [ ] **Input Validation**: Comprehensive data validation and sanitization
- [ ] **Exception Handling**: Improved error handling with custom exceptions
- [ ] **Logging Framework**: Integration of SLF4J/Logback for better debugging

### Feature Additions
- [ ] **Multi-user Support**: Role-based access control
- [ ] **Reporting Module**: Generate inventory and sales reports
- [ ] **Export/Import**: CSV and Excel data handling
- [ ] **Backup System**: Automated database backup functionality
- [ ] **Dashboard Analytics**: Sales trends and inventory analytics

### UI/UX Improvements
- [ ] **Responsive Design**: Scalable layouts using layout managers
- [ ] **Modern Look**: Material Design principles
- [ ] **Dark Mode**: Theme switching capability
- [ ] **Keyboard Shortcuts**: Enhanced accessibility

## 📚 Documentation

For detailed technical information and interview preparation:

- **[Technical Documentation](TECHNICAL_DOCUMENTATION.md)**: Comprehensive system architecture and implementation details
- **[Interview Guide](INTERVIEW_GUIDE.md)**: Complete Q&A guide for technical interviews

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

- Java Swing documentation and tutorials
- MySQL community for database support
- NetBeans IDE for development environment

---

**Note**: This project is designed for educational and demonstration purposes. For production use, please implement proper security measures including input validation, prepared statements, and secure authentication mechanisms.






