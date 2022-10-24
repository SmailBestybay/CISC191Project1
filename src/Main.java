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
			ConsoleUI.greetings();

			while(user == null)
			{
				if(ConsoleUI.promptToShowExistingUsers())
				{
					ConsoleUI.showExistingUsers();
				}
				user = ConsoleUI.login();
				if (user == null)
				{
					// register user
					user = ConsoleUI.register();
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
						String command = ConsoleUI.standByMode();

						/////////////////// if statement for every command ///////////////////

						switch (command) {
							case "help" -> ConsoleUI.listCommands();
							case "error" -> System.out.println("ERROR: command must be only one word.");
							case "logout" -> user = null;
							case "search" -> {
								switch (ConsoleUI.searchMethod()) {
									case "error" -> System.out.println("ERROR: search method undefined");
									case "artist" -> {
										// console gui search artist should return null
										// if the user does not want to add any of the search
										// results. We can use null check to decide weather we need to proceed

										ArrayList<Artist> artists = db.searchArtist(ConsoleUI.searchByArtists());
										user.addArtist(ConsoleUI.displayArtistResults(artists));
										userDatabase.exportCSV(user);
									}
									case "song" -> {
										ArrayList<Song> songs = db.searchSong(ConsoleUI.searchBySong());
										user.addSong(ConsoleUI.displaySongResults(songs));
										userDatabase.exportCSV(user);
									}
									case "mixed" -> {
										ConsoleUI.search();
									}
								}
							}
							case "showAll" -> ConsoleUI.showFavorites(user.getAll());
							case "remove" -> {
								Object object = ConsoleUI.removeEntry(user.getAll());
								if(object instanceof Song)
								{
									user.removeSong((Song) object);
								}
								if(object instanceof Artist)
								{
									user.removeArtist((Artist) object);
								}
								userDatabase.exportCSV(user);
								System.out.println("Entry removed successfully.");
							}
							case "shutdown" -> System.exit(0);
							default -> System.out.println("Command does not exist.");
						}

						//////////////// End of if statement for every command /////////////////
					}
				}
			}
		}
		catch (InterruptedException | NullPointerException e)
		{
			e.printStackTrace();
		}
	}
}
