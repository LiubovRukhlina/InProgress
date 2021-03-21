package com.InProgress;

import javax.annotation.Resource;
import java.util.Scanner;

public class Settler extends TravellerBase {
    /*private String name;

    public Settler(String name) {
        this.name = name;
    }
*/
    private int liveCounter = 1;
    private Inventory itsInventory = new Inventory();

    /**
     *Gets the value of the liveCounter of the Settler
     * @return remaining number of lives
     */
    public int getLiveCounter() {
        return liveCounter;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public void setLiveCounter(int liveCounter) {
        this.liveCounter = liveCounter;
    }

    public Inventory getItsInventory() {

        return itsInventory;
    }

    public void setItsInventory(Inventory itsInventory) {
        this.itsInventory = itsInventory;
    }

    /**
     *This method mines the resource of the Asteroid the Settler is currently on.
     * @param A Asteroid that is mined.
     */
    public void mine(Asteroid A){
        System.out.println("Mine");
        if (!A.getHollow()){
            System.out.println("Only hollow Asteroid can be mined");
            return;
        }

        if (this.itsInventory.getStoredResources().size() >= 10){
            System.out.println("Inventory is full. storeResource before mining");
            return;
        }

        //What is getResource?? later
        System.out.println("Is the core empty?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        //scan.close();
        if (in.equals("yes")){
            System.out.println("Nothing to mine"); // Asks the user for his input.
            //Scanner scan = new Scanner(System.in);
            return;
        }
        if (in.equals("no")){
            System.out.println("Which Resource is in the core?"); // Asks the user for his input.

            in = scan.next();
            ResourceBase myResource = new ResourceBase(in);
            scan.close();

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
            this.currentPosition.setLocation(myRobot);
            this.itsInventory.removeResources(new ResourceBase("Carbon")); //Implemented later?!
            PlaceBase A2 = myRobot.getCurrentPosition().getNeighbors().get(0);
            if (A2 instanceof Asteroid) {
                myRobot.travel((Asteroid) A2);
                myRobot.drill((Asteroid)A2);
            }
        }
    }

    /**
     * This method is used when the settler wants to build a SpaceStation.
     * @param A Asteroid on which the SpaceStation is built.
     */
    public void buildSpaceStation(Asteroid A){
        System.out.println("Build Space Station");

        if (checkResources()){

            this.itsInventory.removeResources(new ResourceBase("Carbon"));
            System.out.println("Space station is built. The game ends, Settlers won");
            Game.endGame();

        }
    }

    /**
     * This method is used to build a transportation-gate and store it in the settler’s inventory.
     */
    public void buildTransportGate(){
        System.out.println("Build Transport gate");

        if (checkResources()){
            this.itsInventory.removeResources(new ResourceBase("Carbon")); //Implemented later?!
           this.itsInventory.addGates(new TransportGate(), new TransportGate());

            System.out.println("Two Gates were built and added.");
        }
    }

    /**
     * This method deploys a transport gate on the asteroid the settler is currently on.
     * @param A Asteroid that the TransportGate is deployed on
     */
    public void deployTransportGate(Asteroid A){
        System.out.println("Deploy Transport Gate");
        TransportGate tg = this.itsInventory.getStoredGates().get(0);
        tg.setCurrentPosition(A);
        this.itsInventory.removeGate(tg);
        System.out.println("Is this the second gate?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){
            tg.activateTransportGate();
            //@Sahej how to activate my pair?
            System.out.println("The Gates are activated");
        }

    }

    /**
     * This method checks whether the settler has enough resources.
     * @return true if Settler has enough resources, and false otherwise.
     */
    public boolean checkResources(){
        System.out.println("Check resources");
        System.out.println("Are there enough resources?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     *
     * This method stores resources in hollow asteroids.
     * @param A Asteroid that the resource is stored on
     */
    public void storeResources(Asteroid A){
        System.out.println("Store Resources");
        ResourceBase rb =  this.itsInventory.getStoredResources().get(0);
        this.itsInventory.removeResources(rb);
        //asteroid needs a method for adding a resource to it - > setter
        System.out.println("The Resource was stored on Asteroid" + A.getName());

    }

    /**
     * This method is used to pick up re-sources which are stored on asteroids by settlers.
     * @param A Asteroid from which Settler picks up the resource
     */
    public void pickUpResources(Asteroid A){
        System.out.println("Pick up resources");
        System.out.println("Which resource is stored on the Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        //missing A.decreaseStoredResource(); -> getter of the list similar to the way you do it with when you add it in the next line.
        // but you should first add the resource to the inventory and then remove it from the asteroid.
        this.itsInventory.addResource(A.getStoredResourceOfAsteroid().get(0));
        System.out.println("The resource was picked up");
    }
}
