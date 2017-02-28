import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import jdk.internal.util.xml.impl.Pair;
import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import javax.swing.text.Position;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


/**
 * Created by lorand on 21/02/2017.
 */
public class Application {
    private int[][] copyOfOriginalMatrix;


    private void MinecratTest() {
        copyOfOriginalMatrix = matrix();
        JPanel panel = new JPanel();
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
                displayTheMatrix(copyOfOriginalMatrix);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
    }

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
                for (int index = 0; index < linesFromFile.length; index++) {

                    if (linesFromFile[index].matches("[0-9]+")) {
                        numbersFromFile = Double.parseDouble(linesFromFile[index]);
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

                        matrix[indexLine][index] = Integer.parseInt(linesFromFile[index]);
                    } else {
                        System.out.println("Please enter only numbers.");
                        return null;
                    }
                }
                indexLine++;
            }

            for (int lineIndex = 0; lineIndex < matrix.length; lineIndex++) {
                for (int colIndex = 0; colIndex < matrix.length; colIndex++) {
                    if (matrix[lineIndex][colIndex] == 1) {
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
                for (int colIndex = 0; colIndex < matrix.length; colIndex++) {
                    System.out.print(matrix[lineIndex][colIndex] + " ");
                }
            }
            System.out.println();
        } else {
            System.out.println("The matrice is not composed of equal number of lines and columns.");
        }


        return matrix;
    }


    public PositionPair positionOfCharacter() {
        int charLinePos = 0;
        int charColPos = 0;

        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++) {
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix.length; colIndex++) {
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 1) {
                    charLinePos = lineIndex;
                    charColPos = colIndex;
                }
            }
        }

        return new PositionPair(charLinePos, charColPos);
    }

    private void displayTheMatrix(int[][] matrix) {
        System.out.println();
        for (int lineIndex = 0; lineIndex < matrix.length; lineIndex++) {
            System.out.println();
            for (int colIndex = 0; colIndex < matrix.length; colIndex++) {
                System.out.print(matrix[lineIndex][colIndex] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.MinecratTest();


    }


}
