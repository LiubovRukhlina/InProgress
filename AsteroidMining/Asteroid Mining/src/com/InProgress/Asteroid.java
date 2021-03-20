package com.InProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asteroid extends PlaceBase{

    /** @Yves!
     * Asteroid needs a name in constructor
     * from Liuba**/

    private String name;

    public String getName() {
        return name;
    }

    private int rockCover;

    private ArrayList<Settler> settlersOnAsteroid = new ArrayList<Settler>();

    private ArrayList<Robot> robotsOnAsteroid = new ArrayList<Robot>();

    private ArrayList<Settler> settlersInAsteroid = new ArrayList<Settler>();

    private ArrayList<Robot> robotsInAsteroid = new ArrayList<Robot>();

    private ArrayList<ResourceBase> resourceOfAsteroid = new ArrayList<ResourceBase>();

    private ArrayList<ResourceBase> storedResourceOfAsteroid = new ArrayList<ResourceBase>();

    private Boolean isAtPerihelion;

    private Boolean isHollow;

    private Boolean isRadioactive;

    private Boolean hasGate;


    /**
     * Gets the remaining rockCover of this Asteroid.
     *
     * @return  the remaining rockCover of this Asteroid.
     *          It must be greater or equal to 0.
     */
    public int getDepth() {

        System.out.println("How thick is the mantle? Enter number between 1 and 5."); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();

        setRockCover(in); // Sets the rockCover of this Asteroid to the user input.

        scan.close();

        return this.rockCover;
    }



    public void decreaseRockCover() {

        this.rockCover--;
    }


    public void sunState(Sun S) {

        // should change the isAtPerihelion attribute. How?
        // asks S if it has to change its state? Random return value.
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


    public void setRockCover(int rockCover) {
        this.rockCover = rockCover;
    }

    public Boolean getAtPerihelion() {
        return isAtPerihelion;
    }

    public void setAtPerihelion(Boolean atPerihelion) {
        isAtPerihelion = atPerihelion;
    }

    public Boolean getHollow() {
        return isHollow;
    }

    public void setHollow(Boolean hollow) {
        isHollow = hollow;
    }

    public Boolean getRadioactive() {
        return isRadioactive;
    }

    public void setRadioactive(Boolean radioactive) {
        isRadioactive = radioactive;
    }

    public Boolean getHasGate() {
        return hasGate;
    }

    public void setHasGate(Boolean hasGate) {
        this.hasGate = hasGate;
    }

    public List<Settler> getSettlersOnAsteroid() {
        return settlersOnAsteroid;
    }

    public List<Robot> getRobotsOnAsteroid() {
        return robotsOnAsteroid;
    }

    public List<Settler> getSettlersInAsteroid() {
        return settlersInAsteroid;
    }

    public List<Robot> getRobotsInAsteroid() {
        return robotsInAsteroid;
    }

    public List<ResourceBase> getStoredResourceOfAsteroid() {
        return storedResourceOfAsteroid;
    }
    /** @Yves
     * getter for both types of resources with 1 parameter (single resource)**/


}
