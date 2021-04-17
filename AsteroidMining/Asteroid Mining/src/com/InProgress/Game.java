package com.InProgress;

import java.io.*;
import java.util.*;

public class Game implements Serializable {

    //<editor-fold desc="Attributes">

    public static ArrayList<Settler> settlers; //list of settlers
    public static ArrayList<Robot> robots; //list of robots
    public static ArrayList<ArrayList<ArrayList<Asteroid>>> asteroids; // 3D-list of all asteroids
    public static ArrayList<TransportGate> gates;
    public static ArrayList<Player> players;

    public static int maxX;
    public static int maxY;
    public static int maxZ;
    Sun sun; // in charge of sun storms

    //</editor-fold>


    //<editor-fold desc="Constructor">

    public Game() { }

    //</editor-fold">


    //<editor-fold desc="Methods">

    /**
     *  Instantiates all the main parts of the skeleton
     */
    void startGame() {
        sun = new Sun();
        settlers = new ArrayList<>();
        robots = new ArrayList<>();
        asteroids = new ArrayList<ArrayList<ArrayList<Asteroid>>>();
        ArrayList list1 = new ArrayList();
        ArrayList<Asteroid> list2 = new ArrayList();
        list1.add(list2);
        asteroids.add(list1);
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
     * places asteroids in the 3D-list
     */
    static public void setAsteroidField(int x, int y, int z) { // see Issue #13: Which resource do we assign?
        maxX = x;
        maxY = y;
        maxZ = z;
        for (int i = 0; i < maxX; i++) {
            for(int j = 0; j < maxY; j++) {
                for(int k = 0; k < maxZ; k++) {
                    asteroids.get(i).get(j).add(k, new Asteroid(i, j, k, new Random().nextInt(6) + 1));
                }
            }
        }
        Tester.generator(Tester.outputFile, "asteroidbelt with dimensions " + maxX + " " + maxY + " " + maxZ + " has been set");
    }

    //</editor-fold>


    //<editor-fold desc="Getters and Setters">

    public static ArrayList<Settler> getSettlers() { return settlers; }
    public static ArrayList<Robot> getRobots() { return robots; }
    //public static ArrayList<Asteroid> getAsteroids() { return asteroids; }
    public static Asteroid getAsteroid(int i, int j, int k) { return asteroids.get(i).get(j).get(k); }


    //</editor-fold>

}
