package model;

public class Song {

	/* INSTANCE VARIABLES */
	private String title;
	private String artist;
	private Rating rating;
	private boolean favorite;

	/* CONSTRUCTOR */
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.rating = Rating.NONE;
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

	public boolean getFavorite() {
		return favorite;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
		if (rating == Rating.FIVE) {
			this.favorite = true;
		}
	}

	public void setFavorite() {
		this.favorite = true;
	}

	public boolean compareTo(Song other) {
		boolean same = false;
		if (other.getTitle().equals(this.title)) {
			if (other.getArtist().equals(this.artist)) {
				if (other.getRating() == this.rating) {
					if (other.getFavorite() == this.favorite) {
						same = true;
					}
				}
			}
		}
		return same;
	}
}