package com.InProgress.Model;

import com.InProgress.GUI.ErrorMessage;
import com.InProgress.GUI.GameWindow;
import com.InProgress.GUI.TravelWindow;

public class Settler extends TravellerBase {

    //<editor-fold desc="Attributes">

    private int playerID;
    private String name;
    private Inventory itsInventory = new Inventory();

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * Constructor of the Settler class
     *
     * @param name Name of the Settler
     * @param currentPosition position of the Settler
     * @param playerID ID of the player the Settler belongs to
     */
    public Settler(String name, Asteroid currentPosition, int playerID) {
        super(currentPosition);
        this.name = name;
        this.playerID = playerID;
        currentPosition.setSettlersOnAsteroid(this);
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Moves this Settler to a neighbouring Asteroid.
     * It checks if the Asteroid is not exploded and if it is in the neighbourhood.
     * Then it checks if the Asteroid accepts the Settler on it.
     * After a successful travel the Settler tries to hide inside the Asteroid.
     *
     * @param destination destination Asteroid
     */
    @Override
    public void travel(Asteroid destination) {
        if (checkDestination(destination)) { // checks if destination is a valid.
            if (destination.acceptTraveller(this)) { // checks if there is enough space for the settler on the destination.
                currentPosition.getSettlersOnAsteroid().remove(this);  // settler is removed from the list of the current asteroid
                isHidden = false; // settler is not hidden after travel
                currentPosition.hideMyTravellers();
                currentPosition = destination;  // successful travel
                hide(currentPosition); // settler tries to hide

                Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
            } else {

                TravelWindow.infobox("Not Enough place on the Asteroid","Error");
            }
        } else {
            TravelWindow.infobox("Asteroid not in Neighborhood or exploded","Error");
        }
    }


    /**
     * This method is used for travel by using a transport gate.
     * It first checks if there is a gate available and if it is active.
     *
     * @param a asteroid that the Traveller is travelling to.
     */
    @Override
    public void fastTravel(Asteroid a) {
        if (a.getHasGate()) { // if the current asteroid has a transport gate
            TransportGate Gate1 = a.getGate();

            if (Gate1.getActive()) {    // if the gate is active (means if the pair is also deployed)
                TransportGate Gate2 = Gate1.getPair();
                if (Gate2.getCurrentPosition().acceptTraveller(this)) {
                    a.getSettlersOnAsteroid().remove(this);  // settler is removed from the list
                    isHidden = false;
                    currentPosition.hideMyTravellers();
                    currentPosition = Gate2.getCurrentPosition();  // successful travel
                    hide(currentPosition); // hides when successfully travels

                    Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
                } else {
                    // TODO No Space Window (Controller)
                }
            } else {
                // TODO Inactive Gate Window (Controller)
            }
        } else {
            // TODO No Gate Window (Controller)
        }
    }

    /**
     * The Settler drills the Asteroid and the rockCover is reduced.
     * When the Asteroid is drilled through after the current drilling,
     * it is checked whether is explodes or the WaterIce sublimes.
     *
     * @param A Asteroid which is drilled
     */
    @Override
    public void drill(Asteroid A) {
        if (A.getDepth() != 0) { // if it is not drilled through, then we drill
            A.decreaseRockCover(); //drilling

            if(A.getDepth() == 0 && A.getAtPerihelion()) { // checks if the remaining rockCover equals 0 and if the Asteroid is at perihelion
                if(A.getResourceOfAsteroid().get(0) instanceof Uranium) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).explode(A); // Uranium explodes

                } else if(A.getResourceOfAsteroid().get(0) instanceof WaterIce) // checks if the Asteroid has Uranium in his core
                {
                    A.getResourceOfAsteroid().get(0).sublime(A); // WaterIce sublimes
                }
            }

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
                GameWindow.infobox("Asteroid is completely drilled","Drill Error");
        }
    }

    /**
     * This method mines the resource of the Asteroid the Settler is currently on.
     * It checks if the Asteroid is drilled through and if it hollow.
     *
     * @param A Asteroid that is mined.
     */
    public void mine(Asteroid A) {
        if(A.getDepth() == 0 && !A.getHollow()) { // checks if the Asteroid is mineable

            GameWindow.resource = A.getResourceOfAsteroid().get(0).resourceType;
            this.itsInventory.addResource(A.getResourceOfAsteroid().get(0)); // adds the Resource of the Asteroid to the Inventory
            A.emptyAsteroid(); // removes the Resource from the Asteroid
            A.hideMyTravellers(); // the Asteroid is hollow now. Travellers can hide.

            Game.getCurrentPlayer().decreaseNumberOfMoves();

        } else {

            ErrorMessage err = new ErrorMessage(); // error message in case the Asteroid is not mineable
            err.initialize();
        }
    }

    /**
     * This method is used to build a robot.
     */
    public void buildRobot() { // TODO Check if removing is working
        int uCount = 0; // counts the number of units of Uranium
        int iCount = 0; // counts the number of units of Iron
        int cCount = 0; // counts the number of units of Carbon

        if (itsInventory.checkResources(1)) { // checks if there are enough resources

            for (ResourceBase resource : itsInventory.getStoredResources()) {
                if (resource instanceof Uranium && uCount == 0) { // removes 1 unit of Uranium
                    uCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);
                } else if (resource instanceof Iron && iCount == 0) { // removes 1 unit of Iron
                    iCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);
                } else if (resource instanceof Carbon && cCount == 0) { // removes 1 unit of Carbon
                    cCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);
                }
            }

            while (itsInventory.getStoredResources().remove(null));

            Robot newRobot = new Robot(currentPosition); // create new Robot
            Game.getRobots().add(newRobot); // adds the Robot to the list of Robots
            newRobot.randomTravel(this.getCurrentPosition()); // robot immediately travels to a different Asteroid

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
            GameWindow.infobox("Cannot Build Robot","Insufficeient Resources");
        }
    }


    /**
     * This method is used when the settler wants to build a SpaceStation.
     *
     * @param A Asteroid on which the SpaceStation is built.
     */
    public void buildSpaceStation(Asteroid A) {

        if (itsInventory.checkResources(A)) { // checks if there are enough resources
            Game.endGame(1);
        } else {
            GameWindow.infobox("Cannot build Space Station","Insufficient resources");

        }
    }

    /**
     * This method is used to build a transportation-gate and store it in the settler’s inventory.
     */
    public void buildTransportGate() { // TODO Check if removing is working
        if (itsInventory.checkResources(2)) { // checks if there are enough resources
            int uCount = 0; // counts the number of units of Uranium
            int iCount = 0; // counts the number of units of Iron
            int wCount = 0; // counts the number of units of WaterIce

            for (ResourceBase resource : itsInventory.getStoredResources()) { // removes the Resources
                if (resource instanceof Uranium && uCount == 0) { // removes 1 unit of Uranium
                    uCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);

                } else if (resource instanceof Iron && iCount < 2) { // removes 2 unit of Iron
                    iCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);

                } else if (resource instanceof WaterIce && wCount == 0) { // removes 1 unit of WaterIce
                    wCount++;
                    int ind = itsInventory.getStoredResources().indexOf(resource);
                    itsInventory.getStoredResources().set(ind, null);

                }
            }

            while (itsInventory.getStoredResources().remove(null));

            TransportGate tg1 = new TransportGate();
            TransportGate tg2 = new TransportGate();

            //Pair the gates together
            tg1.makePair(tg2);
            tg2.makePair(tg1);

            this.itsInventory.addGates(tg1, tg2);

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
            GameWindow.infobox("Failed Check","Error");
        }
    }

    /**
     * This method deploys a transport gate on the asteroid the settler is currently on.
     *
     * @param A Asteroid that the TransportGate is deployed on
     */
    public void deployTransportGate(Asteroid A) {

        if (!itsInventory.getStoredGates().isEmpty() && !A.getHasGate()) {

            TransportGate tg = this.itsInventory.getStoredGates().get(0);
            tg.setCurrentPosition(A); // places the gate at the current Asteroid
            this.itsInventory.removeGate(tg);
            A.setGate(tg);

            if (tg.getPair().getCurrentPosition() != null) { // checks if the paired gate is already deployed
                tg.activateTransportGate();
                tg.getPair().activateTransportGate();
            }

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
            // TODO Cannot Deploy Window (Controller)
        }
    }


    /**
     * This method stores Resources on a hollow asteroid.
     * It checks if there are already Resources stored.
     * If there are already Resources it checks if they are of the same type as the Resources the Settler wants to store.
     *
     * @param index Index of the Resource that is stored.
     */
    public void leaveResource(int index) {
        if(currentPosition.getHollow()) { // checks if the Asteroid is hollow

            if (currentPosition.getStoredResourceOfAsteroid().isEmpty()) { // checks if there are already
                currentPosition.getStoredResourceOfAsteroid().add(itsInventory.getStoredResources().get(index));
                itsInventory.getStoredResources().remove(index);

            } else if (currentPosition.getStoredResourceOfAsteroid().size() < 3) { // checks if there is space to store a Resource
                if (itsInventory.getStoredResources().get(index).getResourceType().equals(currentPosition.getStoredResourceOfAsteroid().get(0).getResourceType())) { // checks if the Resource is the same as the stored ones
                    currentPosition.getStoredResourceOfAsteroid().add(itsInventory.getStoredResources().get(index));
                    itsInventory.getStoredResources().remove(index);
                }
            }

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
            GameWindow.infobox("The Asteroid is not Hollow","Error");
        }
    }

    /**
     * This method is used to pick up resources which are stored on asteroids by settlers.
     * Since only Resources of the same type are stored on the Asteroid the last Element of the
     * StoredResourcesOfAsteroid list is picked up.
     */
    public void pickUpResources() {
        if(!currentPosition.getStoredResourceOfAsteroid().isEmpty()) { // check if there are Resources stored on the Asteroid
            int index = currentPosition.getStoredResourceOfAsteroid().size()-1;

            itsInventory.addResource(currentPosition.getStoredResourceOfAsteroid().get(index));
            currentPosition.getStoredResourceOfAsteroid().remove(index);

            Game.getCurrentPlayer().decreaseNumberOfMoves(); // action was successful, decrease the number of moves
        } else {
            GameWindow.infobox("The Asteroid is full","Error");
        }
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Inventory getItsInventory() { return itsInventory; }
    public void setItsInventory(Inventory itsInventory) { this.itsInventory = itsInventory; }

    public int getPlayerID() { return playerID; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }

    //</editor-fold>

}
