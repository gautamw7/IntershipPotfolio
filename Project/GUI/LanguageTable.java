/*
* Create a table for storing the table data
* create table,, create radio group, create radio button */

package Project.GUI;
import Project.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LanguageTable extends JFrame {
    private DatabaseConnection dbConnection;
    private JTable table;
    private JLabel TitleLabel, MessageLabel;
    public LanguageTable() {
        setTitle("Language Proficiency Table");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a table model with 6 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Language");
        model.addColumn("Very unfamiliar");
        model.addColumn("Somewhat unfamiliar");
        model.addColumn("Somewhat proficient");
        model.addColumn("Very proficient");
        model.addColumn("Expert Level");

        // Define the rows with languages
        String[] languages = {
                "Android (Java/Kotlin)", "C", "C++", "Rust", "Go",
                "iOS (Swift/Objective-C)", "Java (Non-Android)", "JavaScript", "TypeScript", "Julia",
                "PHP", "Python", "Ruby", "SQL (MySQL, PostgreSQL, etc.)", "Haskell", "OCaml"
        };

        // Add rows to the table with radio buttons
        for (String language : languages) {
            Object[] row = new Object[6];
            row[0] = language;

            // Create a button group for each row
            ButtonGroup buttonGroup = new ButtonGroup();

            for (int i = 1; i <= 5; i++) {
                JRadioButton radioButton = new JRadioButton();
                buttonGroup.add(radioButton);
                row[i] = radioButton;
            }

            model.addRow(row);
        }

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
