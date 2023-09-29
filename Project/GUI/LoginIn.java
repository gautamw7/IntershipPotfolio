/*
* Enter the code for home page that leds after Submit Page line 63
* */

package Project.GUI;

import javax.swing.*;
import Project.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginIn extends JFrame {
    private DatabaseConnection dbConnection;
    JLabel UsernameLabel, PasswordLabel, MessageLabel;
    JTextField UsernameField;
    JPasswordField PasswordField;
    JButton SubmitButton, RegisterButton;
    String username, password;

    public LoginIn(){
        setSize(1000, 1000);
        setLayout(null);

        UsernameLabel = new JLabel("Email id : ");
        PasswordLabel = new JLabel("Password");
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        MessageLabel = new JLabel();  // To sent Message to user

        SubmitButton = new JButton("Login");
        RegisterButton = new JButton("Register");


        UsernameLabel.setBounds(100, 100, 100, 30);  // (x, y, width, height)
        PasswordLabel.setBounds(100, 150, 100, 30);  // (x, y, width, height)
        MessageLabel.setBounds(100, 250, 300, 30);  // Set bounds for the message label

        UsernameField.setBounds(220, 100, 200, 30);  // (x, y, width, height) Set bounds for UsernameField
        PasswordField.setBounds(220, 150, 200, 30);  // (x, y, width, height)
        SubmitButton.setBounds(100, 200, 100, 30);  // (x, y, width, height)
        RegisterButton.setBounds(220, 200, 100, 30);  // (x, y, width, height)


        add(UsernameLabel);
        add(PasswordLabel);
        add(UsernameField);
        add(PasswordField);
        add(SubmitButton);
        add(RegisterButton);
        add(MessageLabel);

        //Submit Button Check
        SubmitButton.addActionListener(e -> {

            username = UsernameField.getText();
            char[] passwordArray = PasswordField.getPassword();
            password = new String(passwordArray);
            if(username.isEmpty()){
                MessageLabel.setText("Please enter the UserName");
            }
            if(password.isEmpty()){
                MessageLabel.setText("Please enter the Password");
            }
            if(checkUserNameExist(username)){
                if(checkUserNamePassword(username, password)){
                    // sent message Login Successfully  and led to the next page
                    System.out.println("YEss Broo");
                    MessageLabel.setText("Login Successful.");
            }else {
                // Incorrect password

                MessageLabel.setText("Incorrect Password.");
            }
        } else {
            // User does not exist

                MessageLabel.setText("User does not exist.");
        }

        }

        );

        // Register Button action, in case the user is not register
        RegisterButton.addActionListener( Al -> {
            RegisterPage Page = new RegisterPage();
            dispose();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private boolean checkUserNamePassword(String username, String password) {
        boolean passwordCorrect = false;

        try {
            openDatabaseConnection(); // Open the connection
            Connection connection = dbConnection.getConnection();

            String query = "SELECT Passcode FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Retrieve the stored password from the database
                    String storedPassword = resultSet.getString("Passcode");

                    // Compare the provided password with the stored password (case-sensitive)
                    if (storedPassword != null && storedPassword.equals(password)) {
                        // Passwords match
                        passwordCorrect = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            closeDatabaseConnection(); // Close the database connection
        }

        return passwordCorrect;
    }

    private boolean checkUserNameExist(String username) {
        boolean usernameExists = false;

        try {
            openDatabaseConnection(); // Open the connection
            Connection connection = dbConnection.getConnection();

            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 1) {
                        // Username exists in the database
                        usernameExists = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            closeDatabaseConnection(); // Close the database connection
        }

        return usernameExists;
    }

    private void openDatabaseConnection(){

        dbConnection = new DatabaseConnection();
    }
    private void closeDatabaseConnection() {

        dbConnection.closeConnection();
    }
    private void SentMessageOnScreen(String Message){
        MessageLabel.setText(Message);
    }

    private void ReloadPage() {
        LoginIn reload = new LoginIn();
        dispose();
    }
}
