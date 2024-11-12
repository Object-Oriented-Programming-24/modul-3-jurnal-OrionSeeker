import javax.swing.*;

public class Movie {
    private String title;
    private String genre;
    private int year;
    private String rating;
    private String director;
    private String description;
    private ImageIcon icon;
    private String ImdbID;

    public Movie(String title, String genre, int year, String rating, String director, String description, String imdbID) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.description = description;
        this.ImdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getImdbID() {
        return this.ImdbID;
    }

    public void setIcon(ImageIcon i) {
        this.icon = i;
    }
}
