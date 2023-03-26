public class ValidPlacement {
    int row;
    int col;
    boolean isHorizontal;
    int score;

    public ValidPlacement(int row, int col, boolean isHorizontal, int score) {
        this.row = row;
        this.col = col;
        this.isHorizontal = isHorizontal;
        this.score = score;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int getScore() {
        return score;
    }
}