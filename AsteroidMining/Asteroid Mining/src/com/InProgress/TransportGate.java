package com.InProgress;

public class TransportGate {

    //<editor-fold desc="Attributes">

    private String name;
    private Boolean isActive;
    private Asteroid currentPosition;
    private TransportGate pair;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the TransportGate class.
     */
    public TransportGate()
    {
        this.isActive = Boolean.FALSE;
    }

    public TransportGate(String name)
    {
        this.name = name;
        this.isActive = Boolean.FALSE;
    }

    public TransportGate(String name, Asteroid A)
    {
        this.name = name;
        this.currentPosition = A;
        this.isActive = Boolean.FALSE;
        Tester.generator(Tester.outputFile, "created object TRANSPORTGATE at " + A.getX() + " " + A.getY() + " " + A.getZ());
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Activates the Gate after both the gates have been
     * deployed
     */
    public void activateTransportGate() {

        this.isActive = Boolean.TRUE;
        this.pair.isActive = Boolean.TRUE;
    }

    /**
     * Deactivates the Gate after the other gate has been
     * destroyed in an explosion
     */
    public void deactivateTransportGate() {

        this.isActive = Boolean.FALSE;

    }

    /**
     * Assigns the Transport Gate with a pair
     * which is necessary for activation
     * @param tg2 the second Transport gate
     */
    public void makePair(TransportGate tg2) {

        this.pair= tg2;
        tg2.pair = this;
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Asteroid getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(Asteroid currentPosition) {
        this.currentPosition = currentPosition;
        Tester.generator(Tester.outputFile, "modified " + this.name  + " CURRENTPOSITION " + currentPosition.getX() + " " +
                currentPosition.getY() + " " + currentPosition.getZ());
    }

    public Boolean getActive() { return isActive; }
    public void setActive(Boolean active) {
        isActive = active;
        Tester.generator(Tester.outputFile, "modified " + this.name  + " ISACTIVE to " + isActive);
    }

    public TransportGate getPair() { return pair; }
    public void setPair(TransportGate pair) {
        this.pair = pair;
        Tester.generator(Tester.outputFile, "modified " + this.name  + " PAIR " + pair.getName());
    }

    //</editor-fold>

}
