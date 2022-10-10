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
	private Song[] songs;
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	
	/**
	 *  Constructor that initializes song and artist database
	 */
	public Database(String filename)
	{
		/**
		 * parses csv database file and returns songs array.
		 * @return array of songs
		 */
		this.songs = new Song[201];
		Reader inFile;
		int count = 0;
		try
		{
			inFile = new FileReader( filename ); // "dataBase.csv"
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
	
	
	private static ArrayList<Artist> createArtists(String artists)
	{
		ArrayList<Artist> artistList = new ArrayList<Artist>();
		Scanner sc = new Scanner(artists);
		sc.useDelimiter(",");
		while(sc.hasNext())
		{
			//TODO finish this
			Artist artist = new Artist(sc.next());
			artistList.add(artist);
		}
		return artistList;
	}
	
	public Song[] getSongs()
	{
		Song[] newSongs = new Song[songs.length];
		for(int i = 0; i < songs.length; i++)
		{
			newSongs[i] = songs[i];
		}
		return newSongs;
	}
}
