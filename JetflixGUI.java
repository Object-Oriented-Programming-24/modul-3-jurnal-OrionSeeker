import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class JetflixGUI {

    private static int viewCount = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JetFlix");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        JPanel cardMain = new JPanel();
        JPanel cardProfil = new JPanel();
        
        ImagePanel header = new ImagePanel("./assets/header-background.png");
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(1440, 150));

        JPanel textHeader = new JPanel();
        textHeader.setOpaque(false);
        textHeader.setLayout(new BoxLayout(textHeader, BoxLayout.Y_AXIS));
        textHeader.setBorder(BorderFactory.createEmptyBorder(25, 500, 0, 0));

        JLabel titleHeader = new JLabel("Jetflix", SwingConstants.CENTER);
        titleHeader.setFont(new Font("Poppins", Font.BOLD, 50));
        titleHeader.setForeground(Color.WHITE);
        titleHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleHeader = new JLabel("The best streaming service for movies and series");
        subtitleHeader.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitleHeader.setForeground(Color.LIGHT_GRAY);
        subtitleHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        textHeader.add(titleHeader);
        textHeader.add(subtitleHeader);

        ImageIcon profileIcon = new ImageIcon(new ImageIcon("./assets/button/profile.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        JButton profileBtn = new JButton(profileIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.DARK_GRAY);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillOval(0, 38, 75, 75);
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75, 75);
            }
        };
        profileBtn.setFocusPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorderPainted(false);
        profileBtn.setOpaque(false);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        profilePanel.add(profileBtn, BorderLayout.EAST);

        header.add(textHeader, BorderLayout.CENTER);
        header.add(profilePanel, BorderLayout.EAST);

        JPanel movieSeriesPanel = new JPanel();
        movieSeriesPanel.setLayout(new BoxLayout(movieSeriesPanel, BoxLayout.Y_AXIS));
        movieSeriesPanel.setBackground(Color.BLACK);

        JLabel moviesLabel = new JLabel("MOVIES", SwingConstants.CENTER);
        moviesLabel.setFont(new Font("Poppins", Font.BOLD, 25));  
        moviesLabel.setForeground(Color.WHITE);
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieSeriesPanel.add(Box.createVerticalStrut(10));
        movieSeriesPanel.add(moviesLabel);

        ArrayList<Movie> daftarMovie = new ArrayList<>();
        daftarMovie.add(new Movie("Kumo no Mukou", "Anime, Drama, Romance, Sci-Fi", 2004, "6.8/10", "Makoto Shinkai", "In an alternate postwar timeline, Japan is divided into the Union-controlled North and the US-controlled South.", "tt0381348"));
        daftarMovie.add(new Movie("Byosoku 5 Centimeter", "Anime, Slice of Life, Drama", 2007, "7.5/10", "Makoto Shinkai",  "Told in three interconnected segments, Takaki tells the story of his life as cruel winters, cold technology, and finally, adult obligations and responsibility converge to test the delicate petals of love.", "tt0983213"));
        daftarMovie.add(new Movie("Kimi no Nawa", "Anime, Drama, Fantasy", 2016, "8.4/10", "Makoto Shinkai",  "Two teenagers share a profound, magical connection upon discovering they are swapping bodies. Things manage to become even more complicated when the boy and girl decide to meet in person.", "tt5311514"));
        daftarMovie.add(new Movie("Tenki no Ko", "Anime, Drama, Fantasy", 2019, "7.5/10", "Makoto Shinkai", "Set during a period of exceptionally rainy weather, high-school boy Hodaka Morishima runs away from his troubled rural home to Tokyo and befriends an orphan girl who can manipulate the weather.", "tt9426210"));
        daftarMovie.add(new Movie("Suzume no Tojimari", "Anime, Action, Adventure", 2022, "7.6/10", "Makoto Shinkai",  "A modern action adventure road story where a 17-year-old girl named Suzume helps a mysterious young man close doors from the other side that are releasing disasters all over in Japan.", "tt16428256"));
        daftarMovie.add(new Movie("Kotonoha no Niwa", "Anime, Slice of Life, Drama, Romance", 2013, "7.4/10", "Makoto Shinkai", "On a rainy morning in Tokyo, 15-year-old Takao, an aspiring shoemaker, decides to skip class to sketch designs in a beautiful garden. This is where he meets Yukari, a beautiful yet mysterious woman. They strike an unlikely friendship.", "tt2591814"));
        

        for(int i=0;i<daftarMovie.size();i++){
            String namaMovie = daftarMovie.get(i).getTitle();
            daftarMovie.get(i).setIcon(new ImageIcon(new ImageIcon("./assets/movie/" + namaMovie +".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH)));
        }

        JList<Movie> movieJList = new JList<>(daftarMovie.toArray(new Movie[0]));

        movieJList.setCellRenderer(new ListCellRenderer<Movie>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Movie> list, Movie movie, int idx, boolean isSelected, boolean cellHasFocus) {

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(225, 325));
            panel.setOpaque(false);

            panel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

            ImageIcon movieIcon = new ImageIcon(new ImageIcon("./assets/movie/" + daftarMovie.get(idx).getTitle() + ".jpg").getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH));
            JLabel movieLabel = new JLabel(movieIcon);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            infoPanel.setOpaque(false);

            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel genreLabel = new JLabel(movie.getGenre());
            genreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            genreLabel.setForeground(Color.LIGHT_GRAY);
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            infoPanel.add(titleLabel);
            infoPanel.add(genreLabel);

            panel.add(movieLabel, BorderLayout.CENTER);
            panel.add(infoPanel, BorderLayout.SOUTH);

            return panel;
            }
        });

        movieJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        movieJList.setVisibleRowCount(1);

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

        JScrollPane scrollPane = new JScrollPane(movieJList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        movieSeriesPanel.add(scrollPane);
        movieJList.setBackground(Color.BLACK);

        JLabel SeriesLabel = new JLabel("SERIES", SwingConstants.CENTER);
        SeriesLabel.setFont(new Font("Poppins", Font.BOLD, 25));  
        SeriesLabel.setForeground(Color.WHITE);
        SeriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieSeriesPanel.add(Box.createVerticalStrut(0));
        movieSeriesPanel.add(SeriesLabel);

        JPanel seriesGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        seriesGrid.setBackground(Color.BLACK);

        ArrayList<Movie> daftarSeries = new ArrayList<Movie>();
        daftarSeries.add(new Movie("The Rookie", "Action, Crime, Drama", 2018, "8.0/10", "Alexi Hawley", "Starting over isn't easy, especially for John Nolan who, after a life-altering incident, is pursuing his dream of joining the LAPD. As their oldest rookie, he's met with skepticism from those who see him as just a walking midlife crisis.", "tt7587890"));
        daftarSeries.add(new Movie("Family Guy", "Parody, Satire, Sitcom, Comedy", 1999, "8.1/10", "Seth MacFarlane, David Zuckerman", "In a wacky Rhode Island town, a dysfunctional family strives to cope with everyday life as they are thrown from one crazy scenario to another.", "tt0182576"));
        daftarSeries.add(new Movie("The Simpsons", "Satire, Sitcom, Comedy", 1989, "8.7/10", "James L. Brooks, Matt Groening, Sam Simon", "The satiric half-hour adventures of a working-class family in the misfit city of Springfield.", "tt0096697"));
        daftarSeries.add(new Movie("Boku no Hero Academia", "Anime, Action, Fantasy, Adventure, Superhero", 2016, "8.2/10", "Kohei Horikoshi", "A superhero-admiring boy enrolls in a prestigious hero academy and learns what it really means to be a hero, after the strongest superhero grants him his own powers.", "tt5626028"));
        daftarSeries.add(new Movie("Spongebob Squarepants", "Adventure, Comedy, Family", 1999, "8.2/10", "Stephen Hillenburg, Tim Hill, Nick Jennings", "The misadventures of a talking sea sponge who works at a fast food restaurant, attends a boating school, and lives in an underwater pineapple.", "tt0206512"));
        daftarSeries.add(new Movie("The Walking Dead", "Drama, Horror, Thriller", 2010, "8.1/10", "Frank Darabont", "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins and must lead a group of survivors to stay alive.", "tt1520211"));
        daftarSeries.add(new Movie("The Last of Us", "Quest, Survival, Zombie, Horror, Thriller", 2023, "8.7/10", "Neil Druckmann, Craig Mazin", "After a global pandemic destroys civilization, a hardened survivor takes charge of a 14-year-old girl who may be humanity's last hope.", "tt3581920"));
        for(int i=0;i<daftarSeries.size();i++){
            daftarSeries.get(i).setIcon(new ImageIcon(new ImageIcon("./assets/series/" + daftarSeries.get(i).getTitle() + ".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH)));
        }

        JList<Movie> SeriesJList = new JList<>(daftarSeries.toArray(new Movie[0]));

        SeriesJList.setCellRenderer(new ListCellRenderer<Movie>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Movie> list, Movie movie, int idx, boolean isSelected, boolean cellHasFocus) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(225, 325));
            panel.setOpaque(false);

            panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

            ImageIcon movieIcon = new ImageIcon(new ImageIcon("./assets/series/" + daftarSeries.get(idx).getTitle() + ".jpg").getImage().getScaledInstance(200, 275, Image.SCALE_SMOOTH));
            JLabel seriesLabel = new JLabel(movieIcon);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            infoPanel.setOpaque(false);

            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel genreLabel = new JLabel(movie.getGenre());
            genreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            genreLabel.setForeground(Color.LIGHT_GRAY);
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            infoPanel.add(titleLabel);
            infoPanel.add(genreLabel);

            panel.add(seriesLabel, BorderLayout.CENTER);
            panel.add(infoPanel, BorderLayout.SOUTH);

            return panel;
            }
        });

        SeriesJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        SeriesJList.setVisibleRowCount(1);

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

        JScrollPane scrollPane2 = new JScrollPane(SeriesJList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBackground(Color.BLACK);
        scrollPane2.setBorder(BorderFactory.createEmptyBorder());

        movieSeriesPanel.add(scrollPane2);
        SeriesJList.setBackground(Color.BLACK);

        cardMain.setLayout(new BorderLayout());
        cardMain.setPreferredSize(new Dimension(1440, 900));
        cardMain.add(header, BorderLayout.NORTH);
        cardMain.add(movieSeriesPanel, BorderLayout.CENTER);

        cardPanel.add(cardMain, "main");
        cardPanel.add(cardProfil, "profil");

        cardProfil.setBackground(Color.DARK_GRAY);
        cardProfil.setLayout(new BorderLayout());

        JPanel profileMainPanel = new JPanel();
        profileMainPanel.setBackground(Color.DARK_GRAY);
        profileMainPanel.setLayout(new BoxLayout(profileMainPanel, BoxLayout.Y_AXIS));
        profileMainPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        ImageIcon backIcon = new ImageIcon(new ImageIcon("./assets/button/back.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        JButton backBtn = new JButton(backIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.DARK_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillOval(0, 0, 75, 75);
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75, 75);
            }
        };
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setOpaque(false);
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "main"));

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backBtn);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        profileMainPanel.add(backPanel);

        JPanel isiProfil = new JPanel();
        isiProfil.setBackground(Color.DARK_GRAY);
        isiProfil.setLayout(new BoxLayout(isiProfil, BoxLayout.Y_AXIS));
        isiProfil.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon ImgProfil = new ImageIcon("./assets/foto-profil.jpg");
        Image image = ImgProfil.getImage();
        
        BufferedImage profil_bulat = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = profil_bulat.createGraphics();

        g2d.setClip(new Ellipse2D.Double(0, 0, image.getWidth(null), image.getHeight(null))); 
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        ImageIcon foto_bulat = new ImageIcon(profil_bulat);
        
        JLabel ProfilLabel = new JLabel(foto_bulat);
        ProfilLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(ProfilLabel);

        JLabel nameLabel = new JLabel("Michael Effendy");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(10));
        isiProfil.add(nameLabel);

        JLabel idLabel = new JLabel("F1D022012");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idLabel.setForeground(Color.WHITE);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(5));
        isiProfil.add(idLabel);

        JLabel viewCountLabel = new JLabel("View Count: 0");
        viewCountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        viewCountLabel.setForeground(Color.WHITE);
        viewCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        isiProfil.add(Box.createVerticalStrut(10));
        isiProfil.add(viewCountLabel);

        profileMainPanel.add(isiProfil);
        profileMainPanel.add(Box.createVerticalGlue());

        cardProfil.add(profileMainPanel, BorderLayout.CENTER);

        profileBtn.addActionListener(e -> {
            cardLayout.show(cardPanel, "profil");
            viewCount++;
            viewCountLabel.setText("View Count: " + viewCount);
            isiProfil.revalidate();
            isiProfil.repaint();
        });

        frame.add(cardPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
