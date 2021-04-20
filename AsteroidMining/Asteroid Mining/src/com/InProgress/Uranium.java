package com.InProgress;
import com.InProgress.Asteroid;
import com.InProgress.Settler;

import java.io.FileNotFoundException;
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
    public void explode(Asteroid A)  {

        A.setExploded(true); // change isExploded attribute of Asteroid to true.

        //Tester.generator(Tester.outputFile, "exploded " + A.getX() + " " + A.getY() + " " + A.getZ());

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
            int rndX = A.getX() + new Random().nextInt(5)-2; // adds random number between -2 and 2 to the current X coordinate
            int rndY = A.getY() + new Random().nextInt(5)-2; // adds random number between -2 and 2 to the current Y coordinate
            int rndZ = A.getZ() + new Random().nextInt(5)-2; // adds random number between -2 and 2 to the current Z coordinate

            // checks if rndX is out of bound and adjusts it
            if(rndX < 0 ) {
                rndX = Game.maxX - rndX;
            } else if( rndX > Game.maxX) {
                rndX = rndX - A.getX();
            }
            // checks if rndY is out of bound and adjusts it
            if(rndY < 0 ) {
                rndY = 0;
            } else if( rndY > Game.maxX) {
                rndX = A.getY();
            }
            // checks if rndZ is out of bound and adjusts it
            if(rndZ < 0 ) {
                rndZ = 0;
            } else if( rndZ > Game.maxX) {
                rndZ = A.getZ();
            }
            A.getRobotsOnAsteroid().get(i).travel(Game.getAsteroid(rndX, rndY, rndZ));
        }
    }
}

    //</editor-fold>


