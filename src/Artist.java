import java.util.ArrayList;

public class Artist extends Person
{
	private ArrayList<Song> songs;
	
	public Artist(String name)
	{
		super(name);
		songs = new ArrayList<Song>();
	}
	
	public void addSong(Song song)
	{
		songs.add(song);
	}
	
	public String toString()
	{
		return "Artist name: " + getName() + " with the " +
				"Song count: " + songs.size();
	}
	
}
