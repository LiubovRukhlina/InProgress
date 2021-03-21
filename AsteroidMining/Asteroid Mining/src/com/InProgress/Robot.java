package com.InProgress;

public class Robot extends TravellerBase{

    private int damageCount = 0;

    /**
     * damageCount calculates the robot's damage.
     */

    public void increaseDamage(){
        damageCount++;
        System.out.println("Robot has been damaged.");
        /**
         * Robots damage is increased when it is exposed to the sunstorm.
         */
    }
}
