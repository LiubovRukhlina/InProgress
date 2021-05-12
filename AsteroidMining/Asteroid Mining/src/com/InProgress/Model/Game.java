package com.InProgress.Model;

import com.InProgress.GUI.GameWindow;

import java.io.*;
import java.util.*;

public class Game implements Serializable {

    //<editor-fold desc="Attributes">

    private static int maxX; // x dimension of the asteroid belt
    private static int maxY; // y dimension of the asteroid belt
    private static int maxZ; // z dimension of the asteroid belt
    private static int roundCounter;
    private static int numberOfAsteroids; // counts the number of active asteroids
    private static int numberOfGates;
    private static  Sun sun; // in charge of sun storms
    private static ArrayList<Player> players; //list of players
    private static Player currentPlayer; // the Player who is currently playing
    private static Settler activeSettler; // the Settler the currentPlayer is controlling
    public static  ArrayList<ArrayList<ArrayList<Asteroid>>> asteroids; // 3D-list of all asteroids
    private static  ArrayList<Robot> robots; //list of robots
    public static GameWindow game;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    //public Game() { }

    //</editor-fold">


    //<editor-fold desc="Methods">

    /**
     *  Instantiates all the main parts of the game.
     *  It sets up the asteroid belt and creates a sun object.
     *  Instantiates the lists and sets the first player and settler as the current ones.
     *
     * @param numberOfPlayers Number of players that play the game
     * @param x size of the x dimension of the asteroid belt
     * @param y size of the x dimension of the asteroid belt
     * @param z size of the z dimension of the asteroid belt
     */
    public static void startGame(int numberOfPlayers, int x, int y, int z) {
        asteroids = new ArrayList<ArrayList<ArrayList<Asteroid>>>();
        setAsteroidBelt(x,y,z);//needs to be called before sun
        roundCounter = 0;
        numberOfAsteroids = x*y*z;
        numberOfGates = 0;

        sun = new Sun();
        robots = new ArrayList<>();

        players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) { // creates the players
            players.add(new Player(i));
        }
        currentPlayer = players.get(0);                     // first player is starts
        activeSettler = currentPlayer.getSettlers().get(0); // with its first Settler

    }



    /**
     *  Before each round starts the system checks whether a sunstorm has to occur
     *  and if the perihelion state of the asteroids changes.
     *  The number of moves of the Players is restored and all robots start a drilling action.
     */
    public static void nextRound() {

        roundCounter++;
        System.out.println("roundcounter:"+ roundCounter);
        sun.decreaseCountdown();

        if(sun.getCountdownOfSunStorm() == 0) { // checks if sunstorm occurs in this round
            sun.startSunStorm(); // the sunstrom might kills Settlers or Robots
        }

        if(roundCounter%5 == 0) { // perihelion state changes every 5 rounds
            sun.changeSunX(); // due to the change of the sunX attribute asteroids might explode and kill Settlers or Robots
        }

        for (Robot r : robots ) { // all robots drill on their current asteroid
            r.drill(r.getCurrentPosition()); // drilling might cause an explosion that kills Settlers or Robots
        }

        //-----------------------------------------------------------------------------------
        // the previous 3 checks could kill all remaining settlers.
        // It must be checked if the Players still can play, before the Game can proceed
        boolean isThereStillSomeone = false;
        for (Player p : players ) {
            p.setNumberOfMoves(5);// restore number of moves of all Players
            if(p.checkSettlers()) {
                isThereStillSomeone = true; // if at least one Player remains in the Game
            }
        }
        if(!isThereStillSomeone) {
            Game.endGame(0);
        }
        if(!currentPlayer.getPlaying()) { // if the currentPlayer died during the previous actions we assign a new one
            currentPlayer = currentPlayer.getNextPlayer();
            for(Settler i: Game.getCurrentPlayer().getSettlers())
            {
                if(i.isAlive)
                {
                    Game.setActiveSettler(i);
                }
            }
        }
        //-----------------------------------------------------------------------------------

        for (Player p : players ) { // restore number of moves of all active Players
            if(p.getPlaying()) {
                p.setNumberOfMoves(5);
            }
        }
    }

    /**
     * Checks how many Settlers are still active.
     *
     * @return number of settlers remaining active Settlers
     */

    public static int getNumberOfSettlers()
    {
        int sum = 0;
        for(Player i:players)
        {
            for(int j=0;j<i.getSettlers().size();j++)
                if(i.getSettlers().get(j).getAlive())
                    sum++;
        }
        return sum;
    }

    /**
     * Ends the turn of the currentPlayer if he has no moves left.
     */
    public static void Controller(){
        if(currentPlayer.getNumberOfMoves() == 0)
        {
            currentPlayer.endMyTurn();
        }
    }


    public static void controller(int index, ArrayList<String> input) {

        int returnValue;

        switch(index) {
            case 0: { // set up the game
                int p = Integer.parseInt(input.get(0));
                int x = Integer.parseInt(input.get(1));
                int y = Integer.parseInt(input.get(2));
                int z = Integer.parseInt(input.get(3));
                startGame(p,x,y,z);

                game = new GameWindow();
                game.initialize();

                return;
            }

            //-----------------------------------------------------------------------------------
            // cases 1 to 11 are Settler actions started by the user
            case 1: { // travel
                int x = Integer.parseInt(input.get(0));
                int y = Integer.parseInt(input.get(1));
                int z = Integer.parseInt(input.get(2));

                returnValue = activeSettler.travel(getAsteroid(x,y,z));

                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else if(returnValue == 1) {
                    // TODO no space
                }
                else if(returnValue == 2) {
                    // TODO not neighborhood
                }
            }
            break;

            case 2: { // fastTravel
                returnValue = activeSettler.fastTravel(activeSettler.getCurrentPosition());
                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else if(returnValue == 1) {
                    // TODO no space
                }
                else if(returnValue == 2) {
                    // TODO not active gate
                }
                else if(returnValue == 3) {
                    // TODO no gate
                }
            }
            break;

            case 3: { // drill
                returnValue = activeSettler.drill(activeSettler.getCurrentPosition());
                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else {
                    // TODO already drilled through
                }
            }
            break;

            case 4: { // mine
                returnValue = activeSettler.mine(activeSettler.getCurrentPosition());
                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else if(returnValue == 1) {
                    // TODO cannot mine
                }
                else if(returnValue == 2) {
                    // TODO inventory is full
                }
            }
            break;

            case 5: { // leave resource
                returnValue = 1; // default value if the Resource is not in the Inventory

                for(int i = 0; i < activeSettler.getItsInventory().getStoredResources().size(); i++)
                {
                    if(activeSettler.getItsInventory().getStoredResources().get(i).getResourceType().equals(input.get(0)) )
                    {
                        returnValue = activeSettler.leaveResource(i);
                    }
                }

                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else{
                    // TODO not in the inventory
                }
            }
            break;

            case 6: { // pick up resource
                returnValue = activeSettler.pickUpResources();

                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else if(returnValue == 1) {
                    // TODO nothing to pick up
                }
                else if(returnValue == 2) {
                    // TODO nothing to pick up
                }
            }
            case 7: { // build robot
                returnValue = activeSettler.buildRobot();

                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else {
                    // TODO not enough resources
                }
            }
            break;

            case 8: { // build gates
                returnValue = activeSettler.buildTransportGate();

                if(returnValue == 0) {
                    currentPlayer.decreaseNumberOfMoves();
                }
                else {
                    // TODO not enough resources
                }
            }
            break;

            case 9: { // build robot
                returnValue = activeSettler.buildSpaceStation(activeSettler.getCurrentPosition());

                if(returnValue == 0) {
                    // TODO end game
                }
                else {
                    // TODO not enough resources
                }
            }
            break;

            case 10: { // deploy gate
                returnValue = activeSettler.deployTransportGate(activeSettler.getCurrentPosition());

                if(returnValue == 0) {
                    // TODO end game
                }
                else {
                    // TODO has no gate / there is already a gate?
                }
            }
            break;

            case 11: { // end turn
                currentPlayer.endMyTurn();
            }
            break;
            //-----------------------------------------------------------------------------------

            //-----------------------------------------------------------------------------------
            // cases 12 to 14 are started by the system
            case 12: { // Asteroid explodes
                // TODO output "Asteroid explodes"
            }
            break;

            case 13: { // Sun storm occured
                // TODO output "Sun strom explodes"
            }
            break;

            case 14: { // Settler died
                // TODO output "Settler died"
            }
            break;
            //-----------------------------------------------------------------------------------

            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }

        if(currentPlayer.getNumberOfMoves() == 0) { // ends turn if user has no moves left
            currentPlayer.endMyTurn();
        }
    }


    /**
     * Ends the game.
     * Decides whether it was successful or not
     */
    public static void endGame(int num) {
        if(num == 1)
            GameWindow.infobox("Detim imim finyish du wa ting, im ye fo sémpere.","Beltalowda");

        else
            GameWindow.infobox("Setara imalowda mogut nawit milowda.","Inyalowda ");
    }

    /**
     * Places the asteroids in the 3D-list
     *
     * @param x x dimension of the asteroid belt
     * @param y y dimension of the asteroid belt
     * @param z z dimension of the asteroid belt
     */
    public static void setAsteroidBelt(int x, int y, int z) {
        maxX = x;
        maxY = y;
        maxZ = z;

        asteroids = new ArrayList<ArrayList<ArrayList<Asteroid>>>(maxX);
        for (int i = 0; i < maxX; i++) {
            asteroids.add(new ArrayList<ArrayList<Asteroid>>(maxY));
            for (int j = 0; j < maxY; j++)
                asteroids.get(i).add(new ArrayList<Asteroid>(maxZ));
        }

        for (int i = 0; i < maxX; i++) {
            for(int j = 0; j < maxY; j++) {
                for(int k = 0; k < maxZ; k++) {
                    asteroids.get(i).get(j).add(k, new Asteroid(i, j, k, new Random().nextInt(5) + 1));
                }
            }
        }
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">


    public static ArrayList<Player> getPlayers() { return players; }

    public static int getMaxX() { return maxX; }

    public static int getMaxY() { return maxY; }

    public static int getMaxZ() { return maxZ; }

    public static Sun getSun() { return sun; }

    public static ArrayList<Robot> getRobots() { return robots; }

    public static Asteroid getAsteroid(int i, int j, int k) { return asteroids.get(i).get(j).get(k); }

    public static Player getCurrentPlayer() { return currentPlayer; }
    public static void setCurrentPlayer(Player currentPlayer) { Game.currentPlayer = currentPlayer; }

    public static Settler getActiveSettler() { return activeSettler; }
    public static void setActiveSettler(Settler activeSettler) { Game.activeSettler = activeSettler; }

    public static int getNumberOfAsteroids() { return numberOfAsteroids; }
    public static void setNumberOfAsteroids(int numberOfAsteroids) { Game.numberOfAsteroids = numberOfAsteroids; }

    public static int getNumberOfGates() { return numberOfGates; }
    public static void setNumberOfGates(int numberOfGates) { Game.numberOfGates = numberOfGates; }

    //</editor-fold>

}
