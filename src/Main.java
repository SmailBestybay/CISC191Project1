import java.util.ArrayList;

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
						System.out.println("---------------------------------------------------------");
						System.out.print("Current User: ");
						System.out.print(user.getName() + "\n");

						// Listen to commands from user.
						System.out.println("Standing by for a command...");
						System.out.println("Enter \"help\" for list of available commands");
						String command = ConsoleGUI.standByMode();

						/////////////////// if statement for every command ///////////////////

						switch (command) {
							case "help" -> ConsoleGUI.listCommands();
							case "error" -> System.out.println("ERROR: command must be only one word.");
							case "logout" -> user = null;
							case "search" -> {
								switch (ConsoleGUI.searchMethod()) {
									case "error" -> System.out.println("ERROR: search method undefined");
									case "artist" -> {
										ArrayList<Artist> artists = db.searchArtist(ConsoleGUI.searchByArtists());
										ConsoleGUI.displayArtistResults(artists);

									}
									case "songs" -> {
										ConsoleGUI.searchBySong();
									}
									case "mixed" -> {
										ConsoleGUI.search();
									}
								}
							}
							case "shutdown" -> System.exit(0);
							default -> System.out.println("Command does not exist.");
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
