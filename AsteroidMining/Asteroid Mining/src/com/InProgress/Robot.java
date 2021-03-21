package com.InProgress;

public class Robot extends TravellerBase{

    private int damageCount;


    /**
     * Constructor of the Robot class
     */
    public Robot() {
        this.damageCount = 0;
    }


    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage(){

        System.out.println("increaseDamage()");
        damageCount++;

        // commented the output out. I think right now it is not in the skeleton. (Yves)
        //System.out.println("Robot has been damaged.");
    }
}
