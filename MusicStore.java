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

    //load multiple albums from a filepath
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

    //load an album from a filepath
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

    //return an album with the given title
    public Album getAlbum(String title) {
        return albums.get(title);
    }

    public static void main(String[] args) {
        MusicStore store = new MusicStore();
        try {
            store.loadAlbums("./albums/albums.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}