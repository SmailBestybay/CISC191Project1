import java.util.Scanner;

/**
 * View
 */
public class ConsoleGUI {
   
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
                Thread.sleep(10);
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
        Scanner keyboard = new Scanner(System.in);
        while(true)
        {
            System.out.print("Would you like to see existing users? (Y/N): ");
            String response = keyboard.nextLine();
            if(response.equals("Y") || response.equals("y"))
            {
                keyboard.close();
                return true;
            } 
            else if (response.equals("N") || response.equals("n"))
            {
                keyboard.close();
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
    public static void ShowExistingUsers()
    {
        System.out.println("These are the existing users:");
        System.out.println(userDatabase.showUsers());
    }
}
