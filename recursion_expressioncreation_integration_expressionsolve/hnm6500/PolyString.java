/**
 * @Hrishikesh Moholkar
 * This file creates the polynomial expression
 * from the values of the arraylist and returns it
 * the user in the form of expression.
 */

package poly.stu;
import java.util.ArrayList;


/**
 * This class can return a string representation of a polynomial of order n,
 * in the form:
 * <br>
 * <code>
 * x^n + x^n-1 + ... x^1 + constant
 * </code>
 * <br>
 *
 * @author sps (Sean Strout @ RIT CS)
 * @author rwd (Rob Duncan)
 */
public class PolyString {

    /**
     * Unused constructor, made private to avoid javadoc generation.
     */
    private PolyString() {
    }

    /**
     * The displayed variable name
     */
    public final static String VARIABLE_NAME = "x";

    /**
     * Get the string representation of the polynomial.  For example:
     * <p>
     * <code>
     * poly=[1] : "1" <br>
     * poly=[-2, 3] : "-2x + 3" <br>
     * poly=[-4, 0] : "-4x + 0" <br>
     * poly=[4.1, -6.2, -2.3, 2.4] : "4.1x^3 + -6.2x^2 + -2.3x + 2.4" <br>
     * poly=[6, 15, 12, 0, 0, -5] : "6x^5 + 15x^4 + 12x^3 + -5" <br>
     * </code>
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @rit.pre poly is not an empty list.  Minimally it will contain
     *      a constant term.
     * @return A string representation of the polynomial.
     */
    public static String getString(ArrayList<Double> poly) {
        String result = "";

        // step through list from highest to lowest order term
        //for (int exp=poly.size()-1; exp>=0; --exp) {
        for (int exp = 0; exp < poly.size(); ++exp) {
            double coeff = poly.get(exp);

            // skip 0 terms except constant
            if (coeff == 0 && exp != poly.size() - 1) {
                continue;
            }
            // keep 1.0 constant term, but not 1.0x...
            if (Math.abs(coeff) != 1 || exp == poly.size() - 1) {
                result += coeff;
            }

            // include non-constant, non-zero terms
            //if (exp > 0 && coeff != 0) {
            if (exp < (poly.size() - 1) && coeff != 0) {
                if (coeff == -1) {
                    result += "-"; // ... + -x ...
                }
                result += VARIABLE_NAME;
                //if (exp > 1) {    // don't want x^1
                if (exp < poly.size() - 2) {    // don't want x^1
                    result += "^" + (poly.size() - 1 - exp);
                }
                result += " + ";  // ok because constant always shows at end
            }
        }

        return result;
    }
}
