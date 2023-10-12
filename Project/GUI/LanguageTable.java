
package Project.GUI;

import Project.DatabaseConnection;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LanguageTable extends JFrame {
    private DatabaseConnection dbConnection;
    private DefaultTableModel dtm;
    ButtonGroup[] buttonGroups;
    private JTable table;
    private JScrollPane jsp;
    Integer[] selectedValues; // Array to store selected values
    private JButton SubmitButton;
    public LanguageTable(String name) {
        setTitle("Language Proficiency Test");

        // The languages String
        String[] languages = {
                "Android (Java/Kotlin)", "C", "C++", "Rust", "Go",
                "iOS (Swift/Objective-C)", "Java (Non-Android)", "JavaScript", "TypeScript", "Julia",
                "PHP", "Python", "Ruby", "SQL (MySQL, PostgreSQL, etc.)", "Haskell", "OCaml"
        };
        // the proficiency level string
        String[] proficiencyLevel = {"Very unfamiliar", "Somewhat unfamiliar", "Somewhat proficient", "Very proficient", "Expert Level"};

        // Initialize the selected values array
        selectedValues = new Integer[languages.length];

        // creating the table model to pass to the table
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Language"); // add the column language

        // Add columns for proficiency levels
        for (String language : languages) {
            dtm.addColumn(language);
        }
        // the Value been passed .
        int value = 1; // Initialize the value
        for (String level : proficiencyLevel) {
            // each row is an object which gets the stuff passed in it, we are creating the same for each row
            Object[] row = new Object[languages.length + 1];
            row[0] = level;
            // adding the radiobutton for each element
          //  System.out.println(value);
            for (int i = 1; i <= languages.length; i++) {
                JRadioButton radioButton = new JRadioButton();
                row[i] = radioButton;
                int finalValue = value; // Store the value for this radio button
                int finalI = i;
                radioButton.addItemListener(e -> {
                    int index = finalI - 1;
                    selectedValues[index] = radioButton.isSelected() ? finalValue : null;
                });
            }
            value += 2; // Increment the value for the next radio button
            // add the row to the table
            dtm.addRow(row);
        }

        // create the table
        JTable table = new JTable(dtm) {
            public void tableChanged(TableModelEvent tme) {
                super.tableChanged(tme);
                repaint();
            }
        };

        // each column's button group
        buttonGroups = new ButtonGroup[languages.length];
        for (int i = 0; i < languages.length; i++) {
            buttonGroups[i] = new ButtonGroup();
        }

        // Assign radio buttons to button groups
        for (int row = 0; row < dtm.getRowCount(); row++) {
            for (int col = 1; col <= languages.length; col++) {
                JRadioButton radioButton = (JRadioButton) dtm.getValueAt(row, col);
                buttonGroups[col - 1].add(radioButton);
            }
        }

        // Set cell renderer and editor for proficiency level columns
        for (String language : languages) {
            table.getColumn(language).setCellRenderer(new RadioButtonRenderer());
            table.getColumn(language).setCellEditor(new RadioButtonEditor(new JCheckBox()));
        }
        // add the table to the panel ka North side
        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.NORTH);

        // Submit button
        SubmitButton = new JButton("Submit");
        SubmitButton.setBounds(10, 350, 100, 30); // Adjusted bounds
        SubmitButton.addActionListener(e -> {
            final int length = languages.length;
            int[] AlgoritmValueData = new int[length];
            for (int i = 0; i < buttonGroups.length; i++) {
                AlgoritmValueData[i] = selectedValues[i];
            }
            addDataToDatabase(name, AlgoritmValueData);
            InternHomePage page = new InternHomePage(name);
        });


        // Add the SubmitButton to the frame
        add(SubmitButton, BorderLayout.SOUTH);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addDataToDatabase(String name, int[] algorithmValueData) {
        openDatabaseConnection();
        try {
            String insertSQL = "INSERT INTO candidate_preferences (username, Android_Java_Kotlin, C, Cpp, Rust, Go, iOS_Swift_Objective_C, Java_Non_Android, JavaScript, TypeScript, Julia, PHP, Python, Ruby, codeSQL, Haskell, OCaml) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(insertSQL);

            // enter the username
            preparedStatement.setString(1, name);

            // Set the proficiency levels for each language into the table
            for (int i = 2, proficiencyValue = 1; i <= 17; i++, proficiencyValue += 2) {
                preparedStatement.setInt(i, algorithmValueData[i - 2]);
            }

            // add the data in to the table
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully for " + name);
            } else {
                System.out.println("Data insertion failed for " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseConnection();
        }
    }


    private JTable createProficiencyLevelDataTable(String[] proficiencyLevels, String[] languages) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Language");
        for (String language : languages) {
            dtm.addColumn(language);
        }

        for (String level : proficiencyLevels) {
            Object[] row = new Object[languages.length + 1];
            row[0] = level;
            dtm.addRow(row);
        }

        return new JTable(dtm);
    }

    private void openDatabaseConnection(){
        dbConnection = new DatabaseConnection();
    }
    private void closeDatabaseConnection() {
        dbConnection.closeConnection();
    }


}


class RadioButtonRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return null;
        return (Component) value;
    }
}

// From the
class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
    private JRadioButton button;

    public RadioButtonEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) return null;
        button = (JRadioButton) value;
        button.addItemListener(this);
        return (Component) value;
    }

    public Object getCellEditorValue() {
        button.removeItemListener(this);
        return button;
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }

/*    public static void main(String[] args) {
        LanguageTable languageTable = new LanguageTable("Gautam");
    }
    */
}
