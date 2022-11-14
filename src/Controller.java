import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private static SpotifyDatabase db;
    private static GUI gui;
    private static User currentUser;

    public Controller(SpotifyDatabase model, GUI view) {
        db = model;
        gui = view;
        currentUser = null;

        JTextField userNameField = gui.getLogInPanel().getUserNameField();
        gui.getLogInPanel().getLoginButton().addActionListener(new LogInButtonListener(userNameField));
    }

    static class LogInButtonListener implements ActionListener {

        private JTextField userNameField;

        public LogInButtonListener(JTextField userNameField) {
            this.userNameField = userNameField;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login button pressed!");
            currentUser = userDatabase.importUser(userNameField.getText());
            gui.updateBody(currentUser);
        }
    }
}
