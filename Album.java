import java.util.ArrayList;

public class Album {

	/* INSTANCE VARIABLES */
	private String title;
	private String artist;
	private String genre;
	private int year;
	private ArrayList<Song> songs;

	/* CONSTRUCTOR */
	public Album(String title, String artist, String genre, int year, ArrayList<Song> songs) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = songs;
	}

	/* METHODS */
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}

	public ArrayList<Song> getSongs() {
		ArrayList<Song> songsCopy = new ArrayList<Song>();
		for (Song song : this.songs) {
			Song s = new Song(song.getTitle(), song.getArtist());
			s.setRating(song.getRating());
			if (song.getFavorite()) {
				s.setFavorite();
			}
			songsCopy.add(s);
		}
		return songsCopy;
	}

	public boolean compareTo(Album other) {
		boolean same = false;
		if (other.getTitle() == this.title) {
			if (other.getArtist() == this.artist) {
				same = true;
			}
		}
		return same;
	}
}