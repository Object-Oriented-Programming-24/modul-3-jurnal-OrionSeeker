import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URI;

// Class ini adalah class yang digunakan sebagai frame detail dari movies atau series
class DetailFrame extends JFrame {

    // Method ini adalah konstruktor yang akan digunakan setiap kali objek DetailFrame dibuat. Konstruktor ini sebenarnya adalah inti dari class ini karena
    // semua hal dilakukan di sini
    public DetailFrame(Movie movie) {

        // Kode setTitle ini digunakan untuk mengatur nama atau title dari frame, yaitu nama movie + detail
        setTitle(movie.getTitle() + " Detail");

        // Kode setSize ini digunakan untuk mengatur ukuran dari frame, yaitu 600px x 400px
        setSize(600, 400);

        // Kode setDefaultCloseOperation digunakan untuk mengatur apa yang harus dilakukan ketika frame ditutup. Dalam kasus ini, hanya DISPOSE, jadi frame ini saja yang
        // akan dihapus, tapi frame utamanya aman tidak ikut ditutup.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Kode setLocationRelativeTo diatur ke null, agar posisi frame barunya akan muncul di tengah tengah
        setLocationRelativeTo(null);

        // Panel ini digunakan sebagai panel utama di frame detail ini
        JPanel mainPanel = new JPanel();

        // Kode di bawah ini digunakan untuk mengatur layout dari mainPanel dengan layout BorderLayout ukuran padding 0 (horizontal), dan 10 (vertikal)
        mainPanel.setLayout(new BorderLayout(0, 10));

        // Kode di bawah ini digunakan utnuk mengatur background dari mainPanel menjadi berwarna hitam
        mainPanel.setBackground(Color.BLACK);
        
        // Kode di bawah ini adalah label yang akan diisi judul dari movie atau series, jadi nama movie atau series ditampilkan sama tahun mulai atau rilisnya.
        // Font diatur menjadi Arial, tipenya Bold, ukuran 28pt, dan warna textnya putih
        JLabel titleLabel = new JLabel(movie.getTitle() + " (" + movie.getYear() + ") ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        // Kode di bawah ini digunakan untuk memasukkan titleLabel ke dalam mainPanel di posisi North dari BorderLayout
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Kode di bawah ini digunakan untuk membuat panel baru untuk Image dan label baru yang akan diisi image.
        // Panelnya akan menggunakan BorderLayout, imageLabel ditempatkan di Center dari panel, kemudian background dari panel diset ke hitam
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel(movie.getIcon());
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBackground(Color.BLACK);

        // Kode di bawah ini digunakan untuk membuat panel baru yang akan diisi dengen text (sebenarnya text area) yang menggunakan BorderLayout, dan warna backgroundnya diset ke hitam.
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.BLACK);

        // Kode di bawah ini digunakan untuk membuat garis border dari textPanel dengan ketebalan 2 dan warna abu
        textPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        // Kode di bawah ini digunakan untuk membuat textarea tempat menampilkan informasi-informasi lainnya dari movie atau series, yaitu genre, tahun, rating, director filmnya, dan
        // deskripsi filmnya. Di sini textareanya akan diisi dengan text font arial, ukuran 14, ketebalan standart, warna text darkgray, diatur menjadi non-editable, linewrap
        // true (jadi kata ga terpisah ketika space habis), dan wrapstylenya word (jadi ketika baris habis dipisahnya per kata).
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        // Kode di bawah ini adalah var String yang berisi detail dari movie atau series, textarea kemudian diisi atau diset textnya dengan var ini
        String movieDetails = "Genre: " + movie.getGenre() + "\n" +
                              "Year: " + movie.getYear() + "\n" +
                              "Rating: " + movie.getRating() + "\n" +
                              "Director: " + movie.getDirector() + "\n" +
                              "\nDescription: \n" + movie.getDescription();
        textArea.setText(movieDetails);
        
        // Kode di bawah ini adalah pembuatan scrollPane yang berisi textarea, ukurannya 250x200, warna backgroundnya hitam, teksnya putih. ScrollPane di sini gunanya
        // agar bisa ngescroll ketika teksnya terlalu panjang sampai melebihi ukurannya. Di sini scrollPanel dimasukkan ke textPanel di posisi Center dari BorderLayout.
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 200));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        // Kode di bawah ini adalah panel yang digunakan untuk menyimpan button, kemudian dibuat juga button yang digunakan untuk menuju web ImdB ketika ditekan.
        // Di sini panelnya diatur berwarna hitam, buttonnya diatur ke warna kuning, dan teksnya dari button diatur ke warna hitam.
        JPanel buttonPanel = new JPanel();
        JButton imdbButton = new JButton("Go to IMDb");
        buttonPanel.setBackground(Color.BLACK);
        imdbButton.setBackground(Color.YELLOW);
        imdbButton.setForeground(Color.BLACK);

        // Kode di bawah ini adalah penggunaan ActionListener untuk mengatur aksi yang akan dilakukan ketika objek imdButton ditekan. Dalam kasus ini, ketika button ditekan
        // akan ada try and catch, yaitu mencoba untuk membuka website imdb untuk movies atau series yang sedang dilihat detailnya, tapi jika gagal, maka akan print trace errornya.
        imdbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.imdb.com/title/" + movie.getImdbID()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Kode di bawah digunakan untuk menambah imdbButton ke dalam buttonPanel
        buttonPanel.add(imdbButton);

        // Kode di bawah ini adalah pembuatan panel yang layoutnya pakai borderlayout untuk menyimpan imagePanel (di West atau kiri) dan textPanel (di Center).
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(imagePanel, BorderLayout.WEST);
        centerPanel.add(textPanel, BorderLayout.CENTER);

        // Kode di bawah ini digunakan untuk memasukka centerPanel ke dalam mainPanel di posisi Center dan buttonPanel di posisi South (bawah). Kemudian mainPanel ini
        // dimasukkan ke dalam frame.
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    
}
