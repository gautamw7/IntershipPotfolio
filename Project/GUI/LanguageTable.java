/*
* Get the RadioButton entry
* */

package Project.GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class LanguageTable extends JFrame {
    private DefaultTableModel dtm;
    private ButtonGroup[] buttonGroups;
    private JTable table;
    private JScrollPane jsp;

    public LanguageTable() {
        setTitle("Language Proficiency  Test");

        // The languages String
        String[] languages = {
                "Android (Java/Kotlin)", "C", "C++", "Rust", "Go",
                "iOS (Swift/Objective-C)", "Java (Non-Android)", "JavaScript", "TypeScript", "Julia",
                "PHP", "Python", "Ruby", "SQL (MySQL, PostgreSQL, etc.)", "Haskell", "OCaml"
        };

        String[] proficiencyLevel = {"Very unfamiliar", "Somewhat unfamiliar", "Somewhat proficient", "Very proficient", "Expert Level"};

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Language");

        // Add columns for proficiency levels
        for (String language : languages) {
            dtm.addColumn(language);
        }

        // Add rows for each proficiency level with radio buttons
        for (String level : proficiencyLevel) {
            Object[] row = new Object[languages.length + 1];
            row[0] = level;
            for (int i = 1; i <= languages.length; i++) {
                row[i] = new JRadioButton();
            }
            dtm.addRow(row);
        }

        JTable table = new JTable(dtm) {
            public void tableChanged(TableModelEvent tme) {
                super.tableChanged(tme);
                repaint();
            }
        };

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

        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.NORTH);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

class RadioButtonRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return null;
        return (Component) value;
    }
}

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
}
