package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.MusicStore;
import model.Song;

import java.io.IOException;
import java.util.ArrayList;


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
            ArrayList<Album> album = store.getAlbums();
            Album test_album = album.get(0);
            assertNotNull(album);
            assertEquals("Test Artist", test_album.getArtist());
            assertEquals("Rock", test_album.getGenre());
            assertEquals(2021, test_album.getYear());
            ArrayList<Song> songs = test_album.getSongs();
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
    public void testLoadAlbumInvalidFormat() {
        assertThrows(IOException.class, () -> {
            store.loadAlbums("invalid_format_albums.txt");
        });
    }

    @Test
    public void testSongByTitle() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            ArrayList<String> songs = store.songByTitle("Song 1");
            assertNotNull(songs);
            assertEquals(1, songs.size());
            assertTrue(songs.get(0).contains("Song 1"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testSongByArtist() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            ArrayList<String> songs = store.songByArtist("Test Artist");
            assertNotNull(songs);
            assertEquals(3, songs.size());
            assertTrue(songs.get(0).contains("Test Artist"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testSongsByGenre() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            ArrayList<String> songs = store.songByGenre("Rock");
            assertNotNull(songs);
            assertEquals(3, songs.size());
            assertTrue(songs.get(0).contains("Song 1"));
            assertTrue(songs.get(1).contains("Song 2"));
            assertTrue(songs.get(2).contains("Song 3"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testAlbumByTitle() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            ArrayList<String> albums = store.albumByTitle("Test Album");
            assertNotNull(albums);
            assertTrue(albums.get(0).contains("Test Album"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testAlbumByArtist() {
        try {
            store.loadAlbums("./albums/test_albums.txt");
            ArrayList<String> albums = store.albumByArtist("Test Artist");
            assertNotNull(albums);
            assertTrue(albums.get(0).contains("Test Artist"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}