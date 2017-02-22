import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by lorand on 21/02/2017.
 */
public class Application {

    private void MinecratTest(){
        //declaring the objects in the game
        int character  = 1;
        int sheep = 2;
        int obstacle = 3;
        int clearPath = 0;

        matrix();


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
                    System.out.println();
                    for (int index = 0; index < linesFromFile.length; index++) {

                        matrix[indexLine][index] = Integer.parseInt(linesFromFile[index]);
                    }
                    indexLine++;
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
        application.MinecratTest();
    }
}
