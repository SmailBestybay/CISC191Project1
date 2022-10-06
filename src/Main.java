import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Smail, Dre
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//
		// TODO Open CSV file and print first row.
//		searchCSV(10);
		
		String row = "Starboy,1,https://open.spotify.com/track/5aAx2yezTd8zXrkmtKl66Z,The Weeknd, Daft Punk";
		
		Song song = createSong(row);
		System.out.println(song);
	}
	
	public static String searchCSV(int row)
	{
		File inFile = new File ( "top200GlobalCharts.csv" );
		
		
		Scanner rowSc = null;
		Scanner colSc = null;
		try
		{
			rowSc = new Scanner (inFile);
			int count = 0;
			while (rowSc.hasNextLine() && count <= row)
			{
				String line = rowSc.nextLine();
				
				// search line
				// https://www.youtube.com/watch?v=1AyhR5A6ln4&t=231s&ab_channel=DevNami
				// gonna need to use delimiter
				
				if (count == row)
				{
					System.out.println(line);
					colSc = new Scanner(line);
					colSc.useDelimiter(",");
					while (colSc.hasNext())
					{
						line = colSc.next();
						if(line.contains("I Feel It Coming")) {
							System.out.println(line);
							return line;
						}
					}
				}
				count++;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			rowSc.close();
			colSc.close();
		}
		return "No results";
	}
	
	/**
	 * Parse row into a Song object
	 * 
	 * @param row
	 * @return Song object
	 */
	public static Song createSong(String row)
	{
		Scanner sc = new Scanner(row);
		sc.useDelimiter(",");
		
		String title = sc.next();
		int rank = sc.nextInt();
		String url = sc.next();
		String artists = sc.next() + " " + sc.next();
		
		sc.close();
		return new Song(title, rank, url, artists);
	}

}
