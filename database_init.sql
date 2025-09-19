-- ============================================================================
-- Product Management System - Database Initialization Script
-- ============================================================================
-- This script creates the necessary database structure and sample data
-- for the Product Management System application.
--
-- Author: Product Management System Team
-- Version: 1.0
-- Database: MySQL 5.7+
-- ============================================================================

-- Create database (uncomment if needed)
-- CREATE DATABASE productmanagementsystem;
-- USE productmanagementsystem;

-- ============================================================================
-- 1. CREATE TABLES
-- ============================================================================

-- Drop existing tables if they exist (for clean reinstallation)
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS login;

-- Create login table for user authentication
CREATE TABLE login (
    username VARCHAR(50) PRIMARY KEY COMMENT 'User login name',
    password VARCHAR(50) NOT NULL COMMENT 'User password (plain text - not recommended for production)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Account creation timestamp',
    last_login TIMESTAMP NULL COMMENT 'Last login timestamp'
) COMMENT = 'User authentication table';

-- Create product table for inventory management
CREATE TABLE product (
    proid VARCHAR(20) PRIMARY KEY COMMENT 'Product ID (auto-generated)',
    pname VARCHAR(100) NOT NULL COMMENT 'Product name',
    stock INT NOT NULL DEFAULT 0 COMMENT 'Stock level/inventory quantity',
    cost DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT 'Product cost/price',
    noorder INT NOT NULL DEFAULT 0 COMMENT 'Number of orders/quantity ordered',
    cname VARCHAR(50) NOT NULL COMMENT 'Company/brand name',
    discount VARCHAR(10) DEFAULT '0%' COMMENT 'Discount percentage',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Product creation timestamp',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update timestamp'
) COMMENT = 'Product inventory table';

-- ============================================================================
-- 2. CREATE INDEXES FOR PERFORMANCE
-- ============================================================================

-- Index on company name for faster filtering
CREATE INDEX idx_product_company ON product(cname);

-- Index on product name for search functionality
CREATE INDEX idx_product_name ON product(pname);

-- Index on stock level for inventory management
CREATE INDEX idx_product_stock ON product(stock);

-- ============================================================================
-- 3. INSERT INITIAL DATA
-- ============================================================================

-- Insert default admin user
INSERT INTO login (username, password) VALUES 
('admin', '12345'),
('manager', 'manager123'),
('user', 'user123');

-- Insert sample product data with various companies
INSERT INTO product (proid, pname, stock, cost, noorder, cname, discount) VALUES 
-- Britannia Products
('1001', 'Good Day Cookies', 500, 25.00, 120, 'Britania', '5%'),
('1002', 'Marie Biscuits', 300, 15.50, 80, 'Britania', '3%'),
('1003', 'Tiger Biscuits', 250, 20.00, 60, 'Britania', '8%'),

-- Sunsilk Products
('2001', 'Shampoo Smooth & Silky', 200, 180.00, 45, 'Sunsilk', '10%'),
('2002', 'Hair Conditioner', 150, 220.00, 30, 'Sunsilk', '12%'),
('2003', 'Hair Serum', 100, 350.00, 25, 'Sunsilk', '15%'),

-- Ponds Products
('3001', 'Cold Cream', 180, 95.00, 40, 'Ponds', '7%'),
('3002', 'Face Wash', 220, 125.00, 55, 'Ponds', '9%'),
('3003', 'Moisturizer', 160, 165.00, 35, 'Ponds', '11%'),

-- Parle-G Products
('4001', 'Parle-G Biscuits', 800, 12.00, 200, 'Parle-G', '2%'),
('4002', 'Hide & Seek Cookies', 400, 35.00, 90, 'Parle-G', '6%'),
('4003', 'Monaco Crackers', 350, 18.00, 70, 'Parle-G', '4%'),

-- Joy Products
('5001', 'Dishwash Liquid', 300, 85.00, 75, 'Joy', '8%'),
('5002', 'Hand Wash', 250, 65.00, 60, 'Joy', '6%'),

-- Dettol Products
('6001', 'Hand Sanitizer', 400, 120.00, 100, 'Dettol', '10%'),
('6002', 'Antiseptic Liquid', 200, 95.00, 50, 'Dettol', '8%'),
('6003', 'Soap Bar', 600, 35.00, 150, 'Dettol', '5%'),

-- Haldiram Products
('7001', 'Namkeen Mix', 180, 85.00, 45, 'Haldiram', '12%'),
('7002', 'Bhujia', 220, 75.00, 60, 'Haldiram', '10%'),
('7003', 'Sweets Box', 100, 350.00, 25, 'Haldiram', '18%'),

-- Horlicks Products
('8001', 'Classic Malt', 150, 385.00, 35, 'Horlicks', '15%'),
('8002', 'Chocolate Flavor', 120, 420.00, 28, 'Horlicks', '18%'),
('8003', 'Protein Plus', 100, 485.00, 22, 'Horlicks', '20%');

-- ============================================================================
-- 4. CREATE VIEWS FOR REPORTING (OPTIONAL)
-- ============================================================================

-- View for low stock products (stock < 50)
CREATE VIEW low_stock_products AS
SELECT 
    proid,
    pname,
    stock,
    cname,
    cost
FROM product 
WHERE stock < 50
ORDER BY stock ASC;

-- View for high-value products (cost > 200)
CREATE VIEW premium_products AS
SELECT 
    proid,
    pname,
    cost,
    cname,
    discount
FROM product 
WHERE cost > 200.00
ORDER BY cost DESC;

-- View for product summary by company
CREATE VIEW company_product_summary AS
SELECT 
    cname AS company_name,
    COUNT(*) AS total_products,
    SUM(stock) AS total_stock,
    AVG(cost) AS average_cost,
    MIN(cost) AS min_cost,
    MAX(cost) AS max_cost
FROM product 
GROUP BY cname
ORDER BY total_products DESC;

-- ============================================================================
-- 5. VERIFY DATA INSTALLATION
-- ============================================================================

-- Check login table
SELECT 'Login Table:' as Info;
SELECT * FROM login;

-- Check product table
SELECT 'Product Table:' as Info;
SELECT COUNT(*) as total_products FROM product;

-- Display sample products
SELECT 'Sample Products:' as Info;
SELECT proid, pname, stock, cost, cname FROM product LIMIT 10;

-- Display low stock products
SELECT 'Low Stock Alert:' as Info;
SELECT * FROM low_stock_products;

-- Display company summary
SELECT 'Company Summary:' as Info;
SELECT * FROM company_product_summary;

-- ============================================================================
-- 6. STORED PROCEDURES (OPTIONAL - FOR ADVANCED FEATURES)
-- ============================================================================

DELIMITER //

-- Procedure to add a new product with validation
CREATE PROCEDURE AddProduct(
    IN p_proid VARCHAR(20),
    IN p_pname VARCHAR(100),
    IN p_stock INT,
    IN p_cost DECIMAL(10,2),
    IN p_noorder INT,
    IN p_cname VARCHAR(50),
    IN p_discount VARCHAR(10)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Validate inputs
    IF p_stock < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock cannot be negative';
    END IF;
    
    IF p_cost < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cost cannot be negative';
    END IF;
    
    -- Insert product
    INSERT INTO product (proid, pname, stock, cost, noorder, cname, discount)
    VALUES (p_proid, p_pname, p_stock, p_cost, p_noorder, p_cname, p_discount);
    
    COMMIT;
END//

-- Procedure to update stock level
CREATE PROCEDURE UpdateStock(
    IN p_proid VARCHAR(20),
    IN p_new_stock INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Validate stock
    IF p_new_stock < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock cannot be negative';
    END IF;
    
    -- Update stock
    UPDATE product 
    SET stock = p_new_stock, updated_at = CURRENT_TIMESTAMP 
    WHERE proid = p_proid;
    
    -- Check if product exists
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product not found';
    END IF;
    
    COMMIT;
END//

DELIMITER ;

-- ============================================================================
-- 7. SAMPLE FUNCTION CALLS (TESTING)
-- ============================================================================

-- Test the stored procedure
-- CALL AddProduct('9001', 'Test Product', 100, 50.00, 10, 'TestCorp', '5%');
-- CALL UpdateStock('9001', 150);

-- ============================================================================
-- INSTALLATION COMPLETE
-- ============================================================================

SELECT '============================================================================' AS '';
SELECT 'DATABASE INITIALIZATION COMPLETE' AS '';
SELECT '============================================================================' AS '';
SELECT CONCAT('Total Products: ', COUNT(*)) AS Summary FROM product;
SELECT CONCAT('Total Users: ', COUNT(*)) AS Summary FROM login;
SELECT 'Ready for application connection!' AS Status;
SELECT '============================================================================' AS '';

-- Display connection information
SELECT 'Connection Details:' AS '';
SELECT 'Database: productmanagementsystem' AS '';
SELECT 'Default Username: admin' AS '';
SELECT 'Default Password: 12345' AS '';
SELECT 'JDBC URL: jdbc:mysql://localhost:3306/productmanagementsystem' AS '';