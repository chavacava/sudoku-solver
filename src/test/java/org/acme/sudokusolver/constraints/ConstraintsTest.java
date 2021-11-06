package org.acme.sudokusolver.constraints;

import org.acme.sudokusolver.domain.Block;
import org.acme.sudokusolver.domain.Cell;
import org.acme.sudokusolver.domain.Puzzle;
import org.acme.sudokusolver.domain.Value;
import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

public class ConstraintsTest {
    private ConstraintVerifier<SudokuConstraintsProvider,Puzzle> constraintVerifier = ConstraintVerifier.build(new SudokuConstraintsProvider(), Puzzle.class, Cell.class);

    @Test
    public void blockCells(){
        Block block = new Block(0, 0);  
        Value value1= new Value(1);              
        Value value2= new Value(1);              
        Cell c1 = new Cell(0, block, 0, 0);
        Cell c2 = new Cell(1, block, 1, 0);
        c1.setValue(value1);    
        c2.setValue(value2);
        constraintVerifier.verifyThat(SudokuConstraintsProvider::blockCells).given(c1,c2).penalizesBy(1);        
    }

}

