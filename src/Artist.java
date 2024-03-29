import java.util.ArrayList;

public class Artist extends Person implements Comparable<Artist>
{
	private final ArrayList<Song> songs;
	
	public Artist(String name)
	{
		super(name);
		songs = new ArrayList<Song>();
	}
	
	public void addSong(Song song)
	{
		songs.add(song);
	}
	
	public String toString()
	{
		return "Artist name: " + getName() + " with the " +
				"Song count: " + songs.size();
	}
	
	public ArrayList<Song> getSongs()
	{
		return new ArrayList<Song>(songs);
	}

	/**
	 *
	 * @param artist the object to be compared.
	 * @return int number based on ranking comparison
	 */
	@Override
	public int compareTo(Artist artist) {
		int songCount = getSongs().size();
		int artistSongCount = artist.getSongs().size();
		if(songCount == artistSongCount) {
			return 0;
		} else if (songCount > artistSongCount) {
			return 1;
		} else {
			return -1;
		}
	}
}
