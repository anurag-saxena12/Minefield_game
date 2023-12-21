//Import Section
import java.util.Random;
import java.util.Scanner;

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */

public class main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            //initialize variables
            int rows = 0;
            int columns = 0;
            int flags = 0;
            boolean debug = true;
            //declare variables
            String coordinates;
            String[] cSplit;
            int x, y;

            System.out.println("Choose a difficulty level: Easy, Medium, or Hard");
            String difficulty = scanner.next().toLowerCase();
            if (difficulty.equals("easy")) {
                rows = 5;
                columns = 5;
                flags = 5;
            }
            if (difficulty.equals("medium")) {
                rows = 9;
                columns = 9;
                flags = 12;
            }
            if (difficulty.equals("hard")) {
                rows = 20;
                columns = 20;
                flags = 40;
            }

            Minefield minefield = new Minefield(rows, columns, flags); //sets field based on difficulty

            System.out.println();
            if (debug == true) { //debug the if set debug variable to true
                minefield.debug();
            }
            System.out.println(minefield);
            System.out.println("Enter your initial coordinates in following format (do not include brackets): [x],[y]");
            coordinates = scanner.next();
            //taking in first coordinates
            cSplit = coordinates.split(",");
            x = Integer.parseInt(cSplit[0]);
            y = Integer.parseInt(cSplit[1]);
            minefield.createMines(x, y, flags);
            minefield.evaluateField();
            minefield.guess(x,y,false);
            minefield.revealStartingArea(x, y);

            //game loop
            while (!minefield.gameOver()) {
                System.out.println();
                if (debug == true) { //debug if debug variable is true
                    minefield.debug();
                }
                System.out.println(minefield);
                System.out.println("Enter a coordinate and if you wish to place a flag (remaining: " + minefield.getFlags() + ") in following format (do not include brackets) [x],[y],[F/N]");
                coordinates = scanner.next();
                cSplit = coordinates.split(",");
                x = Integer.parseInt(cSplit[0]);
                y = Integer.parseInt(cSplit[1]);
                //handles flags
                if (cSplit[2].equals("F")) {
                    minefield.guess(x, y, true);
                } else if (cSplit[2].equals("N")) {
                    minefield.guess(x, y, false);
                }
                //will print this after it keeps checking when game is over
                System.out.println(minefield);
                System.out.println("Game is over.");

            }
        }

    }


