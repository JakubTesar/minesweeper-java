package cz.educanet.minesweeper.logic;

public class Minesweeper {

    private final Field playground;
    private int rowsCount;
    private int columnsCount;
    private int flag = 0;


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
        return playground.getCellType(x,y);
    }


    public void toggleFieldState(int x, int y) { // Pravý / Vlaječka
        playground.setCellType(x,y,2);
        setFlag(+1);
    }

    public void reveal(int x, int y) { // Levý / Odhalení
        playground.setCellType(x,y,1);
    }

    public int getAdjacentBombCount(int x, int y) { // Čísla okolo bomb
        return 1;
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
     * Returns the amount of bombs on the field
     *
     * @return bomb count
     */
    public int getBombCount() {
        return 0;
    }

    /**
     * total bombs - number of flags
     *
     * @return remaining bomb count
     */
    public int getRemainingBombCount() {
        return 0;
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
        return false;
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
