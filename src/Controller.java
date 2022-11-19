import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    private SpotifyDatabase db;
    private GUI view;

    public Controller(SpotifyDatabase db, GUI view) {
        this.db = db;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
