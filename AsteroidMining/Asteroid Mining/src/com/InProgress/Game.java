package com.InProgress;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Game implements Serializable {
    public static ArrayList<TravellerBase> active; //list of settlers and robots
    public static ArrayList<Asteroid> asteroids; //list of all asteroids
   // public static GameState gameState; // current game state


    public Game(ArrayList<TravellerBase> active, ArrayList<Asteroid> asteroids) {
        this.active = active;
        this.asteroids = asteroids;
    }

    public Game() { }


    public void parser(String input) {
        String[] parsedInput = input.split(" ");

        int settlerIndex = Integer.parseInt(parsedInput[0]);
        TravellerBase currSettler = active.get(settlerIndex - 1);
        Asteroid asteroid = null;
        TransportGate transportGate = null;
        String destinationName = parsedInput[2];

        for (Asteroid aster:asteroids
             ) {
            if (aster.getName().equals(destinationName)) {
                asteroid = aster;
                break;
            }
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
              //  case "fastTravel": currSettler.fastTravel(new TransportGate(destinationName));break;
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
        active = new ArrayList<TravellerBase>();
        asteroids = new ArrayList<>();

        //add three settlers
        for (int i = 1; i <= 3; i++) {
            active.add(i-1, new Settler(String.valueOf(i)));
        }

        setAsteroidField();

        for (TravellerBase settler:active
             ) {
            settler.setCurrentPosition(asteroids.get(0));
        }




        /*for (int i = 0; i < 30; i++) {

            if (i < 15) {
                Predicate<Asteroid> byName = asteroid -> Integer.parseInt(((asteroid.getName()).substring(1))) <= 15;
                List<Asteroid> neighbors = asteroids.stream().filter(byName).collect(Collectors.toList());
                neighbors.remove(asteroids.get(i));

                asteroids.get(i).setNeighbors((ArrayList<Asteroid>)neighbors);
            }

            if (i > 10 && i < 20) {
                Predicate<Asteroid> byName = asteroid -> Integer.parseInt(((asteroid.getName()).substring(1))) <= 20
                        && Integer.parseInt(((asteroid.getName()).substring(1))) >= 11;
                List<Asteroid> neighbors = asteroids.stream().filter(byName).collect(Collectors.toList());
                neighbors.remove(asteroids.get(i));

                asteroids.get(i).setNeighbors((ArrayList<Asteroid>)neighbors);
            }

            if (i > 15 && i < 30) {

            }

        }*/

        //gameState = GameState.PLAYING;
    }

    public static void endGame() {
        System.out.println("endGame()");
        System.out.println("Did the game end successful?");
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("yes")){
            System.out.println("Congratulation! You colonized the Asteroid Belt!");
        } else {
            System.out.println("You lose!");
        }
        System.exit(0);
    }

    /*private boolean isGameOver() {
        if (gameState == GameState.PLAYING) {
            return false;
        }
        startGame();
        return true;
    }*/


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
        System.out.println("setAsteroidField()");
        for (int i = 1; i <= 5; i++) {
            asteroids.add(i-1, new Asteroid("A" + String.valueOf(i), i*2, false));
        }

        //sets the neighbors
        asteroids.get(0).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1))));
        asteroids.get(1).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(0),asteroids.get(2))));
        asteroids.get(2).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1),asteroids.get(3))));
        asteroids.get(3).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1),asteroids.get(2))));
        asteroids.get(4).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(3))));
    }

    /*// save current flow of game
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
    }*/

    // getters and setters
    public static ArrayList<TravellerBase> getActive() { return active; }

    public static ArrayList<Asteroid> getAsteroids() { return asteroids; }

}
