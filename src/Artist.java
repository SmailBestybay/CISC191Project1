import java.util.ArrayList;
import java.util.Objects;

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
	
	/**
	 * @param Object other
	 * @return boolean 
	 */
	@Override
	public final boolean equals(Object other)
	{
		if(this == other)
		{
			return true;
		}
		
		if(other == null)
		{
			return false;
		}
		
		if(other instanceof Artist)
		{
			Artist artist = (Artist) other;
			return Objects.equals(getName(), artist.getName());
		}
		return false;
	}
}
