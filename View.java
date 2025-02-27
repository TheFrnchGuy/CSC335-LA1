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
		selectionList();
	}

	/* METHODS */
	private static void selectionList() {
		System.out.println("");
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
				model.createPlaylist(scanner.nextLine());
				System.out.println("Playlist created.");
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
				if (model.favSong(scanner.nextLine())) {
					System.out.println("Song favorited.");
				} else {
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
				String title = scanner.nextLine();
				System.out.println("");
				System.out.println("Input the rating of your song (ONE, TWO, THREE, FOUR, or FIVE): ");
				switch (scanner.nextLine().toLowerCase()) {
				case "1":
				case "one":
					exists = model.rateSong(title, Rating.ONE);
					break;
				case "2":
				case "two":
					exists = model.rateSong(title, Rating.TWO);
					break;
				case "3":
				case "three":
					exists = model.rateSong(title, Rating.THREE);
					break;
				case "4":
				case "four":
					exists = model.rateSong(title, Rating.FOUR);
					break;
				case "5":
				case "five":
					exists = model.rateSong(title, Rating.FIVE);
					break;
				default:
					System.out.println("Invalid Input");
					System.out.println("");
					selectionList();
					break;
				}
				if (exists) {
					System.out.println("Song rated.");
				} else {
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
		} catch (NumberFormatException e) {
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
			ArrayList<String> returnedPrints;
			String title;
			String artist;
			switch (selectedOption) {
			case 1:
				// Search for a song by title
				System.out.println("");
				System.out.println("Input title:");
				title = scanner.nextLine();
				returnedPrints = model.music.songByTitle(title);
				songPrintHelper(returnedPrints);
				break;
			case 2:
				// Search for a song by artist
				System.out.println("");
				System.out.println("Input artist:");
				artist = scanner.nextLine();
				returnedPrints = model.music.songByArtist(artist);
				songPrintHelper(returnedPrints);
				break;
			case 3:
				// Search for an album by title
				System.out.println("");
				System.out.println("Input title:");
				title = scanner.nextLine();
				returnedPrints = model.music.albumByTitle(title);
				albumPrintHelper(returnedPrints);
				break;
			case 4:
				// Search for an album by artist
				System.out.println("");
				System.out.println("Input artist:");
				artist = scanner.nextLine();
				returnedPrints = model.music.albumByArtist(artist);
				albumPrintHelper(returnedPrints);
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
		} catch (NumberFormatException e) {
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
			ArrayList<String> returnedPrints;
			String title;
			String artist;
			switch (selectedOption) {
			case 1:
				// Search for a song by title
				System.out.println("");
				System.out.println("Input title:");
				title = scanner.nextLine();
				returnedPrints = model.songByTitle(title);
				songPrintHelper(returnedPrints);
				break;
			case 2:
				// Search for a song by artist
				System.out.println("");
				System.out.println("Input artist:");
				artist = scanner.nextLine();
				returnedPrints = model.songByArtist(artist);
				songPrintHelper(returnedPrints);
				break;
			case 3:
				// Search for an album by title
				System.out.println("");
				System.out.println("Input title:");
				title = scanner.nextLine();
				returnedPrints = model.albumByTitle(title);
				albumPrintHelper(returnedPrints);
			case 4:
				// Search for an album by artist
				System.out.println("");
				System.out.println("Input artist:");
				artist = scanner.nextLine();
				returnedPrints = model.albumByArtist(artist);
				albumPrintHelper(returnedPrints);
				break;
			case 5:
				// Search for a playlist by name
				System.out.println("");
				System.out.println("Input title:");
				title = scanner.nextLine();
				returnedPrints = model.playlistByTitle(title);
				playlistPrintHelper(returnedPrints);
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
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input");
			System.out.println("");
			userLibrarySearch();
		}
	}

	private static void songPrintHelper(ArrayList<String> returnedPrints) {
		if (returnedPrints.size() == 0) {
			System.out.println("Song not found");
			System.out.println("");
			musicStoreSearch();
		} else {
			for (String song : returnedPrints) {
				System.out.println(song);
			}
			System.out.println("");
			selectionList();
		}
	}

	private static void albumPrintHelper(ArrayList<String> returnedPrints) {
		if (returnedPrints.size() == 0) {
			System.out.println("Album not found");
			System.out.println("");
			musicStoreSearch();
		} else {
			for (String albumDetails : returnedPrints) {
				System.out.println(albumDetails);
			}
			System.out.println("");
			selectionList();
		}
	}

	private static void playlistPrintHelper(ArrayList<String> returnedPrints) {
		if (returnedPrints.size() == 0) {
			System.out.println("Playlist not found");
			System.out.println("");
			musicStoreSearch();
		} else {
			for (String playlistDetails : returnedPrints) {
				System.out.println(playlistDetails);
			}
			System.out.println("");
			selectionList();
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
			switch (selectedOption) {
			case 1:
				// Add a song to the library
				System.out.println("");
				System.out.println("Input title of song:");
				title = scanner.nextLine();
				if (model.addSong(title)) {
					System.out.println("Added song");
				} else {
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
				if (model.addAlbum(title)) {
					System.out.println("Added album");
				} else {
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
		} catch (NumberFormatException e) {
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
		} catch (NumberFormatException e) {
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
				} else {
					System.out.println("");
					if (exists[0] == false && exists[1] == true) {
						System.out.println("Song is not in library.");
					} else if (exists[0] == true && exists[1] == false) {
						System.out.println("Playlist is not in library.");
					} else {
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
				} else {
					System.out.println("");
					if (exists[0] == false && exists[1] == true) {
						System.out.println("Song is not in playlist.");
					} else if (exists[0] == true && exists[1] == false) {
						System.out.println("Playlist is not in library");
					} else {
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
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input");
			System.out.println("");
			addRemovePlaylistSongs();
		}
	}
}