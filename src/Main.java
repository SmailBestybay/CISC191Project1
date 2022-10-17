/**
 * @author Smail, Dre
 *
 */
public class Main
{

	/**
	 * Controller
	 *  
	 */
	public static void main(String[] args)
	{
		 SpotifyDatabase db = new SpotifyDatabase("dataBase.csv");

		try {
			ConsoleGUI.greetings();
			if(ConsoleGUI.promptToShowExistingUsers())
			{
				ConsoleGUI.showExistingUsers();
				User user = ConsoleGUI.login();
				if (user == null)
				{
					// register user
					System.out.println("Register here");
				} else {
					System.out.println(user);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// String row = "Starboy,1,https://open.spotify.com/track/5aAx2yezTd8zXrkmtKl66Z,The Weeknd, Daft Punk";
		

		// User user = userDatabase.importUser("Bae");
		// ArrayList<Song> res = db.searchSong("starboy");
		// System.out.println(res);
		// user.addSong(res.get(0)); 
		// ArrayList<Artist> resArt = db.searchArtist("drake");
		// System.out.println(resArt);
		// user.addArtist(resArt.get(0)); 
		// resArt = db.searchArtist("Daft Punk");
		// user.addArtist(resArt.get(0)); 
		// System.out.println(user.removeArtist(res.get(0)));
		// userDatabase.exportCSV(user);
		// user = userDatabase.importUser("Bae");
		// System.out.println(user.showAllFavorites());
	}
}
