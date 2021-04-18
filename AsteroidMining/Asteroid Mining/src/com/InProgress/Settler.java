package com.InProgress;

import java.util.*;

public class Settler extends TravellerBase {

    //<editor-fold desc="Attributes">

    private int playerID;
    private String name;
    private Inventory itsInventory = new Inventory();

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Settler class
     *
     * @param name Name of the Settler
     * @param currentPosition position of the Settler
     * @param playerID ID of the player the Settler belongs to
     */
    public Settler(String name, Asteroid currentPosition, int playerID) {
        super(currentPosition);
        this.name = name;
        this.playerID = playerID;

        Tester.generator(Tester.outputFile, "created object SETTLER at " + currentPosition.getX() + " "
                + currentPosition.getY() + " " + currentPosition.getZ());
    }


    public Settler(String name) {
        this.name = name;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Moves this Settler to a neighbouring Asteroid.
     * It checks if the Asteroid is not exploded and if it is in the neighbourhood.
     * Then it checks if the Asteroid accepts the Settler on it.
     * After a successful travel the Settler tries to hide inside the Asteroid.
     *
     * @param Destination destination Asteroid
     */
    @Override
    public void travel(Asteroid Destination) {
        if (!Destination.getExploded()) { // checks if Asteroid is not exploded.
            if ((Math.abs(Destination.getX() - currentPosition.getX()) <= 2
                    || Math.abs(Destination.getY() - currentPosition.getY()) <= 2
                    || Math.abs(Destination.getZ() - currentPosition.getZ()) <= 2)) { // checks if asteroid is in the neighbourhood.

                if (Destination.acceptTraveller(this)) { // checks if there is enough space for the settler on the destination.
                    currentPosition.getSettlersOnAsteroid().remove(this);  // settler is removed from the list of the current asteroid
                    currentPosition = Destination;  // successful travel
                    isHidden = false; // settler is not hidden after travel
                    hide(currentPosition); // settler tries to hide

                } else {
                    //System.out.println("Destination does not have free space");
                }
            } else {
                //System.out.println("Invalid destination! Enter a new destination");
            }
            Tester.generator(Tester.outputFile, "travelled to " + currentPosition.getX() + " "
                    + currentPosition.getY() + " " + currentPosition.getZ());
        }
    }


    /**
     * This method is used for travel by using a transport gate.
     * It first checks if there is a gate available and if it is active.
     *
     * @param A asteroid that the Traveller is travelling through
     */
    @Override
    public void fastTravel(Asteroid A) {
        if (A.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = A.getGate();

            if (Gate1.getActive()) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                if (Gate2.getCurrentPosition().acceptTraveller(this)) {
                    A.getSettlersOnAsteroid().remove(this);  // settler is removed from the list
                    currentPosition = Gate2.getCurrentPosition();  // successful travel
                    isHidden = false;
                    hide(currentPosition); // hides when successfully travels}
                } else {
                    //System.out.println("Destination does not have free space");
                }
            } else {
                //System.out.println("The other pair is not deployed yet!");
            }
        } else {
            //System.out.println("This asteroid does not have a transport gate.");
        }
        Tester.generator(Tester.outputFile, "travelled through the gate at " + A.getX() + " " + A.getY() + " " + A.getZ());

    }

    /**
     * The Settler hides inside the Asteroid.
     * Hiding is only possible if the Asteroid is drilled through, hollow and
     * has enough space for the Settler.
     *
     * @param A Asteroid this Settler hides in
     */
    @Override
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

                    Tester.generator(Tester.outputFile, "hidden inside  " + A.getX() + " " + A.getY() + " " + A.getZ());
                }
            }
        }
    }

    /**
     * The Robot drills the Asteroid and the rockCover is reduced.
     * When the Asteroid is drilled through after the current drilling,
     * if checks whether is explodes or the WaterIce sublimes
     *
     * @param A Asteroid which is drilled
     */
    @Override
    public void drill(Asteroid A) {
        if (A.getDepth() != 0) { // if it is not drilled through, then we drill
            A.decreaseRockCover(); //drilling

            Tester.generator(Tester.outputFile, "drilled " + A.getX() + " " + A.getY() + " " + A.getZ());

            if(A.getDepth() ==0 && A.getAtPerihelion()) { // checks if the remaining rockCover equals 0 and if the Asteroid is at perihelion
                if(A.getResourceOfAsteroid().get(0) instanceof Uranium) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).explode(A); // Uranium explodes

                } else if(A.getResourceOfAsteroid().get(0) instanceof WaterIce) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).sublime(A); // WaterIce sublimes
                }
            }
        } else {
            //System.out.println("The Asteroid is completely drilled through");
        }
    }

    /**
     * This method mines the resource of the Asteroid the Settler is currently on.
     * It checks if the Asteroid is drilled through and if it hollow.
     *
     * @param A Asteroid that is mined.
     */
    public void mine(Asteroid A) {
        if (A.getDepth() != 0) { // checks if the Asteroid is drilled through
            //System.out.println("The Asteroid is not completely drilled through");
        } else {
            if (A.getHollow()) { // checks if the Asteroid is hollow
                //System.out.println("Nothing to mine");
            } else {
                this.itsInventory.setStoredResources(A.getResourceOfAsteroid().get(0)); // adds the Resource of the Asteroid to the Inventory
                A.emptyAsteroid(); // removes the Resource from the Asteroid

                Tester.generator(Tester.outputFile, "mined " + A.getX() + " " + A.getY() + " " + A.getZ());
            }
        }
    }

    /**
     * This method is used to build a robot.
     */
    public void buildRobot() {
        int uCount = 0; // counts the number of units of Uranium
        int iCount = 0; // counts the number of units of Iron
        int cCount = 0; // counts the number of units of Carbon

        if (itsInventory.checkResources(1)) { // checks if there are enough resources

            for (ResourceBase resource : itsInventory.getStoredResources()) {
                if (resource instanceof Uranium && uCount == 0) { // removes 1 unit of Uranium
                    uCount++;
                    itsInventory.removeResources(resource);
                } else if (resource instanceof Iron && iCount == 0) { // removes 1 unit of Iron
                    iCount++;
                    itsInventory.removeResources(resource);
                } else if (resource instanceof Carbon && cCount == 0) { // removes 1 unit of Carbon
                    cCount++;
                    itsInventory.removeResources(resource);
                }

                Robot myRobot = new Robot(currentPosition); // creates a new Robot
                Game.robots.add(myRobot); // adds the Robot to the list of Robots

                int rndX = new Random().nextInt(5) - 2; // random number between -2 and 2
                int rndY = new Random().nextInt(5) - 2; // random number between -2 and 2
                int rndZ = new Random().nextInt(5) - 2; // random number between -2 and 2
                myRobot.travel(Game.getAsteroid(currentPosition.getX() + rndX, currentPosition.getY() + rndY, currentPosition.getZ() + rndZ));

                Tester.generator(Tester.outputFile, "built ROBOT");
            }
        }
    }


    /**
     * This method is used when the settler wants to build a SpaceStation.
     *
     * @param A Asteroid on which the SpaceStation is built.
     */
    public void buildSpaceStation(Asteroid A) {

        if (itsInventory.checkResources(A)) { // checks if there are enough resources
            Tester.generator(Tester.outputFile, "built SPACESTATION");
            Game.endGame();
        }
    }

    /**
     * This method is used to build a transportation-gate and store it in the settler’s inventory.
     */
    public void buildTransportGate() {
        if (itsInventory.checkResources(2)) { // checks if there are enough resources
            int uCount = 0; // counts the number of units of Uranium
            int iCount = 0; // counts the number of units of Iron
            int wCount = 0; // counts the number of units of WaterIce

            for (ResourceBase resource : itsInventory.getStoredResources()) { // removes the Resources
                if (resource instanceof Uranium && uCount == 0) { // removes 1 unit of Uranium
                    uCount++;
                    itsInventory.removeResources(resource);
                } else if (resource instanceof Iron && iCount < 2) { // removes 2 unit of Iron
                    iCount++;
                    itsInventory.removeResources(resource);
                } else if (resource instanceof WaterIce && wCount == 0) { // removes 1 unit of WaterIce
                    wCount++;
                    itsInventory.removeResources(resource);
                }

                TransportGate tg1 = new TransportGate();
                TransportGate tg2 = new TransportGate();

                //Pair the gates together
                tg1.makePair(tg2);
                tg2.makePair(tg1);

                this.itsInventory.addGates(tg1, tg2);

                Tester.generator(Tester.outputFile, "built TRANSPORTGATES");

            }
        }
    }

    /**
     * This method deploys a transport gate on the asteroid the settler is currently on.
     *
     * @param A Asteroid that the TransportGate is deployed on
     */
    public void deployTransportGate(Asteroid A) {

        if (!itsInventory.getStoredGates().isEmpty()) {

            TransportGate tg = this.itsInventory.getStoredGates().get(0);
            tg.setCurrentPosition(A); // places the gate at the current Asteroid
            this.itsInventory.removeGate(tg);

            if (tg.getPair().getCurrentPosition() != null) { // checks if the paired gate is already deployed
                tg.activateTransportGate();
                tg.getPair().activateTransportGate();
            }
            Tester.generator(Tester.outputFile, "deployed TRANSPORTGATE at " + A.getX() + " " + A.getY() + " " + A.getZ());
        }
    }


    /**
     * This method stores Resources on a hollow asteroid.
     * It checks if there are already Resources stored.
     * If there are already Resources it checks if they are of the same type as the Resources the Settler wants to store.
     *
     * @param index Index of the Resource that is stored.
     */
    public void storeResources(int index) {
        if(currentPosition.getHollow()) { // checks if the Asteroid is hollow

            if (currentPosition.getStoredResourceOfAsteroid().isEmpty()) { // checks if there are already
                currentPosition.getStoredResourceOfAsteroid().add(itsInventory.getStoredResources().get(index));
                itsInventory.getStoredResources().remove(index);
                Tester.generator(Tester.outputFile, "left " + currentPosition.getStoredResourceOfAsteroid().get(currentPosition.getStoredResourceOfAsteroid().size() - 1).getResourceType().toUpperCase() + " on "
                        + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());

            } else if (currentPosition.getStoredResourceOfAsteroid().size() < 3) { // checks if there is space to store a Resource
                if (itsInventory.getStoredResources().get(index).getResourceType().equals(currentPosition.getStoredResourceOfAsteroid().get(0).getResourceType())) { // checks if the Resource is the same as the stored ones
                    currentPosition.getStoredResourceOfAsteroid().add(itsInventory.getStoredResources().get(index));
                    itsInventory.getStoredResources().remove(index);
                    Tester.generator(Tester.outputFile, "left " + currentPosition.getStoredResourceOfAsteroid().get(currentPosition.getStoredResourceOfAsteroid().size() - 1).getResourceType().toUpperCase() + " on "
                            + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());
                }
            }
        }
    }

    /**
     * This method is used to pick up resources which are stored on asteroids by settlers.
     * Since only Resources of the same type are stored on the Asteroid the last Element of the
     * StoredResourcesOfAsteroid list is picked up.
     */
    public void pickUpResources() {
        if(!currentPosition.getStoredResourceOfAsteroid().isEmpty()) { // check if there are Resources stored on the Asteroid
            int index = currentPosition.getStoredResourceOfAsteroid().size()-1;

            itsInventory.setStoredResources(currentPosition.getStoredResourceOfAsteroid().get(index));
            currentPosition.getStoredResourceOfAsteroid().remove(index);

            Tester.generator(Tester.outputFile, "picked up " + itsInventory.getStoredResources().get( itsInventory.getStoredResources().size()-1).getResourceType().toUpperCase() +
                    " from " + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getZ());
        }
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Inventory getItsInventory() { return itsInventory; }
    public void setItsInventory(Inventory itsInventory) { this.itsInventory = itsInventory; }

    public int getPlayerID() { return playerID; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }

    //</editor-fold>

}
