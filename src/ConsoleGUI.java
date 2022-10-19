import java.util.ArrayList;
import java.util.Scanner;

/**
 * View
 */
public class ConsoleGUI {
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
//                Thread.sleep(10);
            }
//            Thread.sleep(1500);
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
        for(String user: userDatabase.showUsers())
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
            String[] arr = name.trim().split(" ");

            if(arr.length == 1)
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
        String[] arr = command.trim().split(" ");
        if(arr.length == 1)
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

    public static void searchBySong() {
    }

    public static void search() {
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
            System.out.println("Pick the artist to add to you favorites list");
            if(keyboard.hasNextInt())
            {
                index = keyboard.nextInt();
                keyboard.nextLine();
                if(index < 0)
                {
                    index = -1;
                    System.out.println("Number can not be negative");
                }
            } else {
                System.out.println("Error: please enter the number");
                keyboard.nextLine();
            }
        }
        return artists.get(index);
    }

    public static void displaySongResults(ArrayList<Song> songs)
    {

    }
}
