/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * Hrishikesh Moholkar
 * this is a specific region for multiplication
 */
public class MultiplyRegion extends Region{
    /**
     * subclass which uses the abstract class
     * @param number: a specific number
     * @param target the required value to be reached by performing operation
     *on numbers.
     */

    public MultiplyRegion(int number, int target){
        /**
         * constructor
         */
        super(number,target);
    }

    public boolean verify(){
        /**
         * verifys the multiply region
         */
        int z=1;

        for (int i = 0; i < values.size(); i++) {
            z=z*values.get(i);

        }

        if(z==target){
            return true;
        }

        return false;


    }


    @Override
    public String toString(){
        String z =super.toString() + "op:*"+verify();
        return z;

    }


}
