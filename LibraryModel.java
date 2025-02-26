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
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void addAlbum(Album album) {
		albums.add(album);
		for (Song song : album.getSongs()) {
			songs.add(song);
		}
	}
	
	public ArrayList<Album> albumByTitle(String title) {
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
    	for (Album album : albums) {
    		if (album.getTitle().equals(title)) {
    			albumsCopy.add(album);
    		}
    	}
        return albumsCopy;
	}
	
	public ArrayList<Album> albumByArtist(String artist) {
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
    	for (Album album : albums) {
				if (album.getArtist().equals(artist)) {
					albumsCopy.add(album);
				}
			}
        return albumsCopy;
	}
	
	public void createPlaylist(String title) {
		playlists.add(new Playlist(title));
	}
	
	public boolean[] addSongToPlaylist(String songTitle, String playTitle) {
		boolean songExists = false;
		boolean playExists = false;
		for (Song song : songs) {
			if (song.getTitle().equals(songTitle)) {
				songExists = true;
				for (Playlist playlist : playlists) {
					if (playlist.getName().equals(playTitle)) {
						playlist.addSong(song);
						playExists = true;
					}
				}
			}
		}
		if (playExists == false) {
			for (Playlist playlist : playlists) {
				if (playlist.getName().equals(playTitle)) {
					playExists = true;
				}
			}
		}
		boolean[] exists = {songExists, playExists};
		return (exists);
	}
	
	public boolean[] removeSongFromPlaylist(String songTitle, String playTitle) {
		boolean songExists = false;
		boolean playExists = false;
		for (Playlist playlist : playlists) {
			if (playlist.getName().equals(playTitle)) {
				playExists = true;
				for (Song song : playlist.getSongs()) {
					if (song.getTitle().equals(songTitle)){
						songExists = true;
						playlist.removeSong(song);
					}
				}
			}
		}
		if (songExists == false) {
			for (Song song : songs) {
				if (song.getTitle().equals(songTitle)){
					songExists = true;
				}
			}
		}
		boolean[] exists = {songExists, playExists};
		return (exists);
	}
	
	public boolean favSong(String title) {
		boolean exists = false;
		for (Song song : songs) {
			if (song.getTitle().equals(title)) {
				song.setFavorite();
				exists = true;
			}
		}
		return exists; 
	}
	
	public boolean rateSong(String title, Rating rating) {
		boolean exists = false;
		for (Song song : songs) {
			if (song.getTitle().equals(title)) {
				song.setRating(rating);
				exists = true;
			}
		}
		return exists; 
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	public Set<String> getArtists() {
		Set<String> artists = new HashSet<>();
		for (Song song : songs) {
			artists.add(song.getArtist());
		}
		return artists;
	}
	
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	
	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}
	
	public ArrayList<Song> getFavs() {
		ArrayList<Song> favs = new ArrayList<Song>();
		for (Song song : songs) {
			if (song.getFavorite()) {
				favs.add(song);
			}
		}
		return favs;
	}
}