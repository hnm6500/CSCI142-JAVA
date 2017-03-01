/**
 * @Hrishikesh Moholkar
 * hnm6500@rit.edu
 * this file makes an oven object by referring to
 * oven class
 */


public class Oven {
    /**this class is the blueprint of all oven objects.
     * oven objects inherit properties from this class.
     * constant field values of oven are given and flags for
     * specifying states are given.
     *
     */
    public static final int BROIL_HIGH_TEMP = 500;
    public static final int BROIL_LOW_TEMP = 400;
    public static final int COOLING_RATE = 8;
    public static final int DEFAULT_OVEN_TEMP = 350;
    public static final int HEATING_RATE = 25;
    public static final int MAX_OVEN_TEMP = 550;
    public static final int MIN_OVEN_TEMP = 100;

    private boolean bake;
    private boolean ovenState;
    private boolean delaycooking;
    private boolean fahreneit;
    private int broiltemp;
    private String toggletemperature;
    private int temperature;
    private int tempSetting1;
    private int clockVal1;
    private int startTime1;
    private int endTime1;
    private int clockVal;
    private int minutes ;
    private int hour;
    private String setting;
    private int tempSetting;
    private int startTime;
    private int endTime;
    private String var;





    public void bake(int tempSetting) {
        /**this method is setting oven to bake
         * precondition : oven on, delaycooking  off
         * return none
         */
        bake = true;
        ovenState=true;
        delaycooking = false;
        if ((fahreneit == true) && (tempSetting >= MIN_OVEN_TEMP && tempSetting <= MAX_OVEN_TEMP)) {
            this.tempSetting = tempSetting;
        } else {
           this.tempSetting = DEFAULT_OVEN_TEMP;
        }

    }

    public void broil(String setting) {
        /**this method sets oven to broil mode
         * precondition bake mode  is off
         * return none
         *
         */
        bake = false;
        ovenState=true;
        delaycooking = false;
        this.setting=setting;
        if ((setting.equals("H")) || (setting.equals("h"))) {
            this.tempSetting = BROIL_HIGH_TEMP;

        } else {
            if ((setting.equals("L") || (setting.equals("l")))){
                this.tempSetting = BROIL_LOW_TEMP;

            }
        }


    }

    public void toggleTemperatureScale() {
        /**
         * toggles between celcius and fahreneit
         * returns none
         */
        fahreneit = !fahreneit;
        if(toggletemperature.equals("C")){
            toggletemperature = "F";
        } else {
            toggletemperature = "C";
        }


    }

    private double converttemp(int temperature) {
        /**
         * helper function to  convert to specific
         * temperature scale.
         * returns double the required temperature value
         */
        double temp;
        if (toggletemperature.equals( "C")) {
            temp = 5.0 * (temperature - 32) / 9.0;
            //System.out.println(temp + "  " + temperature);
        } else {
            temp = 9.0 * (temperature / 5.0 + 32);


        }
        return temp;


    }

    public void turnOff() {
        /**
         * turns the oven off.
         */
        delaycooking = false;
        ovenState = false;
        System.out.println("oven turning off");
    }

    public void setClock(int clockVal) {
        /**
         * sets the clock and checks whether the timming
         * condition is within limits and set to 0
         * when outoff bound
         */


        if ((clockVal >= 0 && clockVal <= 2359)) {
            this.clockVal = clockVal;
            System.out.println("clock is set");
        } else {
            clockVal = 0;
            this.clockVal = clockVal;
            cancel();

        }
        cancel();


    }

    public void delayedCook(int startTime, int endTime, int tempSetting) {
        /**
         * delayedCook method sets the time at which baking will turn on an off after ending time
         * startTime,endTime:starting and ending time
         * tempSetting: the target temperature
         * returns none
         */

        delaycooking = true;


        if ((startTime >= 0 && startTime <= 2359) && (startTime % 100 <= 59)) {
            this.startTime = startTime;
        }
        else {
            this.startTime = 0;
        }

        if ((endTime >= 0 && endTime <= 2359) && (endTime % 100 <= 59)) {
            this.endTime = endTime;
        }
        else {
            this.endTime = 0;
        }


            // if in celsius and out of bounds
        if ((tempSetting > MIN_OVEN_TEMP) && (tempSetting < MAX_OVEN_TEMP)) {
                this.temperature = tempSetting ;
        }
        else {
            this.temperature = DEFAULT_OVEN_TEMP;
        }

    }

    public void simulateTime(){
        /**
         * this function simulates the time
         * during the different modes of oven.
         *
         */
        if (!ovenState){
            tempSetting1-=COOLING_RATE;
            if (tempSetting1<MIN_OVEN_TEMP){
                tempSetting1=MIN_OVEN_TEMP;
            }

        }
        else{
            if (tempSetting1 < tempSetting) {
                tempSetting1 += HEATING_RATE;
                if (tempSetting1 > MAX_OVEN_TEMP) {
                    tempSetting1 = MAX_OVEN_TEMP;
                }
            }else{
                tempSetting1-=COOLING_RATE;
                if (tempSetting1<MIN_OVEN_TEMP){
                    tempSetting1=MIN_OVEN_TEMP;
                }
            }

        }
        clockVal=clockVal+1;
        if (clockVal%100>59){
            clockVal-=60;
            clockVal+=100;
        }
        if(clockVal>2359){
            clockVal=0;

        }


        if ((delaycooking==true)&&(clockVal==startTime)){
            bake=true;
            ovenState=true;
            tempSetting=temperature;

        }
        else if((delaycooking==true)&&(clockVal==endTime)){
            delaycooking=false;
            ovenState=false;

        }
    }




    public void cancel(){
        /**
         * cancels the curent mode  or turns off
         */

        if((ovenState)&&(delaycooking==true)){
            turnOff();
        }
        else{
            delaycooking=false;
        }

    }

    public void display(){
        /**
         * this method performs the function of displaying the
         * virtual display of the oven which includes its state,
         * its current time.
         * returns none
         */
        hour=clockVal/100;
        minutes=clockVal%100;
        if (setting.equals("H")||(setting.equals("h"))){
            String var="High";
            this.var=var;

        }else {
            String var="Low";
            this.var=var;
        }
        System.out.println("--------------------");
        if (!ovenState){
            System.out.println("|                  |");

        }

        if (!ovenState){
            System.out.printf("|"+"%02d"+":"+"%02d"+"             |\n",hour,minutes);
            System.out.println("|                  |");
            System.out.println("-------------------");
        }
        else if(bake==true){
            int displayTemp;
            int displayTemp1;
            if (!fahreneit) {
                displayTemp = (int)converttemp(tempSetting);
                displayTemp1 = (int) converttemp(tempSetting1);
            }
            else
            {
                displayTemp = tempSetting;
                displayTemp1 = tempSetting1;
            }
            System.out.printf("|"+"           "+"BAKE   | \n");
            System.out.printf("|"+"%02d"+":"+"%02d"+"          "+Integer.toString(displayTemp)+"|\n" ,hour,minutes);
            System.out.printf("           "+"curr"+":"+ Integer.toString(displayTemp1) +"|\n");
            System.out.println("--------------------");
        }
        else if(!bake){
            System.out.printf("|"+"           "+"BROIL  |\n");
            System.out.printf("|"+"%02d"+":"+"%02d"+"      %s    |\n",hour,minutes,var);
            System.out.printf("|          "+"curr"+":"+""+Integer.toString(tempSetting1)+"| \n");
            System.out.println("--------------------");
        }
    }
    public Oven() {
        /**
         * constructor
         */
        this.ovenState = false;
        this.clockVal1 = 0;
        this.bake = false;
        this.delaycooking = false;
        this.fahreneit = true;
        this.toggletemperature = "F";
        this.tempSetting1 = MIN_OVEN_TEMP;
        this.setting="";
        this.tempSetting=BROIL_HIGH_TEMP;
        this.startTime=0;
        this.endTime=0;

    }

}
