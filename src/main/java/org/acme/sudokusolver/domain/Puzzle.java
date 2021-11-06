package org.acme.sudokusolver.domain;

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
    @ValueRangeProvider(id = "valuesRange")
    private List<Value> values;
    @PlanningEntityCollectionProperty
    private List<Cell> cells;

    @PlanningScore
    private HardSoftScore score;

    public Puzzle() {
    }

    public Puzzle(List<Value> values, List<Cell> cells) {
        this.values = values;
        this.cells = cells;
    }
    
    public HardSoftScore getScore() {
        return score;
    }

    public List<Cell> getCells() {
        return cells;
    }    
}
