package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicStore {
	/* INSTANCE VARIABLES */
	private Map<String, Album> albums;

	/* CONSTRUCTOR */
	public MusicStore() {
		albums = new HashMap<>();
	}

	/* METHODS */

	// Load multiple albums from a filepath
	public void loadAlbums(String albumsFilePath) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(albumsFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length != 2) {
					throw new IOException("Invalid album format");
				}
				String albumTitle = parts[0];
				String artist = parts[1];
				String albumFilePath = "./albums/" + albumTitle + "_" + artist + ".txt";
				Album album = loadAlbum(albumFilePath);
				albums.put(albumTitle, album);

			}
		}
	}

	// Load an album from a filepath
	private Album loadAlbum(String albumFilePath) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(albumFilePath))) {
			String header = reader.readLine();
			String[] headerParts = header.split(",");
			if (headerParts.length != 4) {
				throw new IOException("Invalid album file format");
			}
			String title = headerParts[0];
			String artist = headerParts[1];
			String genre = headerParts[2];
			int year = Integer.parseInt(headerParts[3]);

			ArrayList<Song> songs = new ArrayList<>();
			String song_title;
			while ((song_title = reader.readLine()) != null) {
				songs.add(new Song(song_title, artist));
			}

			return new Album(title, artist, genre, year, songs);
		}
	}

	// Get a song by its title
	public ArrayList<String> songByTitle(String title) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		ArrayList<Song> resultS = new ArrayList<Song>();
		// adds songs of our title and their matching albums
		for (Album album : albums.values()) {
			for (Song song : album.getSongs()) {
				if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
					resultS.add(song);
					resultA.add(album);
				}
			}
		}
		return songToStringHelper(resultS, resultA);
	}

	// Get a song by its artist
	public ArrayList<String> songByArtist(String artist) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		ArrayList<Song> resultS = new ArrayList<Song>();
		// adds songs of our artist and their matching albums
		for (Album album : albums.values()) {
			for (Song song : album.getSongs()) {
				if (song.getArtist().toLowerCase().equals(artist.toLowerCase())) {
					resultS.add(song);
					resultA.add(album);
				}
			}
		}
		return songToStringHelper(resultS, resultA);
	}

	// Get a song by its genre
	public ArrayList<String> songByGenre(String genre) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		ArrayList<Song> resultS = new ArrayList<Song>();
		// adds songs of our genre and their matching albums
		for (Album album : albums.values()) {
			if (album.getGenre().toLowerCase().equals(genre.toLowerCase())) {
				for (Song song : album.getSongs()) {
					resultS.add(song);
					resultA.add(album);
				}
			}
		}
		return songToStringHelper(resultS, resultA);
	}

	private ArrayList<String> songToStringHelper(ArrayList<Song> resultS, ArrayList<Album> resultA) {
		if (resultS.size() == 0) {
			// if no songs return an empty arraylist
			return new ArrayList<String>();
		} else {
			// go through and create the results of the songs in string form
			ArrayList<String> songsToReturn = new ArrayList<String>();
			int pos = 0;
			for (Song song : resultS) {
				songsToReturn.add("Title: " + song.getTitle() + ", Arist: " + song.getArtist() + ", Album: "
						+ resultA.get(pos).getTitle());
				pos++;
			}
			return songsToReturn;
		}
	}

	// Get an album by its title
	public ArrayList<String> albumByTitle(String title) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		// find albums of our title and record them down
		for (Album album : albums.values()) {
			if (album.getTitle().toLowerCase().equals(title.toLowerCase())) {
				resultA.add(album);
			}
		}
		return albumToStringHelper(resultA);
	}

	// Get an album by its artist
	public ArrayList<String> albumByArtist(String artist) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		// find albums of our artist and record them down
		for (Album album : albums.values()) {
			if (album.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				resultA.add(album);
			}
		}
		return albumToStringHelper(resultA);
	}

	private ArrayList<String> albumToStringHelper(ArrayList<Album> resultA) {
		if (resultA.size() == 0) {
			// if no albums return an empty arraylist
			return new ArrayList<String>();
		} else {
			// go through and create the results of the albums and their songs in string
			// form
			ArrayList<String> albumsToReturn = new ArrayList<String>();

			for (Album album : resultA) {
				albumsToReturn.add("Title: " + album.getTitle() + ", Arist: " + album.getArtist() + ", Genre: "
						+ album.getGenre() + ", Year: " + album.getYear());
				albumsToReturn.add("");
				albumsToReturn.add("Song List:");
				for (Song song : album.getSongs()) {
					albumsToReturn.add(song.getTitle());
				}
				albumsToReturn.add("");
			}
			return albumsToReturn;
		}
	}

	// Get a copy of the albums list
	public ArrayList<Album> getAlbums() {
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
		for (Album album : albums.values()) {
			albumsCopy.add(new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear(),
					album.getSongs()));
		}
		return albumsCopy;
	}
}