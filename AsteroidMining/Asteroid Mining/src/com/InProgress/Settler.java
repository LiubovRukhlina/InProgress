package com.InProgress;

public class Settler extends TravellerBase {
    /*private String name;

    public Settler(String name) {
        this.name = name;
    }
*/
    private int liveCounter = 1;
    private Inventory itsInventory = new Inventory();

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

    public void mine(Asteroid A){
        if (A.isHollow == False){
            System.out.println("Only hollow Asteroid can be mined");
            return;
        }

        if (this.ItsInventory.storedResources >= 10){
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
        System.out.println("Which Resource is in the core?"); // Asks the user for his input.

        String in = scan.next();
        myResource = new ResourceBase(in);
        scan.close();

        A.emptyAsteroid();
        this.itsInventory.addResource(myResource);

    }



    public void buildRobot(){
        System.out.println("Are there enough resources available?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){
            myRobot = new Robot();
            this.currentPosition.setLocation(myRobot);
            this.itsInventory.removeResource(new ResourceBase()); //Implemented later?!
            Asteroid A2 = myRobot.getCurrentPosition().getNeighbors()[0];
            myRobot.travel(A2);
            myRobot.mine(A2);

        }
    }

    public void buildSpaceStation(Asteroid A){
        System.out.println("Are there enough resources available?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){

            this.itsInventory.removeResource(new ResourceBase());
            System.out.println("Space station is built. The game ends, Settlers won");
            Game.endGame();

        }
    }

    public void buildTransportGate(){
        System.out.println("Are there enough resources available?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){
            this.itsInventory.removeResource(new ResourceBase()); //Implemented later?!
           this.itsInventory.addGates(new TransportGate(), new TransportGate());

            System.out.println("Two Gates were built and added.");
        }
    }

    public void deployTransportGate(Asteroid A){
        TransportGate tg = this.itsInventory.storedGate[0];
        tg.setLocation(A);
        this.itsInventory.removeGate(tg);
        System.out.println("Is this the second gate?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        if (in.equals("yes")){
            tg.activateTransportGates();
            //how to activate my pair?
            System.out.println("The Gates are activated");
        }

    }

    /** should there be 2 checks for the robot and space station? how?**/
    public boolean checkResources(){

    }

    public void storeResources(Asteroid A){
        Resourcebase rb =  this.itsInventory.storedResources[0];
        this.itsInventory.removeResource(rb);
        //asteroid needs a method for adding a reslurce to it - > setter
        System.out.println("The Resource was stored on Asteroid %s", A.name);

    }

    public void pickUpResources(asteroid A){
        System.out.println("Which resource is stored on the Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
        //missing A.decreaseStoredResource(); -> getter
        this.itsInventory.addResource(A.getStoredResourceOfAsteroid()[0]);
        System.out.println("The resource was picked up");
    }
}
