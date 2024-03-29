package com.github.chavacava.sudokusolver.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Puzzle {
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "values")
    private List<Integer> values;
    @PlanningEntityCollectionProperty
    private Cell[] cells;

    @PlanningScore
    private HardSoftScore score;

    public Puzzle() {
    }

    public Puzzle(List<Integer> values, Cell[] cells) {
        this.values = values;
        this.cells = cells;
    }
    
    public HardSoftScore getScore() {
        return score;
    }

    public Cell[] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "Puzzle [\n\tcells=" + cells + "\n\tscore=" + score + "\n\tvalues=" + values + "]";
    }    
}
