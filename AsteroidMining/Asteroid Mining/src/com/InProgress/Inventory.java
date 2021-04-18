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
        System.out.println("addResources()");
        //we do not add real resources in the skeleton
      /*  if (storedResources.size() == 10)
        {
            System.out.println("The inventory is full");
        }
        else
           storedResources.add(R);*/
    }

    /**
     * Removes the specific resource from the
     * Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void removeResources(ResourceBase R) {
        System.out.println("removeResources()");
        // we do not remove a real resource for in the skeleton
        //storedResources.remove(R);
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
