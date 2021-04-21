package cz.educanet.minesweeper.logic;

public class Minesweeper {

    private Field playground;
    private int rowsCount;
    private int columnsCount;
    private boolean isClicked2; // If bomb is clicked
    private int clearCells; // Visible Cells, but not yet

    public Minesweeper(int rows, int columns) {
        this.rowsCount = rows;
        this.columnsCount = columns;

        this.playground = Field.generetField(rows, columns);
        clearCells = (rows * columns) - playground.getBombCount();
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
        if (getField(x, y) == 0) { // Hidden -> Flag
            playground.setCellType(x, y, 2);
        } else if (getField(x, y) == 2) { // Flag -> Question mark
            playground.setCellType(x, y, 3);
        } else if (getField(x, y) == 3) { // Question mark -> Hidden
            playground.setCellType(x, y, 0);
        }
    }

    public Field reveal2(int x, int y, Field input) {
        Field playground = input;
        System.out.println("Počet zbývajících polích: " + clearCells);
        playground.getCellPosition(x, y).setType(1);

        if (getAdjacentBombCount(x, y) == 0) {
            boolean Tl = (x != 0) && (y != 0);
            boolean Tr = (x != columnsCount - 1) && (y != 0);
            boolean Bl = (x != 0) && (y != rowsCount - 1);
            boolean Br = (x != columnsCount - 1) && (y != rowsCount - 1);

            if (Br && !isBombOnPosition(x + 1, y + 1) && getField(x + 1, y + 1) == 0) { // Bot right
                playground.getCellPosition(x + 1, y + 1).setType(1);
                reveal2(x + 1, y + 1, input);
                clearCells--;
            }
            if ((Tr || Br) && !isBombOnPosition(x + 1, y) && getField(x + 1, y) == 0) { // Right
                playground.getCellPosition(x + 1, y).setType(1);
                reveal2(x + 1, y, input);
                clearCells--;
            }
            if (Tr && !isBombOnPosition(x + 1, y - 1) && getField(x + 1, y - 1) == 0) { // Top right
                playground.getCellPosition(x + 1, y - 1).setType(1);
                reveal2(x + 1, y - 1, input);
                clearCells--;

            }
            if ((Tr || Tl) && !isBombOnPosition(x, y - 1) && getField(x, y - 1) == 0) { // Top
                playground.getCellPosition(x, y - 1).setType(1);
                reveal2(x, y - 1, input);
                clearCells--;

            }
            if (Tl && !isBombOnPosition(x - 1, y - 1) && getField(x - 1, y - 1) == 0) { // Top left
                playground.getCellPosition(x - 1, y - 1).setType(1);
                reveal2(x - 1, y - 1, input);
                clearCells--;

            }
            if ((Bl || Tl) && !isBombOnPosition(x - 1, y) && getField(x - 1, y) == 0) { // Left
                playground.getCellPosition(x - 1, y).setType(1);
                reveal2(x - 1, y, input);
                clearCells--;

            }
            if (Bl && !isBombOnPosition(x - 1, y + 1) && getField(x - 1, y + 1) == 0) { // Bot Left
                playground.getCellPosition(x - 1, y + 1).setType(1);
                reveal2(x - 1, y + 1, input);
                clearCells--;

            }
            if ((Br || Bl) && !isBombOnPosition(x, y + 1) && getField(x, y + 1) == 0) { // Bot
                playground.getCellPosition(x, y + 1).setType(1);
                reveal2(x, y + 1, input);
                clearCells--;

            }
        }
        return playground;
    }

    public void reveal(int x, int y) { // Levý / Odhalení
        if (playground.isClicked(x, y)) {
            isClicked2 = true;
        } else {
            playground = reveal2(x, y, playground);
            clearCells--;
        }
    }

    public int getAdjacentBombCount(int x, int y) { // Čísla okolo bomb
        int bombs = 0;

        boolean Tl = (x != 0) && (y != 0);
        boolean Tr = (x != columnsCount - 1) && (y != 0);
        boolean Bl = (x != 0) && (y != rowsCount - 1);
        boolean Br = (x != columnsCount - 1) && (y != rowsCount - 1);

        if (Tr && isBombOnPosition(x + 1, y - 1)) { // Pravý horní
            bombs++;
        }
        if ((Tr || Br) && isBombOnPosition(x + 1, y)) { // Pravý střed
            bombs++;
        }
        if (Br && isBombOnPosition(x + 1, y + 1)) { // Pravý dolní
            bombs++;
        }
        if ((Tr || Tl) && isBombOnPosition(x, y - 1)) { // Horní střed
            bombs++;
        }
        if ((Br || Bl) && isBombOnPosition(x, y + 1)) { // Dolní střed
            bombs++;
        }
        if (Tl && isBombOnPosition(x - 1, y - 1)) { // Levý horní
            bombs++;
        }
        if ((Bl || Tl) && isBombOnPosition(x - 1, y)) { // Levý střed
            bombs++;
        }
        if (Bl && isBombOnPosition(x - 1, y + 1)) { // Levý Dolní
            bombs++;
        }
        return bombs;
    }


    public boolean isBombOnPosition(int x, int y) {
        return playground.getCellPosition(x, y).isBomb();
    }

    public boolean didWin() {
        return clearCells == 0;
    }

    public boolean didLoose() {
        return isClicked2;
    }

    public int getRows() {
        return rowsCount;
    }

    public int getColumns() {
        return columnsCount;
    }

}
