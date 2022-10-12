import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	 * @return
	 */
	public static String showUsers()
	{
		File file = new File("Users/");
		String[] list = file.list();
		String res = "";
		for(String str: list)
		{
			res += str + " ";
		}
		return res;
	}
	
	@SuppressWarnings("deprecation")
	public static User importUser(String name)
	{
		String path = findUserPath(name);
		User user = new User(name);
		
		// read songs.csv
		Reader inFile;
		try
		{
			inFile = new FileReader(path + "songs.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			// for every row read rank
			Song[] dbsongs = SpotifyDatabase.getSongs();
			for (CSVRecord record : records) 
			{
				// use rank to find song object in SpotifyDB
				for(int i = 0; i < dbsongs.length; i++)
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
			inFile = new FileReader(path + "songs.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			// for every row read songHash
			ArrayList<Artist> dbartist = SpotifyDatabase.getArtists();
			for (CSVRecord record : records) 
			{
				
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
	 * @param name
	 * @return
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
	
	
	
	
	
	
	
	
}
