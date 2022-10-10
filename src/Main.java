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
		
		for(Song song: db.getSongs())
		{
			System.out.println(song);
		}
		
	}
	
	
	

}
