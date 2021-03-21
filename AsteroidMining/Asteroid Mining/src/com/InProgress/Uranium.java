package com.InProgress;
import com.InProgress.Asteroid;
import com.InProgress.Settler;
import java.lang.Boolean;

public class Uranium extends ResourceBase{

    /**
     * radioactive resource
     * @param resourceType Nma eof the resource.
     */
    public Uranium(String resourceType) {
        super(resourceType);
    }

    /**
     * Uranium explodes when the Asteroid is drilled through at perihelion.
     * It kills all Settlers on the Asteroid and forces all Robots to
     * travel to a neighboring Asteroid.
     *
     * @param A Asteroid in which the Uranium is
     */
    public void explode(Asteroid A){


        /*
        boolean position = getAtPerihelion(A); // this is checked during the drilling.
        if (position == true) {
          int LifeOfSettler = Settler.getLiveCounter(); //@Ash where is the does the settler die? What about robots?
          LifeOfSettler = LifeOfSettler - 1; // this should happen in the die method
            System.out.println("Asteroid explodes");
            System.out.println("Settler on this asteroid is dead");

         */


            //@Ash check out how javadoc is working, this is wrong.
            /**
             *  this asteroid explodes if it is at perihelion.
             * @param A name of the asteroid.
             */
        for (int i = 0; i < A.getSettlersOnAsteroid().size(); i++) {
            A.getSettlersOnAsteroid().get(i).die(); // All settler on the Asteroid die.
        }

        for(int i = 0; i < A.getRobotsOnAsteroid().size(); i++) {

            A.getRobotsOnAsteroid().get(i).travel((Asteroid) A.getNeighbors().get(0)); // All robots on the Asteroid move to a neighbor.
        }
    }

    }
