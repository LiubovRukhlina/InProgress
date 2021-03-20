package com.InProgress;

import java.util.List;

public abstract class PlaceBase {

    private String name;

    public String getName() {
        return name;
    }


    public List<PlaceBase> getNeighbors() {
        return neighbors;
    }

    private List<PlaceBase> neighbors;

  /*  private List<Settler> whoIsOnMe() {
        return null;
    }*/

    public abstract void changeLocation(PlaceBase dest, TravellerBase traveller);

    public void setLocation(TravellerBase traveller) {

    }



}
