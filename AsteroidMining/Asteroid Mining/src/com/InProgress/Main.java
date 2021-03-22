package com.InProgress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.startGame();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // prints out menu options
            System.out.println();
            System.out.println("travel");
            System.out.println("drill");
            System.out.println("mine");
            System.out.println("storeResource");
            System.out.println("pickUpResource");
            System.out.println("buildRobot");
            System.out.println("buildGates");
            System.out.println("deployGate");
            System.out.println("fastTravel");
            System.out.println("buildSpaceStation");
            System.out.println("sunStorm");
            System.out.println();

            String userInput = reader.readLine();
            game.parser(userInput);
        }
    }

}
