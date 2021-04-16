package com.InProgress;

import java.util.Scanner;


public class Sun {

    //<editor-fold desc="Attributes">

    private int sunX;
    private int countDownOfSunStorm;

    //</editor-fold>


    //<editor-fold desc="Constructor">
    public Sun() { }

    /**
     * constructor
     * @param countDownOfSunStorm - time before the sun storm
     *
     */
    public Sun(int sunX, int countDownOfSunStorm) {
        this.sunX = sunX;
        this.countDownOfSunStorm = countDownOfSunStorm;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Increases the sunX value by one.
     * In case the sunX equals the upper boundary it is set back to 0.
     */
    private void changePerihelion() {

        // change atPerihelion to false for current sunX
        for(int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                Game.asteroids.get(sunX).get(j).get(k).setAtPerihelion(false);
            }
        }

        // change sunX value
        if(this.sunX == 9) {
            this.sunX = 0;
        } else {
            this.sunX++;
        }

        // change atPerihelion to true for current sunX
        for(int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                Game.asteroids.get(sunX).get(j).get(k).setAtPerihelion(true);
            }
        }
    }

    /**
     *  Decreases the countdown of this sun by 1 after each round.
     */
    private void decreaseCountdown() {
        countDownOfSunStorm--;
    }

    /**
     * launches the sun storm
     */
    public void startSunStorm() {
        /* No countdown for the prototype
        while (countDownOfSunStorm >= 0)
        {
            decreaseCountdown();
        }
        */
        findTraveller();
    }

    /**
     *  checks the asteroids and if there are any unhidden settlers/robots on them
     */
    private void findTraveller() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int s = 0; s < Game.asteroids.get(i).get(j).get(k).getSettlersOnAsteroid().size(); s++) {
                        if (Game.asteroids.get(i).get(j).get(k).getSettlersOnAsteroid().get(s).isHidden() == false) {
                            Game.asteroids.get(i).get(j).get(k).getSettlersOnAsteroid().get(s).die();
                        }
                    }
                    for (int r = 0; r < Game.asteroids.get(i).get(j).get(k).getRobotsOnAsteroid().size(); r++) {
                        if (Game.asteroids.get(i).get(j).get(k).getRobotsOnAsteroid().get(r).isHidden() == false) {
                            Game.asteroids.get(i).get(j).get(k).getRobotsOnAsteroid().get(r).die();
                        }
                    }
                }
            }
        }

        //this.setCountDownOfSunStorm(5); // 5 is just a placeholder countdown should be assigned randomly.
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public int getCountdownOfSunStorm() { return countDownOfSunStorm; }
    public void setCountDownOfSunStorm(int countDownOfSunStorm) { this.countDownOfSunStorm = countDownOfSunStorm; }

    //</editor-fold>
}
