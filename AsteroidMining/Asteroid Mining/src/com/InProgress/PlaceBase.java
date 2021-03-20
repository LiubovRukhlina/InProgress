package com.InProgress;

import java.util.List;

public abstract class PlaceBase {

    private String name;
    private List<PlaceBase> neighbors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlaceBase> getNeighbors() {
        return neighbors;
    }

  /*  private List<Settler> whoIsOnMe() {
        return null;
    }*/

    public abstract void changeLocation(PlaceBase dest, TravellerBase traveller);

    public void setLocation(TravellerBase traveller) {

    }





}
