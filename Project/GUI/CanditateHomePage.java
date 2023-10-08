/**
 * make homepage for both company and users
 */

package Project.GUI;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class CanditateHomePage extends JFrame {
    public CanditateHomePage() {
        // The name of the home page
        setTitle("Intern Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        // make a Jpanel for better organization, components better visibilty
        JPanel buttonPanel = new JPanel(new GridLayout(5 /* 5 to make the space look better*/, 4, 10, 10));

        // the String array for the developer of the coding
        String[] developers  = {
                "Developer Android (Java/Kotlin)", "Developer C", "Developer C++", "Developer Rust", "Developer Go",
                "Developer iOS (Swift/Objective-C)", "Developer Java (Non-Android)", "Developer JavaScript", "Developer TypeScript", "Developer Julia",
                "Developer PHP", "Developer Python", "Developer Ruby", "Developer SQL (MySQL, PostgreSQL, etc.)", "Developer Haskell", "Developer OCaml"
        };

        // adding the developer using enhaced for loop instead of seprate buttons for each
        for (String developer  : developers ) {
            JButton button = new JButton(developer );
            // button ka action listener and then lead to language page
            button.addActionListener(e ->  {
                        // Create an instance of developerPage and display it
                        DeveloperPage languagePage = new DeveloperPage(developer); // send the language to LanguagePage constructor
                    }
            );
            // add them to the frame
            buttonPanel.add(button);
        }
        // add the panel to the frame
        getContentPane().add(buttonPanel);
        // add the panel to the frame relative to none
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
