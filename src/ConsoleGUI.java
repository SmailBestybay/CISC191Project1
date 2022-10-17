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
                System.out.println("Format is a one word name that starts with a capital letter");
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
    public static String register()
    {
        System.out.println("---------------------------------------------------------");
        System.out.print("Please enter user name to register: ");
        String name = keyboard.next();
        if(keyboard.hasNext())
        {
            keyboard.nextLine();
        }
        // validate name input to be one word
        System.out.println("-------------------NAME IN REGISTER-------------------");
        System.out.println(name);
        System.out.println("-------------------NAME IN REGISTER-------------------");
        return "";
    }
}
