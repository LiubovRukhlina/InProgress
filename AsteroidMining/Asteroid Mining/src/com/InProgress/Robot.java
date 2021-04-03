package com.InProgress;

public class Robot extends TravellerBase{

    //<editor-fold desc="Attributes">

    private int damageCount;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Robot class
     */
    public Robot() {
        this.damageCount = 0;
    }

    //</editor-fold>


    //<editor-fold desc="Method">

    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage() {

        System.out.println("increaseDamage()");
        damageCount++;

        if(this.damageCount == 2)
        {
            this.die();
        }

        // commented the output out. I think right now it is not in the skeleton. (Yves)
        //System.out.println("Robot has been damaged.");
    }

    //</editor-fold>



    //<editor-fold desc="Getters and Setters">

    public int getDamageCount() { return damageCount; }
    public void setDamageCount(int damageCount) { this.damageCount = damageCount; }

    //</editor-fold>

}
