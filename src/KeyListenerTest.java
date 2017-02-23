import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Hermes on 23/02/2017.
 */
public class KeyListenerTest extends JPanel implements KeyListener {

    ArrayList<Integer> keysDown = new ArrayList<Integer>();
    Rectangle2D rect;
    double x = 0;
    double y = 0;
    double velx = 0;
    double vely = 0;

    public KeyListenerTest() {
        //t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        rect = new Rectangle2D.Double(x,y,40,40);
        g2.fill(rect);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!keysDown.contains(e.getKeyCode())){
            keysDown.add(new Integer(e.getKeyCode()));
        }
        if (keysDown.contains(KeyEvent.VK_UP))
            y -= 15;
        if (keysDown.contains(KeyEvent.VK_DOWN))
            y += 15;
        if (keysDown.contains(KeyEvent.VK_LEFT))
            x -= 15;
        if (keysDown.contains(KeyEvent.VK_RIGHT))
            x += 15;

        if (x < 0){
            velx = 0;
            x = 0;
        }
        if (x > 730){
            velx = 0;
            x = 730;
        }

        if (y  < 0){
            vely = 0;
            y = 0;
        }
        if (y > 530){
            vely = 0;
            y = 530;
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysDown.remove(new Integer(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}


