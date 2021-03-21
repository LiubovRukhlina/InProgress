package com.InProgress;

public class TransportGate{

    public Boolean isActive;
    public Asteroid currentPosition;
    public TransportGate pair;



    public Asteroid getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
    }

    public TransportGate()
    {
        System.out.println("create");
        this.isActive = Boolean.FALSE;
        this.currentPosition = PlaceBase.setLocation();
        //make setLocation static
    }

    /**
     * Assigns the Transport Gate with a pair
     * which is necessary for activation
     * @param tg2 the second Transport gate
     */
    public void makePair(TransportGate tg2)
    {
        this.pair= tg2;
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
        this.pair.isActive = Boolean.TRUE;
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
