//import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
//import jdk.internal.util.xml.impl.Pair;
//import oracle.jvm.hotspot.jfr.JFR;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Position;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
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
    private static int sheepCollected = 0;
    private String imagePath = "D:\\Projects InteliJ\\MInecraft2\\MineCraftStuff\\";
    private String imagePath50Pixel = "D:\\Projects InteliJ\\MInecraft2\\MineCraftStuff\\50Pixel\\";


    private static JFrame frame = new JFrame();
    private static JFrame endGameFrame = new JFrame();
    private static JLabel score = new JLabel();
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
            stone = ImageIO.read(new File(imagePath+"stone.png"));
            characterRight = ImageIO.read(new File(imagePath+"steveRight.png"));
            characterLeft = ImageIO.read(new File(imagePath+"steveLeft.png"));
            characterBack = ImageIO.read(new File(imagePath+"steveBack.png"));
            characterFront = ImageIO.read(new File(imagePath+"steveFront.png"));
            grass = ImageIO.read(new File(imagePath+"grass.png"));
            sheep = ImageIO.read(new File(imagePath+"Sheep.png"));
            wormhole =ImageIO.read(new File(imagePath+"wormhole.png"));

            stone50 = ImageIO.read(new File(imagePath50Pixel+"stone.png"));
            characterRight50 = ImageIO.read(new File(imagePath50Pixel+"steveRight.png"));
            characterLeft50 = ImageIO.read(new File(imagePath50Pixel+"steveLeft.png"));
            characterBack50 = ImageIO.read(new File(imagePath50Pixel+"steveBack.png"));
            characterFront50 = ImageIO.read(new File(imagePath50Pixel+"steveFront.png"));
            grass50 = ImageIO.read(new File(imagePath50Pixel+"grass.png"));
            sheep50 = ImageIO.read(new File(imagePath50Pixel+"Sheep.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        PositionPair charPosition = positionOfCharacter();
        Graphics2D graphics2D = (Graphics2D) g;
        String sheepsCollectedValueInString ="";
        sheepsCollectedValueInString = Integer.toString(sheepCollected);
        int numberOfSheeps = 0;


        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("D:\\Projects InteliJ\\MInecraft2\\MineCraftStuff\\Minecraft5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /// Drawing the score at the bottom
        if ((copyOfOriginalMatrix[0].length < 20 && copyOfOriginalMatrix[0].length > 9) ||
                (copyOfOriginalMatrix.length < 20 && copyOfOriginalMatrix.length > 9)) {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
            g.setColor(Color.RED);
            ((Graphics2D) g).drawString("Sheep's teleported: ",700,830);
            ((Graphics2D) g).drawString(sheepsCollectedValueInString,900,830);
        } else if (copyOfOriginalMatrix[0].length == 20 || copyOfOriginalMatrix.length == 20) {
            g.setFont(new Font("Bauhaus 93", Font.BOLD, 19));
            g.setColor(Color.RED);
            ((Graphics2D) g).drawString(sheepsCollectedValueInString, copyOfOriginalMatrix[0].length * 100, copyOfOriginalMatrix.length * 104);
            ((Graphics2D) g).drawString("Sheep's teleported: ",copyOfOriginalMatrix[0].length * 100,copyOfOriginalMatrix.length * 104);
        } else {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            g.setColor(Color.RED);
            ((Graphics2D) g).drawString("Sheep's teleported: ", copyOfOriginalMatrix[0].length * 20, copyOfOriginalMatrix.length * 110);
            ((Graphics2D) g).drawString(sheepsCollectedValueInString, copyOfOriginalMatrix[0].length * 60, copyOfOriginalMatrix.length * 110);
        }



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

        ////  Adding a new frame with the final score
        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++) {
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix[lineIndex].length; colIndex++) {
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 2)
                    numberOfSheeps++;
            }
        }


        JLabel numOfSheeps = new JLabel();
        JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBackground(Color.WHITE);
        progressBar.setVisible(true);
        progressBar.setLocation(0,839);
       // frame.add(progressBar);
        /*
        Thread progressBarUpdate = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 60;


                for (int second = 0; second < time; second++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //progressBar.setValue(100*second/60);
                    collectTime = 100*second/60;
                }


            }
        });
        progressBarUpdate.run();
        */
       // nfinal String collectionTime = "";
        /*
        Timer timer = new Timer(500, new ActionListener() {
            int collectTime = 0;
            String collectionTime = Integer.toString(collectTime);
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Show TIMER");
                collectTime++;
                if ((copyOfOriginalMatrix[0].length < 20 && copyOfOriginalMatrix[0].length > 9) ||
                        (copyOfOriginalMatrix.length < 20 && copyOfOriginalMatrix.length > 9)) {
                    g.setFont(new Font("Bauhaus 93", Font.BOLD, 19));
                    g.setColor(Color.RED);
                    ((Graphics2D) g).drawString("Collection time: ",50,830);
                    ((Graphics2D) g).drawString(collectionTime,100,830);
                } else if (copyOfOriginalMatrix[0].length == 20 || copyOfOriginalMatrix.length == 20) {
                    g.setFont(new Font("Bauhaus 93", Font.BOLD, 19));
                    g.setColor(Color.RED);
                    ((Graphics2D) g).drawString(collectionTime, copyOfOriginalMatrix[0].length * 100, copyOfOriginalMatrix.length * 104);
                    ((Graphics2D) g).drawString("Collection time: ",copyOfOriginalMatrix[0].length * 100,copyOfOriginalMatrix.length * 104);
                } else {
                    g.setFont(new Font("Bauhaus 93", Font.BOLD, 18));
                    g.setColor(Color.RED);
                    ((Graphics2D) g).drawString("Collection time: ", copyOfOriginalMatrix[0].length * 20, copyOfOriginalMatrix.length * 110);
                    ((Graphics2D) g).drawString(collectionTime, copyOfOriginalMatrix[0].length * 60, copyOfOriginalMatrix.length * 110);
                }

            }

        });*/


        if (numberOfSheeps == 0){
            numOfSheeps.setBounds(new Rectangle(50,100,600,200));
            numOfSheeps.setFont(new Font("Comic Sans MS", Font.PLAIN,25));
            numOfSheeps.setForeground(Color.WHITE);
            numOfSheeps.setHorizontalAlignment(SwingConstants.CENTER);
            numOfSheeps.setText("<html><h1>Good job!</h1><br> You have teleported "+sheepCollected+" sheep's to your home planet!" +
                    "<br>Thanks to you, you'r people are fed and worm now. </html>");
            endGameFrame.setSize(780,440);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.dispose();
            endGameFrame.setContentPane(new StartupWindow(backgroundImage));
            endGameFrame.setLayout(null);
            endGameFrame.add(numOfSheeps);
            endGameFrame.setVisible(true);
            endGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            endGameFrame.setResizable(false);

        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        PositionPair charPosition = positionOfCharacter();
        int characterLinePosition = charPosition.getLine();
        int characterColumnPosition = charPosition.getColumn();
        keyDown = e.getKeyCode();


        if (e.getKeyCode() == KeyEvent.VK_UP) {

            //Inchid vortexul daca plec de langa el
            if (down == 1){
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 0;
            }
            down = 0;
            if (up == 1){
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
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

            //Mut characterul sau adaug vortex daca gasesc o oita
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
                    sheepCollected++;
                    System.out.println("You have found the sheep!");
                    //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            //Inchid vortexul daca plec de langa el
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

            //Mut characterul sau adaug vortex daca gasesc o oita
            if (characterColumnPosition - 1 >= 0 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 3 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] != 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 1;
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                rectangleXAxis -= 100;
               // System.out.println(rectangleYAxis);
            }
            if (characterColumnPosition - 1 >= 0 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] == 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition - 1] = 4;
                left = 1;
                sheepCollected++;
                System.out.println("You have found the sheep!");
                // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            //Inchid vortexul daca plec de langa el
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

            //Mut characterul sau adaug vortex daca gasesc o oita
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
               // System.out.println(rectangleYAxis);
            }
            if (characterLinePosition + 1 < copyOfOriginalMatrix.length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] == 2) {
                copyOfOriginalMatrix[characterLinePosition + 1][characterColumnPosition] = 4;
                down = 1;
                sheepCollected++;
                System.out.println("You have found the sheep!");
                // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            //Inchid vortexul daca plec de langa el
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

            //Mut characterul sau adaug vortex daca gasesc o oita
            if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 3 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] != 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 1;
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] = 0;
                rectangleXAxis += 100;

            }
            if (characterColumnPosition + 1 < copyOfOriginalMatrix[characterLinePosition].length &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition] == 1 &&
                    copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] == 2) {
                copyOfOriginalMatrix[characterLinePosition][characterColumnPosition + 1] = 4;
                right = 1;
                sheepCollected++;
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
     * This method is used to read a matrix from a file and store it in a 2d Array.
     *
     * @return 2d Array.
     */

    public int[][] matrix() {
        Scanner input = null;
        try {
            input = new Scanner(new FileReader("D:\\Projects InteliJ\\MInecraft2\\Map1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input2 = null;
        try {
            input2 = new Scanner(new FileReader("D:\\Projects InteliJ\\MInecraft2\\Map1.txt"));
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
            System.out.format("The size of the map is = %d x %d \n", characters / lines, lines);

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
                                && numbersFromFile != 0 && numbersFromFile != 5) {
                            System.out.println("Unknown object " + numbersFromFile + "\n" +
                                    "Please change it to 1, 2, 3, 5 or 0");
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

        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("D:\\Projects InteliJ\\MInecraft2\\MineCraftStuff\\MinecraftPlanet4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        //tartupWindow startupWindow = new StartupWindow(backgroundImage);
        JLabel welcomeMessage = new JLabel("Welcome to mincraft world");
        JLabel information = new JLabel("<html>You'r people are out of food and clothing.<br> You have searched long and hard in the universe to find the right source of food and clothing" +
                " but you have finally found a planet called Earth, inhabited by living creatures that we call sheep's that are just the right source.<br>" +
                "Teleport them all back to your home planet. </html>");
        welcomeMessage.setLocation(150,50);
        welcomeMessage.setSize(500,30);
        welcomeMessage.setForeground(Color.WHITE);
        welcomeMessage.setFont(new Font("Comic Sans MS", Font.BOLD,32));
        information.setLocation(200,100);
        information.setHorizontalAlignment(SwingConstants.LEFT);
        information.setSize(500,200);
        information.setForeground(Color.WHITE);
        information.setFont(new Font("Comic Sans MS", Font.PLAIN,17));

        JButton onePlayer = new JButton("Play");
        JButton twoPlayers = new JButton("2 players");
        onePlayer.setSize(120,50);
        onePlayer.setLocation(320,300);
        onePlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        twoPlayers.setSize(120,50);
        twoPlayers.setLocation(450,300);

        final JFrame frame2 = new JFrame("Minecraft World");
        frame2.setContentPane(new StartupWindow(backgroundImage));
        frame2.setVisible(true);
        frame2.setResizable(false);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(780,440);
        frame2.add(welcomeMessage);
        frame2.add(information);
        frame2.add(onePlayer);
        //frame2.add(twoPlayers);
/*
        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayers twoPlayers1 = new TwoPlayers();
                //frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
                frame2.dispose();
                frame.add(twoPlayers1);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                copyOfOriginalMatrix = twoPlayers1.matrix();

                if (copyOfOriginalMatrix == null) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }

                if (copyOfOriginalMatrix[0].length > 20 || copyOfOriginalMatrix.length > 20) {
                    System.out.println("The map is to big. Please provide a map with lines or/and columns no more then 20.");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                } else if ((copyOfOriginalMatrix[0].length < 20 && copyOfOriginalMatrix[0].length > 9) ||
                        (copyOfOriginalMatrix.length < 20 && copyOfOriginalMatrix.length > 9)) {
                    frame.setSize(copyOfOriginalMatrix[0].length * 50, copyOfOriginalMatrix.length * 51);

                } else if (copyOfOriginalMatrix[0].length == 20 || copyOfOriginalMatrix.length == 20) {
                    frame.setSize(1000, 1020);
                } else
                    frame.setSize(copyOfOriginalMatrix[0].length * 100, copyOfOriginalMatrix.length * 104);
            }
        });
*/
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Application application = new Application();

                        frame.add(application);

                        frame.setVisible(true);
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        if (copyOfOriginalMatrix == null) {
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }

                        if (copyOfOriginalMatrix[0].length > 20 || copyOfOriginalMatrix.length > 20) {
                            System.out.println("The map is to big. Please provide a map with lines or/and columns no more then 20.");
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        } else if ((copyOfOriginalMatrix[0].length < 20 && copyOfOriginalMatrix[0].length > 9) ||
                                (copyOfOriginalMatrix.length < 20 && copyOfOriginalMatrix.length > 9)) {
                            frame.setSize(copyOfOriginalMatrix[0].length * 50, copyOfOriginalMatrix.length * 55);

                        } else if (copyOfOriginalMatrix[0].length == 20 || copyOfOriginalMatrix.length == 20) {
                            frame.setSize(1000, 1020);
                        } else
                            frame.setSize(copyOfOriginalMatrix[0].length * 100, copyOfOriginalMatrix.length * 120);

                    }
                });


            }
        });

    }
}



































