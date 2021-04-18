package com.InProgress;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
    public static FileWriter fileWriter = null;
    public static File outputFile;
    public static File benchmarkFile;
    public static int settlerCounter;
    public static int robotCounter;
    public static int gateCounter;


    public static void parser(String input) throws IOException {
        settlerCounter = 1;
        robotCounter = 1;
        gateCounter = 1;

        Scanner scanner = null;
        String outputFileName = "output" + input + ".txt";
        outputFile = new File("InProgress/Input/" + outputFileName);
        outputFile.delete();

        switch (Integer.parseInt(input)) { // read in test case and input file
            case 1: {
                scanner = new Scanner(new File("InProgress/Input/Set Asteroid Belt.txt"));
                benchmarkFile = new File("InProgress/Output/Set Asteroid Belt.txt");
            }
            break;

            case 2: {
                scanner = new Scanner(new File("InProgress/Input/Creating a Settler.txt"));
                benchmarkFile = new File("InProgress/Output/Creating a Settler.txt");
            }
            break;

            case 3: {
                scanner = new Scanner(new File("InProgress/Input/Travelling to an Asteroid.txt"));
                benchmarkFile = new File("InProgress/Output/Travelling to an Asteroid.txt");
            }
            break;

            case 4: {
                scanner = new Scanner(new File("InProgress/Input/Drilling an Asteroid.txt"));
                benchmarkFile = new File("InProgress/Output/Drilling an Asteroid.txt");
            }
            break;

            case 5: {
                scanner = new Scanner("InProgress/Input/Mining an Asteroid.txt");
                benchmarkFile = new File("InProgress/Output/Mining an Asteroid.txt");
            }
            break;

            case 6: {
                scanner = new Scanner("InProgress/Input/Leaving Resource on an Asteroid.txt");
                benchmarkFile = new File("InProgress/Output/Leaving Resource on an Asteroid.txt");
            }
            break;

            case 7: {
                scanner = new Scanner("InProgress/Input/Pick up Resource.txt");
                benchmarkFile = new File("InProgress/Output/Pick up Resource.txt");
            }
            break;

            case 8: {
                scanner = new Scanner("InProgress/Input/Build Gate.txt");
                benchmarkFile = new File("InProgress/Output/Build Gate.txt");
            }
            break;

            case 9: {
                scanner = new Scanner("InProgress/Input/Deploy Gate.txt");
                benchmarkFile = new File("InProgress/Output/Deploy Gate.txt");
            }
            break;

            case 10: {
                scanner = new Scanner("InProgress/Input/Fast Travel.txt");
                benchmarkFile = new File("InProgress/Output/Fast Travel.txt");
            }
            break;

            case 11: {
                scanner = new Scanner("Input/Hide.txt");
                benchmarkFile = new File("InProgress/Output/Hide.txt");
            }
            break;

            case 12: {
                scanner = new Scanner("InProgress/Input/Build Robot.txt");
                benchmarkFile = new File("InProgress/Output/Build Robot.txt");
            }
            break;

            case 13: {
                scanner = new Scanner(new File("InProgress/Input/Travelling to an Asteroid (Robot).txt"));
                benchmarkFile = new File("InProgress/Output/Travelling to an Asteroid (Robot).txt");
            }
            break;

            case 14: {
                scanner = new Scanner(new File("InProgress/Input/Drilling an Asteroid (Robot).txt"));
                benchmarkFile = new File("InProgress/Output/Drilling an Asteroid (Robot).txt");
            }
            break;

            case 15: {
                scanner = new Scanner("InProgress/Input/Build Space Station.txt");
                benchmarkFile = new File("InProgress/Output/Build Space Station.txt");
            }
            break;

            case 16: {
                scanner = new Scanner("InProgress/Input/SunStorm.txt");
                benchmarkFile = new File("InProgress/Output/SunStorm.txt");
            }
            break;

            case 17: {
                scanner = new Scanner("InProgress/Input/Explode Asteroid.txt");
                benchmarkFile = new File("InProgress/Output/Explode Asteroid.txt");
            }
            break;

            case 18: {
                scanner = new Scanner(new File("InProgress/Input/End Game.txt"));
                benchmarkFile = new File("InProgress/Output/End Game.txt");
            }
            break;

            case 19: {
                scanner = new Scanner("InProgress/Input/Sublime.txt");
                benchmarkFile = new File("InProgress/Output/Sublime.txt");
            }
            break;
            case 20: {
                scanner = new Scanner("InProgress/Input/Example.txt");
                benchmarkFile = new File("InProgress/Output/Example.txt");
            }
            break;
        }


        while (scanner.hasNextLine()) { // read whole file
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parsedLine = line.split(" ");

            switch (parsedLine[0]) {
                case "set": {
                    Game.setAsteroidBelt(Integer.parseInt(parsedLine[2]),
                            Integer.parseInt(parsedLine[3]),
                            Integer.parseInt(parsedLine[4]));
                    break;
                }
                case "create": {
                    if (parsedLine[2].equals("SETTLER")) {
                        String name = "S " + settlerCounter++;
                        Game.settlers.add(new Settler(name, Game.getAsteroid(Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5])), 1));
                    }
                    else if (parsedLine[2].equals("ROBOT")) {
                        String name = "R " + robotCounter++;
                        Game.robots.add(new Robot(name, Game.getAsteroid(Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5]))));
                    }
                    else if (parsedLine[2].equals("TRANSPORTGATE")) {
                        String name = "TG " + gateCounter++;
                        Game.gates.add(new TransportGate(name, Game.getAsteroid(Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5]))));
                    }
                    break;
                }
                case "modify": {
                    if (parsedLine[1].equals("S")) {
                        boolean value = false;
                        if(parsedLine[4].equals("TRUE")) {
                            value = true;
                        }
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).setHidden(value);
                    }
                    else if (parsedLine[1].equals("TG")) {
                        boolean value = false;
                        if (parsedLine[4].equals("TRUE")) {
                            value = true;
                        }
                        if (parsedLine[3].equals("ISACTIVE")) {
                            Game.gates.get(Integer.parseInt(parsedLine[2]) - 1).setActive(value);
                        }
                        if (parsedLine[3].equals("PAIR")) {
                            Game.gates.get(Integer.parseInt(parsedLine[2]) - 1).setPair(Game.gates.get(Integer.parseInt(parsedLine[5]) - 1));
                        }
                    }
                    else {
                        if (parsedLine[4].equals("ROCKCOVER")) {
                            Game.getAsteroid(Integer.parseInt(parsedLine[1]), Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3])).setRockCover(Integer.parseInt(parsedLine[5]));
                        } else if (parsedLine[4].equals("ISHOLLOW")) {
                            boolean value = false;
                            if (parsedLine[5].equals("TRUE")) {
                                value = true;
                            }
                            Game.getAsteroid(Integer.parseInt(parsedLine[1]), Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3])).setHollow(value);
                        } else if (parsedLine[4].equals("ISATPERIHELION")) {
                            boolean value = false;
                            if (parsedLine[5].equals("TRUE")) {
                                value = true;
                            }
                            Game.getAsteroid(Integer.parseInt(parsedLine[1]), Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3])).setAtPerihelion(value);
                        }
                    }
                    break;
                }
                case "add": {
                    if (parsedLine[2].equals("S")) {
                        if (parsedLine[1].equals("TRANSPORTGATE")) {
                            String name = "TG" + gateCounter;
                            Game.settlers.get(Integer.parseInt(parsedLine[5]) - 1).getItsInventory().setStoredGates(new TransportGate(name));
                        } else {
                            switch (parsedLine[1]) {
                                case "IRON": {
                                    Game.settlers.get(Integer.parseInt(parsedLine[3]) - 1).getItsInventory().setStoredResources(new Iron("Iron"));
                                    break;
                                }
                                case "CARBON": {
                                    Game.settlers.get(Integer.parseInt(parsedLine[3]) - 1).getItsInventory().setStoredResources(new Carbon("Carbon"));
                                    break;
                                }
                                case "URANIUM": {
                                    Game.settlers.get(Integer.parseInt(parsedLine[3]) - 1).getItsInventory().setStoredResources(new Uranium("Uranium"));
                                    break;
                                }
                                case "WATERICE": {
                                    Game.settlers.get(Integer.parseInt(parsedLine[3]) - 1).getItsInventory().setStoredResources(new WaterIce("WaterIce"));
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        if (parsedLine[5].equals("RESOURCEOFASTEROID")) {
                            switch (parsedLine[1]) {
                                case "IRON": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setResourceOfAsteroid(new Iron("Iron"));
                                    break;
                                }
                                case "CARBON": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setResourceOfAsteroid(new Carbon("Carbon"));
                                    break;
                                }
                                case "URANIUM": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setResourceOfAsteroid(new Uranium("Uranium"));
                                    break;
                                }
                                case "WATERICE": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setResourceOfAsteroid(new WaterIce("WaterIce"));
                                    break;
                                }
                            }
                        }
                        else if (parsedLine[5].equals("STOREDRESOURCEOFASTEROID")) {
                            switch (parsedLine[1]) {
                                case "IRON": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setStoredResourceOfAsteroid(new Iron("Iron"));
                                    break;
                                }
                                case "CARBON": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setStoredResourceOfAsteroid(new Carbon("Carbon"));
                                    break;
                                }
                                case "URANIUM": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setStoredResourceOfAsteroid(new Uranium("Uranium"));
                                    break;
                                }
                                case "WATERICE": {
                                    Game.getAsteroid(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4])).setStoredResourceOfAsteroid(new WaterIce("WaterIce"));
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case "travel": {
                    if(parsedLine[1].equals("S")) {
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).travel(Game.getAsteroid(Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5])));
                    }
                    if(parsedLine[1].equals("R")) {
                        Game.robots.get(Integer.parseInt(parsedLine[2])-1).travel(Game.getAsteroid(Integer.parseInt(parsedLine[3]), Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5])));
                    }
                    break;
                }
                case "drill": {
                    if(parsedLine[1].equals("S")) {
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).drill(Game.settlers.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    }
                    if(parsedLine[1].equals("R")) {
                        Game.robots.get(Integer.parseInt(parsedLine[2])-1).drill( Game.robots.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    }
                    break;
                }
                case "mine": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2])-1).mine(Game.settlers.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    break;
                }
                case "leave": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2])-1).storeResources(0);
                    break;
                }
                case "pick": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2])-1).pickUpResources();
                    break;
                }
                case "build": {
                    if(parsedLine[3].equals("TRANSPORTGATES")) {
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).buildTransportGate();
                    }
                    else if (parsedLine[3].equals("ROBOT")) {
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).buildRobot();
                    }
                    else if (parsedLine[3].equals("SPACESTATION")) {
                        Game.settlers.get(Integer.parseInt(parsedLine[2])-1).buildSpaceStation(Game.settlers.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    }
                    break;
                }
                case "deploy": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2])-1).deployTransportGate(Game.settlers.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    break;
                }
                case "fastTravel": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2]) - 1).fastTravel(Game.settlers.get(Integer.parseInt(parsedLine[2]) - 1).getCurrentPosition());
                    break;
                }
                case "hide": {
                    Game.settlers.get(Integer.parseInt(parsedLine[2])-1).hide(Game.settlers.get(Integer.parseInt(parsedLine[2])-1).getCurrentPosition());
                    break;
                }
                case "sunstorm": {
                    Game.sun.startSunStorm();
                    break;
                }
                case "endgame": {
                    Game.endGame();
                    break;
                }
            }
            evaluator(outputFile, benchmarkFile);
        }
    }


    /**
     * Generates the content of the output file.
     *
     * @param output - output of the testcases generated by methods
     * @param outputLine - the line that is written into the file
     */
    public static void generator(File output, String outputLine)  {
        //if (!output.exists())
        try {
            fileWriter = new FileWriter(output, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(outputLine);
            bufferWriter.write("\n");
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compares the actual output with the expected output.
     *
     * @param actual - file created by the test case
     * @param expected - file of a successful test case
     */
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

        System.out.println();
        System.out.println("Actual: ");
        printList(actualStrings);

        System.out.println("Expected: ");
        printList(expectedStrings);

        if (areEqual) {
            System.out.println("Files are same! Successful test!");
            System.out.println();
        } else {
            System.out.println("Files are not same at line number: " + lineNumber);
            System.out.println("Test Failed!");
            System.out.println();
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