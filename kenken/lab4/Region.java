/**
 * Created by moholkar.hrishikesh2 on 2/20/2016.
 * Hrishikesh Moholkar
 * this file creates the region.basically an abstract class which can be
 * used as an interface for creating specific regions.
 */

import java.util.ArrayList;
public abstract class Region {
    /**
     * declaration of data fields
     */

    private int number;
    protected int target;
    protected ArrayList<Integer> values;


    public Region(int number,int target){
        /**
         * initialization in constructor
         */
        values=new ArrayList<>();
        this.target=target;
        this.number=number;


    }

    public void addValue(int val){
        /**
         * add the specific value to the region
         */

        values.add(val);

    }

    public abstract boolean verify();

    /**
     * an abstract class no method required
     * @return
     */

    @Override
    public String  toString(){
        /**
         * String representation of verification and the regions
         */
        String representation="Region:"+number+","+"  target:" + target+ "," + " values:" + loop(values);
        return representation;
    }



    public ArrayList loop(ArrayList number){
        /**
         * helper function for values printing
         */
        String z=new String();
        ArrayList<Integer>y=new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {

            y.add(values.get(i));

        }

        return y;
    }


    public int getnumber(){
        /**
         * returns the number associated witht the region.
         */
        return this.number;
    }



     }



