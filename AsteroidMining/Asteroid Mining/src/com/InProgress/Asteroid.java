package com.InProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asteroid extends PlaceBase{


    //<editor-fold desc="Attributes">

    private int rockCover;
    private String name;
    public ArrayList<Asteroid> neighbors;
    private List<Settler> settlersOnAsteroid;
    private List<Robot> robotsOnAsteroid;
    private List<Settler> settlersInAsteroid;
    private List<Robot> robotsInAsteroid;
    private List<ResourceBase> resourceOfAsteroid;
    private List<ResourceBase> storedResourceOfAsteroid;
    private Boolean isAtPerihelion;
    private Boolean isHollow;
    private Boolean isRadioactive;
    private Boolean hasGate;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor for later use in the game.
     *
     * @param name Name of this Asteroid
     * @param rockCover Thickness of the rockCover
     * @param isAtPerihelion determines if this Asteroid is at perihelion
     */
    public Asteroid(String name, int rockCover, Boolean isAtPerihelion) {
        this.name = name;
        this.rockCover = rockCover;
        this.isAtPerihelion = isAtPerihelion;
        this.isHollow = false; // initialized as false. Must be changed when resource is assigned.
        this.isRadioactive = false; // initialized as false. Must be changed when resource is assigned.
        this.hasGate = false; // initialized as false.
        this.settlersOnAsteroid = new ArrayList<>();
        this.robotsOnAsteroid = new ArrayList<>();
        this.settlersInAsteroid = new ArrayList<>();
        this.robotsOnAsteroid = new ArrayList<>();
        this.resourceOfAsteroid = new ArrayList<>();
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

        System.out.println("getDepth()");
        System.out.println("How thick is the mantle? Enter number between 1 and 5."); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();
        setRockCover(in); // Sets the rockCover of this Asteroid to the user input.
        return this.rockCover;
    }

    /**
     * Decreases the rockCover of this Asteroid by 1 unit.
     */
    public void decreaseRockCover() {

        System.out.println("decreaseRockCover()");
        this.rockCover--;
        System.out.println("The remaining rockCover = " + this.rockCover);
    }

    /**
     * Removes the resource of this Asteroid and
     * make this Asteroid hollow.
     */
    public void emptyAsteroid() {

        System.out.println("emptyAsteroid()");

        // there are no resources assigned in the skeleton therefore the clear method is not called.
        //this.resourceOfAsteroid.clear(); // Removes the resource of this Asteroid.

        this.setHollow(true); // This Asteroid is hollow.
        this.setRadioactive(false); // A hollow Asteroid cannot be radioactive.
    }

    /**
     * Adds a new traveller to the traveller list of the asteroid
     * @param traveller new traveller arriving at the asteroid
     */
    public void acceptTraveller(TravellerBase traveller) {
        System.out.println("acceptTraveller()");
        if (traveller instanceof Robot) {
            this.robotsOnAsteroid.add((Robot) traveller);
        } else {
           this.settlersOnAsteroid.add((Settler) traveller);
        }
    }

    /**
     * Removes one of the stored resources of this Asteroid.
     * @param index index of the resource that is removed
     */
    public void decreaseStoredResource(int index) {
        System.out.println("decreaseStoredResource()");

        // we do not remove a real resource in the skeleton
        //storedResourceOfAsteroid.remove(index);
    }
    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public ArrayList<Asteroid> getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(ArrayList<Asteroid> neighbors) {
        this.neighbors = neighbors;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setRockCover(int rockCover) { this.rockCover = rockCover; }

    public Boolean getAtPerihelion() { return isAtPerihelion; }
    public void setAtPerihelion(Boolean atPerihelion) { isAtPerihelion = atPerihelion; }

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

    public List<Settler> getSettlersInAsteroid() { return this.settlersInAsteroid; }
    public void setSettlersInAsteroid(Settler newSettler) { this.settlersInAsteroid.add(newSettler); }

    public List<Robot> getRobotsInAsteroid() { return robotsInAsteroid; }
    public void setRobotsInAsteroid(Robot newRobot) { this.robotsInAsteroid.add(newRobot); }

    public List<ResourceBase> getStoredResourceOfAsteroid() { return storedResourceOfAsteroid; }
    public void setStoredResourceOfAsteroid(ResourceBase newResource) { this.storedResourceOfAsteroid.add(newResource); }


    public List<ResourceBase> getResourceOfAsteroid() { return resourceOfAsteroid; }
    public void setResourceOfAsteroid(ResourceBase rb) { this.resourceOfAsteroid.add(rb); }

    //</editor-fold>
}
