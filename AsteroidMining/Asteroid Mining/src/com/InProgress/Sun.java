package com.InProgress;


public class Sun {

    //<editor-fold desc="Attributes">

    private int sunX;
    private int countDownOfSunStorm;

    //</editor-fold>


    //<editor-fold desc="Constructor">
    public Sun() { }

    /**
     * constructor
     * @param sunX x coordinate of the perihelion area
     * @param countDownOfSunStorm time before the sun storm
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
     * Calls the perihelionChanger to update the asteroids.
     */
    private void changeSunX() {

        // change atPerihelion to false for current sunX
        for(int j = 0; j < Game.maxY; j++) {
            for (int k = 0; k < Game.maxZ; k++) {
                Game.getAsteroid(sunX, j, k).perihelionChanger(false);
            }
        }

        // change sunX value
        if(this.sunX == 9) {
            this.sunX = 0;
        } else {
            this.sunX++;
        }

        // change atPerihelion to true for current sunX
        for(int j = 0; j < Game.maxY; j++) {
            for (int k = 0; k < Game.maxZ; k++) {
                Game.getAsteroid(sunX, j, k).perihelionChanger(true);
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
    public void startSunStorm() { // TODO Final implementation
        /* No countdown for the prototype
        while (countDownOfSunStorm >= 0)
        {
            decreaseCountdown();
        }
        */
        Tester.generator(Tester.outputFile, "sunstorm occurred" ); // has to be printed before Travellers die
        findTraveller();
    }

    /**
     *  checks the asteroids and if there are any unhidden travellers on them.
     *  Calls die() for unhidden travellers.
     */
    private void findTraveller() {

        for (int i = 0; i < Game.maxX; i++) {
            for (int j = 0; j < Game.maxY; j++) {
                for (int k = 0; k < Game.maxZ; k++) {
                    for (int s = 0; s < Game.getAsteroid(i,j,k).getSettlersOnAsteroid().size(); s++) {
                        if (!Game.getAsteroid(i,j,k).getSettlersOnAsteroid().get(s).getHidden()) {
                            Game.getAsteroid(i,j,k).getSettlersOnAsteroid().get(s).die();
                        }
                    }
                    for (int r = 0; r <Game.getAsteroid(i,j,k).getRobotsOnAsteroid().size(); r++) {
                        if (!Game.getAsteroid(i,j,k).getRobotsOnAsteroid().get(r).getHidden()) {
                            Game.getAsteroid(i,j,k).getRobotsOnAsteroid().get(r).die();
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
