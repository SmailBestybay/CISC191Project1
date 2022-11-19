import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Controller {

    private static SpotifyDatabase db;
    private static GUI view;

    public Controller(SpotifyDatabase db, GUI view) {
        Controller.db = db;
        Controller.view = view;
    }


    public static class ListUsersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMessage(Arrays.toString(userDatabase.getUsers()));
        }
    }
    public static class LogInOrRegisterListener implements ActionListener {
        JTextField userNameField;
        public LogInOrRegisterListener(JTextField userNameField) {
            this.userNameField = userNameField;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = null;
            if (e.getActionCommand().equals("Log in")) {
                user = userDatabase.importUser(userNameField.getText());
                if (user == null) {
                    view.showMessage("Log in failed. User not found");
                } else {
                    view.setCurrentUser(user);
                    view.updateBody();
                }
            }

            if (e.getActionCommand().equals("Register")) {
                user = new User(userNameField.getText());
                userDatabase.exportCSV(user);
                view.showMessage("Registration successful");
            }

        }
    }
}
