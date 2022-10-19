import java.util.ArrayList;

public class User extends Person
{
	private ArrayList<Song> songs = new ArrayList<Song>();
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	
	public User(String name)
	{
		super(name);
	}
	
	/**
	 * 
	 * @param newSong
	 * @return string status message
	 */
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
	
	/**
	 * 
	 * @param newSong
	 * @return string status message
	 */
	public String removeSong(Song newSong)
	{
		// check if song already exists
		if(newSong == null)
		{
			return "ERROR: No Song removed, object is null";
		}
		
		for(Song song: songs)
		{
			if(song.equals(newSong))
			{
				songs.remove(song);
				return "Song has been removed";
			}
		}
		return "Song is not in the list";
	}
	
	/**
	 * 
	 * @param newArtist
	 * @return string status message
	 */
	public String addArtist(Artist newArtist)
	{
		// check if Artist already exists
		if(newArtist == null)
		{
			return "ERROR: No Artist added, object is null";
		}
		
		for(Artist Artist: artists)
		{
			if(Artist.equals(newArtist))
			{
				return "Artist is already here";
			}
		}
		artists.add(newArtist);
		return "Artist has been added";
	}
	
	/**
	 * 
	 * @param newArtist
	 * @return string status message
	 */
	public String removeArtist(Artist newArtist)
	{
		// check if Artist already exists
		if(newArtist == null)
		{
			return "ERROR: No Artist removed, object is null";
		}
		
		for(Artist artist: artists)
		{
			if(artist.equals(newArtist))
			{
				artists.remove(artist);
				return "Artist has been removed";
			}
		}
		return "Artist is not in the list";
	}
	
	/**
	 * @return artists string representation
	 */
	public String showArtists()
	{
		return artists.toString();
	}
	
	/**
	 * @return songs string representation
	 */
	public String showSongs()
	{
		return songs.toString();
	}
	
	public String showAllFavorites()
	{
		return "Favorite Songs:\n" + showSongs() + "\n" + "Favorite Artists:\n" + showArtists();
	}

	/**
	 * modify to return deep copy later
	 * @return
	 */
	public ArrayList<Song> getSongs()
	{
		return songs;
	}

	/**
	 * modify to return deep copy later
	 * @return
	 */
	public ArrayList<Artist> getArtists()
	{
		return artists;
	}

	public ArrayList<Object> getAll()
	{
		ArrayList<Object> all = new ArrayList<Object>();
		all.addAll(getSongs());
		all.addAll(getArtists());
		return all;
	}
}
