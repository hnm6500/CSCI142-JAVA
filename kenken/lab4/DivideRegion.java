/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * hrishikesh moholkar
 * this file is for specific region
 */
public class DivideRegion extends Region{
    /**
     * subclass inherits from abstract class region
     * @param number numbers
     * @param target  specific value
     */

    public DivideRegion(int number,int target){
        /**
         * constructor
         */
        super(number,target);

    }

    public boolean verify(){
        /**
         * verification of divide region
         */

        int z=0;
        for (int i = 1; i <values.size() ; i++) {
            z=loop1()/values.get(i);

        }
        if(z==target){
            return true;
        }
        return false;


    }

    public String toString(){

        String z =super.toString() + "op:/"+verify();
        return z;

    }


    public int loop1(){
        /**
         * helper function for sorting
         */
        int max=0;
        for (int i = 0; i <values.size()-1 ; i++) {
            for (int j = i+1; j <values.size() ; j++) {

                if(values.get(i)<values.get(j)){
                    int temp=values.get(i);
                    values.set(i,values.get(j));
                    values.set(j,temp);
                }

            }

        }
        max=values.get(0);
        return max;
    }

}
