package org.acme.sudokusolver.domain;

public class Block {
    private int col;
    private int row;

    public Block(int col, int row) {
        this.col = col;
        this.row = row;
    }
    
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    
}
