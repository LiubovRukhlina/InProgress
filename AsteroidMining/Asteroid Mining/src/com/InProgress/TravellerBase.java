package com.InProgress;

import java.util.Scanner;

public abstract class TravellerBase {

    private String name;
    private boolean isAlive = true;
    protected boolean isHidden = false;
    protected Asteroid currentPosition;
    public boolean isHidden() {
        return isHidden;
    }
    public String getName() {
        return name;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public TravellerBase() {
    }

    public TravellerBase(String name, Asteroid currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }

    public Asteroid getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * moves the object to the chosen Asteroid.
     * @param A destination Asteroid
     */
    public void travel(Asteroid A) {
        System.out.println("Travel");
        if (!A.getNeighbors().contains(this.currentPosition))
        {
            System.out.println("You cannot travel to the non-neighboring Asteroid");
            return;
        }
        this.setCurrentPosition(A);
        this.currentPosition.changeLocation(A, this);
        String s1 = String.format("Settler%s travelled to Asteroid %s", this.name, A.getName());
        System.out.println(s1);
        System.out.println("Is this a hollow Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        //scan.close();
        if (in.equals("yes")){
            System.out.println("Is there enough space available?"); // Asks the user for his input.
            //Scanner scan = new Scanner(System.in);
            in = scan.next();
            scan.close();
            if (in.equals("yes")){
                setHidden(true);
                s1 = String.format("Settler %s is hidden in Asteroid %s", this.name, A.getName());
                System.out.println(s1);
            }
        }
        this.hide(A);
    }

    /**
     * This method is used for travel by using a transport gate.
     * @param TG TransportGate that the Traveller is travelling through
     */
    /*public void fastTravel(TransportGate TG) {
        System.out.println("Fast travel");

       *//* if (TG.getNeighbors() == null)
        {
            System.out.println("Transport Gate doesn't have a pair");
            return;
        }
        PlaceBase TG2 = TG.getNeighbors().get(0);*//*
        if (TG2 instanceof TransportGate){
            this.setCurrentPosition(((TransportGate) TG2).getCurrentPosition());
            this.currentPosition.changeLocation(TG2, this);
            String s1 = String.format("Settler %s travelled to Asteroid %s", this.name, TG.getCurrentPosition());
            System.out.println(s1);

            this.hide(TG.getCurrentPosition());
        }

    }*/

    /**
     * This method is used to drill into an Asteroid.
     * @param A Asteroid that is being drilled
     */
    public void drill(Asteroid A){
        System.out.println("Drill");
        int depth = A.getDepth();
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
        }

    }


    /**
     * This method kills the object.
     */
    public void die(){
        System.out.println("Die");
        System.out.println("Settler" + this.getName() + "is dead");

        this.isAlive = false;
        Game.active.remove(this);
        //do we have to destroy the object or we will delete it from the list in Game only
    }

    /**
     * This method is used to hide the object in a hollow As-teroid. This will happen automatically when a traveller is on the hollow Astteroid.
     * @param A Asteroid that the Traveller is hiding on
     */
    public void hide(Asteroid A){
        System.out.println("Hide");
        System.out.println("Is this a hollow Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        //scan.close();
        if (in.equals("yes")){
            System.out.println("Is there enough space available?"); // Asks the user for his input.
            //Scanner scan = new Scanner(System.in);
            in = scan.next();
            scan.close();
            if (in.equals("yes")){
                setHidden(true);
                String s1 = String.format("Settler %s is hidden in Asteroid %s", this.name, A.getName());
                System.out.println(s1);
            }
        }
    }

}
