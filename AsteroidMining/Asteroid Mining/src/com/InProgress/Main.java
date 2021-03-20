package com.InProgress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
	/*// write your code here
        //String userInput = "1 travel A2";
        GameMain gameMain = new GameMain();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput;


        while (true)
        {
            try {
                userInput = reader.readLine();
                if (userInput.equals("no"))
                    break;
                gameMain.parser(userInput);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Asteroid destAsteroid;
            userInput = reader.readLine(); // is this a hollow asteroid?
            for (Asteroid aster: gameMain.asteroids
                 ) {
                if (aster.getName().equals(gameMain.inputAsteroid) && aster.getHollow()){
                    destAsteroid = aster;
                    System.out.println("yes");
                }

                else if (aster.getName().equals(gameMain.inputAsteroid) && !aster.getHollow()){
                    destAsteroid = aster;
                    System.out.println("no");
                }
            }

            userInput = reader.readLine(); // is there enough space?
            if (destAsteroid.hasEnoughSpace()) {
                System.out.println("yes");
                System.out.println("Settler" + gameMain.inputSettlerIndex + " is hidden in Asteroid " + gameMain.inputAsteroid);
            }
        }
    }*/
    }
}
