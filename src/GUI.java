import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame {

    private static final String APP_NAME = "Spotify Super Lite!";
    private JPanel body;
    private Navbar navbar;
    private JPanel logInPanel;
    private JPanel contentPanel;

    // Logged out state center components, LogInPanel
    private JLabel userNameFieldLabel;
    private JTextField userNameField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel messageLabel;

    // Content panel components
    private JPanel searchPanel;
    private JPanel itemsPanel;

    // searchPanel components
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchMode;

    // itemsPanel components
    private SongItem[] songItems;


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

//        updateBody(new User("Smile"));

    }

    /**
     * make body JPanel.
     * start in Logged out state.
     * add navbar and loginPanel panels.
     */
    private void makeBody() {
        body = new JPanel(new BorderLayout());
        navbar = new Navbar();
        makeLogInPanel();
        body.add(navbar, BorderLayout.NORTH);
        body.add(logInPanel, BorderLayout.CENTER);
    }

    public void updateBody(User user) {

        if (user == null){
            navbar.updateNavbar(null);
            body.remove(contentPanel);
            body.add(logInPanel, BorderLayout.CENTER);
        }

        if (user != null) {
            navbar.updateNavbar(user);
            body.remove(logInPanel);
            makeContentPanel();
            body.add(contentPanel, BorderLayout.CENTER);
        }
        pack();
    }

    private void makeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));

        // instantiate components of this panel
        userNameFieldLabel = new JLabel("User Name");
        userNameField = new JTextField(2);
        userNameField.setMaximumSize(new Dimension(200, 26));
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

    private void makeContentPanel(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        makeSearchPanel();
        makeItemsPanel();

        centerWidget(searchPanel);
        centerWidget(itemsPanel);

        contentPanel.add(searchPanel);
        contentPanel.add(itemsPanel);
    }

    private void makeSearchPanel(){
        searchPanel = new JPanel();

        // components
        searchLabel = new JLabel("Search for a song or an artist");
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        String[] searchOptions = {"Song", "Artist", "Mixed"};
        searchMode = new JComboBox<>(searchOptions);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(searchMode);

    }
    private void makeItemsPanel() {
        itemsPanel = new JPanel(new GridLayout(0,1));
    }

    /**
     * Helper method to center a component.
     * @param component center the component
     */
    private static void centerWidget(JComponent component){
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    class Navbar extends JPanel {
        // Navbar components
        private JLabel appName;
        private JButton listUserButton;
        private JButton favoriteSongsButton;
        private JButton favoriteArtistsButton;
        private JButton logOutButton;
        private JLabel currentUserLabel;

        public Navbar() {
            super();
            appName = new JLabel(GUI.APP_NAME);
            appName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));

            // logged out state components
            listUserButton = new JButton("Show Users");

            // logged in state components
            favoriteSongsButton = new JButton("My Songs");
            favoriteArtistsButton = new JButton("My Artists");
            logOutButton = new JButton("Log Out");


            add(appName);
            add(Box.createHorizontalStrut(30));
            add(listUserButton);
        }

        /**
         * Update navbar from logged in state to logged out state and vice versa.
         * @param user current user data
         */
        public void updateNavbar(User user){

            Component horizontalStrut = Box.createHorizontalStrut(20);

            // if logged out state, remove components
            if (user == null) {
                for (int i = 2; i < getComponents().length; i++) {
                    remove(i);
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
            add(title);
            add(rank);
            add(artists);
            add(addOrRemove);
        }
    }

}
