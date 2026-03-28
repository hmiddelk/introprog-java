package life;

import introprog.PixelWindow;
import java.awt.Color;

/**
 * Visualises a Game-of-Life universe in an introprog.PixelWindow.
 *
 * Universe: 15 rows × 20 columns.
 * Each cell is a CELL_SIZE × CELL_SIZE pixel square separated by
 * a one-pixel grid line.
 *
 * Controls (printed at startup):
 *   Click        – toggle cell alive/dead
 *   Enter        – advance one generation (stops play)
 *   Space        – toggle continuous play / stop
 *   R            – randomise universe
 *   Backspace    – clear universe
 *   Close window – exit
 */
public class LifeWindow {

    // -----------------------------------------------------------------------
    // Event-type constants (PixelWindow integer codes)
    // -----------------------------------------------------------------------
    static final int EVENT_UNDEFINED     = 0;
    static final int EVENT_KEY_PRESSED   = 1;
    static final int EVENT_MOUSE_PRESSED = 3;
    static final int EVENT_WINDOW_CLOSED = 5;

    // -----------------------------------------------------------------------
    // Grid / window constants
    // -----------------------------------------------------------------------
    static final int ROWS = 15;
    static final int COLS = 20;

    static final int CELL_SIZE     = 30;                    // pixels per cell
    static final int WINDOW_WIDTH  = COLS * CELL_SIZE + 1;
    static final int WINDOW_HEIGHT = ROWS * CELL_SIZE + 1;

    static final Color ALIVE_COLOR = new Color(242, 128, 161); // beautiful pink
    static final Color DEAD_COLOR  = Color.BLACK;
    static final Color GRID_COLOR  = Color.DARK_GRAY;

    static final int EVENT_MAX_WAIT_MS     = 1;
    static final int NEXT_GENERATION_DELAY = 200; // ms between generations in play mode

    // -----------------------------------------------------------------------
    // Initial cell colony
    // -----------------------------------------------------------------------
    static final int[][] INITIAL_CELLS = {
        // glider
        {5,4}, {5,6}, {4,6}, {6,6}, {6,5},
        // block
        {9,10}, {10,10}, {9,11}, {10,11},
        // blinker
        {12,5}, {13,5}, {14,5}
    };

    static final String HELP = """
  Welcome to GAME OF LIFE!
  Click on cell to toggle.
  Press ENTER for next generation.
  Press SPACE to toggle play/stop.
  Press R to create random life.
  Press BACKSPACE to clear life.
  Close window to exit.
""";

    // -----------------------------------------------------------------------
    // Instance state
    // -----------------------------------------------------------------------
    private final PixelWindow window;
    private Life life;
    private boolean quit = false;
    private boolean play = false;

    // -----------------------------------------------------------------------
    // Constructor — given to you
    // -----------------------------------------------------------------------
    public LifeWindow() {
        window = new PixelWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Game of Life",
                                 DEAD_COLOR, GRID_COLOR);
        // Build the initial colony from the preset coordinates
        Life l = Life.empty(ROWS, COLS);
        for (int[] cell : INITIAL_CELLS)
            l = l.updated(cell[0], cell[1], true);
        life = l;
    }

    // -----------------------------------------------------------------------
    // Drawing
    // -----------------------------------------------------------------------

    /**
     * Paints the interior of cell (row, col) with the given colour.
     *
     * The pixel origin of a cell is (col * CELL_SIZE, row * CELL_SIZE).
     * Add 1 to each to stay inside the grid line, and use CELL_SIZE - 1
     * for width and height so you don't overwrite the next grid line.
     *
     * The window API:  window.fill(pixelX, pixelY, width, height, color)
     */
    private void fillCell(int row, int col, Color color) {
        // TODO step 8: fill the interior of cell (row, col) with color.
        throw new RuntimeException("TODO step 8: fillCell");
    }

    /**
     * TODO step 9: Repaint cell (row, col) using its current state in
     *              this.life: ALIVE_COLOR if alive, DEAD_COLOR if dead.
     *
     * Hint: one line — call fillCell with the right colour.
     */
    private void drawCell(int row, int col) {
        throw new RuntimeException("TODO step 9: drawCell");
    }

    /**
     * TODO step 10: Draw the full grid — background, grid lines, and cells.
     *
     * Suggested order:
     *   a) Fill the entire window with DEAD_COLOR.
     *   b) Draw COLS+1 vertical lines and ROWS+1 horizontal lines in GRID_COLOR.
     *      window.line(x1, y1, x2, y2, color, thickness)  — use thickness 1.
     *      Vertical line c:   x fixed at c * CELL_SIZE, y from 0 to ROWS * CELL_SIZE.
     *      Horizontal line r: y fixed at r * CELL_SIZE, x from 0 to COLS * CELL_SIZE.
     *   c) Call fillCell for every living cell (use a nested for-loop or
     *      cells.foreachIndex).
     */
    public void drawGrid() {
        throw new RuntimeException("TODO step 10: drawGrid");
    }

    // -----------------------------------------------------------------------
    // State update
    // -----------------------------------------------------------------------

    /**
     * TODO step 11: Replace this.life with newLife and redraw only the
     *               cells that changed between the two generations.
     *
     * Hint:
     *   1. Save this.life as oldLife before reassigning.
     *   2. Set this.life = newLife.
     *   3. Loop over all (r, c); if newLife.apply(r,c) != oldLife.apply(r,c),
     *      call drawCell(r, c)  (which reads from this.life, now == newLife).
     */
    private void update(Life newLife) {
        throw new RuntimeException("TODO step 11: update");
    }

    // -----------------------------------------------------------------------
    // Input handling
    // -----------------------------------------------------------------------

    /**
     * TODO step 12: React to a key press.
     *
     *   "Enter"     → stop play, advance one generation with update(life.evolved())
     *   " "         → toggle play (play = !play)
     *   "R" or "r"  → update(Life.random(ROWS, COLS))
     *   "BackSpace"  → update(Life.empty(ROWS, COLS))
     *
     * Hint: use a switch expression or a chain of if/else-if on the key string.
     */
    private void handleKey(String key) {
        throw new RuntimeException("TODO step 12: handleKey — key was: " + key);
    }

    /**
     * TODO step 13: React to a mouse click at pixel position (pixelX, pixelY).
     *
     * Convert pixel coordinates to a (row, col) grid position:
     *   col = pixelX / CELL_SIZE
     *   row = pixelY / CELL_SIZE
     *
     * If the resulting (row, col) is inside the grid, toggle that cell.
     *
     * Hint: call update(life.toggled(row, col)).
     */
    private void handleClick(int pixelX, int pixelY) {
        throw new RuntimeException("TODO step 13: handleClick");
    }

    // -----------------------------------------------------------------------
    // Main loop — given to you; study how it works
    // -----------------------------------------------------------------------

    /**
     * Runs the game loop until the window is closed.
     *
     * Each iteration:
     *  1. If in play mode, advance one generation.
     *  2. Process all pending window events.
     *  3. Sleep for whatever time remains of NEXT_GENERATION_DELAY ms.
     */
    public void loopUntilQuit() throws InterruptedException {
        while (!quit) {
            long t0 = System.currentTimeMillis();

            if (play)
                update(life.evolved());

            window.awaitEvent(EVENT_MAX_WAIT_MS);

            while (window.lastEventType() != EVENT_UNDEFINED) {
                int et = window.lastEventType();
                if (et == EVENT_KEY_PRESSED) {
                    handleKey(window.lastKey());
                } else if (et == EVENT_MOUSE_PRESSED) {
                    scala.Tuple2<Object, Object> pos = window.lastMousePos();
                    handleClick((Integer) pos._1(), (Integer) pos._2());
                } else if (et == EVENT_WINDOW_CLOSED) {
                    quit = true;
                    break;
                }
                window.awaitEvent(EVENT_MAX_WAIT_MS);
            }

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = NEXT_GENERATION_DELAY - elapsed;
            if (sleep > 0) Thread.sleep(sleep);
        }
    }

    public void start() throws InterruptedException {
        drawGrid();
        loopUntilQuit();
    }

    // -----------------------------------------------------------------------
    // Entry point
    // -----------------------------------------------------------------------
    public static void main(String[] args) throws InterruptedException {
        System.out.println(HELP);
        new LifeWindow().start();
    }
}
