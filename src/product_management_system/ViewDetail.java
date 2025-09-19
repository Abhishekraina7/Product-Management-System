package product_management_system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewDetail extends JFrame implements ActionListener {
    JTable table;
    Choice cpid;
    JButton search, exit;

    ViewDetail() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Product ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        cpid = new Choice();
        cpid.setBounds(180, 20, 150, 20);
        add(cpid);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            while (rs.next()) {
                cpid.add(rs.getString("proid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 500);
        add(jsp);

        // search button
        search = new JButton("Search");
        search.setBounds(350, 20, 80, 35);
        search.addActionListener(this);
        add(search);

        // search button
        exit = new JButton("Exit");
        exit.setBounds(450, 20, 80, 35);
        exit.addActionListener(this);
        add(exit);

        setSize(900, 600);
        setLocation(250, 100);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {
            String query = "select * from product where proid = '" + cpid.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(buildTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }
        return model;
    }

    public static void main(String[] args) {
        new ViewDetail();
    }
}
