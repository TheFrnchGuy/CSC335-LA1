public class LibraryModel {

    class Album {
        /* INSTANCE VARIABLES */
        private String title;
        private String artist;
        private String genre;
        private int year;
        private List<String> songs;
    
        /* CONSTRUCTOR */
        public Album(String title, String artist, String genre, int year, List<String> songs) {
            this.title = title;
            this.artist = artist;
            this.genre = genre;
            this.year = year;
            this.songs = songs;
        }
    
        /* METHODS */

        public String getTitle() {
            return title;
        }
    
        public String getArtist() {
            return artist;
        }
    
        public String getGenre() {
            return genre;
        }
    
        public int getYear() {
            return year;
        }
    
        public List<String> getSongs() {
            ArrayList<String> songs = new ArrayList<>();
            for (String song : this.songs) {
                songs.add(song);
            }
            return songs;
        }
    }
    
    class Song {
        /* INSTANCE VARIABLES */
        private String title;
        private String artist;
        private Rating rating;
    
        /* CONSTRUCTOR */
        public Song(String title, String artist, Rating rating) {
            this.title = title;
            this.artist = artist;
            this.rating = rating;
        }
    
        /* METHODS */

        public String getTitle() {
            return title;
        }
    
        public String getArtist() {
            return artist;
        }
    
        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }
    }

    class Playlist {
        /* INSTANCE VARIABLES */
        private String name;
        private List<Song> songs;
    
        /* CONSTRUCTOR */
        public Playlist(String name) {
            this.name = name;
            this.songs = new ArrayList<>();
        }
    
        /* METHODS */

        public String getName() {
            return name;
        }
    
        public List<Song> getSongs() {
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
}