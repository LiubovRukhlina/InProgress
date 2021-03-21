package com.InProgress;

import java.util.ArrayList;
import java.util.List;

public abstract class PlaceBase {

    private String name;
    // public ArrayList<PlaceBase> neighbors; //I don't think we need it here -> it should be in Asteroid class

    public abstract void changeLocation(PlaceBase dest, TravellerBase traveller);

    public void setLocation(TravellerBase traveller) {

    }

    // getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /* public ArrayList<PlaceBase> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<PlaceBase> neighbors) {
        this.neighbors = neighbors;
    }*/

    /*  private List<Settler> whoIsOnMe() {
        return null;
    }*/

}
