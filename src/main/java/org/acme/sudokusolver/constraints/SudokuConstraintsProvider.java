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
                conflictingCellsInBlock(constraintFactory),                
                conflictingColCells(constraintFactory),
                conflictingRowCells(constraintFactory)
        };
    }

    Constraint conflictingCellsInBlock(ConstraintFactory constraintFactory) {
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
                        .filter((c1,c2) -> c1.getValue().getValue() == c2.getValue().getValue())
                        .penalize("Conflicting value in col", HardSoftScore.ONE_HARD);
    }

    Constraint conflictingRowCells(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUniquePair(Cell.class,
                        Joiners.equal(Cell::getRow))
                        .filter((c1,c2) -> c1.getValue().getValue() == c2.getValue().getValue())
                        .penalize("Conflicting value in row", HardSoftScore.ONE_HARD);
    }
}
