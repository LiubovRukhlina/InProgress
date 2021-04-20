package com.InProgress;

import java.util.Scanner;

public abstract class TravellerBase {

    //<editor-fold desc="Attributes">

    protected Asteroid currentPosition;
    protected boolean isAlive = true;
    protected boolean isHidden = false;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    public TravellerBase() { }

    public TravellerBase(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Abstract method for travelling
     * @param A destination Asteroid
     */
    public abstract void travel(Asteroid A);


    /**
     * Abstract method for fast Travel
     * @param A Asteroid to which the gate belongs
     */
    public abstract void fastTravel(Asteroid A);



    /**
     * Abstract method for hiding
     * @param A Asteroid that the Traveller is hiding in
     */
    public abstract void hide(Asteroid A);


    /**
     * Abstract method for drilling
     * @param A Asteroid that is being drilled
     */
    public abstract void drill(Asteroid A);


    /**
     * This method kills the object.
     */
    public void die() {
        this.isAlive = false;
        Game.settlers.remove(this);

        if (this instanceof  Settler) {
            Tester.generator(Tester.outputFile, "died SETTLER " + currentPosition.getX() + " " +
                    currentPosition.getY() + " " + currentPosition.getZ());
        } else {
            Tester.generator(Tester.outputFile, "died ROBOT " + currentPosition.getX() + " " +
                    currentPosition.getY() + " " + currentPosition.getZ());
        }

    }



    //</editor-fold>

    //<editor-fold desc="Getters and Setters">

    public Asteroid getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;

        if(this instanceof  Settler) {
            Tester.generator(Tester.outputFile, "modified S1 CURRENTPOSITION " + currentPosition.getX() + " " +
                    currentPosition.getY() + " " + currentPosition.getZ());
        } else {
            Tester.generator(Tester.outputFile, "modified R1 CURRENTPOSITION " + currentPosition.getX() + " " +
                    currentPosition.getY() + " " + currentPosition.getZ());
        }
    }

    public boolean getHidden() { return isHidden; }
    public void setHidden(boolean hidden) {
        isHidden = hidden;

        if(this instanceof  Settler) {
            Tester.generator(Tester.outputFile, "modified " + ((Settler) this).getName() + " ISHIDDEN to " + isHidden);
        } else {
            Tester.generator(Tester.outputFile, "modified " + ((Robot) this).getName() + " ISHIDDEN to " + isHidden);
        }
    }

    public boolean getAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    //</editor-fold>
}
