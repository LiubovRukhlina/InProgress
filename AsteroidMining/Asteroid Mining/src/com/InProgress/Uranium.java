package com.InProgress;
import com.InProgress.Asteroid;
import com.InProgress.Settler;
import java.lang.Boolean;
import java.util.concurrent.ThreadLocalRandom;

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
        for (int i = 0; i < A.getSettlersOnAsteroid().size(); i++) {
            A.getSettlersOnAsteroid().get(i).die(); // All settler on the Asteroid die.
        }

        for(int i = 0; i < A.getRobotsOnAsteroid().size(); i++) {
            // random values to decide where the Robot travels to
            int rndX = ThreadLocalRandom.current().nextInt(0, 5)-2; // random number between -2 and 2
            int rndY = ThreadLocalRandom.current().nextInt(0, 5)-2; // random number between -2 and 2
            int rndZ = ThreadLocalRandom.current().nextInt(0, 5)-2; // random number between -2 and 2
            A.getRobotsOnAsteroid().get(i).travel(Game.asteroids.get(A.getX()+rndX).get(A.getY()+rndY).get(A.getY()+rndZ)); // All robots on the Asteroid move to a random neighbor.
        }
    }

    //</editor-fold>

}
