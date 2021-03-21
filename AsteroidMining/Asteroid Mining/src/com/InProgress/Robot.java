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
    public void increaseDamage() {
        this.damageCount++;
    }


}

