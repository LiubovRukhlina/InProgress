package com.InProgress;

public class TransportGate {

    public Boolean isActive;

    public Asteroid currentPosition;

    public TransportGate()
    {
        isActive = Boolean.FALSE;
        //currentPosition = setLocation();
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
}
