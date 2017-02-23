import javax.swing.*;
import java.applet.Applet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by lorand on 21/02/2017.
 */
public class Application{

    private void MinecratTest(){
        //declaring the objects in the game
        int character  = 1;
        int sheep = 2;
        int obstacle = 3;
        int clearPath = 0;

        //matrix();


    }

    private int[][] matrix(){
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
        int numberOfSheeps = 0;

        try {
            //Finding the size of matrice
            int lines = 0;
            int characters = 0;

            while (input.hasNextLine()){
                lines++;
                String line = input.nextLine().replaceAll("\\s","");
                characters += line.length();
            }


            //Marimea matricii
            System.out.println();
            if (characters % lines == 0){
                System.out.format("The size of the matrice is = %d x %d \n", characters / lines,lines);

                //Reading into the matrice
                matrix = new int[lines][characters /lines];

                int indexLine = 0;

                while (input2.hasNextLine()) {
                    String[] linesFromFile = input2.nextLine().split("\\s");
                    for (int index = 0; index < linesFromFile.length; index++) {

                        if (linesFromFile[index].matches("[0-9]+")) {
                            numbersFromFile = Double.parseDouble(linesFromFile[index]);
                            if (numbersFromFile > Integer.MAX_VALUE){
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
                        }
                        else {
                            System.out.println("Please enter only numbers.");
                            return null;
                        }
                    }
                    indexLine++;
                }



                for (int index = 0; index < matrix.length; index++){
                    for (int index2 = 0; index2 < matrix.length; index2++){
                        if (matrix[index][index2] == 1)
                            numberOfCharacters++;
                        if (matrix[index][index2] == 2)
                            numberOfSheeps++;

                    }
                }
                if (numberOfCharacters > 1){
                    System.out.println("Please initialise no more then one character.");
                    return null;
                }
                if (numberOfSheeps > 1){
                    System.out.println("Please initialise no more then one sheep.");
                    return null;
                }
                for (int index = 0; index < matrix.length; index++){
                    System.out.println();
                    for (int index2 = 0; index2 < matrix.length; index2++){
                        System.out.print(matrix[index][index2] + " ");
                    }
                }
            }
            else {
                System.out.println("The matrice is not composed of equal number of lines and columns.");
            }
        } catch (NumberFormatException e) {
            System.out.println("FORMATUL");
        }
        return matrix;
    }


    public static void main(String[] args){
        Application application = new Application();
        //application.MinecratTest();

        JFrame frame = new JFrame();

        KeyListenerTest keyListenerTest = new KeyListenerTest();
        frame.add(keyListenerTest);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

    }
}
