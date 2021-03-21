package com.InProgress;

public class Robot extends TravellerBase{

    /**
     * @Ash
     * constructor is missing
     * methods should print their name first.
     */

    private int damageCount = 0;


    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage(){

        damageCount++;
        System.out.println("Robot has been damaged.");
        /**
         * Robots damage is increased when it is exposed to the sunstorm.
         */
    }
}
