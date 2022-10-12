import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	 * 
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
	 * exports user's song and artist data into 2 csv file in a folder specific to user.
	 */
	public void exportCSV()
	{
		String path = "Users/" + getName() + "/";
		File file = new File(path);
		file.mkdirs();
		// export songs.csv
		try (
				
				FileWriter fw = new FileWriter(path + "songs.csv");
				PrintWriter pw = new PrintWriter(fw);
			)
		{
			pw.println("title,rank,url,artists");
			for(Song song: songs)
			{
				StringBuilder line = new StringBuilder();
				line.append(song.getTitle()+",");
				line.append(song.getRank()+",");
				line.append(song.getUrl()+",");
				// open quote for artist cell
				line.append("\"");
				line.append(song.getArtists().get(0).getName());
				if(song.getArtists().size() > 1)
				{
					for(int i = 1; i < song.getArtists().size(); i++)
					{
						line.append(", "+song.getArtists().get(i).getName());
					}
				}
				// close quote for artist cell
				line.append("\"");
				pw.println(line.toString());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// export artist.csv
		try (
				FileWriter  afw = new FileWriter(path + "artists.csv");
				PrintWriter apw = new PrintWriter(afw);
			)
		{
			apw.println("name,songs");
			for(Artist artist: artists)
			{
				StringBuilder aline = new StringBuilder();
				aline.append(artist.getName()+",");
				aline.append(songHash(artist));
				apw.println(aline.toString());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Helper function to converts artist's songs into a hash
	 * hash format: song ranks separated by dashes " - " 
	 * @param artist
	 * @return
	 */
	private static String songHash(Artist artist)
	{
		if(artist == null) {return "Artist object is null";}
		
		StringBuilder songHash = new StringBuilder();
		for(int i = 0; i < artist.getSongs().size(); i++)
		{
			if(i == artist.getSongs().size()-1)
			{
				songHash.append(artist.getSongs().get(i).getRank());
			} else {
				songHash.append(artist.getSongs().get(i).getRank());
				songHash.append("-");
			}
			
		}
		return songHash.toString();
	}

}
