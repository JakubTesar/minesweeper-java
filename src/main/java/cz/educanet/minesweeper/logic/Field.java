package cz.educanet.minesweeper.logic;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private int rows;
    private int columns;
    private Cell[][] playground;

    public static Field generetField(int rows, int columns) {
        Field myField = new Field();
        myField.rows = rows;
        myField.columns = columns;
        myField.playground = new Cell[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell myCell = new Cell();
                myCell.setType(0);
                myCell.setBomb(false);

                myField.playground[j][i] = myCell;
            }
        }
        return myField.generateBombs(myField);
    }
    /**
     * Returns the amount of bombs on the field
     *
     * @return bomb count
     */
    public int getBombCount() {
        return 30;
    }
    public Field generateBombs(Field myField) {

        Random rand = new Random();
        int counter = 0;
        while (counter != getBombCount()) {
            int x = rand.nextInt(columns);
            int y = rand.nextInt(rows);
            while (playground[x][y].isBomb()) {
                x = rand.nextInt(columns);
                y = rand.nextInt(rows);
            }
            playground[x][y].setBomb(true);
            counter++;
        }
        return myField;
    }
    public boolean isClicked(int x, int y){
        return playground[x][y].isBomb();
    }

    public void setCellType(int x, int y, int type) {
        Cell a = playground[x][y];
        a.setType(type);
        playground[x][y] = a;
    }

    public int getCellType(int x, int y) {
        Cell a = playground[x][y];
        return a.getType();
    }
}
