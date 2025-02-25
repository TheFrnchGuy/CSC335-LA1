import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicStoreTest {
    private MusicStore store;

    @BeforeEach
    public void setUp() {
        store = new MusicStore();
    }

    @Test
    public void testLoadAlbums() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            Album album = store.getAlbum("Test Album");
            assertNotNull(album);
            assertEquals("Test Artist", album.getArtist());
            assertEquals("Rock", album.getGenre());
            assertEquals(2021, album.getYear());
            ArrayList<Song> songs = album.getSongs();
            assertEquals(3, songs.size());
            assertEquals("Song 1", songs.get(0).getTitle());
            assertEquals("Song 2", songs.get(1).getTitle());
            assertEquals("Song 3", songs.get(2).getTitle());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
    
    @Test
    public void testLoadAlbumsBadFormat() {
        try {
        	store.loadAlbums("./albums/test_album_bad.txt");
        	fail("IOException should have been thrown");
        } catch (IOException e) {
        	assertEquals(e.getMessage(), "Invalid album format");
        }
    }
    
    @Test
    public void testLoadAlbumsBadSongFormat() {
        try {
        	store.loadAlbums("./albums/test_album_bad_song.txt");
        	fail("IOException should have been thrown");
        } catch (IOException e) {
        	assertEquals(e.getMessage(), "Invalid album file format");
        }
    }
    
    @Test
    public void testGetAlbum() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            Album album = store.getAlbum("Nonexistent Album");
            assertNull(album);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
   
    @Test
    public void testLoadAlbumInvalidFormat() {
        assertThrows(IOException.class, () -> {
            store.loadAlbums("invalid_format_albums.txt");
        });
    }
}