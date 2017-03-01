/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * Hrishikesh Mohoholkar
 * this file creates and add elements to the grid and values can be extracted whenever
 * required.
 */
import java.util.ArrayList;
public class Grid {
    /**
     * declaration of grid
     */
    private int[][] grid;

    public Grid(int dim){
        /**
         * initialization of grid in
         * constructor
         */
        this.grid=new int[dim][dim];

    }

    public void setVal(int row,int col,int val){
        /**
         * sets the particular value at the specific
         * row and column
         */
        grid[row][col]=val;

    }

    public int getVal(int row,int col){
        /**
         * gets the value from the grid at specific location
         */

        return grid[row][col];
    }


}
