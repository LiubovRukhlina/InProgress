package com.InProgress;

import java.util.List;

public abstract class PlaceBase {

    public List<Asteroid> getNeighbors() {
        return neighbors;
    }

    private List<Asteroid> neighbors;

  /*  private List<Settler> whoIsOnMe() {
        return null;
    }*/

    public abstract void changeLocation(Asteroid dest, TravellerBase traveller);

    private void setLocation() {

    }



}
