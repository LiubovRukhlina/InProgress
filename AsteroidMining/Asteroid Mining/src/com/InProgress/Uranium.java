package com.InProgress;
import com.InProgress.Asteroid;
import com.InProgress.Settler;
import java.lang.Boolean;

public class Uranium extends ResourceBase{

    //<editor-fold desc="Constructor">

    /**
     * radioactive resource
     * @param resourceType Nma eof the resource.
     */
    public Uranium(String resourceType) {
        super(resourceType);
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Uranium explodes when the Asteroid is drilled through at perihelion.
     * It kills all Settlers on the Asteroid and forces all Robots to
     * travel to a neighboring Asteroid.
     *
     * @param A Asteroid in which the Uranium is
     */
    public void explode(Asteroid A) {
        System.out.println("explode()");
        for (int i = 0; i < A.getSettlersOnAsteroid().size(); i++) {
            A.getSettlersOnAsteroid().get(i).die(); // All settler on the Asteroid die.
        }

        for(int i = 0; i < A.getRobotsOnAsteroid().size(); i++) {

            A.getRobotsOnAsteroid().get(i).travel((Asteroid) A.getNeighbors().get(0)); // All robots on the Asteroid move to a neighbor.
        }
    }

    //</editor-fold>

}
