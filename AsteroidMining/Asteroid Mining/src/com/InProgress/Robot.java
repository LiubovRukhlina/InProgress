package com.InProgress;

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
    public Asteroid currentLocation;
    private boolean isHidden = false;

    //</editor-fold>


    //<editor-fold desc="Constructor">

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
        if (!Dest.IsExplode) {
            if ((Math.abs(DestX - currentLocation.getX()) <= 2 || Math.abs(DestY - currentLocation.getY()) <= 2 || Math.abs(DestZ - currentLocation.getZ()) <= 2)) {
                currentLocation.getRobotsOnAsteroid().remove(this);  // robot is removed from the list
                Dest.getRobotsOnAsteroid().add(this); // robot is added to the list
                currentLocation = Dest;  // successful travel
                isHidden = false;
                hide(); // hides when successfully travels
            }
        } else {
            System.out.println("Invalid destination! Enter a new destination");
        }

    }

    /**
     * Allows users to fast travel
     */
    public void fastTravel() {
        if (currentLocation.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = currentLocation.getGate();

            if (Gate1.isActive) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                currentLocation.getRobotsOnAsteroid().remove(this); // robot is removed from the list
                currentLocation = Gate2.getCurrentPosition();// we travel to the location of the pair.
                currentLocation.getRobotsOnAsteroid().add(this); // robot is added to the list
                isHidden = false;
                hide();
            }
        }
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
    public void drill() {

        int depth = currentLocation.getDepth(); // gets the mantle length of the asteroid
        if (depth != 0) { // if it is not hollow, then we drill
            currentLocation.decreaseRockCover(); //drilling
        } else {
            int rndX = ThreadLocalRandom.current().nextInt(0, 5) - 2; // random number between -2 and 2
            int rndY = ThreadLocalRandom.current().nextInt(0, 5) - 2; // random number between -2 and 2
            int rndZ = ThreadLocalRandom.current().nextInt(0, 5) - 2; // random number between -2 and 2
            travel(Game.asteroids.get(currentLocation.getX() + rndX).get(currentLocation.getY() + rndY).get(currentLocation.getY() + rndZ));
        }
    }

    /**
     * the robot hides to evade sunstorms and survive
     */
    public void hide() {
        if (!isHidden) { //if the robot is already hidden
            int cntRobots = 0;
            int cntSettlers =0;
            for (int i = 0; i < currentLocation.getRobotsOnAsteroid().size(); i++){
                if(currentLocation.getRobotsOnAsteroid().get(i).isHidden) // to check if hidden
                    cntRobots++;
            }
            for(int i = 0; i < currentLocation.getSettlersOnAsteroid().size(); i++ ){
                if(currentLocation.getSettlersOnAsteroid().get(i).isHidden)   // to check if hidden
                    cntSettlers++;
            }
            if(cntRobots<=2 && cntSettlers + cntRobots <= 2){ // 2 robots or 1 robot and 1 settler
                if (currentLocation.IsHollow) { // if the asteroid is a hollow one
                    isHidden = true;
            }
            }
        }
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
