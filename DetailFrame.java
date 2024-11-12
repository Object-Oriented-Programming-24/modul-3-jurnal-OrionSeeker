import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URI;

class DetailFrame extends JFrame {
    public DetailFrame(Movie movie) {
        setTitle(movie.getTitle() + " Detail");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 10));
        mainPanel.setBackground(Color.BLACK);
        
        JLabel titleLabel = new JLabel(movie.getTitle() + " (" + movie.getYear() + ") ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel(movie.getIcon());
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBackground(Color.BLACK);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.BLACK);

        textPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        String movieDetails = "Genre: " + movie.getGenre() + "\n" +
                              "Year: " + movie.getYear() + "\n" +
                              "Rating: " + movie.getRating() + "\n" +
                              "Director: " + movie.getDirector() + "\n" +
                              "\nDescription: \n" + movie.getDescription();
        textArea.setText(movieDetails);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 200));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        
        textPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton imdbButton = new JButton("Go to IMDb");
        buttonPanel.setBackground(Color.BLACK);
        imdbButton.setBackground(Color.YELLOW);
        imdbButton.setForeground(Color.BLACK);
        imdbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.imdb.com/title/" + movie.getImdbID()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        buttonPanel.add(imdbButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(imagePanel, BorderLayout.WEST);
        centerPanel.add(textPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    
}
