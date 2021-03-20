package com.InProgress;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class Game implements Serializable {

    List<TravellerBase> active; //list of settlers and robots
    List<Asteroid> asteroids; //list of all asteroids
    String inputAsteroid;
    String inputSettlerIndex;
    public static GameState gameState; // current game state


    public Game(List<TravellerBase> active, List<Asteroid> asteroids) {
        this.active = active;
        this.asteroids = asteroids;
    }

    public void parser(String input) {
        String[] parsedInput = input.split(" ");

        inputSettlerIndex = parsedInput[0];
        int settlerIndex = Integer.parseInt(parsedInput[0]);
        TravellerBase currSettler = active.get(settlerIndex);
        Asteroid destinationAsteroid;
        String destinationName = parsedInput[2];

        for (Asteroid asteroid:asteroids
             ) {
            if (asteroid.getName().equals(destinationName))
                destinationAsteroid = asteroid;
        }

        if (parsedInput.length >= 3) {  // there can be one word as an input
            switch (parsedInput[1].toLowerCase(Locale.ROOT)) {
                case "travel": currSettler.travel(destinationAsteroid); break;
                case "mine": break;
                case "pickUpResource": break;
                case "buildGates": break;
                case "deployGate": break;
                case "fastTravel": break;
                case "buildRobot": break;
                case "buildSpaceStation": break;
                case "drill": break;
            }
        }
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
