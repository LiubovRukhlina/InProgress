package com.InProgress.Model;

import java.io.*;
import java.util.*;

public class Game implements Serializable {

    //<editor-fold desc="Attributes">

    private static int maxX; // x dimension of the asteroid belt
    private static int maxY; // y dimension of the asteroid belt
    private static int maxZ; // z dimension of the asteroid belt
    private static int roundCounter = 0;
    private static  Sun sun; // in charge of sun storms
    private static ArrayList<Player> players; //list of players
    private static Player currentPlayer; // the Player who is currently playing
    private static Settler activeSettler; // the Settler the currentPlayer is controlling
    private static  ArrayList<ArrayList<ArrayList<Asteroid>>> asteroids; // 3D-list of all asteroids
    private static  ArrayList<Robot> robots; //list of robots

    //</editor-fold>


    //<editor-fold desc="Constructor">

    //public Game() { }

    //</editor-fold">


    //<editor-fold desc="Methods">

    /**
     *  Instantiates all the main parts of the skeleton
     */
    public static void startGame(int numberOfPlayers, int x, int y, int z) {
        sun = new Sun();
        robots = new ArrayList<>();
        asteroids = new ArrayList<ArrayList<ArrayList<Asteroid>>>();

        players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) { players.add(new Player(i)); }

        setAsteroidBelt(x,y,z);
    }

    /**
     *  Before each round starts the system checks whether a sunstorm has to occur
     *  and if the perihelion state of the asteroids changes.
     *  The number of moves of the Players is restored and all robots start a drilling action.
     */
    public static void nextRound() {

        roundCounter++;
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
        for (Player p : players ) { // restore number of moves of all Players
           if(p.checkSettlers()) {
              isThereStillSomeone = true; // if at least one Player remains in the Game
           }
        }
        if(!isThereStillSomeone) {
            Game.endGame();
        }
        if(!currentPlayer.getPlaying()) { // if the currentPlayer died during the previous actions we assign a new one
            currentPlayer = currentPlayer.getNextPlayer();
        }
        //-----------------------------------------------------------------------------------

        for (Player p : players ) { // restore number of moves of all active Players
            if(p.getPlaying()) {
                p.setNumberOfMoves(5);
            }
        }
    }



    /**
     * describes the end of the game
     */
    public static void endGame() {
        // TODO Winning Window (Controller)
    }

    /**
     * places asteroids in the 3D-list
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

    //</editor-fold>

}
