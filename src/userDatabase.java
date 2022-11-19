import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Class to manage stored user info
 * @author smile
 *
 */
public class userDatabase
{
	/**
	 * display all existing users by reading sub folder names
	 * @return list of users
	 */
	public static String[] getUsers()
	{
		File file = new File("Users/");
		return file.list();
	}
	
	/**
	 * When gui wants to log in, will probably use this method
	 * 
	 * @param name name of user
	 * @return User object
	 */
	@SuppressWarnings("deprecation")
	public static User importUser(String name)
	{
		String path = findUserPath(name);
		if(path == null)
		{
			return null;
		}
		User user = new User(name);
		
		// read songs.csv
		Reader inFile;
		try
		{
			inFile = new FileReader(path + "songs.csv");
			Iterable<CSVRecord> songRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			// for every row read rank
			Song[] dbsongs = SpotifyDatabase.getSongs();
			for (CSVRecord record : songRecords) 
			{
				// use rank to find song object in SpotifyDB
				for (int i = 0; i < dbsongs.length; i++)
				{
					if(dbsongs[i].getRank().equals(record.get("rank")))
					{
						// add song object to new user.
						user.addSong(dbsongs[i]);
					}
				}
				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// read artists.csv
		try
		{
			inFile = new FileReader(path + "artists.csv");
			Iterable<CSVRecord> artistRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			// for every row read songHash
			ArrayList<Artist> dbartists = SpotifyDatabase.getArtists();
			for (CSVRecord record : artistRecords) 
			{
				for(Artist artist: dbartists)
				{
					if(songHash(artist).equals(record.get("songs")))
					{
						user.addArtist(artist);
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * Finds path to user data in folder structure
	 * should add throw null exception in the future
	 * @param name name of user
	 * @return path to user
	 */
	private static String findUserPath(String name)
	{
		File file = new File("Users/");
		String[] list = file.list();
		String path = null;
		for(String user: list)
		{
			if(name.equals(user))
			{
				System.out.println("match found: " + user);
				path = "Users/" + user + "/";
				return path;
			}
		}
		System.out.println("No match found.");
		return null;
	}
	
	
	/**
	 * Can use this to also "register" the user
	 * exports user's song and artist data into 2 csv file in a folder specific to user.
	 */
	public static void exportCSV(User user) throws NullPointerException
	{
		if (user == null)
		{
			throw new NullPointerException("ERROR: user is null");
		}
		String path = "Users/" + user.getName() + "/";
		File file = new File(path);
		file.mkdirs();
		// export songs.csv
		try (
				
				FileWriter fw = new FileWriter(path + "songs.csv");
				PrintWriter pw = new PrintWriter(fw);
			)
		{
			pw.println("title,rank,url,artists");
			for(Song song: user.getSongs())
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
				pw.println(line);
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
			for(Artist artist: user.getArtists())
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
	 * @param artist artist to hash
	 * @return song hash number in string type
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
