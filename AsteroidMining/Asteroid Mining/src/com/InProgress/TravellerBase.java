package com.InProgress;

import java.util.Scanner;

public abstract class TravellerBase {

    //<editor-fold desc="Attributes">

    protected String name;
    protected int playerID;
    protected Asteroid currentPosition;
    protected boolean isAlive = true;
    protected boolean isHidden = false;


    //</editor-fold>


    //<editor-fold desc="Constructor">

    public TravellerBase() { }

    public TravellerBase(String name, Asteroid currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * moves the object to the chosen Asteroid.
     * @param A destination Asteroid
     */
    public void travel(Asteroid A) {
        System.out.println("travel()");
        if (!A.getNeighbors().contains(this.currentPosition))
        {
            System.out.println("You cannot travel to the non-neighboring Asteroid");
            return;
        }
        this.setCurrentPosition(A);
        this.currentPosition.acceptTraveller(this);
        String s1;
        if (this instanceof Robot) {
            s1 = String.format("Robot travelled to Asteroid %s", A.getName());
        }
        else {
            s1 = String.format("Settler%s travelled to Asteroid %s", this.getName(), A.getName());
        }
        System.out.println(s1);
        System.out.println("Is this a hollow Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")){
            this.hide(A);
        }
    }

    /**
     * This method is used for travel by using a transport gate.
     * @param TG TransportGate that the Traveller is travelling through
     */
    public void fastTravel(TransportGate TG) {
        System.out.println("fastTravel()");

        if (TG.getPair().getActive() == false)
        {
            System.out.println("Transport Gate doesn't have a pair");
            return;
        }
        else
        {
            this.setCurrentPosition(TG.getPair().getCurrentPosition());
            this.currentPosition.acceptTraveller(this);
            String s1 = String.format("Settler%s travelled to Asteroid %s", this.name, TG.getPair().getCurrentPosition().getName());
            System.out.println(s1);
            System.out.println("Is this a hollow Asteroid?"); // Asks the user for his input.
            Scanner scan = new Scanner(System.in);
            String in = scan.next();
            if (in.equals("yes")){
                this.hide(TG.getPair().getCurrentPosition());
            }
        }
    }

    /**
     * This method is used to hide the object in a hollow Asteroid. This will happen automatically when a traveller is on the hollow Astteroid.
     * @param A Asteroid that the Traveller is hiding on
     */
    public void hide(Asteroid A) {
        System.out.println("hide()");
        System.out.println("Is there enough space available?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")) {
            String s1;
            setHidden(true);
            if (this instanceof Robot) {
                s1 = String.format("Robot is hidden in Asteroid %s", A.getName());
            }
            else {
                s1 = String.format("Settler%s is hidden in Asteroid %s", this.getName(), A.getName());
            }
            System.out.println(s1);
        }
    }

    /**
     * This method is used to drill into an Asteroid.
     * @param A Asteroid that is being drilled
     */
    public void drill(Asteroid A) {
        System.out.println("drill()");
        int depth = A.getDepth();

        if(depth == 1){
            System.out.println("rockCover = 1 the asteroid might explode.");
            System.out.println("Is this Asteroid radioactive?");
            Scanner scan = new Scanner(System.in);
            String in = scan.next();
            if (in.equals("yes")){
                A.setResourceOfAsteroid(new Uranium("Uranium")); // creates a Uranium Resource on the Asteroid.
                System.out.println("Is this Asteroid at perihelion?");
                in = scan.next();
                if( in.equals(("yes"))){
                   Uranium mockUp = (Uranium) A.getResourceOfAsteroid().get(0);
                   mockUp.explode(A);
                }

            }
        } else {
            A.decreaseRockCover();
        }
    }

    /**
     * This method kills the object.
     */
    public void die() {
        System.out.println("die()");
        System.out.println("Settler" + this.getName() + " is dead");

        this.isAlive = false;
        Game.active.remove(this);
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isAlive() {
        return isAlive;
    }

    //</editor-fold>

    //<editor-fold desc="Getters and Setters">

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPlayerID() { return playerID; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }

    public Asteroid getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(Asteroid currentPosition) { this.currentPosition = currentPosition; }

    public void setHidden(boolean hidden) { isHidden = hidden; }
    public void setAlive(boolean alive) { isAlive = alive; }

    //</editor-fold>
}
