/**
 * @Hrishikesh Moholkar
 * This file finds the integration of a polynomial
 * expression.
 */
/**
 * the following in-built  methods are imported
 * as well as other files for finding the integration of polynomial expression.
 */
package poly.stu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class PolyIntegrate finds the integration of the
 * following polynomial expression. The method computeIntegral evaluates the
 * integration of the expression and returns a list of double type values which is further represented as
 * an polynomial expression.
 */
public class PolyIntegrate {
    public static ArrayList<Double> computeIntegral(ArrayList<Double> poly){
        ArrayList<Double>list1=new ArrayList<Double>();
        for(int i=0;i<=poly.size();i++){
            int var=poly.size()-i;
            if(i<poly.size()){

                double max=poly.get(i)/(var);
                list1.add(max);



            }else {
                if (i == poly.size()) {
                    list1.add(0.0);
                }
            }
        }

        return list1;
    }
}
