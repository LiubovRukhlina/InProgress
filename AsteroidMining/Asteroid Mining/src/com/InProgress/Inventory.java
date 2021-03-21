package com.InProgress;

import java.util.List;


public class Inventory{
    private List<ResourceBase> storedResources;

    private List<TransportGate> storedGates;

    public List<ResourceBase> getStoredResources() {
        return storedResources;
    }

    public void setStoredResources(List<ResourceBase> storedResources) {
        this.storedResources = storedResources;
    }

    public List<TransportGate> getStoredGates() {
        return storedGates;
    }

    public void setStoredGates(List<TransportGate> storedGates) {
        this.storedGates = storedGates;
    }

    /**
     *Adds the Resource to the Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void addResource(ResourceBase R)
    {
        System.out.println("addResources()");
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
    public void removeResources(ResourceBase R)
    {
        System.out.println("removeResources()");
        //storedResources.remove(R); // we do not remove a real resource for now
    }

    /**
     * Adds the Transport Gates created by the Settler
     * to the Inventory
     * @param tg1 First Transport Gate
     * @param tg2 Second Transport Gate
     */
    public void addGates(TransportGate tg1, TransportGate tg2)
    {
        System.out.println("addGates");
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
    public void removeGate(TransportGate tg)
    {
        System.out.println("removeGate");
        storedGates.remove(tg);
    }
}
