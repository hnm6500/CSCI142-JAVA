/**
 * @ Hrishikesh Moholkar
 * This file PolyEval.java finds the value of the polynomial  expression for
 * specific value.
 */

/**
 * builtin methods and different classes are imported for performing
 * the operation.
 */
package poly.stu;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class evaluates the value of polynomial expression. It contains methods like
 * evaluate for finding the value of a polynomial expression and it returns a double value (area). Another helper method named
 * mult is used for supportive operation.
 */

public class PolyEval {
    public static double evaluate(ArrayList<Double> poly, double x) {
        double var=0.0;
        int curr=0;
        for (int i=poly.size()-1;i>=0;i--){

            double max=poly.get(curr);
            var=var+max*mult(x,i);
            curr=curr+1;
        }
        return var;
        }
    public static double mult(double x,int i){
        double sum=1.0;
        for(int each=0;each<i;each++){
             sum=sum*x;

        }
        return sum;

    }

    }












