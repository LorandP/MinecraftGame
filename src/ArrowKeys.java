import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
 * Created by Hermes on 27/02/2017.
 */
public class ArrowKeys extends JPanel implements KeyListener {
    private int xAxis = 0;
    private int yAxis = 0;
    private int characterLinePos = 0;
    private int characterColPos = 0;


    //am ales sa folosesc un arraylist in care sa stochez arrow-urile, ca sa pot sa le sterg pe urma din keyPressed.
    private ArrayList<Integer> keysDown;
    private int[][] copyOfOriginalMatrix;

    public ArrowKeys(){
        addKeyListener(this);


    }

    public int[][] getMatrix(){
        return this.copyOfOriginalMatrix;
    }
    public void setMatrix(int[][] copyOfOriginalMatrix){
        this.copyOfOriginalMatrix = copyOfOriginalMatrix;
    }


    public PositionPair positionOfCharacter(){
        int charLinePos = 0;
        int charColPos = 0;
        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++){
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix.length; colIndex++){
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 1) {
                    charLinePos = lineIndex;
                    charColPos = colIndex;
                }

            }
        }
        return new PositionPair(charLinePos, charColPos);
    }


    private int[] sheepPosition(){
        int numSheeps = 0;
        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++){
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix.length; colIndex++){
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 2) {
                    numSheeps++;
                }

            }
        }
        int[] sheepPosition = new int[numSheeps*2];
        for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length; lineIndex++){
            for (int colIndex = 0; colIndex < copyOfOriginalMatrix.length; colIndex++){
                if (copyOfOriginalMatrix[lineIndex][colIndex] == 2) {
                    int arrayIndex = 0;
                    sheepPosition[arrayIndex] = lineIndex;
                    arrayIndex++;
                    sheepPosition[arrayIndex] = colIndex;
                    arrayIndex++;
                }

            }
        }
        return sheepPosition;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*if (keysDown.size() < 1) {
            if (!keysDown.contains(e.getKeyCode())) {
                keysDown.add(new Integer(e.getKeyCode()));
            }
        }*/

        PositionPair charPosition = positionOfCharacter();


        if (e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("A intrat");
            for (int lineIndex = 0; lineIndex < copyOfOriginalMatrix.length;lineIndex++){
                for (int colIndex = 0; colIndex < copyOfOriginalMatrix.length;colIndex++){
                    if (copyOfOriginalMatrix[lineIndex][colIndex] == 1){
                        if (copyOfOriginalMatrix[lineIndex - 1][colIndex] >= 0){
                            copyOfOriginalMatrix[lineIndex - 1][colIndex] = 1;
                            copyOfOriginalMatrix[lineIndex][colIndex] = 0;
                        }
                    }
                }
            }
        }
        if (keysDown.contains(KeyEvent.VK_DOWN))
            yAxis += 10;
        if (keysDown.contains(KeyEvent.VK_LEFT))
            xAxis -= 10;
        if (keysDown.contains(KeyEvent.VK_RIGHT))
            xAxis += 10;

        displayTheMatrix(copyOfOriginalMatrix);

    }
    private void displayTheMatrix(int[][] matrix){
        for (int lineIndex = 0; lineIndex < matrix.length;lineIndex++){
            for (int colIndex = 0; colIndex < matrix.length;colIndex++){
                System.out.println(matrix[lineIndex][colIndex] + " ");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //  keysDown.remove(new Integer(e.getKeyCode()));
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
