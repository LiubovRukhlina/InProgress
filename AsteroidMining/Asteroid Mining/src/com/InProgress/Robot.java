package com.InProgress;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Robot extends TravellerBase {

    //<editor-fold desc="Attributes">
    /**
     * currentX gives the x coordinate of the current asteroid of the robot
     * currentY gives the y coordinate of the current asteroid of the robot
     * <p>
     * currentZ gives the Z coordinate of the current asteroid of the robot
     */
    private int damageCount;

    //</editor-fold>


    //<editor-fold desc="Constructor"
    /**
     * Constructor of the Robot class
     */
    public Robot() {
        this.damageCount = 0;
    }


    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * @param Dest destination asteroid where the user wants to travel
     */
    public void travel(Asteroid Dest) {
        int DestX = Dest.getX();
        int DestY = Dest.getY();
        int DestZ = Dest.getZ();

        /**
         * According to the guidelines (check and revert)
         */
        if (!Dest.IsExploded) {
            if ((Math.abs(DestX - currentPosition.getX()) <= 2 || Math.abs(DestY - currentPosition.getY()) <= 2 || Math.abs(DestZ - currentPosition.getZ()) <= 2)) {
                currentPosition.getRobotsOnAsteroid().remove(this);  // robot is removed from the list
                Dest.getRobotsOnAsteroid().add(this); // robot is added to the list
                currentPosition = Dest;  // successful travel
                isHidden = false;
                hide(Dest); // hides when successfully travels
            }
        } else {
            System.out.println("Invalid destination! Enter a new destination");
        }
        Tester.generator(Tester.outputFile, "Travelled to A" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());

    }

    /**
     * Allows users to fast travel
     */
    public void fastTravel(Asteroid A) {
        if (currentPosition.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = currentPosition.getGate();

            if (Gate1.isActive) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                currentPosition.getRobotsOnAsteroid().remove(this); // robot is removed from the list
                currentPosition = Gate2.getCurrentPosition();// we travel to the location of the pair.
                currentPosition.getRobotsOnAsteroid().add(this); // robot is added to the list
                isHidden = false;
                hide(Gate2.getCurrentPosition());
            }
            else{
                System.out.println("The other pair is not deployed yet!");
            }
        }
        else {
            System.out.println("This asteroid does not have a tansport gate.");
        }
        Tester.generator(Tester.outputFile, "travelled through the gate at " + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());
    }

    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage() {

        System.out.println("increaseDamage()");
        damageCount++;

        if (this.damageCount == 2) {
            this.die();
        }

        // commented the output out. I think right now it is not in the skeleton. (Yves)
        //System.out.println("Robot has been damaged.");
    }

    /**
     * the robot drills
     */
    public void drill(Asteroid A) {

        int depth = currentPosition.getDepth(); // gets the mantle length of the asteroid
        if (depth != 0) { // if it is not hollow, then we drill
            currentPosition.decreaseRockCover(); //drilling
        } else {
            if(currentPosition.getHasGate()) {
                fastTravel(A);
            }
            else{
                int rndX = new Random().nextInt(5)-2; // random number between -2 and 2
                int rndY = new Random().nextInt(5)-2; // random number between -2 and 2
                int rndZ = new Random().nextInt(5)-2; // random number between -2 and 2
                travel(Game.asteroids.get(currentPosition.getX() + rndX).get(currentPosition.getY() + rndY).get(currentPosition.getY()
                        + rndZ));
            }
        }
        Tester.generator(Tester.outputFile, "drilled A" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());
    }

    /**
     * the robot hides to evade sunstorms and survive
     */
    public void hide(Asteroid A) {
        if (currentPosition.getDepth() == 0 && currentPosition.getHollow()) {
            if (!isHidden) { //if the robot is already hidden
                int cntRobots = 0;
                int cntSettlers = 0;
                for (int i = 0; i < currentPosition.getRobotsOnAsteroid().size(); i++) {
                    if (currentPosition.getRobotsOnAsteroid().get(i).isHidden()) // to check if hidden
                        cntRobots++; // number of hidden robots on this asteroid
                }
                for (int i = 0; i < currentPosition.getSettlersOnAsteroid().size(); i++) {
                    if (currentPosition.getSettlersOnAsteroid().get(i).isHidden())   // to check if hidden
                        cntSettlers++; // number of hidden settlers on this asteroid
                }
                if ((cntRobots == 1 && cntSettlers == 0) || (cntSettlers == 1 && cntRobots == 0) || (cntRobots ==0 && cntSettlers == 0)) { // 2 robots or 1 robot and 1 settler
                        isHidden = true;
                    }
                }
            }
            Tester.generator(Tester.outputFile, "hide A" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());
        }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public int getDamageCount() {
        return damageCount;
    }

    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }


    //</editor-fold>

}
