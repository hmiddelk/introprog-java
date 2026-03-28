package life;

import java.util.function.BiConsumer;

/**
 * Immutable 2-D boolean matrix.
 *
 * The matrix stores one boolean value per cell.
 * "Immutable" means that no method changes the matrix itself —
 * every modification returns a brand-new Matrix object instead.
 */
public final class Matrix {

    private final boolean[][] data; // data[row][col]
    public final int rows;
    public final int cols;

    /** Creates an all-false matrix of the given dimensions. */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new boolean[rows][cols];
    }

    /** Deep-copies the supplied 2-D array (used internally). */
    public Matrix(boolean[][] data) {
        this.rows = data.length;
        this.cols = rows > 0 ? data[0].length : 0;
        this.data = new boolean[rows][cols];
        for (int r = 0; r < rows; r++)
            System.arraycopy(data[r], 0, this.data[r], 0, cols);
    }

    /** Returns the value stored at (row, col). */
    public boolean get(int row, int col) {
        return data[row][col];
    }

    /**
     * TODO step 1: Return a new Matrix that is identical to this one
     *              except that position (row, col) holds the given value.
     *
     * Hint: use the Matrix(boolean[][] data) constructor above, which
     *       deep-copies its argument — so you can safely mutate the copy
     *       before returning it.
     */
    public Matrix updated(int row, int col, boolean value) {
        throw new RuntimeException("TODO step 1: Matrix.updated");
    }

    /** Calls action.accept(row, col) for every index pair in the matrix. */
    public void foreachIndex(BiConsumer<Integer, Integer> action) {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                action.accept(r, c);
    }
}
