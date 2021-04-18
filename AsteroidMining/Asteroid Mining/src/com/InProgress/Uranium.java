package com.InProgress;
import com.InProgress.Asteroid;
import com.InProgress.Settler;
import java.lang.Boolean;
import java.util.Random;
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
     * It kills all Settlers on the Asteroid and forces Robots to travel to a neighboring Asteroid.
     * It deactivates any TransportGates related to the Asteroid.
     *
     * @param A Asteroid in which the Uranium is
     */
    @Override
    public void explode(Asteroid A) {

        A.setExploded(true); // change isExploded attribute of Asteroid to true.

        Tester.generator(Tester.outputFile, "exploded " + A.getX() + " " + A.getY() + " " + A.getZ());

        if(A.getHasGate() == true) // check for gates of the Asteroid
        {
            A.getGate().deactivateTransportGate(); // deactivates the TransportGate
        }

        for (int i = 0; i < A.getSettlersOnAsteroid().size(); i++) {
            A.getSettlersOnAsteroid().get(i).die(); // All settler on the Asteroid die.
        }

        for(int i = 0; i < A.getRobotsOnAsteroid().size(); i++) {
            // random values to decide where the Robot travels to
            A.getRobotsOnAsteroid().get(i).increaseDamage(); // increases the damage the robot has taken

            // generate random numbers to determine where the robot travels to
            int rndX = new Random().nextInt(5) -2; // random number between -2 and 2
            int rndY = new Random().nextInt(5) -2;; // random number between -2 and 2
            int rndZ = new Random().nextInt(5) -2;; // random number between -2 and 2

            A.getRobotsOnAsteroid().get(i).travel(Game.getAsteroid(A.getX()+rndX, A.getY()+rndY, A.getZ()+rndZ)); // All robots on the Asteroid move to a random neighbor.
        }
    }

    //</editor-fold>

}
