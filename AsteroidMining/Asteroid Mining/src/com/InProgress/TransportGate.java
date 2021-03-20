package com.InProgress;

public class TransportGate extends PlaceBase{

    public Boolean isActive;
    public Asteroid currentPosition;
    public Asteroid getCurrentPosition() {
        return currentPosition;
    }


    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    public TransportGate()
    {
        isActive = Boolean.FALSE;
        //currentPosition = setLocation();
    }

    public TransportGate(String name) {
        this.setName(name);
    }

    /**
     * Activates the Gate after both the gates have been
     * deployed
     */
    public void activateTransportGate()
    {
        isActive = Boolean.TRUE;
    }

    /**
     * Deactivates the Gate after the other gate has been
     * destroyed in an explosion
     */
    public void deactivateTransportGate()
    {
        isActive = Boolean.FALSE;
    }


    @Override
    public void changeLocation(PlaceBase dest, TravellerBase traveller) {
        dest.changeLocation(this, traveller);
    }
}
