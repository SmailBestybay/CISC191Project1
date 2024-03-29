import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class SpotifyDatabase
{
	private static Song[] songs;
	private static final ArrayList<Artist> artists = new ArrayList<Artist>();
	
	/**
	 *  Constructor that initializes song and artist database
	 */
	@SuppressWarnings("deprecation")
	public SpotifyDatabase(String filename)
	{
		songs = new Song[200];
		Reader inFile;
		int count = 0;
		try
		{
			inFile = new FileReader(filename); // "dataBase.csv"
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			for (CSVRecord record : records) {
				// loop through artists cell to create artist with songs as null
				ArrayList<Artist> songArtists = createArtists(record.get("artist"));
			    songs[count] = new Song(
			    					record.get("title"),
			    					record.get("rank"),
			    					record.get("url"),
			    					songArtists
			    					);
			    count++;
			}
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
	 * @param artists song artists
	 * @return new artists
	 */
	private static ArrayList<Artist> createArtists(String artists)
	{
		ArrayList<Artist> artistList = new ArrayList<Artist>();
		Scanner sc = new Scanner(artists);
		sc.useDelimiter(",");
		while(sc.hasNext())
		{
			Artist artist = addOrCreateArtist(sc.next().trim());
			artistList.add(artist);
		}
		sc.close();
		return artistList;
	}
	
	/**
	 * Add or create then add artist to the database.
	 * @param artistName name of artist
	 * @return new or existing artist
	 */
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
	 * add songs to each artist
	 */
	private void addSongsToArtists()
	{
		for(Song song: songs)
		{
			for(int i = 0; i < song.getArtists().size(); i++)
			{
				song.getArtists().get(i).addSong(song);
			}
		}
	}
	
	/**
	 * Will change to return a deep copy
	 * @return  songs
	 */
	public static Song[] getSongs()
	{
		return songs;
	}
	
	/**
	 * Will need to change to return a deep copy
	 * @return the artists
	 */
	public static ArrayList<Artist> getArtists()
	{
		return artists;
	}
	
	/**
	 * 
	 * @param title of the song
	 * @return String of the result
	 */
	public ArrayList<Song> searchSong(String title)
	{
		ArrayList<Song> foundSongs = new ArrayList<Song>();
		for(Song song: songs)
		{
			if(song.getTitle().toUpperCase().contains(title.toUpperCase()))
			{
				foundSongs.add(song);
			}
		}
		
		if (foundSongs.size() == 0)
		{
			return null;
		}
		return foundSongs;
	}
	
	public ArrayList<Artist> searchArtist(String name)
	{
		ArrayList<Artist> foundArtist = new ArrayList<Artist>();
		for(Artist artist: artists)
		{
			if(artist.getName().toUpperCase().contains(name.toUpperCase()))
			{
				foundArtist.add(artist);
			}
		}
		
		if (foundArtist.size() == 0)
		{
			return null;
		}
		return foundArtist;
	}
	
	public ArrayList<Object> search(String entry)
	{
		ArrayList<Artist> artistRes = this.searchArtist(entry);
		ArrayList<Song> songRes = this.searchSong(entry);
		
		if(artistRes == null && songRes == null)
		{
			return null;
		}
		
		ArrayList<Object> result = new ArrayList<Object>();
		result.addAll(artistRes);
		result.addAll(songRes);
		return result;
	}
}
