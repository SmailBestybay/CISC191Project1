import java.util.ArrayList;

/**
 * @author Smail Bestybay
 *
 */
public class Song
{
	private String title;
	private String rank;
	// maybe this could be url class?
	private String url;
	// Artists field is string temporary
	private ArrayList<Artist> artists = null;
	
	public Song() {};
	

	public Song(String title, String rank, String url, ArrayList<Artist> artists)
	{
		this.title = title;
		this.rank = rank;
		this.url = url;
		this.artists = artists;
	}
	
	/**
	 * @return the artists
	 */
	public ArrayList<Artist> getArtists()
	{
		return artists;
	}
	
	public String toString()
	{
		return "Song title: " + title + " | " +
				"Rank:  " + rank + " | " +
				"Url: " + url + " | " +
				"Artists: " + artists;
	}
	
	
}
