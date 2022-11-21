import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                StringBuilder message = new StringBuilder();
                message.append("Existing Users: ");
                String[] usersStringArr = userDatabase.getUsers();
                for (String username: usersStringArr) {
                    message.append(username);
                    message.append(" ");
                }
                view.showMessage(message.toString());
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
            User user;
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

    public static class SearchPanelListener implements ActionListener {

        JTextField searchField;
        JComboBox<String> searchMode;
        public SearchPanelListener(JTextField searchField, JComboBox<String> searchMode) {
            this.searchField = searchField;
            this.searchMode = searchMode;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Song> songs = null;
            ArrayList<Artist> artists = null;
            if (searchMode.getSelectedItem() != null) {
                String selectedItem = (String) searchMode.getSelectedItem();

                if (selectedItem.equals("Mixed")) {
                    songs = db.searchSong(searchField.getText());
                    artists = db.searchArtist(searchField.getText());
                } else {
                    if (selectedItem.equals("Song")) {
                        songs = db.searchSong(searchField.getText());
                        System.out.println(searchField.getText());
                    }

                    if (selectedItem.equals("Artist")) {
                        artists = db.searchArtist(searchField.getText());
                        System.out.println(searchField.getText());
                    }
                }
                view.updateContentPanel(songs, artists);
            }
        }
    }

    /**
     * Add or Remove button listener, adds or removes item from user's favorites
     */
    public static class AddOrRemoveListener implements ActionListener {

        private final Song song;
        private final Artist artist;
        private final User user;
        private final JButton button;

        public AddOrRemoveListener(Song song, Artist artist, User user, JButton button) {
            this.song = song;
            this.artist = artist;
            this.user = user;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("Remove")) {
                user.removeSong(song);
                user.removeArtist(artist);
                button.setText("Add");
            } else {
                user.addSong(song);
                user.addArtist(artist);
                button.setText("Remove");
            }
            userDatabase.exportCSV(user);
            view.setCurrentUser(user);

        }
    }
}
