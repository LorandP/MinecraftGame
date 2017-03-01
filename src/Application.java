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
public class Application extends JPanel implements KeyListener{
    private static int[][] copyOfOriginalMatrix;
    private int rectangleXAxis = 0;
    private int rectangleYAxis = 0;
    private int keyDown = 0;

    JPanel panel = new JPanel();
    private BufferedImage characterRight;
    private BufferedImage characterLeft;
    private BufferedImage characterBack;
    private BufferedImage characterFront;
    private BufferedImage stone;
    private BufferedImage grass;
    private BufferedImage sheep;



    public Application(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        copyOfOriginalMatrix = matrix();
        try
        {
            stone = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/stone.png"));
            characterRight = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveRight.png"));
            characterLeft = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveLeft.png"));
            characterBack = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveBack.png"));
            characterFront = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/steveFront.png"));
            grass = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/grass.png"));
            sheep = ImageIO.read(new File("/Users/lorand/IntellijProjects/MinecraftGame/MineCraftStuff/Sheep.png"));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        PositionPair charPosition = positionOfCharacter();
        Graphics2D graphics2D = (Graphics2D) g;

        ((Graphics2D) g).drawImage(grass, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        ((Graphics2D) g).drawImage(characterFront, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        if (keyDown == KeyEvent.VK_UP)
        {
            ((Graphics2D) g).drawImage(grass, charPosition.getColumn()*100, charPosition.getLine()*100, this);
            ((Graphics2D) g).drawImage(characterBack, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        }
        if (keyDown == KeyEvent.VK_DOWN)
        {
            ((Graphics2D) g).drawImage(grass, charPosition.getColumn()*100, charPosition.getLine()*100, this);
            ((Graphics2D) g).drawImage(characterFront, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        }
        if (keyDown == KeyEvent.VK_LEFT)
        {
            ((Graphics2D) g).drawImage(grass, charPosition.getColumn()*100, charPosition.getLine()*100, this);
            ((Graphics2D) g).drawImage(characterLeft, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        }
        if (keyDown == KeyEvent.VK_RIGHT)
        {
            ((Graphics2D) g).drawImage(grass, charPosition.getColumn()*100, charPosition.getLine()*100, this);
            ((Graphics2D) g).drawImage(characterRight, charPosition.getColumn()*100, charPosition.getLine()*100, this);
        }


        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++) {
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix[lineIndex].length; colIndex++) {

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 0) {
                /*    if (copyOfOriginalMatrix.length > 7)
                        ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 72, this);
                    else if (copyOfOriginalMatrix[0].length > 12)
                        ((Graphics2D) g).drawImage(grass, colIndex * 12, lineIndex * 12, this);
                    else*/
                    ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 100, this);
                }

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 3){

                    /*if (copyOfOriginalMatrix.length > 7)
                        ((Graphics2D) g).drawImage(stone, colIndex * 72, lineIndex * 72, this);
                    else if (copyOfOriginalMatrix[0].length > 12)
                        ((Graphics2D) g).drawImage(stone, colIndex * 12, lineIndex * 12, this);
                    else*/
                        ((Graphics2D) g).drawImage(stone, colIndex * 100, lineIndex * 100, this);
                }

                if (copyOfOriginalMatrix[lineIndex][colIndex] == 2) {
                   /* if (copyOfOriginalMatrix.length > 7) {
                        ((Graphics2D) g).drawImage(grass, colIndex * 72, lineIndex * 72, this);
                        ((Graphics2D) g).drawImage(sheep, colIndex * 72, lineIndex * 72, this);
                    }
                    else if (copyOfOriginalMatrix[0].length > 12){
                        ((Graphics2D) g).drawImage(grass, colIndex * 12, lineIndex * 12, this);
                        ((Graphics2D) g).drawImage(sheep, colIndex * 72, lineIndex * 72, this);
                    }
                    else {*/
                        ((Graphics2D) g).drawImage(grass, colIndex * 100, lineIndex * 100, this);
                        ((Graphics2D) g).drawImage(sheep, colIndex * 100, lineIndex * 100, this);
                    //}
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

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (characterLinePosition - 1 >= 0) {
                if (copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 3 &&
                        copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] != 2) {
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                    copyOfOriginalMatrix[characterLinePosition - 1][characterColumnPosition] = 1;
                    rectangleYAxis -= 100;
                    //System.out.println(rectangleYAxis);
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
                rectangleXAxis -= 100;
                System.out.println(rectangleYAxis);
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
                rectangleYAxis += 100;
                System.out.println(rectangleYAxis);
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
                rectangleXAxis += 100;
                System.out.println(rectangleXAxis);
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
        if (copyOfOriginalMatrix == null || copyOfOriginalMatrix.length > 9 || copyOfOriginalMatrix[0].length > 9) {
            if (copyOfOriginalMatrix.length > 9 || copyOfOriginalMatrix[0].length > 9) {
                System.out.println("The map is to big. Please provide a map with lines or/and columns no more then 9.");
            }
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        /*if (copyOfOriginalMatrix.length > 7)
            frame.setSize(copyOfOriginalMatrix[0].length*100, 720);
        else if (copyOfOriginalMatrix[0].length > 12)
            frame.setSize(1248, copyOfOriginalMatrix.length*100);
        else*/
            frame.setSize(copyOfOriginalMatrix[0].length*100, copyOfOriginalMatrix.length*102);

    }



}
