import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LibraryModel {

	/* INSTANCE VARIABLES */
	protected MusicStore music;
	private ArrayList<Playlist> playlists;
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;

	public LibraryModel() {
		this.music = new MusicStore();
		try {
			music.loadAlbums("./albums/albums.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.playlists = new ArrayList<Playlist>();
		this.songs = new ArrayList<Song>();
		this.albums = new ArrayList<Album>();
	}

	// Get a song by its title
	public ArrayList<String> songByTitle(String title) {
		ArrayList<Album> resultA = new ArrayList<Album>();
		ArrayList<Song> resultS = new ArrayList<Song>();
		// adds songs of our title and their matching albums
		for (Album album : albums) {
			for (Song song : album.getSongs()) {
				if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
					resultS.add(song);
					resultA.add(album);
				}
			}
		}
		// checks to see if the song exists, but it's not tied to an album
		if (resultS.size() == 0) {
			for (Song song : songs) {
				if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
					resultS.add(song);
					resultA.add(new Album("NOT_IN_ALBUM", "NULL", "NULL", 0, new ArrayList<Song>()));
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
		for (Album album : albums) {
			for (Song song : album.getSongs()) {
				if (song.getArtist().toLowerCase().equals(artist.toLowerCase())) {
					resultS.add(song);
					resultA.add(album);
				}
			}
		}
		// checks to see if the song exists, but it's not tied to an album
		if (resultS.size() == 0) {
			for (Song song : songs) {
				if (song.getArtist().toLowerCase().equals(artist.toLowerCase())) {
					resultS.add(song);
					resultA.add(new Album("NOT_IN_ALBUM", "NULL", "NULL", 0, new ArrayList<Song>()));
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
		for (Album album : albums) {
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
		for (Album album : albums) {
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

	// Get a playlist by its title
	public ArrayList<String> playlistByTitle(String title) {
		ArrayList<String> playlistsToReturn = new ArrayList<String>();

		for (Playlist playlist : playlists) {
			if (playlist.getName().toLowerCase().equals(title.toLowerCase())) {
				playlistsToReturn.add("Playlist: " + title);
				playlistsToReturn.add("");
				playlistsToReturn.add("Song List:");
				for (Song song : playlist.getSongs()) {
					playlistsToReturn.add("Title: " + song.getTitle() + ", Artist: " + song.getArtist());
				}
			}
		}
		return playlistsToReturn;
	}

	// Add a song by its title
	public boolean addSong(String title) {
		boolean added = false;
		for (Album album : music.getAlbums()) {
			for (Song song : album.getSongs()) {
				if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
					songs.add(song);
					added = true;
				}
			}
		}
		return added;
	}

	public boolean addAlbum(String title) {
		boolean added = false;
		for (Album album : music.getAlbums()) {
			if (album.getTitle().toLowerCase().equals(title.toLowerCase())) {
				added = true;
				albums.add(album);
				for (Song song : album.getSongs()) {
					songs.add(song);
				}
			}
		}
		return added;
	}

	public void createPlaylist(String title) {
		playlists.add(new Playlist(title));
	}

	public boolean[] addSongToPlaylist(String songTitle, String playTitle) {
		boolean songExists = false;
		boolean playExists = false;
		for (Song song : songs) {
			if (song.getTitle().toLowerCase().equals(songTitle.toLowerCase())) {
				songExists = true;
				for (Playlist playlist : playlists) {
					if (playlist.getName().toLowerCase().equals(playTitle.toLowerCase())) {
						playlist.addSong(song);
						playExists = true;
					}
				}
			}
		}
		if (playExists == false) {
			for (Playlist playlist : playlists) {
				if (playlist.getName().toLowerCase().equals(playTitle.toLowerCase())) {
					playExists = true;
				}
			}
		}
		boolean[] exists = { songExists, playExists };
		return (exists);
	}

	public boolean[] removeSongFromPlaylist(String songTitle, String playTitle) {
		boolean songExists = false;
		boolean playExists = false;
		for (Playlist playlist : playlists) {
			if (playlist.getName().toLowerCase().equals(playTitle.toLowerCase())) {
				playExists = true;
				for (Song song : playlist.getSongs()) {
					if (song.getTitle().toLowerCase().equals(songTitle.toLowerCase())) {
						songExists = true;
						playlist.removeSong(song);
					}
				}
			}
		}
		if (songExists == false) {
			for (Song song : songs) {
				if (song.getTitle().toLowerCase().equals(songTitle.toLowerCase())) {
					songExists = true;
				}
			}
		}
		boolean[] exists = { songExists, playExists };
		return (exists);
	}

	public boolean favSong(String title) {
		boolean exists = false;
		for (Song song : songs) {
			if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
				song.setFavorite();
				exists = true;
			}
		}
		return exists;
	}

	public boolean rateSong(String title, Rating rating) {
		boolean exists = false;
		for (Song song : songs) {
			if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
				song.setRating(rating);
				exists = true;
			}
		}
		return exists;
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

	public Set<String> getArtists() {
		Set<String> artists = new HashSet<>();
		for (Song song : songs) {
			artists.add(song.getArtist());
		}
		return artists;
	}

	public ArrayList<Album> getAlbums() {
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
		for (Album album : albums) {
			albumsCopy.add(new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear(),
					album.getSongs()));
		}
		return albumsCopy;
	}

	public ArrayList<Playlist> getPlaylists() {
		ArrayList<Playlist> playlistsCopy = new ArrayList<Playlist>();
		for (Playlist playlist : playlists) {
			Playlist p = new Playlist(playlist.getName());
			for (Song song : playlist.getSongs()) {
				p.addSong(song);
			}
		}
		return playlistsCopy;
	}

	public ArrayList<Song> getFavs() {
		ArrayList<Song> favs = new ArrayList<Song>();
		for (Song song : songs) {
			if (song.getFavorite()) {
				Song s = new Song(song.getTitle(), song.getArtist());
				s.setRating(song.getRating());
				favs.add(s);
			}
		}
		return favs;
	}
}