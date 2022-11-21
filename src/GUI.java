import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUI class, uses multiple nested inner classes to organise fields
 */
public class GUI extends JFrame {
    private static final String APP_NAME = "Spotify Super Lite!";
    private JPanel body;
    private Navbar navbar;
    private LogInPanel logInPanel;
    private ContentPanel contentPanel;
    private User currentUser = null;

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
        navbar = new Navbar();
        logInPanel = new LogInPanel();
        body.add(navbar, BorderLayout.NORTH);
        body.add(logInPanel, BorderLayout.CENTER);
    }

    /**
     * update body panel
     */
    public void updateBody() {
        User user = currentUser;
        if (user == null){
            navbar.updateNavbar();
            body.remove(contentPanel);
            body.add(logInPanel, BorderLayout.CENTER);
            logInPanel.userNameField.setText("");
        }

        if (user != null) {
            navbar.updateNavbar();
            body.remove(logInPanel);
            contentPanel = new ContentPanel();
            body.add(contentPanel, BorderLayout.CENTER);
        }
        pack();
    }

    /**
     * content panel inner class
     */
    class ContentPanel extends JPanel {
        private SearchPanel searchPanel;
        private ItemsPanel itemsPanel;
        private JScrollPane scrollPane;
        public ContentPanel(){
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            searchPanel = new SearchPanel();
            itemsPanel = new ItemsPanel();
            scrollPane = new JScrollPane(itemsPanel);

            centerWidget(searchPanel);
            centerWidget(itemsPanel);

            add(searchPanel);
            add(scrollPane);
        }

        /**
         * search panel inner class
         */
        class SearchPanel extends JPanel {
            private JLabel searchLabel;
            private JTextField searchField;
            private JButton searchButton;
            private JComboBox<String> searchMode;

            /**
             * Search Panel constructor
             */
            public SearchPanel(){
                super();

                // components
                searchLabel = new JLabel("Search for a song or an artist");
                searchField = new JTextField(10);
                searchButton = new JButton("Search");
                String[] searchOptions = {"Song", "Artist", "Mixed"};
                searchMode = new JComboBox<>(searchOptions);

                Controller.SearchPanelListener searchPanelListener;
                searchPanelListener = new Controller.SearchPanelListener(searchField, searchMode);
                searchButton.addActionListener(searchPanelListener);

                add(searchLabel);
                add(searchField);
                add(searchButton);
                add(searchMode);

            }
        }

        /**
         * Items panel, responsible for displaying query results.
         */
        class ItemsPanel extends JPanel {

            private ArrayList<SongItem> songItems;
            private ArrayList<ArtistItem> artistItems;
            public ItemsPanel() {
                super();
                songItems = new ArrayList<>();
                artistItems = new ArrayList<>();
                this.setLayout(new GridLayout(0,1));
            }

            public void displayItems(ArrayList<Song> songs, ArrayList<Artist> artists, User user) {
                for (int i = getComponentCount()-1; i >= 0; i--) {
                    this.remove(i);
                    revalidate();
                    repaint();
                }

                // clear lists from old query results
                songItems.clear();
                artistItems.clear();

                if (songs != null) {
                    for (int i = 0; i < songs.size(); i++) {
                        songItems.add(new SongItem(songs.get(i), user));
                        this.add(songItems.get(i));
                    }
                }
                if (artists != null) {
                    for (int i = 0; i < artists.size(); i++) {
                        artistItems.add(new ArtistItem(artists.get(i), user));
                        this.add(artistItems.get(i));
                    }
                }
                repaint();
                pack();
            }
        }
    }

    /**
     * Song item inner class
     */
    class SongItem extends JPanel {
        private JLabel title;
        private JLabel rank;
        private JLabel artists;
        private JButton addOrRemove;

        public SongItem(Song song, User user) {
            this.title = new JLabel(song.getTitle());
            this.rank = new JLabel(song.getRank());
            StringBuilder artistsString = new StringBuilder();
            for(Artist artist: song.getArtists()) {
                artistsString.append(artist.getName());
                artistsString.append(" ");
            }
            this.artists = new JLabel(artistsString.toString());
            addOrRemove = new JButton("Add");
            for (Song usersSong: user.getSongs()) {
                if(song.equals(usersSong)) {
                    addOrRemove.setText("Remove");
                }
            }
            Controller.AddOrRemoveListener listener;
            listener = new Controller.AddOrRemoveListener(song, null, currentUser, addOrRemove);
            addOrRemove.addActionListener(listener);
            add(title);
            add(rank);
            add(artists);
            add(addOrRemove);
        }
    }

    /**
     * Artist item inner class
     */
    class ArtistItem extends JPanel {
        private JLabel name;
        private JButton addOrRemove;

        public ArtistItem(Artist artist, User user) {
            this.name = new JLabel(artist.getName());
            addOrRemove = new JButton("Add");

            for (Artist userArtist: user.getArtists()) {
                if(artist.equals(userArtist)) {
                    addOrRemove.setText("Remove");
                }
            }
            Controller.AddOrRemoveListener listener;
            listener = new Controller.AddOrRemoveListener(null, artist, currentUser, addOrRemove);
            addOrRemove.addActionListener(listener);

            add(name);
            add(addOrRemove);

        }
    }

    /**
     * Navbar inner class
     */
    class Navbar extends JPanel {
        // Navbar components
        private JLabel appName;
        private JButton listUserButton;
        private JButton favoriteSongsButton;
        private JButton favoriteArtistsButton;
        private JButton logOutButton;
        private JLabel currentUserLabel;
        private Controller.NavbarListener navbarListener;

        public Navbar() {
            super();
            appName = new JLabel(GUI.APP_NAME);
            appName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));

            // logged out state components
            listUserButton = new JButton("Show Users");
            // add listener
            navbarListener = new Controller.NavbarListener();
            listUserButton.addActionListener(navbarListener);

            // logged in state components
            favoriteSongsButton = new JButton("My Songs");
            favoriteSongsButton.addActionListener(navbarListener);

            favoriteArtistsButton = new JButton("My Artists");
            favoriteArtistsButton.addActionListener(navbarListener);

            logOutButton = new JButton("Log Out");
            logOutButton.addActionListener(navbarListener);


            add(appName);
            add(Box.createHorizontalStrut(30));
            add(listUserButton);
        }

        /**
         * Update navbar from logged in state to logged out state and vice versa.
         */
        public void updateNavbar(){

            Component horizontalStrut = Box.createHorizontalStrut(20);
            User user = currentUser;

            // if logged out state, remove components
            if (user == null) {
                // must remove components in the backwards order
                // otherwise the index numbers get messed up
                // as you are changing the object you are iterating over.
                for (int i = getComponentCount()-1; i > 1; i--) {
                    this.remove(i);
                }
                add(listUserButton);
            }

            // if logged in, add components
            if (user != null) {
                currentUserLabel = new JLabel(user.getName());
                remove(listUserButton);

                add(favoriteSongsButton);
                add(horizontalStrut);
                add(favoriteArtistsButton);
                add(horizontalStrut);
                add(logOutButton);
                add(currentUserLabel);
                add(horizontalStrut);
            }
            pack();
        }
    }

    /**
     * Login panel inner class
     */
    class LogInPanel extends JPanel {
        private JLabel userNameFieldLabel;
        private JTextField userNameField;
        private JButton loginButton;
        private JButton registerButton;
        private JLabel messageLabel;

        public LogInPanel() {
            super();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // instantiate components of this panel
            userNameFieldLabel = new JLabel("User Name");
            userNameField = new JTextField(2);
            userNameField.setMaximumSize(new Dimension(200, 26));

            loginButton = new JButton("Log in");
            Controller.LogInPanelListener logInPanelListener = new Controller.LogInPanelListener(userNameField);
            loginButton.addActionListener(logInPanelListener);

            registerButton = new JButton("Register");
            registerButton.addActionListener(logInPanelListener);
            messageLabel = new JLabel();
            messageLabel.setText("Welcome! Please Log in or Register");

            // center components
            centerWidget(userNameFieldLabel);
            centerWidget(userNameField);
            centerWidget(loginButton);
            centerWidget(registerButton);
            centerWidget(messageLabel);

            // adds space to the top of the center panel
            add(Box.createRigidArea(new Dimension(0,100)));

            // add components
            add(userNameFieldLabel);
            add(userNameField);
            add(loginButton);
            add(registerButton);
            add(Box.createRigidArea(new Dimension(0,50)));
            add(messageLabel);

            // add space to the bot of the center panel
            add(Box.createRigidArea(new Dimension(0,100)));
        }

        public JLabel getMessageLabel() {
            return messageLabel;
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Helper method to center a component.
     * @param component center the component
     */
    private static void centerWidget(JComponent component){
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


    /**
     * Change message label to give feedback to user
     * @param message message for the user
     */
    public void showMessage(String message) {
        this.logInPanel.getMessageLabel().setText(message);
    }

    public void updateContentPanel(ArrayList<Song> songs, ArrayList<Artist> artists) {
        contentPanel.itemsPanel.displayItems(songs, artists, currentUser);
    }
}
