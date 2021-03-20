package com.InProgress;

import java.util.Scanner;

public class TravellerBase {

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

    public void travel(Asteroid A) {
        if (!A.getNeighbors().contains(this.currentPosition))
        {
            System.out.println("You cannot travel to the non-neighboring Asteroid");
            return;
        }
        this.setCurrentPosition(A);
        this.currentPosition.changeLocation(A, this);
        String s1 = String.format("Settler %s travelled to Asteroid %s", this.name, A.getName());
        System.out.println(s1);

        this.hide(A);


    }

    public void fastTravel(TransportGate TG) {

        if (TG.getNeighbors() == null)
        {
            System.out.println("Transport Gate doesn't have a pair");
            return;
        }
        Asteroid TG2 = TG.getNeighbors()[0].currentPosition();
        this.currentPosition().changeLocation(TG2);
        String s1 = String.format("Settler %s travelled to Asteroid %s", this.name, TG.name);
        System.out.println(s1);

        this.hide(TG2);

    }

    public void drill(Asteroid A){
        int depth = A.getDepth();
        if (depth < 1){
            System.out.println("The Asteroid is completely drilled through");
            return;
        }

        A.decreaseRockCover();
        int depth = A.getDepth();
        if (depth == 1){
            System.out.println("rockCover = 1 the asteroid might explode.");
        }
        else{
            System.out.println("The remaining RockCover = %d", depth);
        }

    }



    public void die(){
        System.out.println("Settler %s is dead", this.name);
        this.alive = false;
        Game.active.remove(this);
        //do we have to destroy the object or we will delete it from the list in Game only
    }

    public void hide(Asteroid A){
        System.out.println("Is this a hollow Asteroid?"); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        //scan.close();
        if (in.equals("yes")){
            System.out.println("Is there enough space available?"); // Asks the user for his input.
            //Scanner scan = new Scanner(System.in);
            String in = scan.next();
            scan.close();
            if (in.equals("yes")){
                setHidden(True);
                String s1 = String.format("Settler %s is hidden in Asteroid %s", this.name, A.name);
                System.out.println(s1);
            }
        }
    }

}
