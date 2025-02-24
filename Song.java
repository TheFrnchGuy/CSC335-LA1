public class Song {
     
    class Song {
        /* INSTANCE VARIABLES */
        private String title;
        private String artist;
        private Rating rating;
        private Boolean favorite;
    
        /* CONSTRUCTOR */
        public Song(String title, String artist, Rating rating) {
            this.title = title;
            this.artist = artist;
            this.rating = rating;
            this.favorite = false;
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
        
        public Boolean getFavorite() {
        	return favorite;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
            if (rating == Rating.FIVE) {
                this.favorite = true;
            }
        }
    }

}