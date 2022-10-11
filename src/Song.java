import java.util.ArrayList;
import java.util.Objects;

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
	
	public String toString()
	{
		return "Song title: " + title + " | " +
				"Rank:  " + rank + " | " +
				"Url: " + url + " | " +
				"Artists: " + artists;
	}
	
	/**
	 * @param Object other
	 * @return boolean 
	 */
	@Override
	public final boolean equals(Object other)
	{
		if(this == other)
		{
			return true;
		}
		
		if(other == null)
		{
			return false;
		}
		
		if(other instanceof Song)
		{
			Song song = (Song) other;
			return Objects.equals(rank, song.rank);
		}
		return false;
	}

	/**
	 * 
	 * @return song title
	 */
	public String getTitle()
	{
		return title;
	}


	/**
	 * @return the rank
	 */
	public String getRank()
	{
		return rank;
	}


	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}
	
	/**
	 * @return the artists
	 */
	public ArrayList<Artist> getArtists()
	{
		return artists;
	}
}
