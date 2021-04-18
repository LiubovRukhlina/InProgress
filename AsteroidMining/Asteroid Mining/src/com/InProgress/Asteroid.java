package com.InProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    private ArrayList<ResourceBase> resourceOfAsteroid;
    private ArrayList<ResourceBase> storedResourceOfAsteroid;
    private Boolean isAtPerihelion;
    private Boolean isHollow;
    private Boolean isRadioactive;
    private Boolean hasGate;
    private Boolean isExploded;

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

        switch (rnd) { // switch case to determine which resource is assigned
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
        this.isExploded = false; // initialized as false.
        this.settlersOnAsteroid = new ArrayList<>();
        this.robotsOnAsteroid = new ArrayList<>();
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
     * Removes the resource of this Asteroid and make this Asteroid hollow.
     */
    public void emptyAsteroid() {
        this.resourceOfAsteroid.clear(); // Removes the resource of this Asteroid.
        this.setHollow(true); // This Asteroid is hollow.
        this.setRadioactive(false); // A hollow Asteroid cannot be radioactive.
    }

    /**
     * Adds a new settler to the settler list of the asteroid.
     * This asteroid only accepts the settler if there are less then 3 settlers on it
     * and at most 1 of them is from the same Player.
     * @param settler new settler arriving at the asteroid
     */
    public boolean acceptTraveller(Settler settler) {
        int settlerCounter = 0; // counter to check the number of Settlers of the same Player

        if (this.settlersOnAsteroid.size() < 3) { // checks if there are less than 3 settlers on this Asteroid
            for (Settler settlers : settlersOnAsteroid) {
                // check if how many settlers of the same player are there
                if (settlers.getPlayerID() == settler.getPlayerID()) {
                    settlerCounter++;
                }
            }
            if (settlerCounter < 2) { // checks if there are less then 2 Settlers of the same Player
                this.settlersOnAsteroid.add(settler); // add settler to this asteroid
                return true;
            } else {
                return false; // in case there are already 2 settlers of the same player
            }
        } else {
            return false; // in case there are already 3 settlers in total
        }
    }

    /**
     * Removes one of the stored resources of this Asteroid.
     */
    public void decreaseStoredResource() {
        this.storedResourceOfAsteroid.remove(0);
    }

    /**
     * Changes the atPerihelion attribute of this asteroid.
     * Checks whether the Resource has to explode or sublime.
     *
     * @param atPerihelion new value of the atPerihelion attribute
     */
    public void perihelionChanger(Boolean atPerihelion) {
        isAtPerihelion = atPerihelion;
        if(isAtPerihelion && isRadioactive && rockCover == 0) // checks if the Resource is Uranium and if it has to explode
        {
            this.resourceOfAsteroid.get(0).explode(this);
        }
        if(isAtPerihelion && this.getResourceOfAsteroid().get(0) instanceof WaterIce) // checks if the resource is WaterIce and if it has to sublime
        {
            this.resourceOfAsteroid.get(0).sublime(this);
        }
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
    public void setGate(TransportGate gate) {
        this.gate = gate;
        Tester.generator(Tester.outputFile, "created object TRANSPORTGATE at  " + this.getX() + " " +
                this.getY() + " " + this.getZ());
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setRockCover(int rockCover) {
        this.rockCover = rockCover;
        Tester.generator(Tester.outputFile, "modified " + this.getX() + " " +
                this.getY() + " " + this.getZ() + " ROCKCOVER to " + rockCover );
    }

    public Boolean getAtPerihelion() { return isAtPerihelion; }
    public void setAtPerihelion(Boolean atPerihelion) {
        isAtPerihelion = atPerihelion;
        Tester.generator(Tester.outputFile, "modified " + this.getX() + " " +
                this.getY() + " " + this.getZ() + " ISATPERIHELION to " + atPerihelion );
    }


    public Boolean getHollow() { return isHollow; }
    public void setHollow(Boolean hollow) {
        isHollow = hollow;
        Tester.generator(Tester.outputFile, "modified " + this.getX() + " " +
                this.getY() + " " + this.getZ() + " ISHOLLOW to " + hollow );
    }

    public Boolean getRadioactive() { return isRadioactive; }
    public void setRadioactive(Boolean radioactive) { isRadioactive = radioactive; }

    public Boolean getHasGate() { return hasGate; }
    public void setHasGate(Boolean hasGate) { this.hasGate = hasGate; }

    public List<Settler> getSettlersOnAsteroid() { return this.settlersOnAsteroid; }
    public void setSettlersOnAsteroid(Settler newSettler) { this.settlersOnAsteroid.add(newSettler); }

    public List<Robot> getRobotsOnAsteroid() { return this.robotsOnAsteroid; }
    public void setRobotsOnAsteroid(Robot newRobot) { this.robotsOnAsteroid.add(newRobot); }

    public List<ResourceBase> getStoredResourceOfAsteroid() { return storedResourceOfAsteroid; }
    public void setStoredResourceOfAsteroid(ResourceBase newResource) {
        this.storedResourceOfAsteroid.add(newResource);
        Tester.generator(Tester.outputFile, "added " +newResource.getResourceType().toUpperCase() + this.getX() + " " +
                this.getY() + " " + this.getZ() + " STOREDRESOURCES");
    }

    public List<ResourceBase> getResourceOfAsteroid() { return resourceOfAsteroid; }
    public void setResourceOfAsteroid(ResourceBase rb) {
        this.resourceOfAsteroid.add(rb);
        Tester.generator(Tester.outputFile, "added " + rb.getResourceType().toUpperCase() + this.getX() + " " +
                this.getY() + " " + this.getZ() + " RESOURCEOFASTEROID");
    }

    public Boolean getExploded() { return isExploded; }
    public void setExploded(Boolean exploded) {
        isExploded = exploded;
        Tester.generator(Tester.outputFile, "exploded " + this.getX() + " " +
                this.getY() + " " + this.getZ());
    }
    //</editor-fold>
}
