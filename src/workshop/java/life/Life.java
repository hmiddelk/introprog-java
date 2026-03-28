package life;

import java.util.Random;

/**
 * Immutable representation of a Game-of-Life universe.
 *
 * A Life holds a Matrix of booleans: true = alive, false = dead.
 * Like Matrix, every operation that changes state returns a new Life.
 */
public final class Life {

    public final Matrix cells;

    public Life(Matrix cells) {
        this.cells = cells;
    }

    /**
     * TODO step 2: Return true if the cell at (row, col) is alive,
     *              false otherwise — including when the index is outside
     *              the grid boundaries (do NOT throw an exception).
     *
     * Hint: check row/col against cells.rows and cells.cols first,
     *       then delegate to cells.get(row, col).
     */
    public boolean apply(int row, int col) {
        throw new RuntimeException("TODO step 2: Life.apply");
    }

    /**
     * TODO step 3: Return a new Life identical to this one except that
     *              the cell at (row, col) is set to value.
     *
     * Hint: one line — wrap cells.updated(...) in a new Life(...)
     */
    public Life updated(int row, int col, boolean value) {
        throw new RuntimeException("TODO step 3: Life.updated");
    }

    /**
     * TODO step 4: Return a new Life with the cell at (row, col) flipped
     *              (alive → dead, dead → alive).
     *
     * Hint: one line — use apply(...) and updated(...)
     */
    public Life toggled(int row, int col) {
        throw new RuntimeException("TODO step 4: Life.toggled");
    }

    /**
     * TODO step 5: Count and return the number of living neighbours of
     *              the cell at (row, col).
     *
     * A cell has up to 8 neighbours: all (row+dr, col+dc) where
     * dr and dc are each in {-1, 0, 1}, excluding (dr==0, dc==0).
     *
     * Hint: use apply(row+dr, col+dc) — it safely returns false for
     *       out-of-bounds positions, so no extra bounds check is needed.
     */
    public int nbrOfNeighbours(int row, int col) {
        throw new RuntimeException("TODO step 5: Life.nbrOfNeighbours");
    }

    /**
     * Returns a new Life representing the next generation.
     *
     * Uses an array-of-one trick so the lambda below can update
     * the accumulator (Java lambdas cannot close over non-final locals).
     */
    public Life evolved() {
        final Life current = this;
        // TODO step 7 depends on defaultRule being implemented first.
        Life[] acc = { Life.empty(cells.rows, cells.cols) };
        cells.foreachIndex((r, c) -> {
            acc[0] = acc[0].updated(r, c, defaultRule(r, c, current));
        });
        return acc[0];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < cells.rows; r++) {
            for (int c = 0; c < cells.cols; c++)
                sb.append(cells.get(r, c) ? '0' : '-');
            if (r < cells.rows - 1) sb.append('\n');
        }
        return sb.toString();
    }

    // -----------------------------------------------------------------------
    // Factory methods
    // -----------------------------------------------------------------------

    /**
     * TODO step 6: Return a Life whose every cell is dead.
     *
     * Hint: new Matrix(rows, cols) already creates an all-false matrix.
     *       Wrap it in a Life.
     */
    public static Life empty(int rows, int cols) {
        throw new RuntimeException("TODO step 6: Life.empty");
    }

    /**
     * Returns a Life with randomly alive/dead cells (given to you).
     * Study how the lambda accumulator pattern works here — you will
     * need it in evolved() (step 8 in LifeWindow depends on this).
     */
    public static Life random(int rows, int cols) {
        Random rng = new Random();
        Life[] acc = { Life.empty(rows, cols) };
        new Matrix(rows, cols).foreachIndex((r, c) -> {
            if (rng.nextBoolean())
                acc[0] = acc[0].updated(r, c, true);
        });
        return acc[0];
    }

    /**
     * TODO step 7: Implement Conway's Game-of-Life rules.
     *
     * Given the current universe, decide whether the cell at (row, col)
     * should be alive in the NEXT generation:
     *
     *   Survival: a living cell with 2 or 3 neighbours stays alive.
     *   Death:    a living cell with fewer than 2 or more than 3 neighbours dies.
     *   Birth:    a dead cell with exactly 3 neighbours becomes alive.
     *   Stays dead: a dead cell with any other neighbour count stays dead.
     *
     * Hint: call current.nbrOfNeighbours(row, col) and current.apply(row, col).
     */
    public static boolean defaultRule(int row, int col, Life current) {
        throw new RuntimeException("TODO step 7: Life.defaultRule");
    }
}
