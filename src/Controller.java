import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            Controller.view.showUsers(userDatabase.getUsers());
        }
    }public static class LogInListener implements ActionListener {
        JTextField userNameField;
        public LogInListener(JTextField userNameField) {
            this.userNameField = userNameField;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = userDatabase.importUser(userNameField.getText());
            if (user == null) {
//                GUI.logInFailed();
                System.out.println("Log in failed");
            } else {
                view.setCurrentUser(user);
                view.updateBody();

            }
        }
    }
}
