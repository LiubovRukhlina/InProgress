package com.InProgress;

import java.util.Scanner;


public class Sun {
    private int countDownOfSunStorm;

    public Sun() {
    }

    /**
     * constructor
     * @param countDownOfSunStorm - time before the sun storm
     *
     */
    public Sun(int countDownOfSunStorm) {
        this.countDownOfSunStorm = countDownOfSunStorm;
    }

    private void decreaseCountdown() {
        countDownOfSunStorm--;
    }

    /**
     * launches the sun storm
     */
    public void startSunStorm() {
        System.out.println("startSunStorm()");
        /* No countdown for the skeleton
        while (countDownOfSunStorm >= 0)
        {
            decreaseCountdown();
        }
        */
        findSettlerRobot();
    }

    /**
     *  checks the asteroids and if there are any unhidden settlers/robots on them
     */
    private void findSettlerRobot() {
        System.out.println("findSettlerRobot()");

        // checks all Asteroids
        for(int i = 0; i < Game.getAsteroids().size(); i++)
        {
            // checks all Settlers on the Asteroid
            for(int j = 0; j < Game.getAsteroids().get(i).getSettlersOnAsteroid().size(); j++)
            {
                System.out.println("Is Settler" + Game.getAsteroids().get(i).getSettlersOnAsteroid().get(j).getName() + " hidden?" );
                Scanner scan = new Scanner(System.in);
                String in = scan.next();
                if (in.equals("no"))
                {
                    Game.getAsteroids().get(i).getSettlersOnAsteroid().get(j).die();
                }
                /*
                if(Game.getAsteroids().get(i).getSettlersOnAsteroid().get(j).isHidden() == false)
                {
                    Game.getAsteroids().get(i).getSettlersOnAsteroid().get(j).die();
                }
                */
            }
            
            // checks all Robots on the Asteroid
            for(int j = 0; j < Game.getAsteroids().get(i).getRobotsOnAsteroid().size(); j++)
            {
                System.out.println("Is Robot hidden?" );
                Scanner scan = new Scanner(System.in);
                String in = scan.next();
                if (in.equals("no"))
                {
                    Game.getAsteroids().get(i).getRobotsOnAsteroid().get(j).increaseDamage();
                }
                /*
                if(Game.getAsteroids().get(i).getRobotsOnAsteroid().get(j).isHidden() == false)
                {
                    Game.getAsteroids().get(i).getRobotsOnAsteroid().get(j).increaseDamage();
                }
                */
            }
        }

        this.setCountDownOfSunStorm(5); // 5 is just a placeholder countdown should be assigned randomly.
    }


    // getters and setters
    public int getCountdownOfSunStorm() {
        return countDownOfSunStorm;
    }
    public void setCountDownOfSunStorm(int countDownOfSunStorm) { this.countDownOfSunStorm = countDownOfSunStorm; }
}
