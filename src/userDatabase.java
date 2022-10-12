import java.io.File;

/**
 * Class to manage stored user info
 * @author smile
 *
 */
public class userDatabase
{
	/**
	 * display all existing users by reading sub folder names
	 * @return
	 */
	public static String showUsers()
	{
		File file = new File("Users/");
		String[] list = file.list();
		String res = "";
		for(String str: list)
		{
			res += str + " ";
		}
		return res;
	}
}
