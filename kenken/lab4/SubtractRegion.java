/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * hrishikesh moholkar
 */
public class SubtractRegion extends Region{
    /**
     * subclass inherits from region class
     * @param number specific number
     * @param target a target value to reach by performing operations on
     *               numbers
     */

    public SubtractRegion(int number,int target){
        super(number,target);
    }


    public boolean verify(){
        /**
         * verification of region
         */
        int z=0;
        for (int i = 0; i <values.size() ; i++) {
            z=loop1()-values.get(i);

        }
        if(z==target){
            return true;
        }
        return false;

    }


    public String toString(){

        String z =super.toString() + "op:-"+verify();
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
