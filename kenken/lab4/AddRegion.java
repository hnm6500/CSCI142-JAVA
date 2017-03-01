/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * hrishikesh moholkar
 * subclass for verifying addition region
 */
public class AddRegion extends Region {
    /**
     * subclass using property of abstract class
     * @param number: no. in the regions
     * @param target specific value to be reached by performing operations on numbers
     */

    public AddRegion(int number,int target){
        /**
         * constructor
         */
          super(number,target);

    }

    public boolean verify(){
        /**
         * verification of add region
         */
        int z=0;

        for (int i = 0; i < values.size(); i++) {
            z=z+values.get(i);

        }

        if(z==target){
            return true;
        }

        return false;


    }

    public String toString(){

        String z =super.toString() + "op:+"+verify();
        return z;
    }




}

