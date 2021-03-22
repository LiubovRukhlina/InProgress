package com.InProgress;

import java.util.Scanner;

public class Settler extends TravellerBase {
    private Inventory itsInventory = new Inventory();

    /**
     * Constructor of the Settler class
     *
     * @param name Name of the Settler
     */
    public Settler(String name)
    {
        this.setName(name);
    }

    /**
     *This method mines the resource of the Asteroid the Settler is currently on.
     * @param A Asteroid that is mined.
     */
    public void mine(Asteroid A){
        System.out.println("mine()");
        System.out.println("Is the core empty?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")){
            System.out.println("Nothing to mine"); // Asks the user for his input.
            return;
        }
        if (in.equals("no")){
            System.out.println("Which Resource is in the core?"); // Asks the user for his input.
            in = scan.next();
            ResourceBase myResource = new ResourceBase(in);
            A.emptyAsteroid();
            this.itsInventory.addResource(myResource);
            System.out.println(this.getName() + " mined "+ in);
        }
    }

    /**
     * This method is used to build a robot.
     */
    public void buildRobot(){
        System.out.println("buildRobot()");

        if (checkResources()){
            Robot myRobot = new Robot();
            // I am not sure why we do it that way?
            // this.currentPosition.setLocation(myRobot);
            myRobot.setCurrentPosition(this.currentPosition);
            this.itsInventory.removeResources(new ResourceBase("Carbon"));
            Asteroid destAsteroid = myRobot.getCurrentPosition().getNeighbors().get(0);

            //After creation the Robot travels to the next Asteroid and drills
            myRobot.travel(destAsteroid);
            myRobot.drill(destAsteroid);
        }
    }

    /**
     * This method is used when the settler wants to build a SpaceStation.
     * @param A Asteroid on which the SpaceStation is built.
     */
    public void buildSpaceStation(Asteroid A){
        System.out.println("BbuildSpaceStation()");

        if (checkResources()){

            this.itsInventory.removeResources(new ResourceBase("Carbon"));
            Game.endGame();
        }
    }

    /**
     * This method is used to build a transportation-gate and store it in the settler’s inventory.
     */
    public void buildTransportGate(){
        System.out.println("buildTransportGate()");

        if (checkResources()){
            this.itsInventory.removeResources(new ResourceBase("Carbon"));//Implemented later?!
            //@Sahej made changes
            TransportGate tg1 = new TransportGate();
            TransportGate tg2 = new TransportGate();
            //Pair the gates together
            tg1.makePair(tg2);
            tg2.makePair(tg1);

            this.itsInventory.addGates(tg1,tg2);

            System.out.println("Two Gates were built and added.");
        }
    }

    /**
     * This method deploys a transport gate on the asteroid the settler is currently on.
     * @param A Asteroid that the TransportGate is deployed on
     */
    public void deployTransportGate(Asteroid A){
        System.out.println("deployTransportGate()");
        TransportGate tg = this.itsInventory.getStoredGates().get(0);
        tg.setCurrentPosition(A);
        this.itsInventory.removeGate(tg);

        System.out.println("Is this the second gate?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")){
            tg.activateTransportGate();
            System.out.println("The Gates are activated");
        }
    }

    /**
     * This method checks whether the settler has enough resources.
     * @return true if Settler has enough resources, and false otherwise.
     */
    public boolean checkResources(){
        System.out.println("checkResources()");
        System.out.println("Are there enough resources?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * This method stores resources in hollow asteroids.
     * @param A Asteroid that the resource is stored on
     */
    public void storeResources(Asteroid A){
        System.out.println("storeResources()");
        //ResourceBase rb =  this.itsInventory.getStoredResources().get(0);
        Carbon mockUpResource = new Carbon("Carbon"); // mockUpResource for the Skeleton
        A.setStoredResourceOfAsteroid(mockUpResource); // setter call
        this.itsInventory.removeResources(mockUpResource);
        System.out.println("The Resource was stored on Asteroid" + A.getName());
    }

    /**
     * This method is used to pick up resources which are stored on asteroids by settlers.
     * @param A Asteroid from which Settler picks up the resource
     */
    public void pickUpResources(Asteroid A){
        System.out.println("pickUpResources()");
        System.out.println("Is there a resource to pick up?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if(in.equals("yes")){
            Carbon mockUpResource = new Carbon("Carbon");
            //missing A.decreaseStoredResource(); -> getter of the list similar to the way you do it with when you add it in the next line.
            // but you should first add the resource to the inventory and then remove it from the asteroid.
            this.itsInventory.addResource(mockUpResource);

            A.getStoredResourceOfAsteroid().remove(mockUpResource);

            System.out.println("The resource was picked up");
        }
    }


    // getter and setter
    /*
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    */

    /**
     *Gets the value of the liveCounter of the Settler
     * @return remaining number of lives
     */
    // public int getLiveCounter() { return liveCounter; }
    // public void setLiveCounter(int liveCounter) { this.liveCounter = liveCounter; }

    public Inventory getItsInventory() {

        return itsInventory;
    }
    public void setItsInventory(Inventory itsInventory) {
        this.itsInventory = itsInventory;
    }


}
