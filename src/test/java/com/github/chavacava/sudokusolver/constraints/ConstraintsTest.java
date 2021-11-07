package com.github.chavacava.sudokusolver.constraints;

import com.github.chavacava.sudokusolver.domain.Cell;
import com.github.chavacava.sudokusolver.domain.Puzzle;
import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

public class ConstraintsTest {
    private ConstraintVerifier<SudokuConstraintsProvider,Puzzle> constraintVerifier = ConstraintVerifier.build(new SudokuConstraintsProvider(), Puzzle.class, Cell.class);

    @Test
    public void conflictingCellsInBlock(){
        int block = 0;
        int theValue = 1;  
                     
        Cell c1 = new Cell(1,block, 0, 0);
        Cell c2 = new Cell(2, block, 1, 0);
        c1.setValue(theValue);    
        c2.setValue(theValue);
        constraintVerifier.verifyThat(SudokuConstraintsProvider::conflictingCellsInBlock).given(c1,c2).penalizesBy(1);        
    }

    @Test
    public void conflictingColCells(){
        int theCol = 0;
        int theValue = 1;  
        Cell c1 = new Cell(1,10, theCol, 1);
        Cell c2 = new Cell(2, 11, theCol, 2);
        c1.setValue(theValue);    
        c2.setValue(theValue);
        constraintVerifier.verifyThat(SudokuConstraintsProvider::conflictingColCells).given(c1,c2).penalizesBy(1);        
    }

    @Test
    public void conflictingRowCells(){
        int theRow = 0;
        int theValue = 1;   
        Cell c1 = new Cell(1,10, 1, theRow);
        Cell c2 = new Cell(2, 11, 2, theRow);
        c1.setValue(theValue);    
        c2.setValue(theValue);
        constraintVerifier.verifyThat(SudokuConstraintsProvider::conflictingRowCells).given(c1,c2).penalizesBy(1);        
    }
    
}

