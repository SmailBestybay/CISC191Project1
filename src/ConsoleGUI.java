import java.util.Scanner;

/**
 * View
 */
public class ConsoleGUI {
   
    /**
     * Welcome statement
     * @throws InterruptedException
     */
    public static void greetings() throws InterruptedException
    {
        String[] messages = {
            "---------------------------------------------------------\n"+
            "Welcome to Spotify Top 200 Global Charts Database Lookup!\n"+
            "---------------------------------------------------------\n",
            "---------------------------------------------------------\n"+
            "With this application you can look up songs and artists.\n"+
            "---------------------------------------------------------\n",
            "---------------------------------------------------------\n"+
            "When you find your favorite songs or artists,\n"+
            "you can choose to add them to your favorites list.\n"+
            "---------------------------------------------------------\n",
            "---------------------------------------------------------\n"+
            "The application will save your list to a nice csv file.\n"+
            "So you can always pick up where you left off!\n"+
            "---------------------------------------------------------\n",
        };

        for(String message: messages)
        {
            System.out.print(message);
            Thread.sleep(4500);
        }
    }

    public static boolean promptToShowExistingUsers()
    {
        Scanner keyboard = new Scanner(System.in);
        boolean seeUsers = false;
        while(!seeUsers)
        {
            System.out.print("Would you like to see existing users? (Y/N): ");
            String response = keyboard.nextLine();
            if(response.equals("Y") || response.equals("y"))
            {
                seeUsers = true;
                keyboard.close();
                return seeUsers;
            } 
            else if (response.equals("N") || response.equals("n"))
            {
                keyboard.close();
                return seeUsers;
            }
            else 
            {
                System.out.println();
                System.out.println("INVALID INPUT");
                System.out.println("Your response was: " + response);
                System.out.println();
            }
        }
        keyboard.close();
        return seeUsers;
    }
}
