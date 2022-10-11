import java.util.ArrayList;

public class User extends Person
{
	private ArrayList<Song> songs = new ArrayList<Song>();
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	
	public User(String name)
	{
		super(name);
	}
	
	public String addSong(Song newSong)
	{
		// check if song already exists
		if(newSong == null)
		{
			return "ERROR: No Song added, object is null";
		}
		
		for(Song song: songs)
		{
			if(song.equals(newSong))
			{
				return "Song is already here";
			}
		}
		songs.add(newSong);
		return "Song has been added";
	}
	
	

}
