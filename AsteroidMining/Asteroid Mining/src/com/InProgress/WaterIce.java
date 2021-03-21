package com.InProgress;
import com.InProgress.Asteroid;

public class WaterIce extends ResourceBase{ //@Ash is there a reason why it is implements and not extends?
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
        /*
        boolean position = getAtPerihelion(A); // no need to check it should be checked in the drilling method which then calls this method.
        if (position == true){
            System.out.println("WaterIce has sublimed"); // where is the code that removes the resource.
            /**
             * the resource evaporates if the asteroid is at perihelion.
             */

        A.getResourceOfAsteroid().remove(0); // Removes the resource form the asteroid
    }


}
