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
	        ArrayList<Song> songs = new ArrayList<Song>();
	        for (Song song : this.songs) {
	            songs.add(song);
	        }
	        return songs;
	    }
	}