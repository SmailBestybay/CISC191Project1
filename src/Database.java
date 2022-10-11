import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Database
{
	private static Song[] songs;
	private static ArrayList<Artist> artists = new ArrayList<Artist>();
	
	/**
	 *  Constructor that initializes song and artist database
	 */
	@SuppressWarnings("deprecation")
	public Database(String filename)
	{
		/**
		 * parses csv database file and returns songs array.
		 * @return array of songs
		 */
		songs = new Song[201];
		Reader inFile;
		int count = 0;
		try
		{
			inFile = new FileReader(filename); // "dataBase.csv"
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			for (CSVRecord record : records) {
				// loop through artists cell to create artist with songs as null
				ArrayList<Artist> songArtists = createArtists(record.get("artist"));
				// add artist objects to song constructor
			    songs[count] = new Song(
			    					record.get("title"),
			    					record.get("rank"),
			    					record.get("url"),
			    					songArtists
			    					);
			    count++;
			}
			
			
			// for every artist, loop through songs, if artist in song, add song to artist
			addSongsToArtists();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	


	/**
	 * create artists and add them to db list 
	 * @param artists
	 * @return
	 */
	private static ArrayList<Artist> createArtists(String artists)
	{
		ArrayList<Artist> artistList = new ArrayList<Artist>();
		Scanner sc = new Scanner(artists);
		sc.useDelimiter(",");
		while(sc.hasNext())
		{
			// need to check if artist already exists in the db before creating new artist.
			// so we need addOrCreateArtist method
			Artist artist = addOrCreateArtist(sc.next().trim());
			
//			
//			Artist artist = new Artist(sc.next());
//			// add unique artist to database list
//			addArtistToDatabase(artist);
			artistList.add(artist);
		}
		sc.close();
		return artistList;
	}
	
	private void addSongsToArtists()
	{
		// loop through all songs in db, add song to each artist
		for(Song song: songs)
		{
			for(int i = 0; i < song.getArtists().size(); i++)
			{
				song.getArtists().get(i).addSong(song);
			}
		}
	}
	
	public Song[] getSongs()
	{
		return songs;
	}
	
	private static Artist addOrCreateArtist(String artistName)
	{
		Artist newArtist = null;
		if(artists.size() == 0)
		{
			newArtist = new Artist(artistName);
			artists.add(newArtist);
			return newArtist;
		}
		
		for(int i = 0; i < artists.size(); i++)
		{
			if(artists.get(i).getName().equals(artistName))
			{
				return artists.get(i);
			}
		}
		newArtist = new Artist(artistName);
		artists.add(newArtist);
		return newArtist;
	}
	/**
	 * compare given artist to database, add artist if no match
	 * @param artist
	 */
	private static void addArtistToDatabase(Artist artist)
	{
		if(artists.size() == 0)
		{
			artists.add(artist);
		}
		boolean exists = false;
		for(int i = 0; i < artists.size(); i++)
		{
			if(artists.get(i).equals(artist))
			{
				exists = true;
			}
		}
		if(!exists)
		{
			artists.add(artist);
		}
	}
	/**
	 * @return the artists
	 */
	public ArrayList<Artist> getArtists()
	{
		return artists;
	}
}
