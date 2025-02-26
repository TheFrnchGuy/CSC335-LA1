import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class View {
 
    /* INSTANCE VARIABLES */
	private static LibraryModel model = new LibraryModel();
	private static Scanner scanner = new Scanner(System.in);

    /* CONSTRUCTOR */
    public View() {
    	System.out.println("Welcome to Large Assignment 1");
    	System.out.println("by Paris Garcia and John Schmitt");
    	System.out.println("");
    	selectionList();
    }

    /* METHODS */
    private static void selectionList() {
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Search for information from the music store");
    	System.out.println("[2] Search for information from the user library");
    	System.out.println("[3] Add something to the library");
    	System.out.println("[4] Get a list of items from the library");
    	System.out.println("[5] Create a playlist");
    	System.out.println("[6] Add/remove songs from a playlist");
    	System.out.println("[7] Mark a song as favorite");
    	System.out.println("[8] Rate a song");
    	System.out.println("[9] Quit program");
    	
    	String givenInput = scanner.nextLine();
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            String title;
            switch (selectedOption) {
           		case 1:
           			// Search for information from the music store
           			musicStoreSearch();
           			break;
           		case 2:
           			// Search for information from the user library
           			userLibrarySearch();
           			break;
           		case 3:
           			// Add something to the library
           			addToLibrary();
           			break;
           		case 4:
           			// Get a list of items from the library
           			getItemsFromLibrary();
           			break;
           		case 5:
           			// Create a playlist
           			System.out.println("");
           			System.out.println("Input a title for your playlist: ");
           			title = scanner.nextLine();
           			model.createPlaylist(title);
           			System.out.println("Playlist created.");
           			System.out.println("");
           			selectionList();
           			break;
           		case 6:
           			// Add/remove songs from a playlist
           			addRemovePlaylistSongs();
           			break;
           		case 7:
           			// Mark a song as favorite
           			System.out.println("");
           			System.out.println("Input the title of your song: ");
           			title = scanner.nextLine();
           			if (model.favSong(title)) {
           				System.out.println("Song favorited.");
           			}
           			else {
           				System.out.println("Song not in library.");
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 8:
           			// Rate a song
           			boolean exists = false;
           			System.out.println("");
           			System.out.println("Input the title of your song: ");
           			title = scanner.nextLine();
           			System.out.println("");
           			System.out.println("Input the rating of your song (ONE, TWO, THREE, FOUR, or FIVE): ");
           			switch (scanner.nextLine()) {
           				case "ONE":
           					exists = model.rateSong(title,Rating.ONE);
           					break;
           				case "TWO":
           					exists = model.rateSong(title,Rating.TWO);
           					break;
           				case "THREE":
           					exists = model.rateSong(title,Rating.THREE);
           					break;
           				case "FOUR":
           					exists = model.rateSong(title,Rating.FOUR);
           					break;
           				case "FIVE":
           					exists = model.rateSong(title,Rating.FIVE);
           					break;
           				default:
           					System.out.println("Invalid Input");
                   			System.out.println("");
                   			selectionList();
                   			break;
           			}
           			if (exists) {
           				System.out.println("Song rated.");
           			}
           			else {
           				System.out.println("Song not in library.");
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 9:
           			// Quit program
           			System.out.println("Program Terminated.");
           			scanner.close();
           			System.exit(0);
           		default:
           			// Input detection
           			System.out.println("Invalid Input");
           			System.out.println("");
           			selectionList();
           			break;
            	}
    		} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            selectionList();
        	}
    }
    
    private static void musicStoreSearch() {
    	System.out.println("");
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Search for a song by title");
    	System.out.println("[2] Search for a song by artist");
    	System.out.println("[3] Search for an album by title");
    	System.out.println("[4] Search for an album by artist");
    	System.out.println("[5] Back");
    	
    	String givenInput = scanner.nextLine();
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            ArrayList<Album> resultA;
            ArrayList<Song> resultS;
            String title;
            String artist;
            switch (selectedOption) {
           		case 1:
           			// Search for a song by title
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			resultS = new ArrayList<Song>();
           			resultA = new ArrayList<Album>();
           			for (Album album : model.music.getAlbums()) {
           				for (Song song : album.getSongs()) {
           					if(song.getTitle().equals(title)) {
           						resultS.add(song);
           						resultA.add(album);
           					}
           				}
           			}
           			if (resultS.size() == 0) {
       					System.out.println("Song not found");
           				System.out.println("");
           				musicStoreSearch();
       				}
       				else {
       					int pos = 0;
       					for (Song song : resultS) {
       						System.out.println(("Title: " + song.getTitle() + ", Arist: " + song.getArtist() + ", Album: " +
   									resultA.get(pos).getTitle()));
       						pos++;
       					}
       					System.out.println("");
           				selectionList();
       				}
           			break;
           		case 2:
           			// Search for a song by artist
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			resultS = new ArrayList<Song>();
           			resultA = new ArrayList<Album>();
           			for (Album album : model.music.getAlbums()) {
           				for (Song song : album.getSongs()) {
           					if(song.getArtist().equals(artist)) {
           						resultS.add(song);
           						resultA.add(album);
           					}
           				}
           			}
           			if (resultS.size() == 0) {
       					System.out.println("Song not found");
           				System.out.println("");
           				musicStoreSearch();
       				}
       				else {
       					int pos = 0;
       					for (Song song : resultS) {
       						System.out.println(("Title: " + song.getTitle() + ", Arist: " + song.getArtist() + ", Album: " +
   									resultA.get(pos).getTitle()));
       						pos++;
       					}
       					System.out.println("");
           				selectionList();
       				}
           			break;
           		case 3:
           			// Search for an album by title
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			resultA = model.music.getAlbumByTitle(title);
           			if (resultA.size() == 0) {
           				System.out.println("Album not found");
           				System.out.println("");
           				musicStoreSearch();
           			}
           			else {
           				for (Album album : resultA) {
           					System.out.println("Title: " + album.getTitle() + ", Arist: " + album.getArtist() + ", Genre: " +
   									album.getGenre() + ", Year: " + album.getYear());
           					System.out.println("");
               				System.out.println("Song List:");
               				for (Song song : album.getSongs()) {
               					System.out.println(song.getTitle());
               				}
               				System.out.println("");
           				}
           				System.out.println("");
           				selectionList();
           			}
           			break;
           		case 4:
           			// Search for an album by artist
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			resultA = model.music.getAlbumByArtist(artist);
           			if (resultA.size() == 0) {
           				System.out.println("Album not found");
           				System.out.println("");
           				musicStoreSearch();
           			}
           			else {
           				for (Album album : resultA) {
           					System.out.println("Title: " + album.getTitle() + ", Arist: " + album.getArtist() + ", Genre: " +
   									album.getGenre() + ", Year: " + album.getYear());
           					System.out.println("");
               				System.out.println("Song List:");
               				for (Song song : album.getSongs()) {
               					System.out.println(song.getTitle());
               				}
               				System.out.println("");
           				}
           				System.out.println("");
           				selectionList();
           			}
           			break;
           		case 5:
           			// Back
           			System.out.println("");
           			selectionList();
           			break;
           		default:
           			// Input detection
           			System.out.println("Invalid Input");
           			System.out.println("");
           			musicStoreSearch();
           			break;
            	}
        	} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            musicStoreSearch();
        	}
    }
    
    private static void userLibrarySearch() {
    	System.out.println("");
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Search for a song by title");
    	System.out.println("[2] Search for a song by artist");
    	System.out.println("[3] Search for an album by title");
    	System.out.println("[4] Search for an album by artist");
    	System.out.println("[5] Search for a playlist by name");
    	System.out.println("[6] Back");
    	
    	String givenInput = scanner.nextLine();
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            ArrayList<Album> resultA;
            ArrayList<Song> resultS;
            String title;
            String artist;
            switch (selectedOption) {
           		case 1:
           			// Search for a song by title
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			resultS = new ArrayList<Song>();
           			resultA = new ArrayList<Album>();
           			for (Album album : model.getAlbums()) {
           				for (Song song : album.getSongs()) {
           					if(song.getTitle().equals(title)) {
           						resultS.add(song);
           						resultA.add(album);
           					}
           				}
           			}
           			// checks to see if the song exists without an album tied to it
           			if (resultS.size() == 0) {
           				for (Song song : model.getSongs()) {
           					if(song.getTitle().equals(title)) {
           						resultS.add(song);
           						resultA.add(new Album("NOT_IN_ALBUM", "NULL", "NULL", 0, new ArrayList<Song>()));
           					}
           				}
       				}
           			// now if the size is 0, it's confirmed the song doesn't exist
           			if (resultS.size() == 0) {
       					System.out.println("Song not found");
           				System.out.println("");
           				userLibrarySearch();
       				}
       				else {
       					int pos = 0;
       					for (Song song : resultS) {
       						System.out.println(("Title: " + song.getTitle() + ", Arist: " + song.getArtist() + ", Album: " +
   									resultA.get(pos).getTitle()));
       						pos++;
       					}
       					System.out.println("");
           				selectionList();
       				}
           			break;
           		case 2:
           			// Search for a song by artist
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			resultS = new ArrayList<Song>();
           			resultA = new ArrayList<Album>();
           			for (Album album : model.getAlbums()) {
           				for (Song song : album.getSongs()) {
           					if(song.getArtist().equals(artist)) {
           						resultS.add(song);
           						resultA.add(album);
           					}
           				}
           			}
           			// checks to see if the song exists without an album tied to it
           			if (resultS.size() == 0) {
           				for (Song song : model.getSongs()) {
           					if(song.getArtist().equals(artist)) {
           						resultS.add(song);
           						resultA.add(new Album("NOT_IN_ALBUM", "NULL", "NULL", 0, new ArrayList<Song>()));
           					}
           				}
       				}
           			// now if the size is 0, it's confirmed the song doesn't exist
           			if (resultS.size() == 0) {
       					System.out.println("Song not found");
           				System.out.println("");
           				userLibrarySearch();
       				}
       				else {
       					int pos = 0;
       					for (Song song : resultS) {
       						System.out.println(("Title: " + song.getTitle() + ", Arist: " + song.getArtist() + ", Album: " +
   									resultA.get(pos).getTitle()));
       						pos++;
       					}
       					System.out.println("");
           				selectionList();
       				}
           			break;
           		case 3:
           			// Search for an album by title
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			resultA = model.albumByTitle(title);
           			if (resultA.size() == 0) {
           				System.out.println("Album not found");
           				System.out.println("");
           				userLibrarySearch();
           			}
           			else {
           				for (Album album : resultA) {
           					System.out.println("Title: " + album.getTitle() + ", Arist: " + album.getArtist() + ", Genre: " +
   									album.getGenre() + ", Year: " + album.getYear());
           					System.out.println("");
               				System.out.println("Song List:");
               				for (Song song : album.getSongs()) {
               					System.out.println(song.getTitle());
               				}
               				System.out.println("");
           				}
           				System.out.println("");
           				selectionList();
           			}
           			break;
           		case 4:
           			// Search for an album by artist
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			resultA = model.albumByArtist(artist);
           			if (resultA.size() == 0) {
           				System.out.println("Album not found");
           				System.out.println("");
           				userLibrarySearch();
           			}
           			else {
           				for (Album album : resultA) {
           					System.out.println("Title: " + album.getTitle() + ", Arist: " + album.getArtist() + ", Genre: " +
   									album.getGenre() + ", Year: " + album.getYear());
           					System.out.println("");
               				System.out.println("Song List:");
               				for (Song song : album.getSongs()) {
               					System.out.println(song.getTitle());
               				}
               				System.out.println("");
           				}
           				System.out.println("");
           				selectionList();
           			}
           			break;
           		case 5:
           			// Search for an album by artist
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			boolean exists = false;
           			for (Playlist playlist : model.getPlaylists()) {
           				if (playlist.getName().equals(title)) {
           					System.out.println("Playlist: " + title);
           					System.out.println("");
           					System.out.println("Song List:");
           					exists = true;
           					for (Song song : playlist.getSongs()) {
           						System.out.println("Title: " + song.getTitle() + ", Artist: " + song.getArtist());
           					}
           				}
           			}
           			if (exists) {
           				System.out.println("");
               			selectionList();
           			}
           			else {
           				System.out.println("");
           				System.out.println("Playlist not found");
           				System.out.println("");
           				userLibrarySearch();
           			}
           			break;
           		case 6:
           			System.out.println("");
           			selectionList();
           			break;
           		default:
           			System.out.println("Invalid Input");
           			System.out.println("");
           			userLibrarySearch();
           			break;
            	}
        	} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            userLibrarySearch();
        	}
    }
    
    private static void addToLibrary() {
    	System.out.println("");
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Add a song to the library");
    	System.out.println("[2] Add an album to the library");
    	System.out.println("[3] Back");
    	
    	String givenInput = scanner.nextLine();
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            String title;
            Boolean added;
            switch (selectedOption) {
           		case 1:
           			// Add a song to the library
           			System.out.println("");
           			System.out.println("Input title of song:");
           			title = scanner.nextLine();
           			added = false;
           			for (Album album : model.music.getAlbums()) {
           				for (Song song : album.getSongs()) {
           					if(song.getTitle().equals(title)) {
           						model.addSong(song);
           						added = true;
           					}
           				}
           			}
           			if (added == true) {
           				System.out.println("Added song");
           			}
           			else {
           				System.out.println("Couldn't find song");
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 2:
           			// Add an album to the library
           			System.out.println("");
           			System.out.println("Input title of album:");
           			title = scanner.nextLine();
           			added = false;
           			for (Album album : model.music.getAlbums()) {
           				if(album.getTitle().equals(title)) {
           					model.addAlbum(album);
           					added = true;
           				}
           			}
           			if (added == true) {
           				System.out.println("Added album");
           			}
           			else {
           				System.out.println("Couldn't find album");
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 3:
           			System.out.println("");
           			selectionList();
           			break;
           		default:
           			System.out.println("Invalid Input");
           			System.out.println("");
           			addToLibrary();
           			break;
            	}
        	} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            addToLibrary();
        	}
    }
    
    private static void getItemsFromLibrary() {
    	System.out.println("");
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Obtain a list of song titles");
    	System.out.println("[2] Obtain a list of artists");
    	System.out.println("[3] Obtain a list of albums");
    	System.out.println("[4] Obtain a list of playlists");
    	System.out.println("[5] Obtain a list of favorited songs");
    	System.out.println("[6] Back");
    	
    	String givenInput = scanner.nextLine();
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            switch (selectedOption) {
           		case 1:
           			// Obtain a list of song titles
           			System.out.println("");
           			System.out.println("Song List:");
           			for (Song song : model.getSongs()) {
           				System.out.println(song.getTitle());
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 2:
           			// Obtain a list of artists
           			System.out.println("");
           			System.out.println("Artist List:");
           			Set<String> artists = model.getArtists();
           			for (String artist : artists) {
           				System.out.println(artist);
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 3:
           			// Obtain a list of albums
           			System.out.println("");
           			System.out.println("Album List:");
           			for (Album album : model.getAlbums()) {
           				System.out.println(album.getTitle());
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 4:
           			// Obtain a list of playlists
           			System.out.println("");
           			System.out.println("Playlist List:");
           			for (Playlist playlist : model.getPlaylists()) {
           				System.out.println(playlist.getName());
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 5:
           			// Obtain a list of favorited songs
           			System.out.println("");
           			System.out.println("Favorite Songs List:");
           			for (Song song : model.getFavs()) {
           				System.out.println(song.getTitle());
           			}
           			System.out.println("");
           			selectionList();
           			break;
           		case 6:
           			System.out.println("");
           			selectionList();
           			break;
           		default:
           			System.out.println("Invalid Input");
           			System.out.println("");
           			getItemsFromLibrary();
           			break;
            	}
        	} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            getItemsFromLibrary();
        	}
    }
    
    private static void addRemovePlaylistSongs() {
    	System.out.println("");
    	System.out.println("Choose one of the following options by typing the number next to it:");
    	System.out.println("[1] Add a song to a playlist");
    	System.out.println("[2] Remove a song from a playlist");
    	System.out.println("[3] Back");
    	
    	String givenInput = scanner.nextLine();
    	String songTitle;
    	String playTitle;
    	boolean[] exists;
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            switch (selectedOption) {
           		case 1:
           			// Add a song to a playlist
           			System.out.println("");
           			System.out.println("Input the title of the playlist:");
           			playTitle = scanner.nextLine();
           			System.out.println("");
           			System.out.println("Input the title of the song:");
           			songTitle = scanner.nextLine();
           			exists = model.addSongToPlaylist(songTitle, playTitle);
           			if (exists[0] && exists[1]) {
           				System.out.println("");
               			System.out.println("Song has been added to playlist.");
               			System.out.println("");
               			selectionList();
           			}
           			else {
           				System.out.println("");
           				if (exists[0] == false && exists[1] == true) {
           					System.out.println("Song is not in library.");
           				}
           				else if (exists[0] == true && exists[1] == false) {
           					System.out.println("Playlist is not in library.");
           				}
           				else {
           					System.out.println("Song and playlist are not in library.");
           				}
               			addRemovePlaylistSongs();
           			}
           			break;
           		case 2:
           			// Remove a song from a playlist
           			System.out.println("");
           			System.out.println("Input the title of the playlist:");
           			playTitle = scanner.nextLine();
           			System.out.println("");
           			System.out.println("Input the title of the song");
           			songTitle = scanner.nextLine();
           			exists = model.removeSongFromPlaylist(songTitle, playTitle);
           			if (exists[0] && exists[1]) {
           				System.out.println("");
               			System.out.println("Song has been removed from playlist.");
               			System.out.println("");
               			selectionList();
           			}
           			else {
           				System.out.println("");
           				if (exists[0] == false && exists[1] == true) {
           					System.out.println("Song is not in playlist.");
           				}
           				else if (exists[0] == true && exists[1] == false) {
           					System.out.println("Playlist is not in library");
           				}
           				else{
           					System.out.println("Playlist and song are not in library.");
           				}
               			addRemovePlaylistSongs();
           			}
           			break;
           		case 3:
           			System.out.println("");
           			selectionList();
           			break;
           		default:
           			System.out.println("Invalid Input");
           			System.out.println("");
           			addRemovePlaylistSongs();
           			break;
            	}
        	} 
    	catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            addRemovePlaylistSongs();
        	}
    }
}