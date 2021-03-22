package com.InProgress;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Game implements Serializable {
    public static ArrayList<TravellerBase> active; //list of settlers and robots
    public static ArrayList<Asteroid> asteroids; //list of all asteroids
    public static ArrayList<TransportGate> gates;
    Sun sun; // in charge of sun storms

    public Game() { }

    /**
     * Processes the inputs entered by a user
     * @param input a command a user types in
     */
    public void parser(String input) {
        String[] parsedInput = input.split(" ");
        Asteroid asteroid = null;
        TravellerBase currSettler = null;

        try {
            int settlerIndex = Integer.parseInt(parsedInput[0]);
            currSettler = active.get(settlerIndex - 1);
        } catch (Exception e) {
            switch (parsedInput[0]) { //single-word input
                case "sunStorm": sun.startSunStorm(); break;
            }
        }

        if (parsedInput.length == 2) { //2-word input
            switch (parsedInput[1]) {
                case "buildGates": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildTransportGate();
                }; break;
                case "buildRobot": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildRobot();
                }; break;
            }
        }

        if (parsedInput.length == 3) {  //3-word input
            String destinationName = parsedInput[2];
            for (Asteroid aster:asteroids
            ) {
                if (aster.getName().equals(destinationName)) {
                    asteroid = aster;
                    break;
                }
            }
            switch (parsedInput[1]) {
                case "travel": currSettler.travel(asteroid); break;
                case "mine": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).mine(asteroid);
                };break;
                case "pickUpResource": {
                    if (currSettler instanceof Settler)
                    ((Settler) currSettler).pickUpResources(asteroid);
                };break;
                case "deployGate": {
                    if (currSettler instanceof Settler)
                    ((Settler) currSettler).deployTransportGate(asteroid);
                }; break;
                case "fastTravel": currSettler.fastTravel(gates.get(0));break;
                case "buildSpaceStation": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).buildSpaceStation(asteroid);
                }; break;
                case "drill": currSettler.drill(asteroid); break;
                case "storeResource": {
                    if (currSettler instanceof Settler)
                        ((Settler) currSettler).storeResources(asteroid);
                }break;
            }
        }
    }

    /**
     *  Instantiates all the main parts of the game
     */
    void startGame() {
        active = new ArrayList<TravellerBase>();
        asteroids = new ArrayList<>();
        gates = new ArrayList<>();
        sun = new Sun();

        //add three settlers
        for (int i = 1; i <= 3; i++) {
            active.add(i-1, new Settler(String.valueOf(i)));
        }
        setAsteroidField();

        // at the beginning all of the settlers are on the first asteroid
        for (TravellerBase settler:active
             ) {
            settler.setCurrentPosition(asteroids.get(0));
            asteroids.get(0).setSettlersOnAsteroid((Settler) settler);
        }
        TransportGate tg1 = new TransportGate();
        TransportGate tg2 = new TransportGate();

        tg1.makePair(tg2);
        tg1.activateTransportGate();
        tg1.setCurrentPosition(asteroids.get(0));
        tg2.setCurrentPosition(asteroids.get(4));

        gates.add(tg1);
        gates.add(tg2);
    }

    /**
     * describes the end of the game
     */
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


    /**
     * places asteroids and define neighbors
     */
    void setAsteroidField() {
        System.out.println("setAsteroidField()");
        for (int i = 1; i <= 5; i++) {
            asteroids.add(i-1, new Asteroid("A" + String.valueOf(i), i*2, false));
        }

        //sets neighbors
        asteroids.get(0).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1))));
        asteroids.get(1).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(0),asteroids.get(2))));
        asteroids.get(2).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1),asteroids.get(3))));
        asteroids.get(3).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(1),asteroids.get(2))));
        asteroids.get(4).setNeighbors(new ArrayList<>(Arrays.asList(asteroids.get(3))));
    }

    // getters and setters
    public static ArrayList<TravellerBase> getActive() { return active; }
    public static ArrayList<Asteroid> getAsteroids() { return asteroids; }

}
