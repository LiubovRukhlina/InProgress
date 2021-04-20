package com.InProgress;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    //<editor-fold desc="Attributes">
    private int playerID;
    private int numberOfMoves;
    private ArrayList<Settler> settlers;
    private Boolean isPlaying;

    //<editor-fold desc="Constructor"

    /**
     * Constructor of the Player class.
     *
     * @param playerID ID of the Player
     */
    public Player(int playerID) {
        this.playerID = playerID;
        this.numberOfMoves = 5;
        this.settlers.add( new Settler("Settler1", Game.getAsteroid(new Random().nextInt(Game.maxX), new Random().nextInt(Game.maxY), new Random().nextInt(Game.maxZ)), this.playerID));
        this.settlers.add( new Settler("Settler2", Game.getAsteroid(new Random().nextInt(Game.maxX), new Random().nextInt(Game.maxY), new Random().nextInt(Game.maxZ)), this.playerID));
        this.settlers.add( new Settler("Settler3", Game.getAsteroid(new Random().nextInt(Game.maxX), new Random().nextInt(Game.maxY), new Random().nextInt(Game.maxZ)), this.playerID));
        this.isPlaying = true;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    // TODO implementation of all required methods
    /**
     * Decreases the number of moves of this Player after each action.
     */
    public void decreaseNumberOfMoves(){
        numberOfMoves--;
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">


    public int getPlayerID() {
        return playerID;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getNumberOfMoves() { return numberOfMoves; }
    public void setNumberOfMoves(int numberOfMoves) { this.numberOfMoves = numberOfMoves; }

    public ArrayList<Settler> getSettlers() {
        return settlers;
    }
    public void setSettlers(ArrayList<Settler> settlers) {
        this.settlers = settlers;
    }

    public Boolean getPlaying() { return isPlaying; }
    public void setPlaying(Boolean playing) { isPlaying = playing; }
}

