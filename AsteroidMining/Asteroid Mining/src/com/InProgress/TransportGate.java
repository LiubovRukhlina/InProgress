package com.InProgress;

public class TransportGate extends PlaceBase{

    public Boolean isActive;
    public Asteroid currentPosition;
    public TransportGate neighbor;
    public Asteroid getCurrentPosition() {
        return currentPosition;
    }


    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    public TransportGate(TransportGate tg2)
    {
        System.out.println("create");
        this.neighbor = tg2;
        this.isActive = Boolean.FALSE;
        this.currentPosition = PlaceBase.setLocation();
        //make setLocation static
    }
    /*
    public TransportGate(String name) {
        this.setName(name);
        @Vlad did you add this?
    }
    */
    /**
     * Activates the Gate after both the gates have been
     * deployed
     */
    public void activateTransportGate()
    {
        System.out.println("activateTransportGate");
        this.isActive = Boolean.TRUE;
        this.neighbor.isActive = Boolean.TRUE;
    }

    /**
     * Deactivates the Gate after the other gate has been
     * destroyed in an explosion
     */
    public void deactivateTransportGate()
    {
        System.out.println("deactivateTransportGate");
        this.isActive = Boolean.FALSE;
    }


    @Override
    public void changeLocation(PlaceBase dest, TravellerBase traveller)
    {
        dest.changeLocation(this, traveller);
    }
}
