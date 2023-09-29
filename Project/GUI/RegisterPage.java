/*
*
* */


package Project.GUI;
import Project.DatabaseConnection;
import Project.Authentication;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPage extends JFrame {
    private DatabaseConnection dbConnection;
    Authentication Auth = new Authentication();
    JLabel usernameLabel,contactLabel,passwordLabel,confirmPasswordLabel,emailLabel,messageLabel; // Label Declaration

    JTextField usernameField,contactField,emailField, messageField; // info field
    JPasswordField passwordField,confirmPasswordField;  // Register field
    JButton nextFrameButton; // register button
    public RegisterPage(){
        setSize(1000, 1000);
        setLayout(null);

         usernameLabel = new JLabel("Username:");
         contactLabel = new JLabel("Contact no:");
         passwordLabel = new JLabel("Password:");
         confirmPasswordLabel = new JLabel("Confirm Password:");
         emailLabel = new JLabel("Email:");
         messageLabel = new JLabel();

         usernameField = new JTextField();
         contactField = new JTextField();
         passwordField = new JPasswordField();
         confirmPasswordField = new JPasswordField();
         emailField = new JTextField();
         messageField = new JTextField();


        usernameLabel.setBounds(100, 100, 100, 30);
        contactLabel.setBounds(100, 150, 100, 30);
        passwordLabel.setBounds(100, 200, 100, 30);
        confirmPasswordLabel.setBounds(100, 250, 150, 30);
        emailLabel.setBounds(100, 300, 100, 30);
        messageLabel.setBounds(100, 400, 300, 30);  // Adjust the bounds as needed


        usernameField.setBounds(250, 100, 200, 30);
        contactField.setBounds(250, 150, 200, 30);
        passwordField.setBounds(250, 200, 200, 30);
        confirmPasswordField.setBounds(250, 250, 200, 30);
        emailField.setBounds(250, 300, 200, 30);
        messageField.setBounds(250, 400, 300, 30);  // Adjust the coordinates and size as needed

        // the Register button leds, to Login page again, so they can continue
         nextFrameButton = new JButton("Next");
        nextFrameButton.setBounds(250, 350, 100, 30);
        // Register Button  Action Listener
        nextFrameButton.addActionListener(e -> {
            // Handle registration logic here
            insertData();

           /* LanguageTable obj = new LanguageTable();*/
        });


        add(usernameLabel);
        add(contactLabel);
        add(passwordLabel);
        add(confirmPasswordLabel);
        add(emailLabel);
        add(nextFrameButton);

        add(usernameField);
        add(contactField);
        add(passwordField);
        add(confirmPasswordField);
        add(emailField);
        add(messageField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    String name,email,phoneNumber, password, confirmpassowrd;
    int progress;
    private void insertData() {
        // Retrieve the data
        name = usernameField.getText();
        email = emailField.getText();
        phoneNumber = contactField.getText();
        // Convert the password field data into char array then to password
        char[] passwordArray = passwordField.getPassword();
        password = new String(passwordArray);

        char[] confirmPasswordArray = confirmPasswordField.getPassword();
        confirmpassowrd = new String(confirmPasswordArray);

        // Check if any of the fields are empty or null
        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmpassowrd.isEmpty()) {
            messageField.setText("Please fill in all the fields.");
        } else {
            // Check if the password and confirm password match
            if (password.equals(confirmpassowrd)) {
                // Set up progress bar then send the message

                if(Auth.checkUserNameExist(name)){
                    System.out.println(name);
                    messageField.setText("Welcome !!!");
                    ProgressBarSetUp(name, phoneNumber, email, password);
                    dispose();
                    LanguageTable obj = new LanguageTable();
                }else{
                    messageField.setText("User Name Exists in the database ");
                    ProgressBarSetUpError();
                }
            } else {
                // Passwords don't match, show an error message
                // Add a progress bar here if needed
                messageField.setText("Password & Confirm Password Don't match");
                ProgressBarSetUpError();
                // Reload the registration page
            }
        }
    }

    private void ProgressBarSetUpError() {
        JProgressBar progressBar = new JProgressBar(0, 20);
        progressBar.setBounds(100, 450, 300, 30);  // Adjust the bounds as needed
        progressBar.setStringPainted(true); // Show the progress value
        progressBar.setValue(0);
        add(progressBar);
        Timer timer = new Timer(100, e -> {
            if (progress == 10) {
                // The code to add the data to the database
                ReloadPage();
            }
            progress++;
            progressBar.setValue(progress);
        });
        timer.start();
    }

    private void ProgressBarSetUp(String name, String phoneNumber, String email,String password) {
        JProgressBar progressBar = new JProgressBar(0, 20);
        progressBar.setBounds(100, 450, 300, 30);  // Adjust the bounds as needed
        progressBar.setStringPainted(true); // Show the progress value
        progressBar.setValue(0);
        add(progressBar);
        Timer timer = new Timer(100, e -> {
            if (progress == 10) {
                // The code to add the data to the database
              //  messageField.setText("Welcome !!!");
                addDataToDatabase(name, phoneNumber, email, password);
            }
            progress++;
            progressBar.setValue(progress);
        });
        timer.start();
    }


    private void ReloadPage() {
        RegisterPage reload = new RegisterPage();
        dispose();
    }

    private void addDataToDatabase(String name, String phoneNumber, String email, String password) {
        openDatabaseConnection();

        try {
            String insertQuery = "INSERT INTO users (username, contact_no, email, Passcode ) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                messageField.setText("Congrats!! on Registration");
            } else {
                messageField.setText("Error: Registration Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            messageField.setText("Error: Registration Failed.");
        }
        closeDatabaseConnection();
        //Close the Database
        // The rest of your code to handle UI or page redirection
    }



    private void openDatabaseConnection(){
        dbConnection = new DatabaseConnection();
    }
    private void closeDatabaseConnection() {
        dbConnection.closeConnection();
    }
}
