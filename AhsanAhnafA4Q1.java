
// EACH MINI STEP is one function, no more messy code! 
import java.util.Scanner;

public class AhsanAhnafA4Q1 {
    static int sizeX;
    static int sizeY;
    static String[][] board;

    public static void main(String args[]) {
        sizeSetter();
        boardDrawer();
        boardPrinter(board);
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
        input.close();
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

}