package com.InProgress.Model;

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
            A.getRobotsOnAsteroid().get(i).randomTravel(A); // Robot travels to a different Asteroid
        }
    }
}

    //</editor-fold>


