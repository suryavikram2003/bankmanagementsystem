package bank.management.system;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel adminLabel, passwordLabel;
    JTextField adminTextField;
    JPasswordField passwordField;
    JButton loginButton, backButton;

    AdminLogin() {
        setTitle("Admin Login");

        adminLabel = new JLabel("Admin ID:");
        adminLabel.setFont(new Font("Raleway", Font.BOLD, 24));
        adminLabel.setBounds(50, 100, 200, 30);
        add(adminLabel);

        adminTextField = new JTextField();
        adminTextField.setFont(new Font("Arial", Font.BOLD, 14));
        adminTextField.setBounds(250, 100, 200, 30);
        add(adminTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Raleway", Font.BOLD, 24));
        passwordLabel.setBounds(50, 200, 200, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField.setBounds(250, 200, 200, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(150, 300, 100, 30);
        add(loginButton);
        loginButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(300, 300, 100, 30);
        add(backButton);
        backButton.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(500, 400);
        setLocation(550, 200);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String adminID = adminTextField.getText();
            String password = new String(passwordField.getPassword());

            // Perform admin login authentication here
            // Add your logic to validate the admin credentials
            if (adminID.equals("admin") && password.equals("admin123")) {
                setVisible(false);
                new AdminPage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Admin ID or Password");
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Login().setVisible(true);
    }}

    public static void main(String[] args) {
        new AdminLogin().setVisible(true);
    }
}
