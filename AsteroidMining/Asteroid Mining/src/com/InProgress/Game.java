package com.InProgress;

import java.io.*;

public class Game implements Serializable {

    private Asteroid asteroid;
    private TravellerBase traveller;
    public static GameState gameState; // current game state


    public Game() {

    }

    void startGame() {
        // initialize everything

        gameState = GameState.PLAYING;
    }

    public static void endGame() {

    }

    private boolean isGameOver() {
        if (gameState == GameState.PLAYING) {
            return false;
        }
        startGame();
        return true;
    }


    private void isWinner() {
        if (gameState == GameState.PLAYING) {
            new WinnerFrame(); // notify that you win
            gameState = GameState.WINNER;
        }

        if (gameState == GameState.KILLED) {
            new LoserFrame(); // notify that you lose
        }
    }


    void setAsteroidField() {

    }

    // save current flow of game
    private void serializeGame(Game game, String path) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
            objectOutputStream.writeObject(game);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
