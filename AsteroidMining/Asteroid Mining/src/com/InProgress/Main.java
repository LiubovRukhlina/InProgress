package com.InProgress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.startGame();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            // prints out menu options
            System.out.println("Enter the number of the test case that should run.");
            System.out.println("While the test case runs it will print the input commands to this console window.");
            System.out.println("After that the output will printed and the evaluator will determine if the test was successful.");
            System.out.println();
            System.out.println("Test case 1: Set Asteroid Belt");
            System.out.println("Test case 2: Creating a Settler");
            System.out.println("Test case 3:Travelling to an Asteroid");
            System.out.println("Test case 4: Drilling an Asteroid");
            System.out.println("Test case 5: Mining an Asteroid");
            System.out.println();
            System.out.println("Test case 6: Leaving Resource on an Asteroid");
            System.out.println("Test case 7: Pick up Resource");
            System.out.println("Test case 8: Build Gate");
            System.out.println("Test case 9: Deploy Gate");
            System.out.println("Test case 10: Fast Travel");
            System.out.println();
            System.out.println("Test case 11: Hide");
            System.out.println("Test case 12: Build Robot");
            System.out.println("Test case 13: Travelling to an Asteroid (Robot)");
            System.out.println("Test case 14: Drilling an Asteroid (Robot)");
            System.out.println("Test case 15: Build SpaceStation");
            System.out.println();
            System.out.println("Test case 16: SunStorm");
            System.out.println("Test case 17: Explode Asteroid");
            System.out.println("Test case 18: End Game");
            System.out.println("Test case 19: Sublime");
            System.out.println("Test case 20: Example Case");
            System.out.println();
            System.out.println();
            String userInput = reader.readLine();
            Tester.parser(userInput);
        }
    }
}

