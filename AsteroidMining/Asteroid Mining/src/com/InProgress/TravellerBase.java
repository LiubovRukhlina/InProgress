package com.InProgress;

import java.util.Scanner;

public abstract class TravellerBase {

    private String name;
    private boolean isAlive = true;
    protected boolean isHidden = false;
    protected Asteroid currentPosition;

    public TravellerBase() { }

    public TravellerBase(String name, Asteroid currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }


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
        this.currentPosition.changeLocation(A, this); // what does this do? the location was changed in line 31
        String s1 = String.format("Settler%s travelled to Asteroid %s", this.name, A.getName());
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
            travel(TG.getPair().getCurrentPosition());
        }
    }

    /**
     * This method is used to hide the object in a hollow As-teroid. This will happen automatically when a traveller is on the hollow Astteroid.
     * @param A Asteroid that the Traveller is hiding on
     */
    public void hide(Asteroid A){
        System.out.println("hide()");
        System.out.println("Is there enough space available?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")) {
            setHidden(true);
            String s1 = String.format("Settler %s is hidden in Asteroid %s", this.name, A.getName());
            System.out.println(s1);
        }
    }

    /**
     * This method is used to drill into an Asteroid.
     * @param A Asteroid that is being drilled
     */
    public void drill(Asteroid A){
        System.out.println("drill()");
        /*int depth = A.getDepth();
        if (depth < 1){
            System.out.println("The Asteroid is completely drilled through");
            return;
        }

        A.decreaseRockCover();
        depth = A.getDepth();
        if (depth == 1){
            System.out.println("rockCover = 1 the asteroid might explode.");
        }
        else{
            System.out.println("The remaining RockCover = " + depth);

        }

        if (depth == 0 && A.getRadioactive() && A.getAtPerihelion()){
            ResourceBase rb = A.getResourceOfAsteroid().get(0);
            if(rb instanceof Uranium){
                ((Uranium) rb).explode(A);
            }
        }*/
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
    public void die(){
        System.out.println("Die");
        System.out.println("Settler" + this.getName() + " is dead");

        this.isAlive = false;
        Game.active.remove(this);
        //do we have to destroy the object or we will delete it from the list in Game only
    }


    public boolean isHidden() {
        return isHidden;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // getter and setter
    public Asteroid getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }



}
