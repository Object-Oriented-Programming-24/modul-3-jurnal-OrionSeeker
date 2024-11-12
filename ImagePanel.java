import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel{
    Image bg;

    public ImagePanel(String path){
        this.bg = new ImageIcon(path).getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
