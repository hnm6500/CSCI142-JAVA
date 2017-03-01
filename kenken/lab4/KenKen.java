/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * Hrishikesh Moholkar
 * hnm6500@rit.edu
 *
 * this java class  KenKen.java deals with the creation of
 * ken ken puzzle from the group of different kenken puzzles.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class KenKen {
    /**
     * regions: arraylist which contains several regions such as addition region,etc.
     * incorrect:array list which contains  those region whose number doesn't satisfy that region
     * property.
     * verify1:boolean variable used as a flag
     * */
    boolean verify1 = true;
    private ArrayList<Region> regions;
    private ArrayList<Integer> incorrect;


    public void check() {
        /**
         * checks wether the conditions of region are
         * satisfied by the numbers or not and if not then it adds that number
         * to the incorrect list.
         * */

        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).verify() == false) {
                verify1 = false;
                int z = regions.get(i).getnumber();
                incorrect.add(z);

            } else {
                verify1 = true;
            }

        }


    }


    public ArrayList<Integer> getIncorrectRegions() {

        return incorrect;


    }


    public static void main(String[] args) throws FileNotFoundException {
        /**
         * the main program which runs the verification of ken ken puzzle
         */

        if (args.length < 1) {
            System.out.println("all messages, error not not, go to standard output");

        } else {

            KenKen kenken = new KenKen(args[0]);

            for (int i = 0; i <kenken.regions.size() ; i++) {
                System.out.println(kenken.regions.get(i));

            }



            if (kenken.incorrect.size() != 0) {
                System.out.println("Puzzle is incorrect");
            } else if (kenken.incorrect.size() == 0) {
                System.out.println("Puzzle is correct");
            }
        }

    }


    public KenKen(String filename) throws FileNotFoundException {
        /**
         * constructir for ken ken puzzle
         */
        Scanner in = new Scanner(new File(filename));
        int grid;
        int squaredim;
        grid = in.nextInt();
        squaredim = in.nextInt();
        Grid grid1 = new Grid(grid);
        Region region1 = null;
        regions=new ArrayList<>();
        incorrect=new ArrayList<>();


        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                grid1.setVal(i, j, in.nextInt());

            }
        }


        for (int i = 0; i < squaredim; i++) {
            int target = in.nextInt();
            String operator = in.next();

           // System.out.println(operator);
            if (operator.equals("+"))// Do some checks with operator to MAKE the region
            {
                region1 = new AddRegion(i, target);
            } else if (operator.equals("-")) {
                region1 = new SubtractRegion(i, target);
            } else if (operator.equals("*")) {
                region1 = new MultiplyRegion(i, target);
            } else if (operator.equals("/")) {
                region1 = new DivideRegion(i, target);

            }

            regions.add(region1);

        }


        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                int x= grid1.getVal(i,j);
                regions.get(in.nextInt()).addValue(x);

            }

        }
        check();

    }

}
