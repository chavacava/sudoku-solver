package org.acme.sudokusolver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.entity.PlanningPin;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PlanningEntity
public class Cell {
    private static final Logger LOGGER = LoggerFactory.getLogger(Cell.class);
    
    @PlanningId
    private int id;
    private int col;
    private int row;
    private Block block;

    @PlanningVariable(valueRangeProviderRefs = "valuesRange")
    private Value value;
    
    private boolean pinned;

    public Cell(){}

    public Cell(int id, Block block, int col, int row) {
        this.id = id;
        this.block = block;
        this.col = col;
        this.row = row;
        this.value = null; // not necessary
    }

    public Cell(int id, Block block, int col, int row, Value value) {
        this.id = id;
        this.block = block;
        this.col = col;
        this.row = row;
        this.value = value;
        this.pinned = true;
    }

    @PlanningPin
    public boolean isPinned() {
        return pinned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
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

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        LOGGER.info(">>>>>>>> setting "+value+" to "+id);
        this.value = value;
    }

    @Override
    public String toString() {
        return value!=null?value.toString():"" ;
    }

}
