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
		User user = null;
		// Greet user, then login or register user
		try {
			ConsoleGUI.greetings();

			while(user == null)
			{
				if(ConsoleGUI.promptToShowExistingUsers())
				{
					ConsoleGUI.showExistingUsers();
				}
				user = ConsoleGUI.login();
				if (user == null)
				{
					// register user
					user = ConsoleGUI.register();
				} else {

					// Enter standby mode:
					while(user != null)
					{
						System.out.println("Logged in user: ");
						System.out.println(user.getName());

						// Listen to commands from user.
						String command = ConsoleGUI.standByMode();

						/////////////////// if statement for every command ///////////////////

						if(command.equals("logout"))
						{
							user = null;
						}

						if(command.equals("help"))
						{
							ConsoleGUI.listCommands();
						}

						if(command.equals("shutdown"))
						{
							System.exit(0);
						}

						//////////////// End of if statement for every command /////////////////
					}
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
