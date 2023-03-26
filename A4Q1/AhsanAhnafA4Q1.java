
// EACH MINI STEP is one function, no more messy code! 
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AhsanAhnafA4Q1 {
    static boolean thirdWord;
    static int sizeX;
    static int sizeY;
    static String[][] board;
    static int wordCount = 0;
    static final int[] LETTER_SCORES = new int[] {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };
    static HashMap<Character, Integer> HASH_SCORES = new HashMap<Character, Integer>(); // hash map of the scores
    static ArrayList<Integer[][]> postion_of_words = new ArrayList<Integer[][]>(); // [Y][X]

    public static void main(String args[]) {
        hashMapSetter();
        sizeSetter();
        boardDrawer();
        boardPrinter(board);
        userStringInput();
    }

    // I was too lazy to set the hashmap manually, so I just made a forloop that
    // initializes the ASCII for letter a and loops setting the key as letter and
    // value as the score.
    public static void hashMapSetter() {
        int letter = 97;
        for (int i = 0; i < LETTER_SCORES.length; i++) {
            char chars = (char) letter;
            HASH_SCORES.put(chars, LETTER_SCORES[i]);
            letter++;
        }
    }

    public static void boardPrinter(String[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static void sizeSetter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input horizontal size: ");
        int x = input.nextInt();
        System.out.println("Please input vertical size");
        int y = input.nextInt();
        if (!(x % 2 == 0 || y % 2 == 0)) {
            sizeX = x;
            sizeY = y;
            board = new String[sizeY][sizeX];
        } else {
            System.out.println("Both X and Y need to be odd!");
        }
    }

    public static void boardDrawer() {
        // lets make the board frame
        for (int i = 0; i < sizeY; i++) { // y size
            for (int j = 0; j < sizeX; j++) {
                board[i][j] = ".";
            }
        }
        // make the borders
        for (int i = 0; i < sizeY; i++) { // y size
            board[i][0] = "+";
            board[i][sizeX - 1] = "+";
            for (int j = 0; j < sizeX; j++) {
                board[0][j] = "+";
                board[sizeY - 1][j] = "+";
            }
        }
        // set the middle as !, one layer less til i reaches size/2
        int j = sizeX / 2; // 5 = 2
        if (sizeX >= sizeY) {
            for (int i = 0; i <= sizeY / 2; i++) {
                if (i >= sizeY / 2) {
                    board[(sizeY / 2 - i)][j + i] = "*";
                    board[(sizeY / 2 - i)][j - i] = "*";
                    board[(sizeY / 2 + i)][j - i] = "*";
                    board[(sizeY / 2 + i)][j + i] = "*";
                } else {
                    board[(sizeY / 2 - i)][j + i] = "!";
                    board[(sizeY / 2 - i)][j - i] = "!";
                    board[(sizeY / 2 + i)][j - i] = "!";
                    board[(sizeY / 2 + i)][j + i] = "!";
                }
            }
        } else {
            for (int i = 0; i <= sizeX / 2; i++) {
                if (i >= sizeX / 2) {
                    board[(sizeY / 2 - i)][j + i] = "*";
                    board[(sizeY / 2 - i)][j - i] = "*";
                    board[(sizeY / 2 + i)][j - i] = "*";
                    board[(sizeY / 2 + i)][j + i] = "*";
                } else {
                    board[(sizeY / 2 - i)][j + i] = "!";
                    board[(sizeY / 2 - i)][j - i] = "!";
                    board[(sizeY / 2 + i)][j - i] = "!";
                    board[(sizeY / 2 + i)][j + i] = "!";
                }
            }
        }
    }

    public static int scoreCalc(char letter) {
        return HASH_SCORES.get(letter);
    }

    public static String userString() {
        Scanner swag = new Scanner(System.in);
        String x = swag.nextLine();
        x = x.stripTrailing();
        x = x.toLowerCase();
        return x;
    }

    public static void userStringInput() {
        Scanner swag = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("Input word or type 'exit' to end the game:");
            String word = swag.nextLine();
            word = word.stripTrailing();
            word = word.toLowerCase();

            if (word.equals("exit")) {
                keepPlaying = false;
            } else if (word.length() <= sizeX) {
                if (wordCount == 0) {
                    placeFirstWordOnBoard(word);
                    wordCount++;
                    boardPrinter(board);
                } else {
                    // Place subsequent words and update the score
                    placeSubsequentWords(word);
                    boardPrinter(board);
                }
            }
        }
    }

    public static void placeFirstWordOnBoard(String word) {
        int length = word.length();
        int counter = 0;
        int score = 0;
        boolean doubleWord = false;

        for (int i = ((sizeX) / 2) - (length / 2); i < sizeX && counter < length; i++) {
            int j = sizeY / 2;
            String s = String.valueOf(word.charAt(counter));

            if (String.valueOf(board[j][i]).equals(".")) {
                score += scoreCalc(word.charAt(counter));
            } else if (String.valueOf(board[j][i]).equals("!")) {
                score += scoreCalc(word.charAt(counter)) * 2;
            } else if (String.valueOf(board[j][i]).equals("+")) {
                doubleWord = true;
            } else if (String.valueOf(board[j][i]).equals("*")) {
                score += scoreCalc(word.charAt(counter)) * 2;
                doubleWord = true;
            }
            board[j][i] = String.valueOf(word.charAt(counter));
            counter++;
        }
        if (doubleWord) {
            score = score * 2;
        }
        System.out.println("Score for this word: " + score);
    }

    public static void placeSubsequentWords(String word) {
        ArrayList<ValidPlacement> valid_placements = new ArrayList<>();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (!board[i][j].equals(".")) {
                    if (j + word.length() <= sizeX && (j == 0 || board[i][j - 1].equals("."))
                            && (j + word.length() == sizeX || board[i][j + word.length()].equals("."))) {
                        int score = horizontalPlacement(word, board, i, j);
                        valid_placements.add(new ValidPlacement(i, j, true, score));
                    }
                    if (i + word.length() <= sizeY && (i == 0 || board[i - 1][j].equals("."))
                            && (i + word.length() == sizeY || board[i + word.length()][j].equals("."))) {
                        int score = verticalPlacement(word, board, i, j);
                        valid_placements.add(new ValidPlacement(i, j, false, score));
                    }
                }
            }
        }

        if (valid_placements.size() == 0) {
            System.out.println("Invalid word choice.");
            userStringInput();
        } else {
            ValidPlacement highest_scoring_placement = valid_placements.get(0);
            for (ValidPlacement placement : valid_placements) {
                if (placement.getScore() > highest_scoring_placement.getScore()) {
                    highest_scoring_placement = placement;
                }
            }
            placeNewWord(word, board, highest_scoring_placement.getRow(), highest_scoring_placement.getCol(),
                    highest_scoring_placement.isHorizontal());
        }
    }

    public static int horizontalPlacement(String word, String[][] board, int row, int col) {
        int score = 0;
        int length = word.length();
        int counter = 0;
        boolean doubleWord = false;
        for (int i = col; i < sizeX && counter < length; i++) {
            int j = row;
            String s = String.valueOf(word.charAt(counter));
            if (String.valueOf(board[j][i]).equals(".")) {
                score += scoreCalc(word.charAt(counter));
            } else if (String.valueOf(board[j][i]).equals("!")) {
                score += scoreCalc(word.charAt(counter)) * 2;
            } else if (String.valueOf(board[j][i]).equals("+")) {
                doubleWord = true;
            } else if (String.valueOf(board[j][i]).equals("*")) {
                score += scoreCalc(word.charAt(counter)) * 2;
                doubleWord = true;
            }
            counter++;
        }
        if (doubleWord) {
            score = score * 2;
        }
        return score;
    }

    public static int verticalPlacement(String word, String[][] board, int row, int col) {
        int score = 0;
        int length = word.length();
        int counter = 0;
        boolean doubleWord = false;
        for (int j = row; j < sizeY && counter < length; j++) {
            int i = col;
            String s = String.valueOf(word.charAt(counter));

            if (String.valueOf(board[j][i]).equals(".")) {
                score += scoreCalc(word.charAt(counter));
            } else if (String.valueOf(board[j][i]).equals("!")) {
                score += scoreCalc(word.charAt(counter)) * 2;
            } else if (String.valueOf(board[j][i]).equals("+")) {
                doubleWord = true;
            } else if (String.valueOf(board[j][i]).equals("*")) {
                score += scoreCalc(word.charAt(counter)) * 2;
                doubleWord = true;
            }
            counter++;
        }
        if (doubleWord) {
            score = score * 2;
        }
        return score;
    }

    public static void placeNewWord(String word, String[][] board, int row, int col, boolean isHorizontal) {
        int length = word.length();
        int counter = 0;
        int score = 0;
        boolean doubleLetter = false;
        boolean doubleWord = false;

        if (isHorizontal) {
            for (int i = col; i < col + length; i++) {
                String s = String.valueOf(word.charAt(counter));

                if (String.valueOf(board[row][i]).equals(".")) {
                    score += scoreCalc(word.charAt(counter));
                } else if (String.valueOf(board[row][i]).equals("*")) {
                    score += scoreCalc(word.charAt(counter)) * 2;
                    doubleLetter = true;
                } else if (String.valueOf(board[row][i]).equals("!")) {
                    score += scoreCalc(word.charAt(counter));
                    doubleWord = true;
                } else {
                    score += scoreCalc(word.charAt(counter));
                }
                board[row][i] = String.valueOf(word.charAt(counter));
                counter++;
            }
        } else {
            for (int i = row; i < row + length; i++) {
                String s = String.valueOf(word.charAt(counter));

                if (String.valueOf(board[i][col]).equals(".")) {
                    score += scoreCalc(word.charAt(counter));
                } else if (String.valueOf(board[i][col]).equals("*")) {
                    score += scoreCalc(word.charAt(counter)) * 2;
                    doubleLetter = true;
                } else if (String.valueOf(board[i][col]).equals("!")) {
                    score += scoreCalc(word.charAt(counter));
                    doubleWord = true;
                } else {
                    score += scoreCalc(word.charAt(counter));
                }
                board[i][col] = String.valueOf(word.charAt(counter));
                counter++;
            }
        }

        if (doubleLetter) {
            score = score * 2;
        }

        if (doubleWord) {
            score = score * 2;
        }

        System.out.println("Score for this word: " + score);
    }
}