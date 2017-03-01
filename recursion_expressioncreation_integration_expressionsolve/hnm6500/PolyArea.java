/**
*@Hrishikesh Moholkar
 * This file is the module for finding the area enclosed by
 * polynomial expression.
 */
/**
 * the following in-built  methods are imported
 * as well as other files for finding the area.
 */

package poly.stu;
import java.util.ArrayList;
import java.util.Scanner;
import poly.stu.PolyEval;
import poly.stu.PolyIntegrate;

/**
 * this class PolyArea finds the area enclosed by the
 * polynomial expression.the methods are compute area which
 * calculates the area and returns a double value.
 */

public class PolyArea {
    public static double computeArea(ArrayList<Double> poly, double x1,double x2){
        double list3;
        double list4;
        double sum1;
        ArrayList<Double>list5=new ArrayList<>();
        list5=PolyIntegrate.computeIntegral(poly);

        list3=PolyEval.evaluate(list5,x1);
        list4=PolyEval.evaluate(list5,x2);
        sum1=list4-list3;

    return sum1;

    }
}
