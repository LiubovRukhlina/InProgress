package com.InProgress;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<ResourceBase> storedResources = new ArrayList<ResourceBase>();

    private ArrayList<TransportGate> storedGates = new ArrayList<TransportGate>();

    /**
     *Adds the Resource to the Settler's Inventory
     * @param R Type of the Resource to be added
     */
    public void addResource(ResourceBase R)
    {
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
    public void removeResources(ResourceBase R)
    {
        storedResources.remove(R);
    }

    /**
     * Adds the Transport Gates created by the Settler
     * to the Inventory
     * @param tg1 First Transport Gate
     * @param tg2 Second Transport Gate
     */
    public void addGates(TransportGate tg1, TransportGate tg2)
    {
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
        storedGates.remove(tg);
    }
}
