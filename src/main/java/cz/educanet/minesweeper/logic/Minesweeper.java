package cz.educanet.minesweeper.logic;

public class Minesweeper {

    private Field playground;
    private int rowsCount;
    private int columnsCount;
    private int flag = 0;
    private boolean isClicked2;


    public Minesweeper(int rows, int columns) {
        this.rowsCount = rows;
        this.columnsCount = columns;

        this.playground = Field.generetField(rows, columns);
    }

    /**
     * 0 - Hidden
     * 1 - Visible
     * 2 - Flag
     * 3 - Question mark
     *
     * @param x X
     * @param y Y
     * @return field type
     */
    public int getField(int x, int y) {
        return playground.getCellType(x, y);
    }

    public void toggleFieldState(int x, int y) { // Pravý / Vlaječka
        playground.setCellType(x, y, 2);

        setFlag(+1);
    }

    public void reveal(int x, int y) { // Levý / Odhalení
        playground.setCellType(x, y, 1);
        if (playground.isClicked(x, y)) {
            isClicked2 = true;
        }
    }

    public int getAdjacentBombCount(int x, int y) { // Čísla okolo bomb
        int bombs = 0;

        if (playground.getCellPosition(x + 1, y + 1).isBomb()) { // Pravý horní
            bombs++;
        }
        if (playground.getCellPosition(x + 1, y).isBomb()) { // Pravý střed
            bombs++;
        }
        if (playground.getCellPosition(x + 1, y -1).isBomb()) { // Pravý dolní
            bombs++;
        }
        if (playground.getCellPosition(x, y +1).isBomb()) { // Horní střed
            bombs++;
        }
        if (playground.getCellPosition(x, y-1).isBomb()) { // Dolní střed
            bombs++;
        }
        if (playground.getCellPosition(x - 1, y+1).isBomb()) { // Levý horní
            bombs++;
        }
        if (playground.getCellPosition(x - 1, y).isBomb()) { // Levý střed
            bombs++;
        }
        if (playground.getCellPosition(x - 1, y-1).isBomb()) { // Levý Dolní
            bombs++;
        }

        return bombs;
    }

    /**
     * Checks if there is a bomb on the current position
     *
     * @param x X
     * @param y Y
     * @return true if bomb on position
     */
    public boolean isBombOnPosition(int x, int y) {
        return false;
    }

    /**
     * total bombs - number of flags
     *
     * @return remaining bomb count
     */
    public int getRemainingBombCount() {
        return playground.getBombCount() - getFlag();
    }

    /**
     * returns true if every flag is on a bomb, else false
     *
     * @return if player won
     */
    public boolean didWin() {
        return false;
    }

    /**
     * returns true if player revealed a bomb, else false
     *
     * @return if player lost
     */
    public boolean didLoose() {
        return isClicked2;
    }

    public int getRows() {
        return rowsCount;
    }

    public int getColumns() {
        return columnsCount;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
