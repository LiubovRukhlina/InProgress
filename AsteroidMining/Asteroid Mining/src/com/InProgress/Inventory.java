package com.InProgress;

import java.util.ArrayList;
import java.util.List;

import static com.InProgress.Settler.findByType;


public class Inventory{

    //<editor-fold desc="Attributes">

    private List<ResourceBase> storedResources;
    private List<TransportGate> storedGates;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Inventory class
     */
    public Inventory() {
        this.storedResources = new ArrayList<>();
        this.storedGates = new ArrayList<>();
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     *Adds the Resource to the Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void addResource(ResourceBase R) {
       if (storedResources.size() == 10)
        {
            System.out.println("The inventory is full");
        }
        else
           storedResources.add(R);
    }

    /**
     * Removes the specific resource from the
     * Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void removeResources(ResourceBase R) {
        System.out.println("removeResources()");

        storedResources.remove(R);
    }

    /**
     * Adds the Transport Gates created by the Settler
     * to the Inventory
     * @param tg1 First Transport Gate
     * @param tg2 Second Transport Gate
     */
    public void addGates(TransportGate tg1, TransportGate tg2) {
        System.out.println("addGates()");
        if (storedGates.size() == 0) {
            storedGates.add(tg1);
            storedGates.add(tg2);
        }
        else
        {
            System.out.println("Inventory Full,deploy previous Gates");
        }
    }

    /**
     * Removes the Transport Gate from the Settler's Inventory
     * after it has been deployed
     * @param tg Transport Gate
     */
    public void removeGate(TransportGate tg) {
        System.out.println("removeGate()");
        storedGates.remove(tg);
    }

    /**
     * Determines whether we have sufficient resources to build an object
     * of different types
     * @param type Instance of object to be built
     * @return True if we have enough resources
     *         False if we do not have enough resources
     *
     */
    public Boolean checkResources(int type) {
        if (type == 1) {
            Ucount = 0;
            Icount = 0;
            Ccount = 0;
            for (ResourceBase resource : storedResources) {
                if (resource instanceof Uranium && Ucount == 0) {
                    Ucount++;
                    removeResources(resource);
                }
                else if (resource instanceof Iron && Icount == 0) {
                    Icount++;
                    removeResources(resource);
                }
                else if (resource instanceof Carbon && Ccount == 0) {
                    Ccount++;
                    removeResources(resource);
                }

            }
            return Ucount == 1 && Icount == 1 && Ccount == 1;
        }
        else if (type == 2) {

            Ucount = 0;
            Icount = 0;
            Wcount = 0;
            for (ResourceBase resource : storedResources) {
                if (resource instanceof Uranium && Ucount == 0) {
                    Ucount++;
                    removeResources(resource);
                }
                else if (resource instanceof Iron && Icount < 2) {
                    Icount++;
                    removeResources(resource);
                }
                else if (resource instanceof WaterIce && Wcount == 0) {
                    Wcount++;
                    removeResources(resource);
                }

            }
            return Ucount == 1 && Icount == 2 && Ccount == 1
        }

        return false;
    }


    /**
     * Fucntion checks whether we have enough resources to build a space station
     * @param A Asteroid where some of the resources are stored
     * @return True if we have enough resources
     *         False if we do not have enough resources
     */
    public Boolean checkResources(Asteroid A)
    {
            Ucount = 0;
            Icount = 0;
            Wcount = 0;
            Ccount = 0;
            for(ResourceBase resource:storedResources)
            {
                if(resource instanceof Uranium && Ucount <3)
                {
                    Ucount++;
                    removeResources(resource);
                }
                else if(resource instanceof Iron && Icount <3)
                {
                    Icount++;
                    removeResources(resource);
                }
                else if(resource instanceof WaterIce && Wcount <3)
                {
                    Wcount++;
                    removeResources(resource);
                }
                else if(resources instanceof Carbon && Ccount < 3)
                {
                    Ccount++;
                    removeResources(resource);
                }

        }
            for(ResourceBase resource :A.storedResourceOfAsteroid )
            {
                if(resource instanceof Uranium && Ucount <3)
                {
                    Ucount++;
                    removeResources(resource);
                }
                else if(resource instanceof Iron && Icount <3)
                {
                    Icount++;
                    removeResources(resource);
                }
                else if(resource instanceof WaterIce && Wcount <3)
                {
                    Wcount++;
                    removeResources(resource);
                }
                else if(resources instanceof Carbon && Ccount < 3)
                {
                    Ccount++;
                    removeResources(resource);
                }
            }
            return Ucount == 3 && Icount == 3 && Ccount == 3 && Wcount ==3;
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public List<ResourceBase> getStoredResources() { return storedResources; }
    public void setStoredResources(List<ResourceBase> storedResources) { this.storedResources = storedResources; }

    public List<TransportGate> getStoredGates() { return storedGates; }
    public void setStoredGates(List<TransportGate> storedGates) { this.storedGates = storedGates; }

    public boolean checkResources(List<ResourceBase> requiredResources) {
        List<ResourceBase> fakeStoredResources = storedResources;
        for (int i = 0; i < requiredResources.size(); i++) {
            ResourceBase r = findByType(storedResources, requiredResources.get(i).getResourceType());
            if (r != null) // checks if there is resource in inventory
            {
                requiredResources.remove(i);
                fakeStoredResources.remove(r);
            }
            else{
                return false;
            }
        }
        if (requiredResources.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //</editor-fold>
}
