/**
 * @Hrishikesh Moholkar
 * This file Htree creates the Htree figure by the method of recursion.
 */
/**
 * built in function and other classes are imported for
 * performing the operations.
 */

import turtle.Turtle;
import java.util.Scanner;

/**
 * this class HTree performs the operation of creation of H-shaped structures.
 * It contains a constant length denoted by MAX_SEGMENT_LENGTH.
 * The initial method which creates a turtle object so that it can be used for drawing
 * the required figure.
 * DrawHTree method for drawing the required figure with the turtle object
 * formed from init method. It displays the figure on java console.
 */

public class HTree {
    public static void main(String[] args){
        //Scanner enter1=new Scanner(System.in);
        int depth;
        if(args.length>0){
           // System.out.print("Usage:Java htree #");
            depth=Integer.parseInt(args[0]);
            if(depth>=0){
                init(MAX_SEGMENT_LENGTH,depth);
                drawHtree(init(MAX_SEGMENT_LENGTH,depth),MAX_SEGMENT_LENGTH,depth);


            }else{
                if (depth<0){
                    System.out.println(" The depth must be greater than or equal to zero");
                }
            }

            }else{
            System.out.println("Usage:Java htree #");
        }

    }
    public static final int MAX_SEGMENT_LENGTH=1024;// a fixed length of segment.

    /**
     * this function draws the required figure.
     * @param t:turtle object
     * @param length the length of segment
     * @param depth the no.of times to draw the figure.
     */

    public static void drawHtree(Turtle t,int length,int depth){
        if (depth>0) {
            t.goForward(length/2);
            t.turnLeft(90);
            t.goForward(length/2);
            t.turnRight(90);

            drawHtree(t,length/2,depth-1);

            t.turnRight(90);
            t.goForward(length);
            t.turnLeft(90);

            drawHtree(t,length/2,depth-1);

            t.turnLeft(90);
            t.goForward(length/2);
            t.turnLeft(90);
            t.goForward(length);
            t.turnRight(90);
            t.goForward(length/2);
            t.turnRight(90);

            drawHtree(t,length/2,depth-1);

            t.turnRight(90);
            t.goForward(length);
            t.turnLeft(90);

            drawHtree(t,length/2,depth-1);

            t.turnLeft(90);
            t.goForward(length/2);
            t.turnRight(90);
            t.goForward(length/2);
        }
        }
    public static Turtle init(int length,int depth){
        /**
         * creates the turtle object to be used for
         * drawing the figure.
         */



        Turtle t1=new Turtle(0,0,0);
        t1.setWorldCoordinates(-1*length*2,-1*length*2,length*2,length*2);
        t1.setCanvasTitle("H-Tree, depth:"+  depth);

        return t1;

    }

}
