import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import jdk.internal.util.xml.impl.Pair;
import oracle.jvm.hotspot.jfr.JFR;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by lorand on 21/02/2017.
 */
public class Application extends JPanel implements KeyListener {
    private static int[][] copyOfOriginalMatrix;
    private int rectangleXAxis = 0;
    private int rectangleYAxis = 0;
    private int keyDown = 0;
    private int up = 0;
    private int down = 0;
    private int left = 0;
    private int right = 0;

    JPanel panel = new JPanel();
    private BufferedImage characterRight;
    private BufferedImage characterLeft;
    private BufferedImage characterBack;
    private BufferedImage characterFront;
    private BufferedImage stone;
    private BufferedImage grass;
    private BufferedImage sheep;
    private BufferedImage characterRight50;
    private BufferedImage characterLeft50;
    private BufferedImage characterBack50;
    private BufferedImage characterFront50;
    private BufferedImage stone50;
    private BufferedImage grass50;
    private BufferedImage sheep50;
    private BufferedImage wormhole;


    public Application() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        copyOfOriginalMatrix = matrix();
        try {
            stone = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/stone.png"));
            characterRight = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveRight.png"));
            characterLeft = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveLeft.png"));
            characterBack = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveBack.png"));
            characterFront = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveFront.png"));
            grass = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/grass.png"));
            sheep = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/Sheep.png"));
            wormhole =ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/wormhole.png"));

            stone50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/stone.png"));
            characterRight50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/steveRight.png"));
            characterLeft50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/steveLeft.png"));
            characterBack50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/steveBack.png"));
            characterFront50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/steveFront.png"));
            grass50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/grass.png"));
            sheep50 = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/50Pixel/Sheep.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        PositionPair charPosition = positionOfCharacter();
        Graphics2D graphics2D = (Graphics2D) g;

        if (copyOfOriginalMatrix[0].length > 9 || copyOfOriginalMatrix.length > 9) {
            ((Graphics2D) g).drawImage(grass50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
            ((Graphics2D) g).drawImage(characterFront50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);

            if (keyDown == KeyEvent.VK_UP) {
                ((Graphics2D) g).drawImage(grass50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
                ((Graphics2D) g).drawImage(characterBack50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
            }
            if (keyDown == KeyEvent.VK_DOWN) {
                ((Graphics2D) g).drawImage(grass50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
                ((Graphics2D) g).drawImage(characterFront50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
            }
            if (keyDown == KeyEvent.VK_LEFT) {
                ((Graphics2D) g).drawImage(grass50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
                ((Graphics2D) g).drawImage(characterLeft50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
            }
            if (keyDown == KeyEvent.VK_RIGHT) {
                ((Graphics2D) g).drawImage(grass50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
                ((Graphics2D) g).drawImage(characterRight50, charPosition.getColumn() * 50, charPosition.getLine() * 50, this);
            }
        } else {
            ((Graphics2D) g).drawImage(grass, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
            ((Graphics2D) g).drawImage(characterFront, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);

            if (keyDown == KeyEvent.VK_UP) {
                ((Graphics2D) g).drawImage(grass, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
                ((Graphics2D) g).drawImage(characterBack, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
            }
            if (keyDown == KeyEvent.VK_DOWN) {
                ((Graphics2D) g).drawImage(grass, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
                ((Graphics2D) g).drawImage(characterFront, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
            }
            if (keyDown == KeyEvent.VK_LEFT) {
                ((Graphics2D) g).drawImage(grass, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
                ((Graphics2D) g).drawImage(characterLeft, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
            }
            if (keyDown == KeyEvent.VK_RIGHT) {
                ((Graphics2D) g).drawImage(grass, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
                ((Graphics2D) g).drawImage(characterRight, charPosition.getColumn() * 100, charPosition.getLine() * 100, this);
            }

        }

        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++) {
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix[lineIndex].length; colIndex++) {

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 0) {
                    if (copyOfOriginalMatrix[0].length > 9 || copyOfOriginalMatrix.length > 9)
                        ((Graphics2D) g).drawImage(grass50, colIndex * 50, lineIndex * 50, this);
                    else
                        ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 100, this);
                }

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 3) {

                    if (copyOfOriginalMatrix[0].length > 9 || copyOfOriginalMatrix.length > 9)
                        ((Graphics2D) g).drawImage(stone50, colIndex * 50, lineIndex * 50, this);
                    else
                        ((Graphics2D) g).drawImage(stone, colIndex * 100, lineIndex * 100, this);
                }

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 2) {
                    if (copyOfOriginalMatrix[0].length > 9 || copyOfOriginalMatrix.length > 9) {
                        ((Graphics2D) g).drawImage(grass50, colIndex * 50, lineIndex * 50, this);
                        ((Graphics2D) g).drawImage(sheep50, colIndex * 50, lineIndex * 50, this);
                    } else {
                        ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 100, this);
                        ((Graphics2D) g).drawImage(sheep, colIndex * 100, lineIndex * 100, this);
                    }
                }
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 4) {
                    if (copyOfOriginalMatrix[0].length > 9 || copyOfOriginalMatrix.length > 9) {
                        ((Graphics2D) g).drawImage(grass50, colIndex * 50, lineIndex * 50, this);
                        ((Graphics2D) g).drawImage(wormhole, colIndex * 50, lineIndex * 50, this);
                    } else {
                        ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 100, this);
                        ((Graphics2D) g).drawImage(wormhole, colIndex * 100, lineIndex * 100, this);
                    }
                }

            }
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        PositionPair charPosition = positionOfCharacter();
        int characterLinePosition = charPosition.getLine();
        int characterColumnPosition = charPosition.getColumn();
        keyDown = e.getKeyCode();
        System.out.println("up de sus= " +up);


        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (down == 1){
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 0;
            }
            down = 0;
            if (up == 1){
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 0;
            }
            up = 0;
            if (right == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 0;
            }
            right = 0;
            if (left == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 0;
            }
            left = 0;
            if (characterLinePosition - 1 >= 0) {
                if (copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 3 &&
                        copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 2) {
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                    copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 1;
                    rectangleYAxis -= 100;
                }
            }
            if (characterLinePosition - 1 >= 0) {
                if (copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                        copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] == 2) {
                    copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 4;
                    up = 1;
                    System.out.println("You have found the sheep!");
                    //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            if (right == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 0;
            }
            right = 0;
            if (left == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 0;
            }
            left = 0;
            if (down == 1){
                copyOfOriginalMatrix[characterLinePosition+1][characterColumnPosition] = 0;
            }
            down = 0;
            if (up == 1){
                copyOfOriginalMatrix[characterLinePosition-1][characterColumnPosition] = 0;
            }
            up = 0;
            if (characterColumnPosition - 1 >= 0 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 3 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 1;
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                rectangleXAxis -= 100;
                System.out.println(rectangleYAxis);
            }
            if (characterColumnPosition - 1 >= 0 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] == 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 4;
                left = 1;
                System.out.println("You have found the sheep!");
                // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (up == 1){
                copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 0;
            }
            up = 0;
            if (right == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition+ 1] = 0;
            }
            right = 0;
            if (left == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition -1] = 0;
            }
            left = 0;
            if (down == 1){
                copyOfOriginalMatrix[characterLinePosition+1][characterColumnPosition] = 0;
            }
            down = 0;
            if (characterLinePosition + 1 < copyOfOriginalMatrix.length &&
                    copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] != 3 &&
                    copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] != 2) {
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 1;
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                rectangleYAxis += 100;
                if (up == 1){
                    copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 0;
                }
                up = 0;
                System.out.println(rectangleYAxis);
            }
            if (characterLinePosition + 1 < copyOfOriginalMatrix.length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] == 2) {
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 4;
                down = 1;

                System.out.println("You have found the sheep!");
                // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (right == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 0;
            }
            right = 0;
            if (left == 1)
            {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 0;
            }
            if (down == 1){
                copyOfOriginalMatrix[characterLinePosition+1][characterColumnPosition] = 0;
            }
            down = 0;
            if (up == 1){
                copyOfOriginalMatrix[characterLinePosition-1][characterColumnPosition] = 0;
            }
            up = 0;
            left = 0;
            if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 3 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 1;
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                rectangleXAxis += 100;
                System.out.println(rectangleXAxis);
            }
            if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] == 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 4;
                right = 1;
                System.out.println("You have found the sheep!");
                // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }

        }


        repaint();
        displayTheMatrix(copyOfOriginalMatrix);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }


    /**
     * This method is used to initialise copyOfOriginalMatrix with a 2dArray that has all the numbers stored into it
     * that was read from a file.
     * After that, this method listens for user input keys. Only the arrow keys are registered.
     * If an arrow key is registered then the appropriate actions are taken, like moving the number 1 in different locations
     * in the matrix depending upon what arrow key was pressed.
     */

    private void MinecratTest() {
        //  copyOfOriginalMatrix = matrix();

/*
        JFrame frame = new JFrame();

        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                PositionPair charPosition = positionOfCharacter();
                int characterLinePosition = charPosition.getLine();
                int characterColumnPosition = charPosition.getColumn();

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (characterLinePosition - 1 >= 0) {
                        if (copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 3 &&
                                copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 2) {
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                            copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 1;
                        }
                    }
                    if (characterLinePosition - 1 >= 0) {
                        if (copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                                copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] == 2) {
                            System.out.println("You have found the sheep!");
                            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                    if (characterColumnPosition - 1 >= 0 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 3 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 2) {
                        copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 1;
                        copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                    }
                    if (characterColumnPosition - 1 >= 0 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] == 2) {
                        System.out.println("You have found the sheep!");
                        // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    if (characterLinePosition + 1 < copyOfOriginalMatrix.length &&
                            copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] != 3 &&
                            copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] != 2) {
                        copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 1;
                        copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                    }
                    if (characterLinePosition + 1 < copyOfOriginalMatrix.length &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                            copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] == 2) {
                        System.out.println("You have found the sheep!");
                        // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 3 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 2) {
                        copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 1;
                        copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                    }
                    if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                            copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] == 2) {
                        System.out.println("You have found the sheep!");
                        // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }

                }
                repaint();
                displayTheMatrix(copyOfOriginalMatrix);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(copyOfOriginalMatrix[0].length*100, copyOfOriginalMatrix.length*100);

  */
    }


    /**
     * This method is used to read a matrix from a file and store it in a 2d Array.
     *
     * @return 2d Array.
     */

    public int[][] matrix() {
        Scanner input = null;
        try {
            input = new Scanner(new FileReader("/Users/lorand/IntellijProjects/MinecraftGame/Map1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input2 = null;
        try {
            input2 = new Scanner(new FileReader("/Users/lorand/IntellijProjects/MinecraftGame/Map1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int[][] matrix = null;
        double numbersFromFile = 0;
        int numberOfCharacters = 0;

        //Finding the size of matrice
        int lines = 0;
        int characters = 0;

        while (input.hasNextLine()) {
            lines++;
            String line = input.nextLine().replaceAll("\\s", "");
            characters += line.length();
        }


        //Marimea matricii
        System.out.println();
        if (characters % lines == 0) {
            System.out.format("The size of the matrice is = %d x %d \n", characters / lines, lines);

            //Reading into the matrice
            matrix = new int[lines][characters / lines];

            int indexLine = 0;

            while (input2.hasNextLine()) {
                String[] linesFromFile = input2.nextLine().split("\\s");
                for (int columnIndex = 0; columnIndex < linesFromFile.length; columnIndex++) {

                    if (linesFromFile[columnIndex].matches("[0-9]+")) {
                        numbersFromFile = Double.parseDouble(linesFromFile[columnIndex]);
                        if (numbersFromFile > Integer.MAX_VALUE) {
                            System.out.println("Your matrix contains a number that is to big. Please change it.");
                            return null;
                        }
                        if (numbersFromFile != 1 && numbersFromFile != 2 && numbersFromFile != 3
                                && numbersFromFile != 0) {
                            System.out.println("Unknown object " + numbersFromFile + "\n" +
                                    "Please change it to 1, 2, 3 or 0");
                            return null;
                        }

                        matrix[indexLine][columnIndex] = Integer.parseInt(linesFromFile[columnIndex]);
                    } else {
                        System.out.println("Please enter only numbers.");
                        return null;
                    }
                }
                indexLine++;
            }

            for (int lineIndex = 0; lineIndex < matrix.length; lineIndex++) {
                for (int columnIndex = 0; columnIndex < matrix[lineIndex].length; columnIndex++) {
                    if (matrix[lineIndex][columnIndex] == 1) {
                        numberOfCharacters++;
                    }
                }
            }
            if (numberOfCharacters > 1) {
                System.out.println("Please initialise no more then one character.");
                return null;
            }

            //Afisez matricea
            for (int lineIndex = 0; lineIndex < matrix.length; lineIndex++) {
                System.out.println();
                for (int columnIndex = 0; columnIndex < matrix[lineIndex].length; columnIndex++) {
                    System.out.print(matrix[lineIndex][columnIndex] + " ");
                }
            }
            System.out.println();
        } else {
            System.out.println("The matrice is not composed of equal number of lines and columns.");
        }

        return matrix;
    }

    /**
     * This method is used to find and return the position of the character (number 1) in the matrix.
     *
     * @return the position of the character in the matrix.
     */
    public PositionPair positionOfCharacter() {
        int charLinePos = 0;
        int charColPos = 0;
        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++) {
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix[lineIndex].length; colIndex++) {
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 1) {
                    charLinePos = lineIndex;
                    charColPos = colIndex;
                }
            }
        }

        return new PositionPair(charLinePos, charColPos);
    }

    /**
     * This method is used to display the matrix after the position of the character was changed.
     *
     * @param matrix takes a 2d Array that has the position of the character changed.
     */
    private void displayTheMatrix(int[][] matrix) {
        System.out.println();
        for (int lineIndex = 0; lineIndex < matrix.length; lineIndex++) {
            System.out.println();
            for (int colIndex = 0; colIndex < matrix[lineIndex].length; colIndex++) {
                System.out.print(matrix[lineIndex][colIndex] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Application application = new Application();
        //application.MinecratTest();
        JFrame frame = new JFrame();
        // panel.setFocusable(true);
        //panel.requestFocusInWindow();
        frame.add(application);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        if (copyOfOriginalMatrix[0].length > 20 || copyOfOriginalMatrix.length > 20) {
            System.out.println("The map is to big. Please provide a map with lines or/and columns no more then 20.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        else if ((copyOfOriginalMatrix[0].length < 20 && copyOfOriginalMatrix[0].length > 9)||
                (copyOfOriginalMatrix.length < 20 && copyOfOriginalMatrix.length > 9)) {
            frame.setSize(copyOfOriginalMatrix[0].length * 50, copyOfOriginalMatrix.length * 51);

        } else if (copyOfOriginalMatrix[0].length == 20 || copyOfOriginalMatrix.length == 20) {
            frame.setSize(1000, 1020);
        } else
            frame.setSize(copyOfOriginalMatrix[0].length * 100, copyOfOriginalMatrix.length * 104);

    }


}
