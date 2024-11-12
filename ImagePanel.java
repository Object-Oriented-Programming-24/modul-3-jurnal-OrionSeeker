import javax.swing.*;
import java.awt.*;

// Class ini adalah class ImagePanel yang mengextends JPanel. Class ini digunakan untuk menjadikan suatu gambar menjadi background dari suatu panel
public class ImagePanel extends JPanel{
    Image bg;

    // Konstruktor ini digunakan untuk menset atribut bg dengan ImageIcon yang path filenya diketahui dari parameter methodnya.
    public ImagePanel(String path){
        this.bg = new ImageIcon(path).getImage();
    }

    // Method paintComponent ini adalah overriding dari class JPanel, method ini akan meletakkan image di bg ke dalam panel dari posisi 0,0 dengan lebar dan tinggi
    // yang sesuai dengan panelnya
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
