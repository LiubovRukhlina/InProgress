package com.InProgress;

public class Robot extends TravellerBase{

    //<editor-fold desc="Attributes">
    /**
     * currentX gives the x coordinate of the current asteroid of the robot
     * currentY gives the y coordinate of the current asteroid of the robot
     *
     * currentZ gives the Z coordinate of the current asteroid of the robot
     */
    private int damageCount;
    public Asteroid currentLocation;
    private int currentX = currentLocation.getX();
    private int currentY = currentLocation.getY();
    private int currentZ = currentLocation.getZ();
    private boolean isHidden = false;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Robot class
     */
    public Robot() {
        this.damageCount = 0;
    }

    public int getDamageCount() { return damageCount; }
    public void setDamageCount(int damageCount) { this.damageCount = damageCount; }

    /**
     *
     * @param Dest destination asteroid where the user wants to travel
     */
    public void travel(Asteroid Dest){
        int DestX = Dest.getX();
        int DestY = Dest.getY();
        int DestZ = Dest.getZ();

        /**
         * According to the guidelines (check and revert)
         */
        if(Maths.abs(DestX-currentX) <= 2 || Maths.abs(DestY-currentY) <= 2 || Maths.abs(DestZ-currentZ) <= 2 )
            currentLocation = Dest;

    }

    /**
     * Allows users to fast travel
     */
    public void fastTravel(){
        if(currentLocation.getHasGate()){ // if the current asteroid has a transport gate
            TransportGate Gate1 = currentLocation.getGate();

            if(Gate1.isActive){    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                currentLocation = Gate2.getCurrentPosition(); // we travel to the location of the pair.
            }
        }
    }



    //</editor-fold>


    //<editor-fold desc="Method">

    /**
     * Increases the damageCount of this Robot.
     * A robot will die when the damageCount equals 2.
     */
    public void increaseDamage() {

        System.out.println("increaseDamage()");
        damageCount++;

        if(this.damageCount == 2)
        {
            this.die();
        }

        // commented the output out. I think right now it is not in the skeleton. (Yves)
        //System.out.println("Robot has been damaged.");
    }

    //</editor-fold>



    //<editor-fold desc="Getters and Setters">



    /**
     * drill method
     */
    public void drill() {

        int depth = currentLocation.getDepth(); // gets the mantle length of the asteroid
        if(depth != 0) { // if it is not hollow, then we drill
            for (int i = 0; i < depth; i++) {
                currentLocation.decreaseRockCover(); //drilling
            }
        }
    }

    /**
     * hide method
     */
    public void hide{
        if(!isHidden){ //if the robot is already hidden
            if(currentLocation.IsHollow){ // if the asteroid is a hollow one
                isHidden = true;
            }
        }

    }


    //</editor-fold>

}
