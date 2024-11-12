import javax.swing.*;

// Class ini adalah class yang digunakan untuk menyimpan detail dari movie atau series karena isinya sebenarnya sama saja
public class Movie {
    // Atribut atribut yang ada di sini modifiernya private jadi kalo mau diakses harus melalui setter dan getter. Bisa diset melalui konstruktor juga sebenarnya.
    private String title;
    private String genre;
    private int year;
    private String rating;
    private String director;
    private String description;
    private ImageIcon icon;
    private String ImdbID;

    // Method di bawah ini adalah konstruktor yang digunakan untuk menset semua atribut yang ada di class ini selain atribut icon.
    // icon di sini tidak saya set dengan konstruktor, tapi dengan setter.
    public Movie(String title, String genre, int year, String rating, String director, String description, String imdbID) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.description = description;
        this.ImdbID = imdbID;
    }

    // Method getTitle ini digunakan untuk mengambil title dari objek movie jika diakses dari objek class lainnya.
    public String getTitle() {
        return title;
    }

    // Method getGenre ini digunakan untuk mengambil genre dari objek movie jika diakses dari objek class lainnya.
    public String getGenre() {
        return genre;
    }

    // Method getYear ini digunakan untuk mengambil year dari objek movie jika diakses dari objek class lainnya.
    public int getYear() {
        return year;
    }

    // Method getRating ini digunakan untuk mengambil rating dari objek movie jika diakses dari objek class lainnya.
    public String getRating() {
        return rating;
    }

    // Method getDirector ini digunakan untuk mengambil director dari objek movie jika diakses dari objek class lainnya.
    public String getDirector() {
        return director;
    }

    // Method getDescription ini digunakan untuk mengambil description dari objek movie jika diakses dari objek class lainnya.
    public String getDescription() {
        return description;
    }

    // Method getIcon ini digunakan untuk mengambil icon dari objek movie jika diakses dari objek class lainnya.
    public ImageIcon getIcon() {
        return icon;
    }

    // Method getImdbID ini digunakan untuk mengambil imdbID dari objek movie jika diakses dari objek class lainnya.
    public String getImdbID() {
        return this.ImdbID;
    }

    // Method setIcon ini digunakan untuk mengatur atau menset icon dari objek movie jika diakses dari objek class lainnya.
    public void setIcon(ImageIcon i) {
        this.icon = i;
    }
}
