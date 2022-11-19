import java.util.ArrayList;
import java.util.Scanner;

/**
 * View
 */
public class ConsoleUI {
    // scanner needs to be static so that we do not close it multiple times
    // closing scanner closes System.in, for all other methods.
    private static final Scanner keyboard = new Scanner(System.in);
   
    /**
     * Welcome statement
     * @throws InterruptedException interrupted exception
     */
    public static void greetings() throws InterruptedException
    {
        String[] messages = {
            """
            ---------------------------------------------------------
            Welcome to Spotify Top 200 Global Charts Database Lookup!
            ---------------------------------------------------------
            """,
            """
            ---------------------------------------------------------
            With this application you can look up songs and artists.
            ---------------------------------------------------------
            """,
            """
            ---------------------------------------------------------
            When you find your favorite songs or artists,
            you can choose to add them to your favorites list.
            ---------------------------------------------------------
            """,
            """
            ---------------------------------------------------------
            The application will save your list to a nice csv file.
            So you can always pick up where you left off!
            ---------------------------------------------------------
            """
        };

        for(String message: messages)
        {
            for(int i = 0; i < message.length(); i++)
            {
                System.out.print(message.charAt(i));
            }
            Thread.sleep(1500);
        }
    }

    /**
     * Ask if user wants to see existing users
     * @return boolean user's choice
     */
    public static boolean promptToShowExistingUsers()
    {
        while(true)
        {
            System.out.print("Would you like to see existing users? (Y/N): ");
            String response = keyboard.nextLine();
            if(response.equals("Y") || response.equals("y"))
            {
                return true;
            } 
            else if (response.equals("N") || response.equals("n"))
            {
                return false;
            }
            else 
            {
                System.out.println();
                System.out.println("INVALID INPUT");
                System.out.println("Your response was: " + response);
                System.out.println();
            }
        }
    }

    /**
     * Show existing users
     */
    public static void showExistingUsers()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("Here are the existing users:");
        for(String user: userDatabase.getUsers())
        {
            System.out.println(user);
        }
        System.out.println("---------------------------------------------------------");
    }

    /**
     *
     * @return user that exists in database or null
     */
    public static User login()
    {
        while (true) {
            System.out.println("---------------------------------------------------------");
            System.out.print("Please enter user name to login: ");
            String name = keyboard.nextLine();
            User user = userDatabase.importUser(name);
            if (user == null) {
                System.out.println();
                System.out.println("User does not exist");
                System.out.println("Format for username is a one word name");
                boolean register = true;
                while (register) {
                    System.out.print("Would you like to register a new user instead? (Y/N): ");
                    String response = keyboard.nextLine();
                    if (response.equals("Y") || response.equals("y")) {
                        return null;
                    } else if (response.equals("N") || response.equals("n")) {
                        register = false;
                    } else {
                        System.out.println();
                        System.out.println("INVALID INPUT");
                        System.out.println("Your response was: " + response);
                        System.out.println();
                    }
                }
            } else {
                return user;
            }
        }
    }

    /**
     *
     * @return String name of successfully registered username
     */
    public static User register()
    {
        User user = null;
        while (user == null)
        {
            System.out.println("---------------------------------------------------------");
            System.out.println("Type exit to exit registration");
            System.out.println("Please enter user name to register: ");
            String name = keyboard.nextLine();
            // validate name input to be one word
            if(isOneWord(name))
            {
                if(name.equals("exit"))
                {
                    System.out.println("Exited registration");
                    System.out.println("---------------------------------------------------------");
                    return null;
                }
                else if(name.equals(""))
                {
                    System.out.println("ERROR: Username cannot be empty");
                } else {
                    user = new User(name);
                    userDatabase.exportCSV(user);
                }
            }
           else {
                System.out.println("ERROR: Username must be only one word.");
            }
        }
        return user;
    }

    /**
     * Listens to commands from the user
     * @return one word command
     */
    public static String standByMode()
    {
        String command = keyboard.nextLine();
        if(isOneWord(command))
        {
            return command;
        } else {
            return "error";
        }
    }

    /**
     * List all commands available to the user
     */
    public static void listCommands()
    {
        System.out.println("LIST OF COMMANDS");
        System.out.println("help -- lists commands");
        System.out.println("logout -- logout user");
        System.out.println("search -- search spotify database");
        System.out.println("showAll -- show current user's all favorites");
        System.out.println("remove -- remove entry from favorites");
        System.out.println("shutdown -- shutdown app.");
    }

    public static String searchMethod()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("Search mode entered:");
        System.out.println("Search by Artist or Song or Mixed? (A/S/M):" );
        String category = keyboard.nextLine();
        return switch (category) {
            case "A", "a" -> "artist";
            case "S", "s" -> "song";
            case "M", "m" -> "mixed";
            default -> "error";
        };
    }

    public static String searchByArtists() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Please enter your search query:");
        return keyboard.nextLine();
    }

    public static String searchBySong() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Please enter your search query:");
        return keyboard.nextLine();
    }

    public static void search() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Not implemented yet.");
    }

    public static Artist displayArtistResults(ArrayList<Artist> artists)
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("Artist search results: ");
        if (artists == null)
        {
            System.out.println("No Artists Found.");
            return null;
        }

        for (int i = 0; i < artists.size(); i++) {
            System.out.println("--> " + i + " <--");
            System.out.println(artists.get(i));
        }
        int index = -1;
        while(index == -1)
        {
            System.out.println("Pick the artist to add to you favorites list (To exit enter -2)");
            if(keyboard.hasNextInt())
            {
                index = keyboard.nextInt();
                keyboard.nextLine();
                if (index == -2)
                {
                    return null;
                }
                if(index < 0)
                {
                    index = -1;
                    System.out.println("Number can not be negative");
                } else if (index >= artists.size()) {
                    index = -1;
                    System.out.println("Invalid number");
            }
            } else {
                System.out.println("Error: please enter the number");
                keyboard.nextLine();
            }
        }
        return artists.get(index);
    }

    public static Song displaySongResults(ArrayList<Song> songs)
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("Song search results: ");
        if (songs == null)
        {
            System.out.println("No Songs Found.");
            return null;
        }

        for (int i = 0; i < songs.size(); i++) {
            System.out.println("--> " + i + " <--");
            System.out.println(songs.get(i));
        }
        int index = -1;
        while(index == -1)
        {
            System.out.println("Pick the song to add to you favorites list (To exit enter -2)");
            if(keyboard.hasNextInt())
            {
                index = keyboard.nextInt();
                keyboard.nextLine();
                if (index == -2)
                {
                    return null;
                }
                if(index < 0)
                {
                    index = -1;
                    System.out.println("Number can not be negative");
                } else if (index >= songs.size()) {
                    index = -1;
                    System.out.println("Invalid number");
                }
            } else {
                System.out.println("Error: please enter the number");
                keyboard.nextLine();
            }
        }
        return songs.get(index);
    }

    public static void showFavorites(ArrayList<ArrayList<Object>> all) {
        for (int i = 0; i < all.size(); i++)
        {
            System.out.println("---------------------------------------------------------");
            if(i == 0)
            {
                System.out.println("These are your favorite songs");
            } else {
                System.out.println("These are your favorite artists");
            }
            for (int j = 0; j < all.get(i).size(); j++)
            {
                if (i == 0)
                {
                    System.out.println("--> Song entry number: " + j + " <--");
                } else {
                    System.out.println("--> Artist entry number: " + j + " <--");

                }
                System.out.println(all.get(i).get(j));
            }
        }
    }

    /**
     *
     * @param all combined array of songs and artists
     * @return entryObject song or artist object as object.
     */
    public static Object removeEntry(ArrayList<ArrayList<Object>> all)
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("To remove song enter 0, to remove artist enter 1");
        System.out.println("Enter your choice:");
        String songOrArtist = keyboard.nextLine();
        if(!isOneWord(songOrArtist))
        {
            System.out.println("Error: input should be only one word");
            return null;
        }
        int category;
        try {
            category =Integer.parseInt(songOrArtist);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error: Must enter a number: 0 for song, 1 for artist.");
            return null;
        }

        System.out.println("Please enter the number of the entry: ");
        String entryChoice = keyboard.nextLine();

        if(!isOneWord(entryChoice))
        {
            System.out.println("Error: entry should be one word");
            return null;
        }

        int entry;
        try {
            entry = Integer.parseInt(entryChoice);
        } catch (NumberFormatException e)
        {
            System.out.println("Error: Must enter a number");
            return null;
        }

        Object entryObject = null;
        try {
            entryObject = all.get(category).get(entry);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Choice is not valid.");
        }

        return entryObject;
    }

    private static boolean isOneWord(String input)
    {
        String[] arr = input.trim().split(" ");
        return arr.length == 1;
    }
}
