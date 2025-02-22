import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                if (parts.length == 2) {
                    String albumTitle = parts[0];
                    String artist = parts[1];
                    String albumFilePath = albumTitle + "_" + artist + ".txt";
                    Album album = loadAlbum(albumFilePath);
                    albums.put(albumTitle, album);
                }
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

            List<String> songs = new ArrayList<>();
            String song;
            while ((song = reader.readLine()) != null) {
                songs.add(song);
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
            store.loadAlbums("albums.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}