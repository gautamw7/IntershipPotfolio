/*
 * make the pages for each langauge
 * Add the recommedation ka fucking good the last thing to do
 * Ask who add the developer chocies page in faster way and effiecent way 
 * */

package Project.GUI;

import javax.swing.*;
import Project.DatabaseConnection;
import java.lang.reflect.InvocationTargetException;

public class DeveloperPage extends JFrame {
    public DeveloperPage(String language) {
        switch (language) {
            case "Developer Android (Java/Kotlin)":
                AndroidLanguagePage androidPage = new AndroidLanguagePage();
                break;
            case "Developer C":
                CLanguagePage cPage = new CLanguagePage();
                break;
            case "Developer C++":
                CppLanguagePage cppPage = new CppLanguagePage();
                break;
            case "Developer Rust":
                RustLanguagePage rustPage = new RustLanguagePage();
                break;
            case "Developer Go":
                GoLanguagePage goPage = new GoLanguagePage();
                break;
            case "Developer iOS (Swift/Objective-C)":
                IosLanguagePage iosPage = new IosLanguagePage();
                break;
            case "Developer Java (Non-Android)":
                JavaLanguagePage javaPage = new JavaLanguagePage();
                break;
            case "Developer JavaScript":
                JavaScriptLanguagePage jsPage = new JavaScriptLanguagePage();
                break;
            case "Developer TypeScript":
                TypeScriptLanguagePage tsPage = new TypeScriptLanguagePage();
                break;
            case "Developer Julia":
                JuliaLanguagePage juliaPage = new JuliaLanguagePage();
                break;
            case "Developer PHP":
                PHPLanguagePage phpPage = new PHPLanguagePage();
                break;
            case "Developer Python":
                PythonLanguagePage pythonPage = new PythonLanguagePage();
                break;
            case "Developer Ruby":
                RubyLanguagePage rubyPage = new RubyLanguagePage();
                break;
            case "Developer SQL (MySQL, PostgreSQL, etc.)":
                SQLLanguagePage sqlPage = new SQLLanguagePage();
                break;
            case "Developer Haskell":
                HaskellLanguagePage haskellPage = new HaskellLanguagePage();
                break;
            case "Developer OCaml":
                OCamlLanguagePage ocamlPage = new OCamlLanguagePage();
                break;
            default:
                // Handle the case where the language name doesn't match any of the cases
                System.out.println("Invalid language selection");
                break;
        }
    }


    public class AndroidLanguagePage extends JFrame {
        public AndroidLanguagePage() {
            // Configuration for the AndroidLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    public class CLanguagePage extends JFrame {
        public CLanguagePage() {
            // Configuration for the CLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    public class CppLanguagePage extends JFrame {
        public CppLanguagePage() {
            // Configuration for the CppLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    public class RustLanguagePage extends JFrame {
        public RustLanguagePage() {
            // Configuration for the RustLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class GoLanguagePage extends JFrame {
        public GoLanguagePage() {
            // Configuration for the GoLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class IosLanguagePage extends JFrame {
        public IosLanguagePage() {
            // Configuration for the IosLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class JavaLanguagePage extends JFrame {
        public JavaLanguagePage() {
            // Configuration for the JavaLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class JavaScriptLanguagePage extends JFrame {
        public JavaScriptLanguagePage() {
            // Configuration for the JavaScriptLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class TypeScriptLanguagePage extends JFrame {
        public TypeScriptLanguagePage() {
            // Configuration for the TypeScriptLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class JuliaLanguagePage extends JFrame {
        public JuliaLanguagePage() {
            // Configuration for the JuliaLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class PHPLanguagePage extends JFrame {
        public PHPLanguagePage() {
            // Configuration for the PHPLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class PythonLanguagePage extends JFrame {
        public PythonLanguagePage() {
            // Configuration for the PythonLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class RubyLanguagePage extends JFrame {
        public RubyLanguagePage() {
            // Configuration for the RubyLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class SQLLanguagePage extends JFrame {
        public SQLLanguagePage() {
            // Configuration for the SQLLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class HaskellLanguagePage extends JFrame {
        public HaskellLanguagePage() {
            // Configuration for the HaskellLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public class OCamlLanguagePage extends JFrame {
        public OCamlLanguagePage() {
            // Configuration for the HaskellLanguagePage JFrame
            setSize(400, 400);
            setLayout(null);
           //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }

    }

}
