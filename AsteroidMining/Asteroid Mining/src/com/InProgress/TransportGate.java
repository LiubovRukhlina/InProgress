package com.InProgress;

public class TransportGate extends PlaceBase{

    //<editor-fold desc="Attributes">

    public Boolean isActive;
    public Asteroid currentPosition;
    public TransportGate pair;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the TransportGate class.
     */
    public TransportGate()
    {
        this.isActive = Boolean.FALSE;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Activates the Gate after both the gates have been
     * deployed
     */
    public void activateTransportGate() {
        System.out.println("activateTransportGate()");
        this.isActive = Boolean.TRUE;
        this.pair.isActive = Boolean.TRUE;
    }

    /**
     * Deactivates the Gate after the other gate has been
     * destroyed in an explosion
     */
    public void deactivateTransportGate() {
        System.out.println("deactivateTransportGate");
        this.isActive = Boolean.FALSE;
    }

    /**
     * Assigns the Transport Gate with a pair
     * which is necessary for activation
     * @param tg2 the second Transport gate
     */
    public void makePair(TransportGate tg2) {
        System.out.println("makePair()");
        this.pair= tg2;
        tg2.pair = this;
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public Asteroid getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(Asteroid currentPosition) { this.currentPosition = currentPosition; }

    public Boolean getActive() { return isActive; }
    public void setActive(Boolean active) { isActive = active; }

    public TransportGate getPair() { return pair; }
    public void setPair(TransportGate pair) { this.pair = pair; }

    //</editor-fold>

}
