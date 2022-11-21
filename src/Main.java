/**
 * @author Smail, Dre
 *
 */
public class Main
{
	/**
	 * MVC implementation
	 */
	public static void main(String[] args)
	{
		SpotifyDatabase db = new SpotifyDatabase("dataBase.csv");
		GUI view = new GUI();
		new Controller(db, view);
	}
}
