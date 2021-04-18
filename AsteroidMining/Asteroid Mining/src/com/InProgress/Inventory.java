package com.InProgress;

import java.util.ArrayList;
import java.util.List;


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

        if (storedResources.size() == 10) { // checks if this Inventory is full
            //System.out.println("The inventory is full");
        } else {
           storedResources.add(R);

           Tester.generator(Tester.outputFile, "added " + R.getResourceType().toUpperCase() + " S1 STOREDRESOURCES" );
       }
    }

    /**
     * Removes the specific resource from the
     * Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void removeResources(ResourceBase R) {
        storedResources.remove(R);
    }

    /**
     * Adds the Transport Gates created by the Settler to this Inventory
     * @param tg1 First Transport Gate
     * @param tg2 Second Transport Gate
     */
    public void addGates(TransportGate tg1, TransportGate tg2) {
        if (storedGates.size() == 0) { // checks if this Inventory is full.
            storedGates.add(tg1);
            storedGates.add(tg2);
        }
        else
        {
            //System.out.println("Inventory Full,deploy previous Gates");
        }
    }

    /**
     * Removes the Transport Gate from the Settler's Inventory
     * after it has been deployed
     * @param tg Transport Gate
     */
    public void removeGate(TransportGate tg) {
        storedGates.remove(tg);
    }

    /**
     * Determines whether we have sufficient resources to build an object
     * of different types
     * @param type Integer that determines what should be build
     * @return True if we have enough resources
     *         False if we do not have enough resources
     */
    public Boolean checkResources(int type) {
        int uCount = 0; // counts the number of units of Uranium
        int iCount = 0; // counts the number of units of Iron
        int cCount = 0; // counts the number of units of Carbon
        int wCount = 0; // counts the number of units of WaterIce

        if (type == 1) { // checks if a Robot should be build

            for (ResourceBase resource : storedResources) {
                if (resource instanceof Uranium && uCount == 0) { // checks if there is 1 unit of Uranium
                    uCount++;
                }
                else if (resource instanceof Iron && iCount == 0) { // checks if there is 1 unit of Iron
                    iCount++;
                }
                else if (resource instanceof Carbon && cCount == 0) { // checks if there is 1 unit of Carbon
                    cCount++;
                }

            }
            return uCount == 1 && iCount == 1 && cCount == 1; // returns true if enough resources are available
        }
        else if (type == 2) { // checks if Transport Gates should be build

            for (ResourceBase resource : storedResources) {
                if (resource instanceof Uranium && uCount == 0) { // checks if there is 1 unit of Uranium
                    uCount++;
                }
                else if (resource instanceof Iron && iCount < 2) { // checks if there is 2 unit of Iron
                    iCount++;
                }
                else if (resource instanceof WaterIce && wCount == 0) { // checks if there is 1 unit of WaterIce
                    wCount++;
                }

            }
            return uCount == 1 && iCount == 2 && wCount == 1; // returns true if enough resources are available
        }

        return false; // default return
    }


    /**
     * Function checks whether we have enough resources to build a space station
     * @param A Asteroid where some of the resources are stored
     * @return True if we have enough resources
     *         False if we do not have enough resources
     */
    public Boolean checkResources(Asteroid A) {
        int uCount = 0; // counts the number of units of Uranium
        int iCount = 0; // counts the number of units of Iron
        int cCount = 0; // counts the number of units of Carbon
        int wCount = 0; // counts the number of units of WaterIce

        for(ResourceBase resource:storedResources) // checks the Resource in this Inventory
        {
            if(resource instanceof Uranium && uCount < 3) // checks if there is 3 unit of Uranium
            {
                uCount++;
            }
            else if(resource instanceof Iron && iCount < 3) // checks if there is 3 unit of Iron
            {
                iCount++;
            }
            else if(resource instanceof WaterIce && wCount < 3) // checks if there is 3 unit of WaterIce
            {
                wCount++;
            }
            else if(resource instanceof Carbon && cCount < 3) // checks if there is 3 unit of Carbon
            {
                cCount++;
            }
        }

        for(ResourceBase resource :A.getStoredResourceOfAsteroid() ) // checks the Resources stored on the Asteroid
        {
            if(resource instanceof Uranium && uCount < 3) // checks if there is 3 unit of Uranium
            {
                uCount++;
            }
            else if(resource instanceof Iron && iCount < 3) // checks if there is 3 unit of Iron
            {
                iCount++;
            }
            else if(resource instanceof WaterIce && wCount < 3) // checks if there is 3 unit of WaterIce
            {
                wCount++;
            }
            else if(resource instanceof Carbon && cCount < 3) // checks if there is 3 unit of Carbon
            {
                cCount++;
            }
        }
        return uCount == 3 && iCount == 3 && cCount == 3 && wCount == 3; // returns true if enough resources are available
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public List<ResourceBase> getStoredResources() { return storedResources; }
    public void setStoredResources(ResourceBase rb) {
        this.storedResources.add(rb);
        Tester.generator(Tester.outputFile, "added " + rb.getResourceType().toUpperCase() + "S1 STOREDRESOURCES");
    }

    public List<TransportGate> getStoredGates() { return storedGates; }
    public void setStoredGates(TransportGate tg) {
        this.storedGates.add(tg);
        Tester.generator(Tester.outputFile, "added " + tg.getName().toUpperCase() + "S1 STOREDGATES");
    }

    //</editor-fold>
}
