package com.InProgress;

import java.util.Random;

public class Robot extends TravellerBase {

    //<editor-fold desc="Attributes">

    private String name;



    private int damageCount;

    //</editor-fold>


    //<editor-fold desc="Constructor"

    public Robot() {
        this.damageCount = 0;
    }


    /**
     * Constructor of the Robot class
     *
     * @param currentPosition Position of the Robot
     */
    public Robot(String name, Asteroid currentPosition) {
        super(currentPosition);
        this.name = name;
        this.damageCount = 0;
        currentPosition.setRobotsOnAsteroid(this);

        Tester.generator(Tester.outputFile, "created object ROBOT at " + currentPosition.getX() + " "
                + currentPosition.getY() + " " + currentPosition.getZ());
    }

    public Robot(Asteroid currentPosition) {
        super(currentPosition);
        this.damageCount = 0;

        /*Tester.generator(Tester.outputFile, "created object ROBOT at " + currentPosition.getX() + " "
                + currentPosition.getY() + " " + currentPosition.getZ());*/
    }
    
    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Moves this Robot to a neighbouring Asteroid.
     * It checks if the Asteroid is not exploded and if it is in the neighbourhood.
     * After a successful travel the Robot tries to hide inside the Asteroid.
     * @param Destination destination Asteroid
     */
    public void travel(Asteroid Destination) {

        if (!Destination.getExploded()) { // checks if the Asteroid is not exploded
            if ((Math.abs(Destination.getX() - currentPosition.getX()) <= 2 ||
                    Math.abs(Destination.getY() - currentPosition.getY()) <= 2 ||
                    Math.abs(Destination.getZ() - currentPosition.getZ()) <= 2)) { // checks if the Asteroid is in the neighbourhood.
                currentPosition.getRobotsOnAsteroid().remove(this);  // Robot is removed from the list of the current Asteroid
                currentPosition = Destination;  // successful travel
                currentPosition.getRobotsOnAsteroid().add(this); // robot is added to the list of the new Asteroid
                isHidden = false; // Robot is unhidden after travel
                hide(currentPosition); // tires to hide
            }
        } else {
            //System.out.println("Invalid destination! Enter a new destination");
        }
        Tester.generator(Tester.outputFile, "travelled to " + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());

    }

    /**
     * This method is used for travel by using a transport gate.
     * It first checks if there is a gate available and if it is active.
     * @param A Asteroid to which the gate belongs
     */
    public void fastTravel(Asteroid A) {
        if (A.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = A.getGate();

            if (Gate1.getActive()) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                A.getRobotsOnAsteroid().remove(this); // Robot is removed from the list of the current Asteroid
                currentPosition = Gate2.getCurrentPosition();// we travel to the location of the pair.
                currentPosition.getRobotsOnAsteroid().add(this); // robot is added to the list of the new Asteroid
                isHidden = false; // Robot is unhidden after travel
                hide(currentPosition); // tires to hide
            }
            else{
                //System.out.println("The other pair is not deployed yet!");
            }
        }
        else {
            //System.out.println("This asteroid does not have a transport gate.");
        }
        Tester.generator(Tester.outputFile, "travelled through the gate at " + A.getX() + " " + A.getY() + " " + A.getZ());
    }

    /**
     * The robot hides inside the Asteroid.
     * Hiding is only possible if the Asteroid is drilled through, hollow and
     * has enough space for the robot.
     *
     * @param A Asteroid this Robot hides in
     */
    public void hide(Asteroid A) {
        if (A.getDepth() == 0 && A.getHollow()) {
            if (!isHidden) { //if the robot is already hidden
                int cntRobots = 0;
                int cntSettlers = 0;
                for (int i = 0; i < A.getRobotsOnAsteroid().size(); i++) {
                    if (A.getRobotsOnAsteroid().get(i).getHidden()) // to check if hidden
                        cntRobots++; // number of hidden robots on this asteroid
                }
                for (int i = 0; i < A.getSettlersOnAsteroid().size(); i++) {
                    if (A.getSettlersOnAsteroid().get(i).getHidden())   // to check if hidden
                        cntSettlers++; // number of hidden settlers on this asteroid
                }
                if ((cntRobots == 1 && cntSettlers == 0) || (cntSettlers == 1 && cntRobots == 0) || (cntRobots ==0 && cntSettlers == 0)) { // 2 robots or 1 robot and 1 settler
                    isHidden = true;

                    Tester.generator(Tester.outputFile, "hidden inside " + A.getX() + " " + A.getY() + " " + A.getZ());
                }
            }
        }
    }

    /**
     * The Robot drills the Asteroid and the rockCover is reduced.
     * When the Asteroid is drilled through after the current drilling,
     * if checks whether is explodes or the WaterIce sublimes
     * If the Asteroid is already drilled through the Robots travels to a random neighbouring Asteroid
     * or uses a Transport Gate if available.
     *
     * @param A Asteroid which is drilled
     */
    public void drill(Asteroid A) {
        if (A.getDepth() != 0) { // if it is not hollow, then we drill
            A.decreaseRockCover(); //drilling

            Tester.generator(Tester.outputFile, "drilled " + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());

            if(A.getDepth() ==0 && A.getAtPerihelion() ) { // checks if the remaining rockCover equals 0 and if the Asteroid is at perihelion

                if (A.getResourceOfAsteroid().get(0) instanceof Uranium) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).explode(A); // Uranium explodes

                } else if (A.getResourceOfAsteroid().get(0) instanceof WaterIce) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).sublime(A); // WaterIce sublimes
                }
            }
        } else {
            if(A.getHasGate()) {
                fastTravel(A);
            }
            else{
                int rndX = new Random().nextInt(5)-2; // random number between -2 and 2
                int rndY = new Random().nextInt(5)-2; // random number between -2 and 2
                int rndZ = new Random().nextInt(5)-2; // random number between -2 and 2
                travel(Game.asteroids.get(A.getX() + rndX).get(A.getY() + rndY).get(A.getY() + rndZ));
            }
        }
    }

    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage() {
        damageCount++;

        if (this.damageCount == 2) {
            this.die();
        }

        // commented the output out. I think right now it is not in the skeleton. (Yves)
        //System.out.println("Robot has been damaged.");
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public String getName() {
        return name;
    }
    public int getDamageCount() {
        return damageCount;
    }
    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }


    //</editor-fold>

}
