package org.acme.sudokusolver;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.acme.sudokusolver.constraints.SudokuConstraintsProvider;
import org.acme.sudokusolver.domain.Puzzle;
import org.acme.sudokusolver.domain.Value;
import org.acme.sudokusolver.domain.Block;
import org.acme.sudokusolver.domain.Cell;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuSolverApp {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuSolverApp.class);
    private static String lb = System.getProperty("line.separator");

    public static void main(String[] args) {
        SolverFactory<Puzzle> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(Puzzle.class)
                .withEntityClasses(Cell.class)
                .withConstraintProviderClass(SudokuConstraintsProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        ScoreManager<Puzzle, HardSoftScore> scoreManager = ScoreManager.create(solverFactory);
       
        // Load the problem
        Puzzle problem = loadData();

        // Solve the problem
        Solver<Puzzle> solver = solverFactory.buildSolver();
        Puzzle solution = solver.solve(problem);

        if (solution.getScore().isFeasible()) {
            LOGGER.info("SOLUTION FOUND");    
        } else {
            LOGGER.info("UNABLE TO FIND A SOLUTION");    
            LOGGER.info(scoreManager.explainScore(solution).getSummary());
        }

        int j=0;
        for (Cell cell : problem.getCells()) {
            String val = cell.getValue()==null? "_":cell.getValue()+"";
            System.out.print(val+" ");
            if (++j >2) {
                System.out.println();
                j=0;
            }
        }
    }

    public static Puzzle loadData() {
        List<Cell> cells = new ArrayList<>();

        Block block00 = new Block(0, 0);                
        cells.add(new Cell(0, block00, 0, 0));
        cells.add(new Cell(1, block00, 1, 0));
        cells.add(new Cell(2, block00, 2, 0));
        cells.add(new Cell(3, block00, 0, 1));
        cells.add(new Cell(4, block00, 1, 1,new Value(1)));
        cells.add(new Cell(5, block00, 2, 1,new Value(2)));
        cells.add(new Cell(6, block00, 0, 2));
        cells.add(new Cell(7, block00, 1, 2));
        cells.add(new Cell(8, block00, 2, 2));

        Block block01 = new Block(0, 1);                
        cells.add(new Cell(9, block01, 0, 0));
        cells.add(new Cell(10, block01, 1, 0));
        cells.add(new Cell(11, block01, 2, 0));
        cells.add(new Cell(12, block01, 0, 1));
        cells.add(new Cell(13, block01, 1, 1));
        cells.add(new Cell(14, block01, 2, 1));
        cells.add(new Cell(15, block01, 0, 2));
        cells.add(new Cell(16, block01, 1, 2));
        cells.add(new Cell(17, block01, 2, 2));

        List<Value> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.add(new Value(i));            
            values.add(new Value(i));
            values.add(new Value(i));
        }
        
                
        return new Puzzle(values, cells);
    }
  
}
