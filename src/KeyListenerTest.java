import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Hermes on 23/02/2017.
 */
public class KeyListenerTest extends JPanel implements KeyListener {

    ArrayList<Integer> keysDown = new ArrayList<Integer>();
    Application application = new Application();
    ImageIcon character = new ImageIcon("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveRight.png");
    ImageIcon stone = new ImageIcon("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/stone.png");
    ImageIcon grass = new ImageIcon("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/grass.png");
    ImageIcon sheep = new ImageIcon("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/Sheep.png");

    Rectangle2D rect;
    GridBagConstraints gbc = new GridBagConstraints();
    GridLayout grid = new GridLayout(5,5);


    JPanel panel = new JPanel(grid);
    private JButton[][] buttons;
    double x = 0;
    double y = 0;
    double velx = 0;
    double vely = 0;

    public KeyListenerTest(int row, int col) {
        //t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setLayout(grid);


        /*buttons = new JButton[row][col];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int curRow = i;
                int curCol = j;
                buttons[i][j] = new JButton(i + ", " + j);
                buttons[0][0].requestFocusInWindow();
                buttons[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                if (curRow > 0) {
                                    buttons[curRow - 1][curCol].requestFocus();
                                }
                                break;
                            case KeyEvent.VK_DOWN:
                                if (curRow < buttons.length - 1)
                                    buttons[curRow + 1][curCol].requestFocus();
                                break;
                            case KeyEvent.VK_LEFT:
                                if (curCol > 0)
                                    buttons[curRow][curCol - 1].requestFocus();
                                break;
                            case KeyEvent.VK_RIGHT:
                                if (curCol < buttons[curRow].length - 1)
                                    buttons[curRow][curCol + 1].requestFocus();
                                break;
                            default:
                                break;
                        }
                    }


                });
                add(buttons[i][j]);
            }

        }*/
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        rect = new Rectangle2D.Double(x, y, 40, 40);
        g2.fill(rect);
        JLabel label = new JLabel();
        try{
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIcon(stone);
        }
        catch (Exception e){}
        panel.add(label);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (keysDown.size() < 1) {
            if (!keysDown.contains(e.getKeyCode())) {
                keysDown.add(new Integer(e.getKeyCode()));
            }
        }

        if (keysDown.contains(KeyEvent.VK_UP))
            y -= 10;
        if (keysDown.contains(KeyEvent.VK_DOWN))
            y += 10;
        if (keysDown.contains(KeyEvent.VK_LEFT))
            x -= 10;
        if (keysDown.contains(KeyEvent.VK_RIGHT))
            x += 10;

        if (x < 0){
            velx = 0;
            x = 0;
        }
        if (x > 760){
            velx = 0;
            x = 760;
        }

        if (y  < 0){
            vely = 0;
            y = 0;
        }
        if (y > 540){
            vely = 0;
            y = 540;
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


