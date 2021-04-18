package com.InProgress;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
    public static File outputFile;
    public static int counter = 1;

    public static void parser(String input) throws IOException {
        Scanner scanner = null;
        String outputFileName = "output" + counter + ".txt";
        outputFile = new File("Input/" + outputFileName);
        counter++;
        switch (Integer.parseInt(input)) {
            case 1: {
                try {
                    scanner = new Scanner(new File("Input/Set Asteroid Belt.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");
                    Game.setAsteroidBelt(Integer.parseInt(parsedLine[2]),
                            Integer.parseInt(parsedLine[3]),
                            Integer.parseInt(parsedLine[4]));
                }
                evaluator(outputFile, new File("Output/Set Asteroid Belt.txt"));
            }break;

            case 2: {
                scanner = new Scanner(new File("Input/Set Settler.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    if (parsedLine[0].equals("set"))
                        Game.setAsteroidBelt(Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]));
                    else {
                        Settler settler = new Settler("S1");
                        Game.settlers.add(settler);
                        settler.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));
                    }
                }
                evaluator(outputFile, new File("Output/Set Settler.txt"));
            }break;

            case 3: {
                scanner = new Scanner(new File("Input/Travelling to an Asteroid.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    if (parsedLine[0].equals("set"))
                        Game.setAsteroidBelt(
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4])
                        );
                    else if (parsedLine[0].equals("create")) {
                        Settler settler = new Settler("S1");
                        Game.settlers.add(settler);
                        settler.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));
                    }/* else {
                        settlers.get(0).travel(getAsteroid(
                                Integer.parseInt(parsedLine[1]),
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3])
                        ));
                    }*/
                }
                evaluator(outputFile, new File("Output/Travelling to an Asteroid.txt"));
            }break;

            case 4: {
                scanner = new Scanner(new File("Input/Drilling an Asteroid.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    if (parsedLine[0].equals("set"))
                        Game.setAsteroidBelt(
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4])
                        );
                    else if (parsedLine[0].equals("create")) {
                        Settler settler = new Settler("S1");
                        Game.settlers.add(settler);
                        settler.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));
                    } else {
                        Game.settlers.get(0).drill(Game.getAsteroid(
                                Integer.parseInt(parsedLine[1]),
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3])
                        ));
                    }
                }
                evaluator(outputFile, new File("Output/Drilling an Asteroid.txt"));
            }break;

            case 5: {
                scanner = new Scanner("Input/Mining an Asteroid.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    switch (parsedLine[0]) {
                        case "set": {
                            Game.setAsteroidBelt(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                        }break;

                        case "create": {
                            Settler settler = new Settler("S1");
                            Game.settlers.add(settler);
                            settler.setCurrentPosition(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4]),
                                    Integer.parseInt(parsedLine[5])
                            ));
                        }break;

                        case "add": {
                            Asteroid asteroid = Game.getAsteroid(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                            asteroid.getResourceOfAsteroid().set(0, new Carbon(parsedLine[1]));
                        }

                        case "modify": {
                            Asteroid asteroid = Game.getAsteroid(
                                    Integer.parseInt(parsedLine[1]),
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3])
                            );
                            asteroid.setRockCover(Integer.parseInt(parsedLine[5]));
                        }break;

                        case "mine": {
                            Game.settlers.get(0).mine(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[1]),
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3])
                            ));
                        }break;
                    }break;
                }
                evaluator(outputFile, new File("Output/Mining an Asteroid.txt"));
            }break;

            case 6: {
                scanner = new Scanner("Input/Leaving Resource on an Asteroid.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    switch (parsedLine[0]) {
                        case "set": {
                            Game.setAsteroidBelt(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                        }break;

                        case "create": {
                            Settler settler = new Settler("S1");
                            Game.settlers.add(settler);
                            settler.setCurrentPosition(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4]),
                                    Integer.parseInt(parsedLine[5])
                            ));
                        }break;

                        case "add": {

                        }break;

                        case "leave": {

                        }
                    }break;
                }
                evaluator(outputFile, new File("Output/Leaving Resource on an Asteroid.txt"));
            }break;

            case 7: {
                scanner = new Scanner("Input/Pick up Resource.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    switch (parsedLine[0]) {
                        case "set": {
                            Game.setAsteroidBelt(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                        }
                        break;

                        case "create": {
                            Settler settler = new Settler("S1");
                            Game.settlers.add(settler);
                            settler.setCurrentPosition(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4]),
                                    Integer.parseInt(parsedLine[5])
                            ));
                        }
                        break;
                    }break;
                }
                evaluator(outputFile, new File("Output/Pick up Resource.txt"));
            }break;

            case 8: {
                scanner = new Scanner("Input/Build Gate.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    switch (parsedLine[0]) {
                        case "set": {
                            Game.setAsteroidBelt(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                        }
                        break;

                        case "create": {
                            Settler settler = new Settler("S1");
                            Game.settlers.add(settler);
                            settler.setCurrentPosition(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4]),
                                    Integer.parseInt(parsedLine[5])
                            ));
                        }
                        break;
                    }break;
                }
                evaluator(outputFile, new File("Output/Build Gate.txt"));
            }break;

            case 9: {
                scanner = new Scanner("Input/Deploy Gate.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    switch (parsedLine[0]) {
                        case "set": {
                            Game.setAsteroidBelt(
                                    Integer.parseInt(parsedLine[2]),
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4])
                            );
                        }
                        break;

                        case "create": {
                            Settler settler = new Settler("S1");
                            Game.settlers.add(settler);
                            settler.setCurrentPosition(Game.getAsteroid(
                                    Integer.parseInt(parsedLine[3]),
                                    Integer.parseInt(parsedLine[4]),
                                    Integer.parseInt(parsedLine[5])
                            ));
                        }
                        break;
                    }break;
                }
                evaluator(outputFile, new File("Output/Deploy Gate.txt"));
            }break;

            case 10: {
                scanner = new Scanner("Input/Fast Travel.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    if (parsedLine[0].equalsIgnoreCase("set")){
                        Game.setAsteroidBelt(
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4])
                        );
                    } else if (parsedLine[0].equalsIgnoreCase("create")
                            && (parsedLine[2].equalsIgnoreCase("settler"))) {
                        Settler settler = new Settler("S1");
                        Game.settlers.add(settler);
                        settler.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));
                    } else if (parsedLine[0].equalsIgnoreCase("create")
                            && (parsedLine[2].equalsIgnoreCase("transportgate"))) {
                        TransportGate tg1 = new TransportGate();
                        tg1.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));

                        TransportGate tg2 = new TransportGate();
                        tg2.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));

                        tg1.activateTransportGate();
                        tg2.activateTransportGate();
                        tg1.makePair(tg2);

                    } else {
                        //settlers.get(0).fastTravel(settlers.get(0).getCurrentPosition()); //should be implemented later
                    }
                }
                evaluator(outputFile, new File("Output/Fast Travel.txt"));
            }

            case 11: {
                scanner = new Scanner("Input/Hide.txt");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] parsedLine = line.split(" ");

                    if (parsedLine[0].equalsIgnoreCase("set")){
                        Game.setAsteroidBelt(
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4])
                        );
                    } else if (parsedLine[0].equalsIgnoreCase("create")) {
                        Settler settler = new Settler("S1");
                        Game.settlers.add(settler);
                        settler.setCurrentPosition(Game.getAsteroid(
                                Integer.parseInt(parsedLine[3]),
                                Integer.parseInt(parsedLine[4]),
                                Integer.parseInt(parsedLine[5])
                        ));
                    } else if (parsedLine[0].equalsIgnoreCase("modify")
                            && (parsedLine[4].equalsIgnoreCase("rockcover"))) {
                        Asteroid asteroid = Game.getAsteroid(
                                Integer.parseInt(parsedLine[1]),
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3])
                        );
                        asteroid.setRockCover(Integer.parseInt(parsedLine[5]));

                    } else if (parsedLine[0].equalsIgnoreCase("modify")
                            && (parsedLine[4].equalsIgnoreCase("ishollow"))) {
                        Asteroid asteroid = Game.getAsteroid(
                                Integer.parseInt(parsedLine[1]),
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3])
                        );
                        String bool = parsedLine[5];
                        if (bool.equalsIgnoreCase("true"))
                            asteroid.setHollow(true);
                        else
                            asteroid.setHollow(false);
                    } else {
                        Game.settlers.get(0).hide(Game.getAsteroid(
                                Integer.parseInt(parsedLine[1]),
                                Integer.parseInt(parsedLine[2]),
                                Integer.parseInt(parsedLine[3])
                        ));
                    }
                }
                evaluator(outputFile, new File("Output/Hide.txt"));
            }
        }
    }


    /**
     * @param output - output of the testcases generated by methods
     * @param outputLine - the line that is written into the file
     */
    public static void generator(File output, String outputLine)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printWriter.println(outputLine);
        printWriter.close();
    }

    public static void evaluator(File actual, File expected) throws IOException {
        BufferedReader bufferedReaderActual = new BufferedReader(new FileReader(actual));
        BufferedReader bufferedReaderExpected = new BufferedReader(new FileReader(expected));
        ArrayList<String> actualStrings = new ArrayList<>();
        ArrayList<String> expectedStrings = new ArrayList<>();

        String string1 = bufferedReaderActual.readLine();
        String string2 = bufferedReaderExpected.readLine();
        actualStrings.add(string1);
        expectedStrings.add(string2);
        int lineNumber = 1;
        boolean areEqual = false; // initialization

        while(string1 != null && string2 != null) {
            if (string1.equalsIgnoreCase(string2)) {
                areEqual = true;
                lineNumber++;
            } else {
                areEqual = false;
                break;
            }
            string1 = bufferedReaderActual.readLine();
            string2 = bufferedReaderExpected.readLine();
            actualStrings.add(string1);
            expectedStrings.add(string2);
        }

        System.out.println("Actual: ");
        printList(actualStrings);

        System.out.println("Expected: ");
        printList(expectedStrings);

        if (areEqual)
            System.out.println("Files are same! Successful test!");
        else {
            System.out.println("Files are not same at line number: " + lineNumber);
            System.out.println("Test Failed!");
        }

    }

    private static void printList(ArrayList<String> strings)
    {
        for (String s:strings
        ) {
            if (s != null)
                System.out.println(s);
        }
        System.out.println();
    }
}
