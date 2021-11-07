package com.github.chavacava.sudokusolver;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.github.chavacava.sudokusolver.constraints.SudokuConstraintsProvider;
import com.github.chavacava.sudokusolver.domain.Puzzle;
import com.github.chavacava.sudokusolver.domain.Cell;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.EnvironmentMode;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuSolverApp {
    private static final int BLOCK_SIZE = 3; // cells
    private static final int PUZZLE_SIZE = 2; // blocks
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuSolverApp.class);
    private static String lb = System.getProperty("line.separator");

    public static void main(String[] args) {
        SolverFactory<Puzzle> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(Puzzle.class)
                .withEntityClasses(Cell.class)
                .withConstraintProviderClass(SudokuConstraintsProvider.class)
                .withEnvironmentMode(EnvironmentMode.FULL_ASSERT)
                .withTerminationSpentLimit(Duration.ofSeconds(120)));

        ScoreManager<Puzzle, HardSoftScore> scoreManager = ScoreManager.create(solverFactory);
       
        // Load the problem
        Puzzle problem = loadData();

        LOGGER.info(lb+dumpPuzzle(problem, BLOCK_SIZE,PUZZLE_SIZE));
        
        // Solve the problem
        Solver<Puzzle> solver = solverFactory.buildSolver();
        Puzzle solution = solver.solve(problem);

        if (solution.getScore().isFeasible()) {
            LOGGER.info("SOLUTION FOUND");    
        } else {
            LOGGER.info("UNABLE TO FIND A SOLUTION");    
            LOGGER.info(scoreManager.explainScore(solution).getSummary());
        }

        LOGGER.info(lb+dumpPuzzle(solution, BLOCK_SIZE,PUZZLE_SIZE));
    }

    private static String dumpPuzzle(Puzzle puzzle, int blockSize, int puzzleSize) {
        StringBuilder result = new StringBuilder();
        Cell[] cells = puzzle.getCells();
        int col = 0;
        int row = 0;
        for (int i = 0; i < cells.length; i++) {
            result.append(cells[i]);
            col++;
            if (col % blockSize == 0) {
                result.append("|");
            }
            if (col == puzzleSize*blockSize ) {
                result.append(lb);
                col = 0;
                row++;
            }
            if (row == blockSize ) {
                result.append("-".repeat(puzzleSize*blockSize+puzzleSize)+lb);
                row = 0;
            }                        
        }
        return result.toString();
    }

    public static Puzzle loadData() {
        Cell[] cells = createCells(PUZZLE_SIZE,BLOCK_SIZE);
        cells[0].pinWith(5);
        cells[1].pinWith(3);        
        cells[6].pinWith(6);        
        cells[13].pinWith(9);        
        cells[14].pinWith(8);        
        cells[4].pinWith(7);        
        cells[9].pinWith(1);        
        cells[10].pinWith(9);        
        cells[11].pinWith(5);        
        cells[18].pinWith(8);        
        cells[24].pinWith(4);        
        cells[30].pinWith(7);        
        cells[22].pinWith(6);        
        cells[27].pinWith(8);        
        cells[29].pinWith(3);        
        cells[34].pinWith(2);        

        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= (9*PUZZLE_SIZE); i++) {
            values.add((i%9)+1);            
        }
                        
        return new Puzzle(values, cells);
    }
  
    private static Cell[] createCells(int puzzleSize, int blockSize) {
        Cell[] result = new Cell[puzzleSize*puzzleSize*blockSize*blockSize];
        int col = 0;
        int row = 0;        
        for (int i = 0; i < result.length; i++) {            
            if (col == puzzleSize*blockSize ) {
                row++;
                col = 0;
            }
            int blockCol = col/blockSize;
            int blockRow = row/blockSize;   
            int blockId = blockRow*10+blockCol;
            result[i] = new Cell(i,blockId, col, row);
            col++;
        }
        return result;
    }
}
