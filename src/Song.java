/**
 * 
 */

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
	private String artists;
	
	public Song() {};
	
	public Song(String title, String rank, String url, String artists)
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
}