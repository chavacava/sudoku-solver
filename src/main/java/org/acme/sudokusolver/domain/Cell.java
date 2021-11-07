package org.acme.sudokusolver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.entity.PlanningPin;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Cell {
   
    @PlanningId
    private int id;
    private int col;
    private int row;
    private int block;

    @PlanningVariable(valueRangeProviderRefs = "values")
    private Value value;
    
    private boolean pinned;

    public Cell(){}

    public Cell(int id, int block, int col, int row) {
        this.id = id;
        this.block = block;
        this.col = col;
        this.row = row;
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void pinWith(Value value) {
            this.value = value;
            this.pinned = true;
    }

    @Override
    public String toString() {
        return value!=null?value.toString():"_" ;
    }
    public String toDebugString() {
        return id+","+block+","+col+","+row ;
    }
}
