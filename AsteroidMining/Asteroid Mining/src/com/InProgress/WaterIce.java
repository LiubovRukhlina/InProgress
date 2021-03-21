package com.InProgress;

public class WaterIce extends ResourceBase{
    public WaterIce(String resourceType) {
        super(resourceType);
    }

    /**
     * Removes the WaterIce from the Asteroid when it is
     * drilled through at perihelion.
     *
     * @param A Asteroid of the WaterIce Resource
     */
    public void sublime(Asteroid A) {

        A.getResourceOfAsteroid().remove(0);

    }
}