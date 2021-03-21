package com.InProgress;
import com.InProgress.Asteroid;

public class WaterIce extends ResourceBase{ 
    /**
     * A resource that disappears when the asteroid is at perihelion.
     * @param resourceType Name of the resource.
     */

    public WaterIce(String resourceType) {
        super(resourceType);
    }

    /**
     * Removes the WaterIce from the Asteroid when it is
     * drilled through
     * @param A Asteroid of this WaterIce Resource
     */
    public void sublime(Asteroid A){

        A.getResourceOfAsteroid().remove(0); // Removes the resource form the asteroid
    }


}
