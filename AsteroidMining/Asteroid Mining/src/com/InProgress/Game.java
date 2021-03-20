package com.InProgress;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Game implements Serializable {

    public static List<TravellerBase> active; //list of settlers and robots

    public static List<TravellerBase> getActive() {
        return active;
    }

    public static List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public static List<Asteroid> asteroids; //list of all asteroids
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
        Asteroid asteroid = null;
        TransportGate transportGate = null;
        String destinationName = parsedInput[2];

        for (Asteroid aster:asteroids
             ) {
            if (asteroid.getName().equals(destinationName))
                aster = asteroid;
        }

        if (parsedInput.length >= 3) {  // there can be one word as an input
            switch (parsedInput[1].toLowerCase(Locale.ROOT)) {
                case "travel": currSettler.travel(asteroid); break;
                case "mine": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).mine(asteroid);
                };break;
                case "pickUpResource": {
                    if (currSettler instanceof Settler)
                    ((Settler) currSettler).pickUpResources(asteroid);
                };break;
                case "buildGates": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildTransportGate();
                }; break;
                case "deployGate": {
                    if (currSettler instanceof Settler)
                    ((Settler) currSettler).deployTransportGate(asteroid);
                }; break;
                case "fastTravel": currSettler.fastTravel(new TransportGate(destinationName));break;
                case "buildRobot": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildRobot();
                }; break;
                case "buildSpaceStation": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildSpaceStation(asteroid);
                }; break;
                case "drill": currSettler.drill(asteroid); break;
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


/*    private void isWinner() {
        if (gameState == GameState.PLAYING) {
            new WinnerFrame(); // notify that you win
            gameState = GameState.WINNER;
        }

        if (gameState == GameState.KILLED) {
            new LoserFrame(); // notify that you lose
        }
    }*/


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
