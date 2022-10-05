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
	private int rank;
	// maybe this could be url class?
	private String url;
	// Artists field is string temporary
	private String artists;
	
	public Song() {};
	
	public Song(String title, int rank, String url, String artists)
	{
		this.title = title;
		this.rank = rank;
		this.url = url;
		this.artists = artists;
	}
}
