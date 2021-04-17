package com.InProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Asteroid extends PlaceBase{


    //<editor-fold desc="Attributes">

    private int x;
    private int y;
    private int z;
    private int rockCover;
    private String name;
    public TransportGate gate;
    private ArrayList<Settler> settlersOnAsteroid;
    private ArrayList<Robot> robotsOnAsteroid;
    //private ArrayList<Settler> settlersInAsteroid;
    //private ArrayList<Robot> robotsInAsteroid;
    private ArrayList<ResourceBase> resourceOfAsteroid;
    private ArrayList<ResourceBase> storedResourceOfAsteroid;
    private Boolean isAtPerihelion;
    private Boolean isHollow;
    private Boolean isRadioactive;
    private Boolean hasGate;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor for later use in the game.
     *
     * @param x X-coordinate of this Asteroid
     * @param y Y-coordinate of this Asteroid
     * @param z Z-coordinate of this Asteroid
     * @param rnd random value used to determine the Resource
     */
    public Asteroid(int x, int y, int z, int rnd) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = "A" + x + y +z;
        this.rockCover = new Random().nextInt(10); // random number 0=<rockCover<10
        this.isAtPerihelion = false;
        this.resourceOfAsteroid = new ArrayList<>();

        switch (rnd) { // TODO see Issue #13: Which resource do we assign?
            case 1: { // Assigns Carbon to this Asteroid
                this.resourceOfAsteroid.add(new Carbon("Carbon"));
                this.isHollow = false;
                this.isRadioactive = false;
            } break;
            case 2: { // Assigns Iron to this Asteroid
                this.resourceOfAsteroid.add(new Iron("Iron"));
                this.isHollow = false;
                this.isRadioactive = false;
            }break;
            case 3: { // Assigns Uranium to this Asteroid
                this.resourceOfAsteroid.add(new Uranium("Uranium"));
                this.isHollow = false;
                this.isRadioactive = true;
            }break;
            case 4: { // Assigns WaterIce to this Asteroid
                this.resourceOfAsteroid.add(new WaterIce("WaterIce"));
                this.isHollow = false;
                this.isRadioactive = false;
            }break;
            case 5: { // Assigns no Resource to this Asteroid, s. t. this Asteroid is hollow
                this.isHollow = true;
                this.isRadioactive = false;
            }break;
        }

        this.hasGate = false; // initialized as false.
        this.settlersOnAsteroid = new ArrayList<>();
        this.robotsOnAsteroid = new ArrayList<>();
        // this.settlersInAsteroid = new ArrayList<>();
        // this.robotsInAsteroid = new ArrayList<>();
        this.storedResourceOfAsteroid = new ArrayList<>();
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Gets the remaining rockCover of this Asteroid.
     *
     * @return  the remaining rockCover of this Asteroid.
     *          It must be greater or equal to 0.
     */
    public int getDepth() {
        return this.rockCover;
    }

    /**
     * Decreases the rockCover of this Asteroid by 1 unit.
     */
    public void decreaseRockCover() {
        this.rockCover--;
    }

    /**
     * Removes the resource of this Asteroid and
     * make this Asteroid hollow.
     */
    public void emptyAsteroid() {
        this.resourceOfAsteroid.clear(); // Removes the resource of this Asteroid.
        this.setHollow(true); // This Asteroid is hollow.
        this.setRadioactive(false); // A hollow Asteroid cannot be radioactive.
    }

    /**
     * Adds a new traveller to the traveller list of the asteroid.
     * If the traveller is a settler it first only accepts this asteroid only accepts the settler
     * if there are less then 3 settlers on it and at most 1 of them is from the same Player.
     * @param traveller new traveller arriving at the asteroid
     */
    public boolean acceptTraveller(TravellerBase traveller) {

        int settlerCounter = 0;

        // if the Traveller is a Robot it will be always accepted.
        if (traveller instanceof Robot) {
            this.robotsOnAsteroid.add((Robot) traveller); // add robot to this asteroid
            return true;

        } else {
            // check number of setters on this Asteroid
            if (this.settlersOnAsteroid.size() < 3) {
                for (Settler settler : settlersOnAsteroid) {
                    // check if how many settlers of the same player are there
                    if (traveller.getPlayerID() == settler.getPlayerID()) {
                        settlerCounter++;
                    }
                }
                if(settlerCounter < 2) {
                    this.settlersOnAsteroid.add((Settler) traveller); // add settler to this asteroid
                    return true;
                } else {
                    return false; // in case there are already 2 settlers of the same player
                }

            } else {
                return false; // in case there are already 3 settlers in total
            }
        }
    }

    /**
     * Removes one of the stored resources of this Asteroid.
     */
    public void decreaseStoredResource() {
        this.storedResourceOfAsteroid.remove(0);
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getZ() { return z; }
    public void setZ(int z) { this.z = z; }

    public TransportGate getGate() { return gate; }
    public void setGate(TransportGate gate) { this.gate = gate; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setRockCover(int rockCover) { this.rockCover = rockCover; }

    public Boolean getAtPerihelion() { return isAtPerihelion; }
    public void setAtPerihelion(Boolean atPerihelion) {
        isAtPerihelion = atPerihelion;
        if(isAtPerihelion && isRadioactive && rockCover == 0)
        {
            // TODO explosion of uranium
            // (Uranium) this.resourceOfAsteroid.get(0).explode();
        }
    }

    public Boolean getHollow() { return isHollow; }
    public void setHollow(Boolean hollow) { isHollow = hollow; }

    public Boolean getRadioactive() { return isRadioactive; }
    public void setRadioactive(Boolean radioactive) { isRadioactive = radioactive; }

    public Boolean getHasGate() { return hasGate; }
    public void setHasGate(Boolean hasGate) { this.hasGate = hasGate; }

    public List<Settler> getSettlersOnAsteroid() { return this.settlersOnAsteroid; }
    public void setSettlersOnAsteroid(Settler newSettler) { this.settlersOnAsteroid.add(newSettler); }

    public List<Robot> getRobotsOnAsteroid() { return this.robotsOnAsteroid; }
    public void setRobotsOnAsteroid(Robot newRobot) { this.robotsOnAsteroid.add(newRobot); }

    //public List<Settler> getSettlersInAsteroid() { return this.settlersInAsteroid; }
    //public void setSettlersInAsteroid(Settler newSettler) { this.settlersInAsteroid.add(newSettler); }

    //public List<Robot> getRobotsInAsteroid() { return robotsInAsteroid; }
    //public void setRobotsInAsteroid(Robot newRobot) { this.robotsInAsteroid.add(newRobot); }

    public List<ResourceBase> getStoredResourceOfAsteroid() { return storedResourceOfAsteroid; }
    public void setStoredResourceOfAsteroid(ResourceBase newResource) { this.storedResourceOfAsteroid.add(newResource); }


    public List<ResourceBase> getResourceOfAsteroid() { return resourceOfAsteroid; }
    public void setResourceOfAsteroid(ResourceBase rb) { this.resourceOfAsteroid.add(rb); }

    //</editor-fold>
}
