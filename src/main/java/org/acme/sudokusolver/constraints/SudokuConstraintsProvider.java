package org.acme.sudokusolver.constraints;

import org.acme.sudokusolver.domain.Cell;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class SudokuConstraintsProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                blockCells(constraintFactory),                
                emptyCells(constraintFactory),                
                conflictingColCells(constraintFactory),
                conflictingRowCells(constraintFactory)
        };
    }

    Constraint blockCells(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUniquePair(Cell.class,
                        Joiners.equal(Cell::getBlock)
                ).filter((c1,c2) -> c1.getValue().getValue() == c2.getValue().getValue())
                .penalize("Value is repeated in the block", HardSoftScore.ONE_HARD);
    }

    Constraint conflictingColCells(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUniquePair(Cell.class,
                        Joiners.equal(Cell::getCol))
                        .filter((c1,c2) -> c1.getBlock().getCol() == c2.getBlock().getCol())                        
                        .filter((c1,c2) -> c1.getValue().getValue() == c2.getValue().getValue())
                        .penalize("Conflicting value in col", HardSoftScore.ONE_HARD);
    }

    Constraint conflictingRowCells(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUniquePair(Cell.class,
                        Joiners.equal(Cell::getRow))
                        .filter((c1,c2) -> c1.getBlock().getRow() == c2.getBlock().getRow())                        
                        .filter((c1,c2) -> c1.getValue().getValue() == c2.getValue().getValue())
                        .penalize("Conflicting value in row", HardSoftScore.ONE_HARD);
    }

    Constraint emptyCells(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Cell.class)
                        .filter(c -> c.getValue() == null)                        
                .penalize("Null cell", HardSoftScore.ONE_SOFT);
    }

}
