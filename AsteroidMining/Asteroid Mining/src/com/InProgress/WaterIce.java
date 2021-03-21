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
     *
     * @param A the asteroid at which this resource is found.
     */
    public void sublime(Asteroid A){
        boolean position = getAtPerihelion(A);
        if (position == true){
            System.out.println("WaterIce has sublimed");
            /**
             * the resource evaporates if the asteroid is at perihelion.
             */
        }

    }


}
