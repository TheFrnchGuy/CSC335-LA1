package model;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

	/* INSTANCE VARIABLES */
	private String name;
	private ArrayList<Song> songs;

	/* CONSTRUCTOR */
	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<>();
	}

	/* METHODS */
	public String getName() {
		return name;
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

	public void addSong(Song song) {
		songs.add(song);
	}

	public void removeSong(Song song) {
		Song toBeRemoved = null;
		for (Song songHere : this.songs) {
			if (songHere.compareTo(song)) {
				toBeRemoved = songHere;
			}
		}
		songs.remove(toBeRemoved);
	}

	public void shuffle() {
		Collections.shuffle(songs);
	}
}