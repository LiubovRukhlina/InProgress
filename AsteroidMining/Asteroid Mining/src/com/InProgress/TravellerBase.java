package com.InProgress;

public class TravellerBase {

    protected boolean isHidden;

    protected Asteroid currentPosition;

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public Asteroid getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void travel(PlaceBase P) {
        if (!P.getNeighbors().contains(this.currentPosition()))
        {
            System.out.println("You cannot travel to the non-neighboring Asteroid");
            return;
        }

        System.out.println("How thick is the mantle? Enter number between 1 and 5."); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();

        setRockCover(in); // Sets the rockCover of this Asteroid to the user input.

        scan.close();

    }

    public void fastTravel(TransportGate TG) {

        System.out.println("How thick is the mantle? Enter number between 1 and 5."); // Asks the user for his input.
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();

        setRockCover(in); // Sets the rockCover of this Asteroid to the user input.

        scan.close();

    }

    public void drill(Asteroid A){

    }

    public void die(){

    }

    public void hide(Asteroid A){

    }

}
