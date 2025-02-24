import java.util.ArrayList;

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
        ArrayList<Song> songs = new ArrayList<>();
        for (Song song : this.songs) {
            songs.add(song);
        }
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }
}