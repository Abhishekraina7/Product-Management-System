
package product_management_system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Login Authentication Module
 * Provides secure user authentication interface using Swing GUI
 * 
 * Features:
 * - User credential verification against database
 * - Form validation and error handling
 * - Navigation to main application upon successful login
 * 
 * UI Components:
 * - Username and password input fields
 * - Submit, Reset, and Close action buttons
 * - User icon and styled interface
 */
public class Login extends JFrame implements ActionListener{
    
    // UI Component declarations
    JButton submit, reset, close;           // Action buttons
    JTextField tfusername;                  // Username input field
    JPasswordField tfpassword;              // Password input field (masked)

    /**
     * Constructor: Initializes and displays the login window
     * Sets up all UI components with proper styling and positioning
     */
    Login(){
        // Set background color and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Absolute positioning for precise control
        
        // Load and display user icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Product_Management_System/icons/user.png"));
        Image i2 = i1.getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(370,10,160,160);
        add(image);
        
        // Username label and input field
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20, 20, 100, 20);
        lblusername.setForeground(Color.BLACK);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);
        
        // Password label and input field
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20, 60, 100, 20);
        lblpassword.setForeground(Color.BLACK);
        lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblpassword);
        
        // Password field with character masking
        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        add(tfpassword);
        
        // Submit button - triggers authentication
        submit = new JButton("Submit");
        submit.setBounds(40, 120, 120, 30);
        submit.addActionListener(this);
        add(submit);
        
        // Reset button - clears form fields
        reset = new JButton("Reset");
        reset.setBounds(190, 120, 120, 30);
        reset.addActionListener(this);
        add(reset);
       
        // Close button - exits application
        close = new JButton("Close");
        close.setBounds(120, 160, 120, 30);
        close.addActionListener(this);
        add(close);
        
        // Window properties
        setSize(600,250);
        setLocation(500,250);
        setVisible(true);
    }
    
    /**
     * Event handler for button clicks
     * Handles Submit, Reset, and Close button actions
     * 
     * @param ae ActionEvent triggered by button clicks
     */
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == close){
            // Close application
            setVisible(false);
            
        }else if (ae.getSource() == reset){
            // Clear input fields
            tfusername.setText("");
            tfpassword.setText("");
            
        }else{
            // Handle login submission
            String username = tfusername.getText();
            String password = tfpassword.getText();
            
            // SQL query for user authentication
            // Security Issue: Vulnerable to SQL injection due to string concatenation
            // Recommended: Use PreparedStatement instead
            String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
            
            try {
                // Execute authentication query
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next()){
                    // Authentication successful - navigate to main menu
                    setVisible(false);
                    new Main();
                }else{
                    // Authentication failed - display error message
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                }
                
            } catch (Exception e) {
                // Handle database connection or query errors
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Main method - Application entry point
     * Creates and displays the login window
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Login();
    }
}
