import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private static final String APP_NAME = "Spotify Super Lite!";
    private JPanel body;
    private JPanel navbar;
    private JPanel logInPanel;
    private JLabel appName;

    // Logged out state components
    private JLabel userNameFieldLabel;
    private JTextField userNameField;
    private JButton loginButton;


    /**
     * GUI constructor
     * add all panels to the JFrame, start in Logged out state.
     */
    public GUI(){
        super(GUI.APP_NAME);

        makeBody();

        add(body);

        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * make body JPanel.
     * start in Logged out state.
     * add navbar and loginPanel panels.
     */
    private void makeBody() {
        body = new JPanel(new BorderLayout());
        makeNavbar();
        makeLogInPanel();
        body.add(navbar, BorderLayout.NORTH);
        body.add(logInPanel, BorderLayout.CENTER);


    }

    /**
     * make navbar JPanel.
     * start in Logged out state.
     *
     */
    private void makeNavbar() {
        navbar = new JPanel();
        appName = new JLabel(GUI.APP_NAME);
        appName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        navbar.add(appName);
    }

    private void makeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));

        // instantiate components of this panel
        userNameFieldLabel = new JLabel("User Name");
        userNameField = new JTextField(2);
        userNameField.setMaximumSize(new Dimension(200, 50));
        loginButton = new JButton("Log in");

        // center components
        userNameFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adds space to the top of the center panel
        logInPanel.add(Box.createRigidArea(new Dimension(0,100)));

        // add components
        logInPanel.add(userNameFieldLabel);
        logInPanel.add(userNameField);
        logInPanel.add(loginButton);

        // add space to the bot of the center panel
        logInPanel.add(Box.createRigidArea(new Dimension(0,100)));
    }

}
