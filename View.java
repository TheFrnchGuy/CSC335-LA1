import java.util.ArrayList;
import java.util.Scanner;

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
           			musicStoreSearch();
           			break;
           		case 2:
           			userLibrarySearch();
           			break;
           		case 3:
           			addToLibrary();
           			break;
           		case 4:
           			getItemsFromLibrary();
           			break;
           		case 5:
           			break;
           		case 6:
           			addRemovePlaylistSongs();
           			break;
           		case 7:
           			break;
           		case 8:
           			break;
           		case 9:
           			System.exit(0);
           		default:
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
            ArrayList<String> results;
            String title;
            String artist;
            switch (selectedOption) {
           		case 1:
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			results = model.songByTitle(title);
           			if (results.size() == 0) {
           				System.out.println("Invalid Input");
           				musicStoreSearch();
           			}
           			else {
           				System.out.println("Song: " + results.get(0) + ", Artist: " + results.get(1) + ", Album: " + results.get(2));
           			}
           			break;
           		case 2:
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			results = model.songByArtist(artist);
           			if (results.size() == 0) {
           				System.out.println("Invalid Input");
           				musicStoreSearch();
           			}
           			else {
           				System.out.println("Song: " + results.get(0) + ", Artist: " + results.get(1) + ", Album: " + results.get(2));
           			}
           			break;
           		case 3:
           			System.out.println("");
           			System.out.println("Input title:");
           			title = scanner.nextLine();
           			results = model.albumByTitle(title);
           			if (results.size() == 0) {
           				System.out.println("Invalid Input");
           				musicStoreSearch();
           			}
           			else {
           				// print out album details and then a song list
           			}
           			break;
           		case 4:
           			System.out.println("");
           			System.out.println("Input artist:");
           			artist = scanner.nextLine();
           			results = model.albumByArtist(artist);
           			if (results.size() == 0) {
           				System.out.println("Invalid Input");
           				musicStoreSearch();
           			}
           			else {
           				// print out album details and then a song list
           			}
           			break;
           		case 5:
           			selectionList();
           			break;
           		default:
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
            ArrayList<String> results;
            String title;
            String artist;
            switch (selectedOption) {
           		case 1:
           			break;
           		case 2:
           			break;
           		case 3:
           			break;
           		case 4:
           			break;
           		case 5:
           			break;
           		case 6:
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
            switch (selectedOption) {
           		case 1:
           			break;
           		case 2:
           			break;
           		case 3:
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
           			break;
           		case 2:
           			break;
           		case 3:
           			break;
           		case 4:
           			break;
           		case 5:
           			break;
           		case 6:
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
    	try {
            int selectedOption = Integer.parseInt(givenInput);
            switch (selectedOption) {
           		case 1:
           			break;
           		case 2:
           			break;
           		case 3:
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