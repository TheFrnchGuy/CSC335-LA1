package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Rating;
import model.Song;

import java.util.ArrayList;
import java.util.Set;


public class LibraryModelTests {
    private LibraryModel library;

    @BeforeEach
    public void setUp() {
        library = new LibraryModel();
    }

    @Test
    public void testPlaySong() {
        library.addSong("Amen", "Leonard Cohen");
        library.playSong("Amen", "Leonard Cohen");
        ArrayList<Song> songs = library.getSongs();
        assertEquals(1, songs.get(0).getPlays());
    }

    @Test
    public void testSongByTitle() {
        library.addSong("Amen", "Leonard Cohen");
        ArrayList<String> songs = library.songByTitle("Amen");
        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertTrue(songs.get(0).contains("Amen"));
    }
    @Test
    public void testSongByTitleFromAlbum() {
        library.addAlbum("Old Ideas", "Leonard Cohen");
        ArrayList<String> songs = library.songByTitle("Amen");
        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertTrue(songs.get(0).contains("Amen"));
    }

    @Test
    public void testSongByArtist() {
        library.addSong("Amen", "Leonard Cohen");
        library.addSong("Banjo", "Leonard Cohen");
        ArrayList<String> songs = library.songByArtist("Leonard Cohen");
        assertNotNull(songs);
        assertEquals(2, songs.size());
        assertTrue(songs.get(0).contains("Leonard Cohen"));
    }

    @Test
    public void testSongByArtistFromAlbum() {
    	library.addAlbum("Old Ideas", "Leonard Cohen");
        ArrayList<String> songs = library.songByArtist("Leonard Cohen");
        assertNotNull(songs);
        assertEquals(10, songs.size());
        assertTrue(songs.get(0).contains("Leonard Cohen"));
    }
    
    @Test
    public void testAlbumByTitle() {
        library.addAlbum("19", "Adele");
        ArrayList<String> albums = library.albumByTitle("19");
        assertNotNull(albums);
        assertTrue(albums.get(0).contains("19"));
    }

    @Test
    public void testAlbumByArtist() {
        library.addAlbum("19", "Adele");
        ArrayList<String> albums = library.albumByArtist("Adele");
        assertNotNull(albums);
        assertTrue(albums.get(0).contains("Adele"));
    }

    @Test
    public void testAddSong() {
        boolean added = library.addSong("Uh Oh", "Norah Jones");
        assertTrue(added);
        ArrayList<Song> songs = library.getSongs();
        assertEquals(1, songs.size());
        assertEquals("Uh Oh", songs.get(0).getTitle());
    }

    @Test
    public void testAddAlbum() {
        boolean added = library.addAlbum("Begin Again", "Norah Jones");
        assertTrue(added);
        ArrayList<Album> albums = library.getAlbums();
        assertEquals(1, albums.size());
        assertEquals("Begin Again", albums.get(0).getTitle());
    }

    @Test
    public void testCreatePlaylist() {
        library.createPlaylist("Playlist 1");
        ArrayList<String> playlists = library.playlistByTitle("Playlist 1");
        assertNotNull(playlists);
        assertTrue(playlists.get(0).contains("Playlist 1"));
    }

    @Test
    public void testAddSongToPlaylist() {
        library.addSong("Anyhow", "Leonard Cohen");
        library.createPlaylist("Playlist 1");
        boolean[] result = library.addSongToPlaylist("Anyhow", "Playlist 1", "Leonard Cohen");
        assertTrue(result[0]);
        assertTrue(result[1]);
    }

    @Test
    public void testRemoveSongFromPlaylist() {
        library.addSong("Anyhow", "Leonard Cohen");
        library.createPlaylist("Playlist 1");
        library.addSongToPlaylist("Anyhow", "Playlist 1", "Leonard Cohen");
        boolean[] result = library.removeSongFromPlaylist("Anyhow", "Playlist 1", "Leonard Cohen");
        assertTrue(result[0]);
        assertTrue(result[1]);
    }

    @Test
    public void testFavSong() {
        library.addSong("Clocks", "Coldplay");
        boolean result = library.favSong("Clocks", "Coldplay");
        assertTrue(result);
        ArrayList<Song> favs = library.getFavs();
        assertEquals(1, favs.size());
        assertEquals("Clocks", favs.get(0).getTitle());
    }

    @Test
    public void testRateSong() {
        library.addSong("Clocks", "Coldplay");
        boolean result = library.rateSong("Clocks", "Coldplay", Rating.THREE);
        assertTrue(result);
        ArrayList<Song> songs = library.getSongs();
        assertEquals(Rating.THREE, songs.get(0).getRating());
    }

    @Test
    public void testRateSongAndFav() {
        library.addSong("Clocks", "Coldplay");
        boolean result = library.rateSong("Clocks", "Coldplay", Rating.FIVE);
        assertTrue(result);
        ArrayList<Song> songs = library.getSongs();
        assertEquals(Rating.FIVE, songs.get(0).getRating());
        ArrayList<Song> favs = library.getFavs();
        assertEquals(1, favs.size());
        assertEquals("Clocks", favs.get(0).getTitle());
    }

    @Test
    public void testGetArtists() {
        library.addSong("It Was You", "Norah Jones");
        library.addSong("Daydreamer", "Adele");
        Set<String> artists = library.getArtists();
        assertEquals(2, artists.size());
        assertTrue(artists.contains("Norah Jones"));
        assertTrue(artists.contains("Adele"));
    }

    @Test
    public void testGetAlbums() {
        library.addAlbum("19", "Adele");
        library.addAlbum("19", "Adele");
        ArrayList<Album> albums = library.getAlbums();
        assertEquals(1, albums.size());
        assertEquals("19", albums.get(0).getTitle());
    }

    @Test
    public void testGetPlaylistsEmpty() {
        library.createPlaylist("Playlist 1");
        ArrayList<Playlist> playlists = library.getPlaylists();
        assertEquals("Playlist 1", playlists.get(2).getName());
    }
    
    @Test
    public void testGetPlaylistsWithSongs() {
    	library.addSong("Anyhow", "Leonard Cohen");
        library.createPlaylist("Playlist 1");
        boolean[] result = library.addSongToPlaylist("Anyhow", "Playlist 1", "Leonard Cohen");
        ArrayList<Playlist> playlists = library.getPlaylists();
        assertTrue(result[0]);
        assertTrue(result[1]);
        assertEquals("Playlist 1", playlists.get(2).getName());
    }

    @Test
    public void testGetFavs() {
        library.addSong("Wintertime", "Norah Jones");
        library.addSong("Wintertime", "Norah Jones");
        library.favSong("Wintertime", "Norah Jones");
        ArrayList<Song> favs = library.getFavs();
        assertEquals(1, favs.size());
        assertEquals("Wintertime", favs.get(0).getTitle());
    }
    
    @Test
    public void testGetFavsEmpty() {
        library.addSong("Wintertime", "Norah Jones");
        ArrayList<Song> favs = library.getFavs();
        assertEquals(0, favs.size());
    }
}