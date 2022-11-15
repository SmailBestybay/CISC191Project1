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
        JLabel messageLabel = gui.getLogInPanel().getMessageLabel();
        gui.getNavbar().getListUserButton().addActionListener(new ListUsersButtonListener(messageLabel));

    }

    static class LogInButtonListener implements ActionListener {

        private JTextField userNameField;

        public LogInButtonListener(JTextField userNameField) {
            this.userNameField = userNameField;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            currentUser = userDatabase.importUser(userNameField.getText());
            gui.updateBody(currentUser);
        }
    }

    static class ListUsersButtonListener implements ActionListener {
        JLabel messageLabel;

        public ListUsersButtonListener(JLabel messageLabel) {
            this.messageLabel = messageLabel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String [] users = userDatabase.showUsers();
            StringBuilder message = new StringBuilder("Existing users: ");
            for(String user:users) {
                message.append(user);
                message.append(", ");
            }
            messageLabel.setText(message.toString());
        }
    }
}
