package com.InProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asteroid extends PlaceBase{

    public ArrayList<Asteroid> neighbors;
    private String name;
    private int rockCover;
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


    /**
     * Constructor for later use in the game.
     * @all how and when do we assign the resources? from Yves
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
     * Changes the isAtPerihelion attribute of this Asteroid.
     * This Asteroid asks the Sun object if it must change its status.
     *
     * @param S Sun object
     */
    public void sunState(Sun S) {

        /**
         * @Vlad
         * How do the Sun and Asteroid interact?
         * from Yves
         */
        System.out.println("sunState()");
        this.setAtPerihelion(true); // Sun should have a function that returns random boolean values.
    }

    /**
     * Removes the resource of this Asteroid and
     * make this Asteroid hollow.
     */
    public void emptyAsteroid() {

        System.out.println("emptyAsteroid()");

        //this.resourceOfAsteroid.clear(); // Removes the resource of this Asteroid.

        this.setHollow(true); // This Asteroid is hollow.
        this.setRadioactive(false); // A hollow Asteroid cannot be radioactive.
    }

    /**
     * Changes the current location of traveller to its destination.
     *
     * @param dest Destination of the traveller
     * @param traveller traveller changing its location
     */
    @Override
    public void changeLocation(PlaceBase dest, TravellerBase traveller) {
        System.out.println("changeLocation()");

        traveller.setCurrentPosition((Asteroid) dest);
    }

    public void decreaseStoredResource(int index){
        System.out.println("decreaseStoredResource()");

        // we do not remove a real resource in the skeleton
        //storedResourceOfAsteroid.remove(index);
    }

    // Getters and setters
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

    /**
     * Returns a list of all Setters who are currently on this Asteroid.
     *
     * @return List of all Settlers on this Asteroid.
     */
    public List<Settler> getSettlersOnAsteroid() {
        return this.settlersOnAsteroid;
    }

    /**
     * Adds a new Settler to this Asteroids list of Settlers currently on it.
     *
     * @param newSettler new Settler who arrived on this Asteroid
     */
    public void setSettlersOnAsteroid(Settler newSettler) {
        this.settlersOnAsteroid.add(newSettler);
    }

    /**
     * Returns a list of all Robots which are currently on this Asteroid.
     *
     * @return List of all Robots on this Asteroid.
     */
    public List<Robot> getRobotsOnAsteroid() {
        return this.robotsOnAsteroid;
    }

    /**
     * Adds a new Robot to this Asteroids list of Settlers currently on it.
     *
     * @param newRobot new Robot which arrived on this Asteroid
     */
    public void setRobotsOnAsteroid(Robot newRobot) {
        this.robotsOnAsteroid.add(newRobot);
    }

    /**
     * Returns a list all of Settlers hidden in the Asteroid.
     *
     * @return List all of Settlers hidden in the Asteroid
     */
    public List<Settler> getSettlersInAsteroid() {
        return this.settlersInAsteroid;
    }

    /**
     * Adds a new Settler to the list of Settlers in this Asteroid.
     *
     * @param newSettler new Settler hidden in this Asteroid
     */
    public void setSettlersInAsteroid(Settler newSettler) {
        this.settlersInAsteroid.add(newSettler);
    }

    /**
     * Returns a list of all of Robots hidden in the Asteroid.
     *
     * @return List of all Robots hidden in the Asteroid
     */
    public List<Robot> getRobotsInAsteroid() {
        return robotsInAsteroid;
    }

    /**
     * Adds a new Robot to the list of Robots in this Asteroid.
     *
     * @param newRobot new Robot hidden in this Asteroid
     */
    public void setRobotsInAsteroid(Robot newRobot) {
        this.robotsInAsteroid.add(newRobot);
    }

    /**
     * Returns a list of all Resources currently stored on the Asteroid.
     *
     * @return List of all Resources stored on the Asteroid
     */
    public List<ResourceBase> getStoredResourceOfAsteroid() {
        return storedResourceOfAsteroid;
    }

    /**
     * Adds a new Resource to the list of stored Resources of this Asteroid.
     *
     * @param newResource new Resource that is stored on this Asteroid
     */
    public void setStoredResourceOfAsteroid(ResourceBase newResource) {
        this.storedResourceOfAsteroid.add(newResource);
    }

    /**
     * Returns the list of Resource of this Asteroid.
     * Since Asteroid are homogeneous and store only 1 unit,
     * the list has only one element.
     *
     * @return List of Resource of this Asteroid
     */
    public List<ResourceBase> getResourceOfAsteroid() {
        return resourceOfAsteroid;
    }

    public void setResourceOfAsteroid(ResourceBase rb) {
        this.resourceOfAsteroid.add(rb);
    }

}
