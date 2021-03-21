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

    public void explode(Asteroid A){


        boolean position = getAtPerihelion(A);
        if (position == true) {
          int LifeOfSettler = Settler.getLiveCounter();
          LifeOfSettler = LifeOfSettler - 1;
            System.out.println("Asteroid explodes");
            System.out.println("Settler on this asteroid is dead");

            /**
             *  this asteroid explodes if it is at perihelion.
             * @param A name of the asteroid.
             */
        }
    }

    }
