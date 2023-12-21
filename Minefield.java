import java.util.Random;

public class Minefield {
    /**
     * Global Section
     */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    // Class Variables
    private Cell[][] minefield;
    private int rows;
    private int columns;
    private int flags;
    private int mines;
    public boolean gameOver;

    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */

    /**
     * Minefield
     * <p>
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     *
     * @param rows    Number of rows.
     * @param columns Number of columns.
     * @param flags   Number of flags, should be equal to mines
     */
    public Minefield (int rows, int columns, int flags) {
        this.minefield = new Cell[rows][columns];
        this.columns = columns;
        this.rows = rows;
        this.mines = flags;
        this.flags = flags;
        this.gameOver = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                minefield[i][j] = new Cell(false, "-");
            }
        }
    }

    //getter for flags (my own method)
    public int getFlags() {
        return flags;
    }

    /**
     * evaluateField
     *
     * @function: Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     */
    public void evaluateField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (minefield[i][j].getStatus().equals("-")) { //does this if the status is at "-" for chosen coordinate
                    minefield[i][j].setStatus("0");
                }
                if (minefield[i][j].getStatus() == "M") { //operates evaluation when it has found a mine
                    try {
                        if (minefield[i + 1][j].getStatus() != "M" && minefield[i + 1][j].getStatus() != "-") {
                            minefield[i + 1][j].setStatus((Integer.parseInt(minefield[i + 1][j].getStatus()) + 1) + "");
                        }
                        if (minefield[i + 1][j].getStatus() == "-") {
                            minefield[i + 1][j].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i - 1][j].getStatus() != "M" && minefield[i - 1][j].getStatus() != "-") {
                            minefield[i - 1][j].setStatus((Integer.parseInt(minefield[i - 1][j].getStatus()) + 1) + "");
                        }
                        if (minefield[i - 1][j].getStatus() == "-") {
                            minefield[i - 1][j].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i][j + 1].getStatus() != "M" && minefield[i][j + 1].getStatus() != "-") {
                            minefield[i][j + 1].setStatus((Integer.parseInt(minefield[i][j + 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i][j + 1].getStatus() == "-") {
                            minefield[i][j + 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i][j - 1].getStatus() != "M" && minefield[i][j - 1].getStatus() != "-") {
                            minefield[i][j - 1].setStatus((Integer.parseInt(minefield[i][j - 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i][j - 1].getStatus() == "-") {
                            minefield[i][j - 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i - 1][j - 1].getStatus() != "M" && minefield[i - 1][j - 1].getStatus() != "-") {
                            minefield[i - 1][j - 1].setStatus((Integer.parseInt(minefield[i - 1][j - 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i - 1][j - 1].getStatus() == "-") {
                            minefield[i - 1][j - 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i + 1][j + 1].getStatus() != "M" && minefield[i + 1][j + 1].getStatus() != "-") {
                            minefield[i + 1][j + 1].setStatus((Integer.parseInt(minefield[i + 1][j + 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i + 1][j + 1].getStatus() == "-") {
                            minefield[i + 1][j + 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i - 1][j + 1].getStatus() != "M" && minefield[i - 1][j + 1].getStatus() != "-") {
                            minefield[i - 1][j + 1].setStatus((Integer.parseInt(minefield[i - 1][j + 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i - 1][j + 1].getStatus() == "-") {
                            minefield[i - 1][j + 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (minefield[i + 1][j - 1].getStatus() != "M" && minefield[i + 1][j - 1].getStatus() != "-") {
                            minefield[i + 1][j - 1].setStatus((Integer.parseInt(minefield[i + 1][j - 1].getStatus()) + 1) + "");
                        }
                        if (minefield[i + 1][j - 1].getStatus() == "-") {
                            minefield[i + 1][j - 1].setStatus("1");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
        }
    }

    /**
     * createMines
     * <p>
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     *
     * @param x Start x, avoid placing on this square.
     * @param y Start y, avoid placing on this square.
     * @param m Number of mines to place.
     */
    public void createMines(int x, int y, int m) {
        Random rand = new Random();
        int randX, randY;
        while (m > 0) {
            randY = rand.nextInt(columns);
            randX = rand.nextInt(rows);
            if (randY != y || randX != x) {
                minefield[randX][randY].setStatus("M");
                m--;
            }
        }
    } //randomly creates mine though out field

    /**
     * guess
     * <p>
     * Check if the guessed cell is inbounds (if not done in the Main class).
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     *
     * @param x    The x value the user entered.
     * @param y    The y value the user entered.
     * @param flag A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        if (x<0 || x>rows || y<0 || y>columns) {
            return false;
        }
        if (flag == true) { //run this only when the guess sets flag to true
            if (flags < 0) {
                System.out.println("No flags remaining"); //
                return false;
            }
            minefield[x][y].setRevealed(true);
            minefield[x][y].setStatus("F");
            flags--;
            return false;
        } else { //Do this if flag is set to false
            minefield[x][y].setRevealed(true);
            if (minefield[x][y].getStatus() == "M") {
                gameOver = true;
                System.out.println("You have hit a mine.");
                return true;
            }
            revealZeroes(x, y); //keep revealing 0's
            return false;
        }
    }

    /**
     * gameOver
     * <p>
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     *
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        if (gameOver == true) { //It can only be gameOver once during a run, so this is just a check or guess calls for game over to be true
            return gameOver;
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (minefield[i][j].getRevealed() == false) { //only is game over when everything is revealed
                        return false;
                    }
                }
            }
            gameOver = true;
            System.out.println("You win!");
            return gameOver;
        }
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     * <p>
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x The x value the user entered.
     * @param y The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        Stack1Gen<Cell> stack = new Stack1Gen<Cell>();
        stack.push(minefield[x][y]);
        while (stack.top() != null) { //while stack still has something in it, it will keep revealing 0's in any direction, using recursion
            try {
                if (!minefield[x + 1][y].getRevealed() && minefield[x + 1][y].getStatus().equals("0")) {
                    minefield[x + 1][y].setRevealed(true);
                    stack.push(minefield[x + 1][y]);
                    revealZeroes(x + 1, y);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (!minefield[x - 1][y].getRevealed() && minefield[x - 1][y].getStatus().equals("0")) {
                    minefield[x - 1][y].setRevealed(true);
                    stack.push(minefield[x - 1][y]);
                    revealZeroes(x - 1, y);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (!minefield[x][y + 1].getRevealed() && minefield[x][y + 1].getStatus().equals("0")) {
                    minefield[x][y + 1].setRevealed(true);
                    stack.push(minefield[x][y + 1]);
                    revealZeroes(x, y + 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (!minefield[x][y - 1].getRevealed() && minefield[x][y - 1].getStatus().equals("0")) {
                    minefield[x][y - 1].setRevealed(true);
                    stack.push(minefield[x][y - 1]);
                    revealZeroes(x, y - 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            stack.pop().setRevealed(true);
        }
    }

    /**
     * revealStartingArea
     * <p>
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * <p>
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x The x value the user entered.
     * @param y The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
        Q1Gen<Cell> queue = new Q1Gen<Cell>();
        Cell current;
        queue.add(minefield[x][y]);
        while (queue.length() != 0) { //while queue still has an element within, keep revealing
            current = queue.remove();
            current.setRevealed(true);
            try {
                if (!minefield[x - 1][y].getRevealed()) {
                    queue.add(minefield[x - 1][y]);
                }
            } catch (Exception e) {}
            try {
                if (!minefield[x + 1][y].getRevealed()) {
                    queue.add(minefield[x + 1][y]);
                }
            } catch (Exception e) {}
            try {
                if (!minefield[x][y - 1].getRevealed()) {
                    queue.add(minefield[x][y - 1]);
                }
            } catch (Exception e) {}
            try {
                if (!minefield[x][y + 1].getRevealed()) {
                    queue.add(minefield[x][y + 1]);
                }
            } catch (Exception e) {}
            if (current.getStatus().equals("M")) {
                break;
            }
        }
    }

    /**
     * For both printing methods utilize the ANSI colour codes provided!
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected.
     */
    public void debug() { //reveals right away
        StringBuilder debugField = new StringBuilder();
        debugField.append(" ");
        for (int i = 0; i < rows; i++) {
            debugField.append(" ");
            debugField.append(i);
        }
        debugField.append('\n');
        for (int i = 0; i < rows; i++) {
            debugField.append(i);
            debugField.append(" ");
            for (int j = 0; j < columns; j++) { //assigning colors to each letter/string
                if (minefield[i][j].getStatus().equals("F")) {
                    debugField.append(minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND + " ");
                } if (minefield[i][j].getStatus().equals("M")) {
                    debugField.append(ANSI_RED + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND+ " ");
                } if (minefield[i][j].getStatus().equals("0")) {
                    debugField.append(ANSI_YELLOW_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND + " ");
                } if (minefield[i][j].getStatus().equals("1")) {
                    debugField.append(ANSI_GREEN + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND + " ");
                } if (minefield[i][j].getStatus().equals("2")) {
                    debugField.append(ANSI_BLUE + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND + " ");
                } if(minefield[i][j].getStatus().equals("3")){
                    debugField.append(ANSI_RED_BRIGHT+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                } if(minefield[i][j].getStatus().equals("4")){
                    debugField.append(ANSI_BLUE+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                } if(minefield[i][j].getStatus().equals("5")){
                    debugField.append(ANSI_CYAN+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                } if(minefield[i][j].getStatus().equals("6")){
                    debugField.append(ANSI_YELLOW+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                } if(minefield[i][j].getStatus().equals("7")){
                    debugField.append(ANSI_PURPLE+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                } if(minefield[i][j].getStatus().equals("8")){
                    debugField.append(ANSI_RED+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                }
            }
            debugField.append("\n");
        }
        System.out.println(debugField);
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() { //very similar to debug
        StringBuilder field = new StringBuilder();
        field.append(" ");
        for (int i = 0; i < rows; i++) {
            field.append(" ");
            field.append(i);
        }
        field.append('\n');
        for (int i = 0; i < rows; i++) {
            field.append(i);
            field.append(" ");
            for (int j = 0; j < columns; j++) {
                if (!minefield[i][j].getRevealed()) {
                    if (j >= 10) {                      //makes minefield more appealing to view
                        field.append("-  ");
                    } else{
                        field.append("- ");
                    }
                } else {
                    if(minefield[i][j].getStatus().equals("M")){
                        field.append(ANSI_RED+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("F")){
                        field.append(minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("0")){
                        field.append(ANSI_YELLOW_BRIGHT+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("1")){
                        field.append(ANSI_BLUE_BRIGHT+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("2")){
                        field.append(ANSI_GREEN+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("3")){
                        field.append(ANSI_RED_BRIGHT+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("4")){
                        field.append(ANSI_BLUE+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("5")){
                        field.append(ANSI_PURPLE+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("6")){
                        field.append(ANSI_CYAN+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("7")){
                        field.append(ANSI_YELLOW+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    } if(minefield[i][j].getStatus().equals("8")){
                        field.append(ANSI_PURPLE_BACKGROUND+minefield[i][j].getStatus()+ANSI_GREY_BACKGROUND+" ");
                    }
                }
            }
            field.append("\n");
        }
        return field.toString();

    }
}