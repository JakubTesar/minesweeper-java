package cz.educanet.minesweeper.logic;

public class Field {
    private int rows;
    private int columns;
    private Cell[][] playground;

    public static Field generetField(int rows, int columns){
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
        return myField;
    }

    public void setCellType(int x, int y, int type){
        Cell a = playground[x][y];
        a.setType(type);
        playground[x][y] = a;
    }
    
    public int getCellType(int x, int y){
        Cell a = playground[x][y];
        return a.getType();
    }
}
