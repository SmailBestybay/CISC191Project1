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
		
//		for(int i = 0; i < db.getArtists().size(); i++)
//		{
//			System.out.println(db.getArtists().get(i));
//		}
		
//		for(Song song: db.getSongs())
//		{
//			System.out.println(song);
//		}
//		for(Artist artist: db.getArtists())
//		{
//			System.out.println(artist);
//		}
//		String res = db.searchSong("ocean");
		String res = db.searchArtist("");
		System.out.println(res);
	}
	
	
	

}
