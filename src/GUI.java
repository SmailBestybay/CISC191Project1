import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame {

    private static final String APP_NAME = "Spotify Super Lite!";
    private JPanel body;
    private JPanel navbar;
    private JPanel logInPanel;

    // Navbar components
    private JLabel appName;
    private JButton listUserButton;

    // Logged out state center components
    private JLabel userNameFieldLabel;
    private JTextField userNameField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel messageLabel;


    /**
     * GUI constructor
     * add all panels to the JFrame, start in Logged out state.
     */
    public GUI(){
        super(GUI.APP_NAME);

        makeBody();

        add(body);

        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
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
        listUserButton = new JButton("Show Users");

        navbar.add(appName);
        navbar.add(Box.createHorizontalStrut(10));
        navbar.add(listUserButton);
        System.out.println(Arrays.toString(navbar.getComponents()));
    }

    private void makeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));

        // instantiate components of this panel
        userNameFieldLabel = new JLabel("User Name");
        userNameField = new JTextField(2);
        userNameField.setMaximumSize(new Dimension(200, 50));
        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");
        messageLabel = new JLabel();
        messageLabel.setText("Welcome! Please Log in or Register");

        // center components
        centerWidget(userNameFieldLabel);
        centerWidget(userNameField);
        centerWidget(loginButton);
        centerWidget(registerButton);
        centerWidget(messageLabel);

        // adds space to the top of the center panel
        logInPanel.add(Box.createRigidArea(new Dimension(0,100)));

        // add components
        logInPanel.add(userNameFieldLabel);
        logInPanel.add(userNameField);
        logInPanel.add(loginButton);
        logInPanel.add(registerButton);
        logInPanel.add(Box.createRigidArea(new Dimension(0,50)));
        logInPanel.add(messageLabel);

        // add space to the bot of the center panel
        logInPanel.add(Box.createRigidArea(new Dimension(0,100)));
    }

    /**
     * Helper method to center a component.
     * @param component center the component
     */
    private static void centerWidget(JComponent component){
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void updateNavbar(User user){

        // if logged out state, remove components
        if (user == null) {
            navbar.add(Box.createHorizontalStrut(10));
            navbar.add(listUserButton);
        }

        // if logged in, add components
        if (user != null) {

        }


    }

}
