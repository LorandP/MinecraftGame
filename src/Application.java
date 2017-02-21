import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lorand on 21/02/2017.
 */
public class Application {

    private void MinecratTest(){
        Scanner input = null;
        try {
            input = new Scanner(new FileReader("/Users/lorand/IntellijProjects/MinecraftGame/Map1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        BufferedReader readLine = null;
        ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();
        String line = "";
        int numbers = 0;


        //Reading the lines
        try {

            while (input.hasNext()) {
                String[] linesFromFile = input.nextLine().split("\\s");
                ArrayList<Integer> list = new ArrayList<Integer>();

                for (int index = 0; index < linesFromFile.length; index++) {
                    list.add(Integer.parseInt(linesFromFile[index]));
                }
                list2.add(list);
            }

        } catch (NumberFormatException e) {
            System.out.println("FORMATUL");
        }


        for (int index = 0; index < list2.size();index++)
        {
            System.out.println(index+" "+list2.get(index));

            ArrayList<Integer> numbersFromList = (ArrayList<Integer>)list2.get(index);
            for (int index2 = 0; index2 < numbersFromList.size();index2++){

            }
            System.out.println();
        }
    }


    public static void main(String[] args){
        Application application = new Application();
        application.MinecratTest();
    }
}
