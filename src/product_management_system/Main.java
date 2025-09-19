
package product_management_system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main Application Dashboard
 * Central navigation hub for all product management operations
 * 
 * Features:
 * - Navigation to Add, View, Update product modules
 * - Professional UI with background image and styled buttons
 * - Clean window management and user experience
 * 
 * Design Pattern: Controller pattern for navigation management
 */
public class Main extends JFrame implements ActionListener{
    
    // UI Component declarations for navigation buttons
    JButton view, add, close, update;

    /**
     * Constructor: Initializes the main dashboard interface
     * Creates and positions all navigation buttons with styling
     */
    Main() {
        // Set absolute layout for precise component positioning
        setLayout(null);
         
        // Load and set background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Product_Management_System/icons/back.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,650,450);
        add(image);
        
        // Add Products button - Red theme for creation action
        add = new JButton("Add Products");
        add.setBounds(210, 50, 220, 60);
        add.setBackground(Color.red);
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Tahoma", Font.BOLD, 20));
        add.addActionListener(this);
        image.add(add);  // Add to background image layer
        
        // View Products button - Green theme for read-only action
        view = new JButton("View Products");
        view.setBounds(210, 140, 220, 60);
        view.setBackground(Color.GREEN);
        view.setForeground(Color.WHITE);
        view.setFont(new Font("Tahoma", Font.BOLD, 20));
        view.addActionListener(this);
        image.add(view);
        
        // Update Products button - Blue theme for modification action
        update = new JButton("Update Products");
        update.setBounds(210, 230, 220, 60);
        update.setBackground(Color.BLUE);
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Tahoma", Font.BOLD, 20));
        update.addActionListener(this);
        image.add(update);
        
        // Close button - Black theme for exit action
        close = new JButton("Close");
        close.setBounds(260, 320, 100, 40);
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Tahoma", Font.BOLD, 20));
        close.addActionListener(this);
        image.add(close);
         
        // Window configuration
        setSize(650,450);
        setLocation(400,180);  // Center window on screen
        setVisible(true);
    }
     
    /**
     * Event handler for navigation button clicks
     * Routes user to appropriate module based on selection
     * 
     * Navigation Pattern:
     * - Close current window
     * - Open target window
     * - Single window active at a time
     * 
     * @param ae ActionEvent from button clicks
     */
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == close){
            // Exit application
            setVisible(false);
            
        }else if(ae.getSource() == add){
            // Navigate to Add Product module
            setVisible(false);
            new AddProduct();
            
        }else if(ae.getSource() == view){
            // Navigate to View Products module
            setVisible(false);
            new ViewDetail();
            
        }else{
            // Navigate to Update Product module (default case)
            setVisible(false);
            new UpdateProduct();
        }
    }
    
    /**
     * Main method for direct testing of dashboard
     * Note: In normal flow, this is called from Login.java after authentication
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Main();
    }
}
