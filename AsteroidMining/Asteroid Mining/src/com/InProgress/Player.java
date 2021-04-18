package com.InProgress;

import java.util.ArrayList;

public class Player {

    //<editor-fold desc="Attributes">
    private int playerID;
    private int numberOfMoves;
    private ArrayList<Settler> settlers;
    private Boolean isPlaying;

    //<editor-fold desc="Constructor"
    /**
     * Constructor of the Player class
     */

    public Player(int playerID) {
        this.playerID = playerID;
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

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

    public ArrayList<Settler> getSettlers() {
        return settlers;
    }

    public void setSettlers(ArrayList<Settler> settlers) {
        this.settlers = settlers;
    }
}

