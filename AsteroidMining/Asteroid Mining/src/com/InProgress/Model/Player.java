package com.InProgress.Model;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    //<editor-fold desc="Attributes">
    private int playerID;
    private int numberOfMoves;
    private ArrayList<Settler> settlers = new ArrayList<Settler>();//added
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


        this.settlers.add( new Settler("Settler1", Game.getAsteroid(new Random().nextInt(Game.getMaxX()), new Random().nextInt(Game.getMaxY()), new Random().nextInt(Game.getMaxZ())), this.playerID));
        this.settlers.add( new Settler("Settler2", Game.getAsteroid(new Random().nextInt(Game.getMaxX()), new Random().nextInt(Game.getMaxY()), new Random().nextInt(Game.getMaxZ())), this.playerID));
        this.settlers.add( new Settler("Settler3", Game.getAsteroid(new Random().nextInt(Game.getMaxX()), new Random().nextInt(Game.getMaxY()), new Random().nextInt(Game.getMaxZ())), this.playerID));
        this.isPlaying = true;// makes all the players as true, only 1 can be playing at a time
    }

    //</editor-fold>


    //<editor-fold desc="Methods">

    /**
     * Decreases the number of moves of this Player after each successful action.
     */
    public void decreaseNumberOfMoves(){

        numberOfMoves--;
        System.out.println(numberOfMoves);
    }

    /**
     * Checks if the Settlers of this Player are alive.
     * @return true if the player has alive settlers
     * @return false if all the settlers of the player are dead
     */
    public boolean checkSettlers() {
        int cnt = settlers.size(); //counter to keep track of living Settlers

        for (int i = 0; i < settlers.size(); i++) {
            if (!settlers.get(i).getAlive())
                cnt--; //decrease counter if Settler is dead
        }

        if (cnt == 0) { // if no Settler is alive return false
            this.isPlaying = false; // Player is not Playing anymore
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method ends the turn of this Player.
     * It is called whenever the Player decides to end its turn,
     * when no more moves are left or when all the Settlers are dead.
     */
    public void endMyTurn() {
        Game.setCurrentPlayer(getNextPlayer()); // assigns the next Player
        if(this.playerID == Game.getCurrentPlayer().getPlayerID() || this.playerID < Game.getCurrentPlayer().getPlayerID() ) { // checks if a complete round was played
            Game.nextRound();
        }
    }

    /**
     * Checks which Player is the next one.
     * @return pNext the next Player.
     */
    public Player getNextPlayer() {
        Player pNext = this;

        if(Game.getPlayers().size()== 1) {
            return pNext;
        }
        else if (Game.getPlayers().size()== 2) {

            if (this.getPlayerID() == 0) {

                if (Game.getPlayers().get(1).getPlaying()) {
                    pNext = Game.getPlayers().get(1);
                } else if (Game.getPlayers().get(2).getPlaying()) {
                    pNext = Game.getPlayers().get(0);
                }
            } else if (this.getPlayerID() == 1) {

                if (Game.getPlayers().get(0).getPlaying()) {
                    pNext = Game.getPlayers().get(0);
                } else if (Game.getPlayers().get(1).getPlaying()) {
                    pNext = Game.getPlayers().get(1);
                }
            }
        } else {
            if (this.getPlayerID() == 0) {

                if (Game.getPlayers().get(1).getPlaying()) {
                    pNext = Game.getPlayers().get(1);
                } else if (Game.getPlayers().get(2).getPlaying()) {
                    pNext = Game.getPlayers().get(2);
                }
            } else if (this.getPlayerID() == 1) {

                if (Game.getPlayers().get(2).getPlaying()) {
                    pNext = Game.getPlayers().get(2);
                } else if (Game.getPlayers().get(0).getPlaying()) {
                    pNext = Game.getPlayers().get(0);
                }
            } else if (this.getPlayerID() == 2) {

                if (Game.getPlayers().get(0).getPlaying()) {
                    pNext = Game.getPlayers().get(0);
                } else if (Game.getPlayers().get(1).getPlaying()) {
                    pNext = Game.getPlayers().get(1);
                }
            }
        }
        return pNext;
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public int getPlayerID() { return playerID; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }

    public int getNumberOfMoves() { return numberOfMoves; }
    public void setNumberOfMoves(int numberOfMoves) { this.numberOfMoves = numberOfMoves; }

    public ArrayList<Settler> getSettlers() { return settlers; }
    public void setSettlers(ArrayList<Settler> settlers) { this.settlers = settlers; }

    public Boolean getPlaying() { return isPlaying; }
    public void setPlaying(Boolean playing) { isPlaying = playing; }

    //</editor-fold>
}

