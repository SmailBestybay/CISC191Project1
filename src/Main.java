import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Smail, Dre
 *
 */
public class Main
{

	/**
	 * @param args
	 *  
	 */
	public static void main(String[] args)
	{
		
//		String row = "Starboy,1,https://open.spotify.com/track/5aAx2yezTd8zXrkmtKl66Z,The Weeknd, Daft Punk";
		
		Song[] songs = createSongs();
		for(Song song: songs)
		{
			System.out.println(song);
		}
		
		
	}
	
	/**
	 * parses csv database file and returns songs array.
	 * @return
	 */
	public static Song[] createSongs()
	{
		Song[] songs = new Song[201];
		Reader inFile;
		int count = 0;
		try
		{
			inFile = new FileReader( "dataBase.csv" );
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(inFile);
			for (CSVRecord record : records) {
				
//			    String title = record.get("title");
//			    System.out.print(title + " | ");
//			    String rank = record.get("rank");
//			    System.out.print(rank + " | ");
//			    String url = record.get("url");
//			    System.out.print(url + " | ");
//			    String artist = record.get("artist");
//			    System.out.print(artist + " | ");
//			    System.out.println();
			    
			    songs[count] = new Song(
			    					record.get("title"),
			    					record.get("rank"),
			    					record.get("url"),
			    					record.get("artist")
			    					);
			    count++;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return songs;
	}
	
	/**
	 * Parse row into a Song object
	 * 
	 * TODO some song titles have a comma.
	 * 
	 * @param row
	 * @return Song object
	 */
	public static Song createSong(String row)
	{
		Scanner sc = new Scanner(row);
		sc.useDelimiter(",");
		
		String title = sc.next();
		String rank = sc.next();
		String url = sc.next();
		String artists = sc.nextLine();
		
		sc.close();
		return new Song(title, rank, url, artists);
	}

}
