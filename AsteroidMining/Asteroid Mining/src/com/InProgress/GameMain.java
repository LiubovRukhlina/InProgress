package com.InProgress;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class GameMain {

    List<TravellerBase> active; //list of settlers and robots
    List<Asteroid> asteroids; //list of all asteroids
    String inputAsteroid;
    String inputSettlerIndex;

    public void parser(String input) {
        String[] parsedInput = input.split(" ");

        inputSettlerIndex = parsedInput[0];
        int travellerIndex = Integer.parseInt(parsedInput[0]);
        String destination = parsedInput[2];

        if (parsedInput.length >= 3) {  // there can be one word as an input
            switch (parsedInput[1].toLowerCase(Locale.ROOT)) {
                case "travel": travel(travellerIndex, destination); break;
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

    private void travel(int travellerIndex, String destinationName) {
        inputAsteroid = destinationName;
        System.out.println("Get neighbors");
        System.out.println("travel " + destinationName);
        System.out.println("change location " + destinationName);
        System.out.println("Settler " + travellerIndex + " travelled to Asteroid " + destinationName);



        /*System.out.println();
        TravellerBase settler = active.get(travellerIndex);
        TravellerBase robotOrSettler = active.get(travellerIndex);
        Optional<Asteroid> destination = asteroids.stream().filter(a -> a.getName().equals(destinationName)).findAny();
        robotOrSettler.setCurrentPosition(destination.get());*/
    }
}
