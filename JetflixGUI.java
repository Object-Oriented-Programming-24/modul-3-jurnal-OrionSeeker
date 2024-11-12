import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

// Class JetflixGUI ini adalah class utama dari aplikasi JetFlix ini
public class JetflixGUI {

    // variabel integer viewCount yang sifatnya static digunakan untuk menghitung jumlah view count profil yang telah dilakukan. Value awalnya 0, akan bertambah 1 ketika
    // button profil ditekan, di sini static agar menjadi milik class bukan objek (gada buat objek) dan diatur ke private agar hanya bisa diakses dari class ini.
    private static int viewCount = 0;

    // Method main ini adalah method yang dijalankan oleh program ketika program dirun
    public static void main(String[] args) {

        // Kode di bawah adalah pembuatan frame dengan nama Jetflix, ukurannya 1440x900, dan diset ketika diclose akan close aplikasinya
        JFrame frame = new JFrame("JetFlix");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Kode di bawah ini adalah pembuatan layout baru, yaitu CardLayout yang akan digunakan oleh cardPanel. Dibuat juga panel baru, yaitu cardMain dan cardProfil yang akan dimasukkan ke dalam cardPanel.
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        JPanel cardMain = new JPanel();
        JPanel cardProfil = new JPanel();
        
        // Kode di bawah ini adalah kode yang digunakan membuat header, yang sebenarnya adalah ImagePanel (class yang saya buat sebagai panel dengan background gambar).
        // Header di sini memakai layout BorderLayout dan ukurannya 1440x150px
        ImagePanel header = new ImagePanel("./assets/header-background.png");
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(1440, 150));

        // Kode di bawah ini adalah pembuatan panel untuk menyimpan text dari header. Diatur Opaquenya false, jadi transparan, layout yang dipakai adalah BoxLayout dengan
        // arah axis Y, dan diberikan padding 25 px dari atas dan 500 px dari kiri.
        JPanel textHeader = new JPanel();
        textHeader.setOpaque(false);
        textHeader.setLayout(new BoxLayout(textHeader, BoxLayout.Y_AXIS));
        textHeader.setBorder(BorderFactory.createEmptyBorder(25, 500, 0, 0));

        // Kode di bawah ini adalah pembuatan label untuk menyimpan judul utama atau title, yaitu "Jetflix", yang menggunakan font poppins ukurannya 50, dibold, warnanya putih, dan dialign left.
        JLabel titleHeader = new JLabel("Jetflix");
        titleHeader.setFont(new Font("Poppins", Font.BOLD, 50));
        titleHeader.setForeground(Color.WHITE);
        titleHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Kode di bawah ini adalah pembuatan label untuk menyimpan judul sekunder atau subtitle, yaitu "The best streaming service for movies and series" yang fontnya Arial, ukurannya 20, warnanya abu muda, dan dialign left.
        JLabel subtitleHeader = new JLabel("The best streaming service for movies and series");
        subtitleHeader.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitleHeader.setForeground(Color.LIGHT_GRAY);
        subtitleHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Kode di bawah ini digunakan untuk memasukkan titleHeader dan subtitleHeader ke dalam textHeader.
        textHeader.add(titleHeader);
        textHeader.add(subtitleHeader);

        // Kode di bawah ini adalah kode yang digunakan untuk membuat profile button. Awalnya image dibaca dan diresize menjadi 75x75px
        ImageIcon profileIcon = new ImageIcon(new ImageIcon("./assets/button/profile.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        // Button dibuat dengan imageicon image yang sudah dibaca dan diresize sebelumnya.
        JButton profileBtn = new JButton(profileIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                // Jika button ditekan, maka warnanya berubah jadi abu tua
                if (getModel().isArmed()) {
                    g.setColor(Color.DARK_GRAY);
                // Jika button tidak ditekan, maka warnanya abu
                } else {
                    g.setColor(Color.GRAY);
                }
                // Di sini dibuat sebuah lingkaran di posisi (0,38) dan ukurannya 75x75px
                g.fillOval(0, 38, 75, 75);
                // Memanggil fungsi paintComponent dari superclass
                super.paintComponent(g);
            }

            // Mengatur ukuran dari button menjadi 75x75px
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75, 75);
            }
        };
        // profileBtn ini diatur sehingga tidak ada garis kotak ketika tombol ditekan, tidak ada warna background di button, tidak ada terlihat border dari button, dan dibuat transparan.
        profileBtn.setFocusPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorderPainted(false);
        profileBtn.setOpaque(false);

        // Kode di bawah ini membuat panel yang layoutnya BorderLayout, diatur transparan, diberikan padding 20px dari kanan, dan memasukkan profileBtn ke dalamnya di posisi East.
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        profilePanel.add(profileBtn, BorderLayout.EAST);

        // Kode di bawah ini digunakan untuk memasukkan textHeader ke dalam header di posisi Center dan profilePanel di posisi East.
        header.add(textHeader, BorderLayout.CENTER);
        header.add(profilePanel, BorderLayout.EAST);

        // kode di bawah ini digunakan untuk membuat panel bernama movieSeriesPanel yang akan digunakan untuk mengisi panel daftar movie dan panel daftar series, di sini layoutnya menggunakan
        // boxLayout secara Y_AXIS (1 kolom, tapi banyak baris). Warna backgroundnya diatur ke hitam.
        JPanel movieSeriesPanel = new JPanel();
        movieSeriesPanel.setLayout(new BoxLayout(movieSeriesPanel, BoxLayout.Y_AXIS));
        movieSeriesPanel.setBackground(Color.BLACK);

        // Kode di bawah ini digunakan untuk membuat label yang menampung tulisan MOVIES, fontnya poppins, dibold, ukurannya 25pt, warna textnya putih, dibuat align center.
        JLabel moviesLabel = new JLabel("MOVIES");
        moviesLabel.setFont(new Font("Poppins", Font.BOLD, 25));  
        moviesLabel.setForeground(Color.WHITE);
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // movieSeriesPanel diberikan jarak 10px sebelum memasukkan moviesLabel, jadi moviesLabel ini ga mepet atas
        movieSeriesPanel.add(Box.createVerticalStrut(10));
        movieSeriesPanel.add(moviesLabel);

        // Kode di bawah ini adalah arrayList bertipe data Movie yang berisi daftar movie-movie yang mau ditampilkan beserta informasinya
        ArrayList<Movie> daftarMovie = new ArrayList<>();
        daftarMovie.add(new Movie("Kumo no Mukou", "Anime, Drama, Romance, Sci-Fi", 2004, "6.8/10", "Makoto Shinkai", "In an alternate postwar timeline, Japan is divided into the Union-controlled North and the US-controlled South.", "tt0381348"));
        daftarMovie.add(new Movie("Byosoku 5 Centimeter", "Anime, Slice of Life, Drama", 2007, "7.5/10", "Makoto Shinkai",  "Told in three interconnected segments, Takaki tells the story of his life as cruel winters, cold technology, and finally, adult obligations and responsibility converge to test the delicate petals of love.", "tt0983213"));
        daftarMovie.add(new Movie("Kimi no Nawa", "Anime, Drama, Fantasy", 2016, "8.4/10", "Makoto Shinkai",  "Two teenagers share a profound, magical connection upon discovering they are swapping bodies. Things manage to become even more complicated when the boy and girl decide to meet in person.", "tt5311514"));
        daftarMovie.add(new Movie("Tenki no Ko", "Anime, Drama, Fantasy", 2019, "7.5/10", "Makoto Shinkai", "Set during a period of exceptionally rainy weather, high-school boy Hodaka Morishima runs away from his troubled rural home to Tokyo and befriends an orphan girl who can manipulate the weather.", "tt9426210"));
        daftarMovie.add(new Movie("Suzume no Tojimari", "Anime, Action, Adventure", 2022, "7.6/10", "Makoto Shinkai",  "A modern action adventure road story where a 17-year-old girl named Suzume helps a mysterious young man close doors from the other side that are releasing disasters all over in Japan.", "tt16428256"));
        daftarMovie.add(new Movie("Kotonoha no Niwa", "Anime, Slice of Life, Drama, Romance", 2013, "7.4/10", "Makoto Shinkai", "On a rainy morning in Tokyo, 15-year-old Takao, an aspiring shoemaker, decides to skip class to sketch designs in a beautiful garden. This is where he meets Yukari, a beautiful yet mysterious woman. They strike an unlikely friendship.", "tt2591814"));
        

        // Kode di bawah ini digunakan untuk mengatur value dari icon dari objek Movie yang ada di dalam daftarMovie. Jadi bakalan ngelooping dari awal sampe seluruh isi arraylist.
        // title dari movie diambil, kemudian gambarnya diload dengan path assets/movie/ + judul movie + jpg. Gambar yang diload diresize menjadi 200x275px.
        for(int i=0;i<daftarMovie.size();i++){
            String namaMovie = daftarMovie.get(i).getTitle();
            daftarMovie.get(i).setIcon(new ImageIcon(new ImageIcon("./assets/movie/" + namaMovie +".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH)));
        }

        // Kode di bawah ini adalah pembuatan JList dengan isi daftarMovie yang dikonversi ke array
        JList<Movie> movieJList = new JList<>(daftarMovie.toArray(new Movie[0]));

        // Kode di bawah ini digunakan untuk menampilkan apa saja isi dari JList
        movieJList.setCellRenderer(new ListCellRenderer<Movie>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Movie> list, Movie movie, int idx, boolean isSelected, boolean cellHasFocus) {

            // Kode di bawah ini adalah pembuatan panel dengan layout BorderLayout dan ukuran 225x325px, dibuat transparan
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(225, 325));
            panel.setOpaque(false);

            // Kode di bawah ini adalah pemberian padding di kiri 15px, bawah 15px, dan kanan 15px.
            panel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

            // Kode di bawah adalah pembacaan gambar berdasarkan path assets/movie/judul.jpg, gambarnya diresize jadi 200x250px. Kemudian dibuat objek label yang menggunakan ImageIcon ini
            ImageIcon movieIcon = new ImageIcon(new ImageIcon("./assets/movie/" + daftarMovie.get(idx).getTitle() + ".jpg").getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH));
            JLabel movieLabel = new JLabel(movieIcon);

            // Kode di bawah adalah pembuatan panel untuk informasi (nantinya diisi titlelabel dan genrelabel). Di sini layoutnya BoxLayout, secara Y_AXIS (vertical), align center, paddingnya 5px atas dan 5px bawah, dibuat transparan.
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            infoPanel.setOpaque(false);

            // Kode di bawah ini adalah pembuatan label untuk judul movies, fontnya Arial, bold, dan ukurannya 14pt, warna teksnya putih, dan align center.
            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Kode di bawah ini adalah pembuatan label untuk genre movies, fontnya Arial, ukurannya 12pt, warna teksnya abu muda, dan align center.
            JLabel genreLabel = new JLabel(movie.getGenre());
            genreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            genreLabel.setForeground(Color.LIGHT_GRAY);
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Kode di bawah adalah memasukkan titleLabel dan genreLabel ke dalam infoPanel
            infoPanel.add(titleLabel);
            infoPanel.add(genreLabel);

            // Kode di bawah adalah memasukkan movieLabel (gambar) di posisi Center dan infoPanel di posisi South dari panel.
            panel.add(movieLabel, BorderLayout.CENTER);
            panel.add(infoPanel, BorderLayout.SOUTH);

            // Kode di bawah ini digunakan untuk mereturn panel yang sudah diisi oleh list movies
            return panel;
            }
        });

        // Kode di bawah ini digunakan untuk mengatur arah JList menjadi Horizontal dan cuma 1 baris
        movieJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        movieJList.setVisibleRowCount(1);

        // Kode di bawah ini digunakan untuk menangkap inputan klik mouse ketika menekan salah satu dari daftar list movies. Di sini akan dicari posisi mousenya yang mana
        // kemudian frame baru akan dibuat untuk menampilkan detail dari movies yang diklik.
        movieJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = movieJList.locationToIndex(e.getPoint());
        
                if (idx >= 0 && idx < movieJList.getModel().getSize()) {
                    Movie selectedMovie = movieJList.getModel().getElementAt(idx);
                    new DetailFrame(selectedMovie).setVisible(true);
                }
            }
        });

        // Kode di bawah ini digunakan untuk membuat objek JScrollPane, agar bisa scrolling. Scroll vertical didisable, tapi yang horizontal ketika dibutuhkan saja (ketika
        // terlalu banyak sampai di luar frame. Di sini border dibuat empty agar tidak terlihat garis border dari ScrollPane.
        JScrollPane scrollPane = new JScrollPane(movieJList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Kode di bawah ini digunakan untuk memasukkan scrollPane ke dalam panel movieSeriesPanel. Background dari movieJList diatur ke hitam.
        movieSeriesPanel.add(scrollPane);
        movieJList.setBackground(Color.BLACK);

        // Kode di bawah ini digunakan untuk membuat label yang menampung tulisan SERIES, fontnya poppins, dibold, ukurannya 25pt, warna textnya putih, dibuat align center. Kemudian dimasukkan ke dalam panel MovieSeriesPanel.
        JLabel SeriesLabel = new JLabel("SERIES", SwingConstants.CENTER);
        SeriesLabel.setFont(new Font("Poppins", Font.BOLD, 25));  
        SeriesLabel.setForeground(Color.WHITE);
        SeriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieSeriesPanel.add(SeriesLabel);

        // Kode di bawah ini adalah arrayList bertipe data Movie yang berisi daftar series yang mau ditampilkan beserta informasinya
        ArrayList<Movie> daftarSeries = new ArrayList<Movie>();
        daftarSeries.add(new Movie("The Rookie", "Action, Crime, Drama", 2018, "8.0/10", "Alexi Hawley", "Starting over isn't easy, especially for John Nolan who, after a life-altering incident, is pursuing his dream of joining the LAPD. As their oldest rookie, he's met with skepticism from those who see him as just a walking midlife crisis.", "tt7587890"));
        daftarSeries.add(new Movie("Family Guy", "Parody, Satire, Sitcom, Comedy", 1999, "8.1/10", "Seth MacFarlane, David Zuckerman", "In a wacky Rhode Island town, a dysfunctional family strives to cope with everyday life as they are thrown from one crazy scenario to another.", "tt0182576"));
        daftarSeries.add(new Movie("The Simpsons", "Satire, Sitcom, Comedy", 1989, "8.7/10", "James L. Brooks, Matt Groening, Sam Simon", "The satiric half-hour adventures of a working-class family in the misfit city of Springfield.", "tt0096697"));
        daftarSeries.add(new Movie("Boku no Hero Academia", "Anime, Action, Fantasy, Adventure, Superhero", 2016, "8.2/10", "Kohei Horikoshi", "A superhero-admiring boy enrolls in a prestigious hero academy and learns what it really means to be a hero, after the strongest superhero grants him his own powers.", "tt5626028"));
        daftarSeries.add(new Movie("Spongebob Squarepants", "Adventure, Comedy, Family", 1999, "8.2/10", "Stephen Hillenburg, Tim Hill, Nick Jennings", "The misadventures of a talking sea sponge who works at a fast food restaurant, attends a boating school, and lives in an underwater pineapple.", "tt0206512"));
        daftarSeries.add(new Movie("The Walking Dead", "Drama, Horror, Thriller", 2010, "8.1/10", "Frank Darabont", "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins and must lead a group of survivors to stay alive.", "tt1520211"));
        daftarSeries.add(new Movie("The Last of Us", "Quest, Survival, Zombie, Horror, Thriller", 2023, "8.7/10", "Neil Druckmann, Craig Mazin", "After a global pandemic destroys civilization, a hardened survivor takes charge of a 14-year-old girl who may be humanity's last hope.", "tt3581920"));
        
        // Kode di bawah ini digunakan untuk mengatur value dari icon dari objek Movie yang ada di dalam daftarSeries. Jadi bakalan ngelooping dari awal sampe seluruh isi arraylist.
        // title dari movie diambil, kemudian gambarnya diload dengan path assets/series/ + judul series + jpg. Gambar yang diload diresize menjadi 200x275px.
        for(int i=0;i<daftarSeries.size();i++){
            daftarSeries.get(i).setIcon(new ImageIcon(new ImageIcon("./assets/series/" + daftarSeries.get(i).getTitle() + ".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH)));
        }

        // Kode di bawah ini adalah pembuatan JList dengan isi daftarSeries yang dikonversi ke array
        JList<Movie> SeriesJList = new JList<>(daftarSeries.toArray(new Movie[0]));

        // Kode di bawah ini digunakan untuk menampilkan apa saja isi dari JList
        SeriesJList.setCellRenderer(new ListCellRenderer<Movie>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Movie> list, Movie movie, int idx, boolean isSelected, boolean cellHasFocus) {

            // Kode di bawah ini adalah pembuatan panel dengan layout BorderLayout dan ukuran 225x325px, dibuat transparan
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(225, 325));
            panel.setOpaque(false);

            // Kode di bawah ini adalah pemberian padding di kiri 15px, bawah 15px, dan kanan 15px.
            panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

            // Kode di bawah adalah pembacaan gambar berdasarkan path assets/series/judul.jpg, gambarnya diresize jadi 200x275. Kemudian dibuat objek label yang menggunakan ImageIcon ini
            ImageIcon movieIcon = new ImageIcon(new ImageIcon("./assets/series/" + daftarSeries.get(idx).getTitle() + ".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH));
            JLabel seriesLabel = new JLabel(movieIcon);

            // Kode di bawah adalah pembuatan panel untuk informasi (nantinya diisi titlelabel dan genrelabel). Di sini layoutnya BoxLayout, secara Y_AXIS (vertical), align center, paddingnya 5px atas dan 5px bawah, dibuat transparan.
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            infoPanel.setOpaque(false);

            // Kode di bawah ini adalah pembuatan label untuk judul movies, fontnya Arial, bold, dan ukurannya 14pt, warna teksnya putih, dan align center.
            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Kode di bawah ini adalah pembuatan label untuk genre movies, fontnya Arial, ukurannya 12pt, warna teksnya abu muda, dan align center.
            JLabel genreLabel = new JLabel(movie.getGenre());
            genreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            genreLabel.setForeground(Color.LIGHT_GRAY);
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

             //Kode di bawah adalah memasukkan titleLabel dan genreLabel ke dalam infoPanel
            infoPanel.add(titleLabel);
            infoPanel.add(genreLabel);

            // Kode di bawah adalah memasukkan seriesLabel (gambar) di posisi Center dan infoPanel di posisi South dari panel.
            panel.add(seriesLabel, BorderLayout.CENTER);
            panel.add(infoPanel, BorderLayout.SOUTH);

            // Kode di bawah ini digunakan untuk mereturn panel yang sudah diisi oleh list movies
            return panel;
            }
        });

        // Kode di bawah ini digunakan untuk mengatur arah JList menjadi Horizontal dan cuma 1 baris
        SeriesJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        SeriesJList.setVisibleRowCount(1);

        // Kode di bawah ini digunakan untuk menangkap inputan klik mouse ketika menekan salah satu dari daftar list movies. Di sini akan dicari posisi mousenya yang mana
        // kemudian frame baru akan dibuat untuk menampilkan detail dari movies yang diklik.
        SeriesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = SeriesJList.locationToIndex(e.getPoint());
                if (idx >= 0 && idx < SeriesJList.getModel().getSize()) {
                    Movie selectedMovie = SeriesJList.getModel().getElementAt(idx);
                    new DetailFrame(selectedMovie).setVisible(true);
                }
            }
        });

        // Kode di bawah ini digunakan untuk membuat objek scrollPane2, agar bisa scrolling. Scroll vertical didisable, tapi yang horizontal ketika dibutuhkan saja (ketika
        // terlalu banyak sampai di luar frame. Di sini border dibuat empty agar tidak terlihat garis border dari ScrollPane.
        JScrollPane scrollPane2 = new JScrollPane(SeriesJList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBackground(Color.BLACK);
        scrollPane2.setBorder(BorderFactory.createEmptyBorder());

        // Kode di bawah ini digunakan untuk memasukkan scrollPane2 ke dalam panel movieSeriesPanel. Background dari SeriesJList diatur ke hitam.
        movieSeriesPanel.add(scrollPane2);
        SeriesJList.setBackground(Color.BLACK);

        // Kode di bawah ini adalah pengaturan layout dari panel CardMain ke BorderLayout. Kemudian diatur ukurannya 1440x900px, header ditambahkan di North dan movieSeriesPanel ditambahkan di Center
        cardMain.setLayout(new BorderLayout());
        cardMain.setPreferredSize(new Dimension(1440, 900));
        cardMain.add(header, BorderLayout.NORTH);
        cardMain.add(movieSeriesPanel, BorderLayout.CENTER);

        // Kode di bawah ini adalah memasukkan panel cardMain ke dalam cardPanel dengan nama main, sementara itu cardProfil dimasukkan dengan nama profil.
        cardPanel.add(cardMain, "main");
        cardPanel.add(cardProfil, "profil");

        // Kode di bawah ini digunakan untuk mengatur warna background dari panel cardProfil menjadi abu gelap, layoutnya diatur ke BorderLayout
        cardProfil.setBackground(Color.DARK_GRAY);
        cardProfil.setLayout(new BorderLayout());

        // Kode di bawah ini adalah pembuatan panel untuk profil pengguna, warna backgroundnya abu gelap, menggunakan Layout BoxLayout secara Vertical (Y_AXIS), dan ada padding 50 dari atas dan 50 dari bawah.
        JPanel profileMainPanel = new JPanel();
        profileMainPanel.setBackground(Color.DARK_GRAY);
        profileMainPanel.setLayout(new BoxLayout(profileMainPanel, BoxLayout.Y_AXIS));
        profileMainPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        // Kode di bawah ini adalah kode yang digunakan untuk membaca gambar button back. Awalnya image dibaca dan diresize menjadi 75x75px
        ImageIcon backIcon = new ImageIcon(new ImageIcon("./assets/button/back.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        // Button dibuat dengan imageicon image yang sudah dibaca dan diresize sebelumnya.
        JButton backBtn = new JButton(backIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                // Jika button ditekan, maka warnanya berubah jadi abu tua
                if (getModel().isArmed()) {
                    g.setColor(Color.DARK_GRAY);
                // Jika button tidak ditekan, maka warnanya abu
                } else {
                    g.setColor(Color.WHITE);
                }
                // Di sini dibuat sebuah lingkaran di posisi (0,0) dan ukurannya 75x75px
                g.fillOval(0, 0, 75, 75);
                // Memanggil fungsi paintComponent dari superclass
                super.paintComponent(g);
            }

            // Mengatur ukuran dari button menjadi 75x75px
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75, 75);
            }
        };
        // backBtn ini diatur sehingga tidak ada garis kotak ketika tombol ditekan, tidak ada warna background di button, tidak ada terlihat border dari button, dan dibuat transparan.
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setOpaque(false);
        
        // backBtn diatur ketika ditekan akan berpindah ke kartu dengan nama "main"
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "main"));

        // Kode di bawah ini adalah pembuatan panel untuk meletakkan button back. Layoutnya flow Left, dibuat transparan, dan diberikan padding 30 dari kiri
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backBtn);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        // Kode di bawah ini menambahkan backPanel ke dalam profileMainPanel
        profileMainPanel.add(backPanel);

        // Kode di bawah ini digunakan untuk membuat panel yang menampung isi profil, backgroundnya abu gelap, layoutnya BoxLayout secara vertical (Y_AXIS), dan isinya align center.
        JPanel isiProfil = new JPanel();
        isiProfil.setBackground(Color.DARK_GRAY);
        isiProfil.setLayout(new BoxLayout(isiProfil, BoxLayout.Y_AXIS));
        isiProfil.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Kode di bawah adalah penbacaan image profil dari suatu path assets/foto-profil.jpg
        ImageIcon ImgProfil = new ImageIcon("./assets/foto-profil.jpg");
        Image image = ImgProfil.getImage();
    
        // Kode di bawah ini digunakan untuk menyimpan gambar secara ARGB, kemudian digambar dengan Graphics2D 
        BufferedImage profil_bulat = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = profil_bulat.createGraphics();

        // Gambar dipotong menjadi berbentuk lingkaran dengan Ellipse2D.Double. Disimpan dalam profil_bulat, kemudian g2d dibuang untuk membebaskan resource
        g2d.setClip(new Ellipse2D.Double(0, 0, image.getWidth(null), image.getHeight(null))); 
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Mengkonversi hasil pemotongan jadi bulat ke dalam ImageIcon. yang kemudian digunakan di sebuah label, dialign center, dan dimasukkan ke dalam panel isiProfil.
        ImageIcon foto_bulat = new ImageIcon(profil_bulat);
        JLabel ProfilLabel = new JLabel(foto_bulat);
        ProfilLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(ProfilLabel);

        // Kode di bawah ini digunakan untuk membuat label yang menyimpan nama, fontnya arial, bold, 28pt, warnanya putih, align center, diberikan padding vertikal 10px sebelum dimasukkan ke dalam panel isiProfil.
        JLabel nameLabel = new JLabel("Michael Effendy");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(10));
        isiProfil.add(nameLabel);

        // Kode di bawah ini digunakan untuk membuat label yang menyimpan NIM, fontnya arial, 20pt, warnanya putih, align center, diberikan padding vertikal 5px sebelum dimasukkan ke dalam panel isiProfil.
        JLabel idLabel = new JLabel("F1D022012");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idLabel.setForeground(Color.WHITE);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(5));
        isiProfil.add(idLabel);

        // Kode di bawah ini digunakan untuk membuat label yang menyimpan view count, fontnya arial, 20pt, warnanya putih, align center, diberikan padding vertikal 10px sebelum dimasukkan ke dalam panel isiProfil.
        JLabel viewCountLabel = new JLabel("View Count: 0");
        viewCountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        viewCountLabel.setForeground(Color.WHITE);
        viewCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(10));
        isiProfil.add(viewCountLabel);

        // Kode di bawah ini memasukkan panel isiProfil ke dalam panel profileMainPanel, kemudian diberikan jarak sedikit
        profileMainPanel.add(isiProfil);
        profileMainPanel.add(Box.createVerticalGlue());

        // Kode di bawah ini memasukkan panel profileMainPanel ke dalam cardProfil di posisi center
        cardProfil.add(profileMainPanel, BorderLayout.CENTER);

        // Kode di bawah ini adalah ActionListener dari button profile, jadi ketika diklik akan berpindah ke cardPanel dengan nama "profil", kemudian viewCount ditambah 1
        // viewCountLabel diubah textnya mengikuti perubahan viewCount, dilakukan revalidate dan repaint di panel isiProfil.
        profileBtn.addActionListener(e -> {
            cardLayout.show(cardPanel, "profil");
            viewCount++;
            viewCountLabel.setText("View Count: " + viewCount);
            isiProfil.revalidate();
            isiProfil.repaint();
        });

        // Kode di bawah ini digunakan untuk menambahkan cardPanel ke dalam frame, ukuran frame disesuaikan, dan ditampilkan
        frame.add(cardPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
