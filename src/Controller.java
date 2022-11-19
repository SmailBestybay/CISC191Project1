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


    public static class NavbarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = view.getCurrentUser();
            if(e.getActionCommand().equals("Show Users")) {
                view.showMessage(Arrays.toString(userDatabase.getUsers()));
            } else if (e.getActionCommand().equals("Log Out")) {
                view.setCurrentUser(null);
                view.updateBody();
            } else if (e.getActionCommand().equals("My Songs")) {
                view.updateContentPanel(user.getSongs(), null);
            } else if (e.getActionCommand().equals("My Artists")) {
                view.updateContentPanel(null, user.getArtists());
            }


        }
    }
    public static class LogInPanelListener implements ActionListener {
        JTextField userNameField;
        public LogInPanelListener(JTextField userNameField) {
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
            else if (e.getActionCommand().equals("Register")) {
                user = new User(userNameField.getText());
                userDatabase.exportCSV(user);
                view.showMessage("Registration successful");
            }

        }
    }
}
