package com.InProgress;

import java.util.*;

public class Settler extends TravellerBase {

    //<editor-fold desc="Attributes">

    private int playerID;
    private Inventory itsInventory = new Inventory();

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Settler class
     *
     * @param name Name of the Settler
     */
    public Settler(String name, Asteroid currentPosition, int playerID) {
        super(name, currentPosition);
        this.playerID = playerID;
    }


    public Settler(String name) {
        this.setName(name);
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * moves the object to the chosen Asteroid.
     *
     * @param Dest destination Asteroid
     */

    @Override
    public void travel(Asteroid Dest) {

        int DestX = Dest.getX();
        int DestY = Dest.getY();
        int DestZ = Dest.getZ();

        if (!Dest.IsExploded) {
            if ((Math.abs(DestX - currentPosition.getX()) <= 2
                    || Math.abs(DestY - currentPosition.getY()) <= 2
                    || Math.abs(DestZ - currentPosition.getZ()) <= 2)) {

                if (Dest.acceptTraveller(this)) {
                    currentPosition.getSettlersOnAsteroid().remove(this);  // settler is removed from the list
                    currentPosition = Dest;  // successful travel
                    isHidden = false;
                    hide(Dest); // hides when successfully travels}

                } else {
                    System.out.println("Destination does not have free space");
                }
            } else {
                System.out.println("Invalid destination! Enter a new destination");
            }
            Tester.generator(Tester.outputFile, "Travelled to A" + currentPosition.getX()
                    + currentPosition.getY() + currentPosition.getZ());

        }
    }


    /**
     * This method is used for travel by using a transport gate.
     *
     * @param A asteroid that the Traveller is travelling through
     */
    @Override

    public void fastTravel(Asteroid A) {
        if (currentPosition.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = currentPosition.getGate();

            if (Gate1.isActive) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                if (Gate2.getCurrentPosition().acceptTraveller(this)) {
                    currentPosition.getSettlersOnAsteroid().remove(this);  // settler is removed from the list
                    currentPosition = Gate2.getCurrentPosition();  // successful travel
                    isHidden = false;
                    hide(Gate2.getCurrentPosition()); // hides when successfully travels}

                } else {
                    System.out.println("Destination does not have free space");
                }

            } else {
                System.out.println("The other pair is not deployed yet!");
            }
        } else {
            System.out.println("This asteroid does not have a tansport gate.");
        }
        Tester.generator(Tester.outputFile, "travelled through the gate at " + currentPosition.getX()
                + " " + currentPosition.getY() + " " + currentPosition.getZ());

    }

    /**
     * This method is used to hide the object in a hollow Asteroid. This will happen automatically when a traveller is on the hollow Astteroid.
     *
     * @param A Asteroid that the Traveller is hiding on
     */
    @Override
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
                if ((cntRobots == 1 && cntSettlers == 0)
                        || (cntRobots == 0 && cntSettlers == 0)) { // 2 robots or 1 robot and 1 settler
                    isHidden = true;
                }
            }
        }
        Tester.generator(Tester.outputFile, "hide A" + currentPosition.getX()
                + currentPosition.getY() + currentPosition.getZ());

    }

    /**
     * This method is used to drill into an Asteroid.
     *
     * @param A Asteroid that is being drilled
     */
    @Override
    public void drill(Asteroid A) {
        int depth = currentPosition.getDepth(); // gets the mantle length of the asteroid
        if (depth != 0) { // if it is not hollow, then we drill
            currentPosition.decreaseRockCover(); //drilling
        } else {
            System.out.println("The Asteroid is completely drilled through");
        }

        Tester.generator(Tester.outputFile, "drilled A" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());

    }

    /**
     * This method mines the resource of the Asteroid the Settler is currently on.
     *
     * @param A Asteroid that is mined.
     */
    public void mine(Asteroid A) {
        int depth = currentPosition.getDepth(); // gets the mantle length of the asteroid
        if (depth != 0) { // if it is not hollow, then we drill
            System.out.println("The Asteroid is not completely drilled through");
            return;
        } else {
            if (currentPosition.getHollow()) {
                System.out.println("Nothing to mine");
                return;
            } else {
                ResourceBase myResource = currentPosition.getResourceOfAsteroid().get(0);
                currentPosition.emptyAsteroid();
                this.itsInventory.addResource(myResource);
                Tester.generator(Tester.outputFile, "mined A" + currentPosition.getX() + currentPosition.getY()
                        + currentPosition.getZ());

            }
        }

    }

    /**
     * This method is used to build a robot.
     */
    public void buildRobot() {
        List<ResourceBase> requiredResources = new ArrayList<>();
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Uranium"));
        requiredResources.add(new ResourceBase("Carbon"));

        if (itsInventory.checkResources(requiredResources)) {
            Robot myRobot = new Robot();
            myRobot.setCurrentPosition(this.currentPosition);
            List<ResourceBase> r = itsInventory.getStoredResources();
            this.itsInventory.removeResources(findByType(r, "Iron"));
            this.itsInventory.removeResources(findByType(r, "Carbon"));
            this.itsInventory.removeResources(findByType(r, "Uranium"));
            int rndX = new Random().nextInt(5) - 2; // random number between -2 and 2
            int rndY = new Random().nextInt(5) - 2; // random number between -2 and 2
            int rndZ = new Random().nextInt(5) - 2; // random number between -2 and 2
            myRobot.travel(Game.asteroids.get(currentPosition.getX() + rndX).get(currentPosition.getY() + rndY).get(currentPosition.getY() +
                    rndZ));
            myRobot.drill(myRobot.getCurrentPosition());
            Tester.generator(Tester.outputFile, "built ROBOT");
        }
    }

    /**
     * This is a helper method to find resource by its type
     *
     * @param listR list of resources to look in
     * @param type  of the resource
     */
    public static ResourceBase findByType(Collection<ResourceBase> listR, String type) {
        return listR.stream().filter(resource -> type.equals(resource.getResourceType())).findFirst().orElse(null);
    }

    /**
     * This method is used when the settler wants to build a SpaceStation.
     *
     * @param A Asteroid on which the SpaceStation is built.
     */
    public void buildSpaceStation(Asteroid A) {
        List<ResourceBase> requiredResources = new ArrayList<>();
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Uranium"));
        requiredResources.add(new ResourceBase("Uranium"));
        requiredResources.add(new ResourceBase("Uranium"));
        requiredResources.add(new ResourceBase("WaterIce"));
        requiredResources.add(new ResourceBase("WaterIce"));
        requiredResources.add(new ResourceBase("WaterIce"));
        requiredResources.add(new ResourceBase("Carbon"));
        requiredResources.add(new ResourceBase("Carbon"));
        requiredResources.add(new ResourceBase("Carbon"));

        List<ResourceBase> fakeAsteroidResources = currentPosition.getStoredResourceOfAsteroid();

        for (int i = 0; i < requiredResources.size(); i++) {
            ResourceBase r = findByType(currentPosition.getStoredResourceOfAsteroid(), requiredResources.get(i).getResourceType());
            if (r != null) // checks if there is resource on the asteroid
            {
                requiredResources.remove(i);
                fakeAsteroidResources.remove(r);
            }
        }

        if (itsInventory.checkResources(requiredResources)) {
            Tester.generator(Tester.outputFile, "built SPACESTATION");
            Game.endGame();
        }
    }

    /**
     * This method is used to build a transportation-gate and store it in the settler’s inventory.
     */
    public void buildTransportGate() {
        List<ResourceBase> requiredResources = new ArrayList<>();
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Iron"));
        requiredResources.add(new ResourceBase("Uranium"));
        requiredResources.add(new ResourceBase("WaterIce"));

        if (itsInventory.checkResources(requiredResources)) {
            List<ResourceBase> r = itsInventory.getStoredResources();
            this.itsInventory.removeResources(findByType(r, "Iron"));
            this.itsInventory.removeResources(findByType(r, "Iron"));
            this.itsInventory.removeResources(findByType(r, "WaterIce"));
            this.itsInventory.removeResources(findByType(r, "Uranium"));

            TransportGate tg1 = new TransportGate();
            TransportGate tg2 = new TransportGate();
            //Pair the gates together
            tg1.makePair(tg2);
            tg2.makePair(tg1);

            this.itsInventory.addGates(tg1, tg2);

            Tester.generator(Tester.outputFile, "built TRANSPORTGATES");

        }
    }

    /**
     * This method deploys a transport gate on the asteroid the settler is currently on.
     *
     * @param A Asteroid that the TransportGate is deployed on
     */
    public void deployTransportGate(Asteroid A) {

        TransportGate tg = this.itsInventory.getStoredGates().get(0);
        tg.setCurrentPosition(A);
        this.itsInventory.removeGate(tg);

        if (tg.getPair().getCurrentPosition() != null) {
            tg.activateTransportGate();
            tg.getPair().activateTransportGate();
        }
        Tester.generator(Tester.outputFile, "deployed TG" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());
    }


    /**
     * This method stores resources in hollow asteroids.
     *
     * @param type of the resource is stored on asteroid
     */
    public void storeResources(String type) {
        ResourceBase rb = findByType(itsInventory.getStoredResources(), type);
        if (rb != null) {
            //ResourceBase rb = this.itsInventory.getStoredResources().get(0);
            currentPosition.getStoredResourceOfAsteroid().add(rb);
            this.itsInventory.removeResources(rb);
            Tester.generator(Tester.outputFile, "left" + rb.getResourceType() + "on" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());
        }

    }

    /**
     * This method is used to pick up resources which are stored on asteroids by settlers.
     *
     * @param type which Settler picks up the resource
     */
    public void pickUpResources(String type) {
        ResourceBase rb = findByType(currentPosition.getStoredResourceOfAsteroid(), type);
        if (rb != null) {
            this.itsInventory.getStoredResources().add(rb);
            currentPosition.getStoredResourceOfAsteroid().remove(rb);
            Tester.generator(Tester.outputFile, "picked up" + rb.getResourceType() + "from" + currentPosition.getX() + currentPosition.getY() + currentPosition.getZ());
        }
    }



    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    //public String getName() { return name; }
    //public void setName(String name) { this.name = name; }


    //public int getLiveCounter() { return liveCounter; }
    //public void setLiveCounter(int liveCounter) { this.liveCounter = liveCounter; }

    public Inventory getItsInventory() {
        return itsInventory;
    }

    public void setItsInventory(Inventory itsInventory) {
        this.itsInventory = itsInventory;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }


    //</editor-fold>

}
