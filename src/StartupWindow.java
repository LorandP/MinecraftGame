import javax.swing.*;
import java.awt.*;

/**
 * Created by Hermes on 02/03/2017.
 */
public class StartupWindow extends JComponent {
    private Image image;
    public StartupWindow (Image image){
        this.image = image;
    }
    @Override
    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
