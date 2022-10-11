import java.util.ArrayList;

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
		
		Database db = new Database("dataBase.csv");
		User user = new User("Smile");
		
		ArrayList<Artist> res = db.searchArtist("The weeknd");
//		user.addArtist(res.get(0)); 
		System.out.println(user.removeArtist(res.get(0)));
	}
}
