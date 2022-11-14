import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private SpotifyDatabase db;
    private GUI gui;
    private User currentUser;

    public Controller(SpotifyDatabase db, GUI gui) {
        this.db = db;
        this.gui = gui;

        gui.getLogInPanel().getLoginButton().addActionListener(new LogInButtonListener());
    }

    static class LogInButtonListener implements ActionListener {

        private JButton loginButton;
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login button pressed!");
        }
    }
}
