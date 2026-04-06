package blockbattle;
import java.awt.Color;

public class Game {

    // ---- Window / grid constants ----------------------------------------
    static final int    WIDTH      = 30;
    static final int    HEIGHT     = 50;
    static final String TITLE      = "EPIC BLOCK BATTLE";
    static final int    BLOCK_SIZE = 14;
    static final int    SKY_MAX    = 7;   // rows 0..SKY_MAX are sky
    static final int    GRASS_Y    = 8;   // row GRASS_Y is grass
    static final int    WIN_SCORE  = 100;

    /**
     * Colour palette for the game world and moles.
     *
     * <p>TODO: Fill in each colour constant below.
     * Use {@code new Color(r, g, b)} with the RGB values described in the
     * comments. {@code Color.ORANGE} and {@code Color.CYAN} are predefined
     * Java constants you can use directly.</p>
     */
    static class Colors {
        // TODO: sky — a light sky-blue, e.g. new Color(135, 206, 235)
        static final Color sky       = null;
        // TODO: grass — a medium green, e.g. new Color(34, 139, 34)
        static final Color grass     = null;
        // TODO: soil — dark brown underground, e.g. new Color(101, 67, 33)
        static final Color soil      = null;
        // TODO: tunnel — lighter brown left behind by moles, e.g. new Color(181, 101, 29)
        static final Color tunnel    = null;
        // TODO: leftMole — use Color.ORANGE
        static final Color leftMole  = null;
        // TODO: rightMole — use Color.CYAN
        static final Color rightMole = null;
    }

    /**
     * Background colour for a given row y.
     *
     * <p>Hint: Return different colours depending on depth:
     * <ul>
     *   <li>Rows 0 to {@code SKY_MAX} (inclusive) are sky → {@code Colors.sky}</li>
     *   <li>Row {@code GRASS_Y} is the grass strip → {@code Colors.grass}</li>
     *   <li>All deeper rows are underground soil → {@code Colors.soil}</li>
     * </ul>
     * Use two {@code if} statements (or an if/else-if/else chain).</p>
     */
    static Color backgroundColorAtDepth(int y) {
        throw new RuntimeException("TODO: return Colors.sky, Colors.grass, or Colors.soil depending on y");
    }

    // ---- Instance state -------------------------------------------------
    final String      leftPlayerName;
    final String      rightPlayerName;
    final BlockWindow window;
    final Mole        leftMole;
    final Mole        rightMole;

    /**
     * Constructs a new Game with the given player names.
     *
     * <p>The window is already created for you. You must create both moles.
     *
     * <p>TODO: Replace the two {@code null} assignments with real {@code Mole}
     * objects and remove the {@code throw} below.
     *
     * <p>Hint for leftMole:
     * <pre>
     *   new Mole(
     *       leftPlayerName,
     *       new Pos(5, 20),           // starting position
     *       new int[]{1, 0},          // initial direction: moving right
     *       Colors.leftMole,          // mole colour
     *       new KeyControl("a", "d", "w", "s")  // WASD controls
     *   )
     * </pre>
     *
     * <p>Hint for rightMole:
     * <pre>
     *   new Mole(
     *       rightPlayerName,
     *       new Pos(24, 20),          // starting position
     *       new int[]{-1, 0},         // initial direction: moving left
     *       Colors.rightMole,
     *       new KeyControl("Left", "Right", "Up", "Down")  // arrow keys
     *   )
     * </pre>
     */
    public Game(String leftPlayerName, String rightPlayerName) {
        this.leftPlayerName  = leftPlayerName;
        this.rightPlayerName = rightPlayerName;
        window    = new BlockWindow(WIDTH, HEIGHT, TITLE, BLOCK_SIZE);
        leftMole  = null; // TODO: create a Mole for the left player
        rightMole = null; // TODO: create a Mole for the right player
        throw new RuntimeException("TODO: initialize leftMole and rightMole");
    }

    public Game() { this("LEFT", "RIGHT"); }

    // ---- Drawing --------------------------------------------------------

    /**
     * Fill every block with the appropriate background colour, then draw moles and scores.
     *
     * <p>Hint: Use two nested {@code for} loops over all {@code y} (0 to
     * {@code HEIGHT - 1}) and {@code x} (0 to {@code WIDTH - 1}) coordinates.
     * For each position call:
     * <pre>
     *   window.setBlock(new Pos(x, y), backgroundColorAtDepth(y));
     * </pre>
     * After the loops, paint both moles at their current positions:
     * <pre>
     *   window.setBlock(leftMole.pos,  leftMole.color);
     *   window.setBlock(rightMole.pos, rightMole.color);
     * </pre>
     * Finally call {@code showPoints()} to draw the score display.</p>
     */
    public void drawWorld() {
        throw new RuntimeException("TODO: fill every block with background colour, draw moles, call showPoints()");
    }

    /**
     * Repaint a rectangular region of blocks with background colours.
     *
     * <p>Hint: Use two nested {@code for} loops: outer loop {@code y} from
     * {@code y1} to {@code y2} (inclusive), inner loop {@code x} from
     * {@code x1} to {@code x2} (inclusive). For each block call:
     * <pre>
     *   window.setBlock(new Pos(x, y), backgroundColorAtDepth(y));
     * </pre>
     * This method is used by {@code showPoints()} to clear the sky area
     * before redrawing score text.</p>
     */
    public void eraseBlocks(int x1, int y1, int x2, int y2) {
        throw new RuntimeException("TODO: loop over the rectangle and call window.setBlock with backgroundColorAtDepth(y)");
    }

    // ---- Given helpers --------------------------------------------------

    /** Redraw the score area in the sky. (Given — do not modify.) */
    void showPoints() {
        eraseBlocks(0,         0, WIDTH / 2 - 1, SKY_MAX);
        eraseBlocks(WIDTH / 2, 0, WIDTH - 1,     SKY_MAX);
        window.write(leftPlayerName  + ": " + leftMole.points,
                     new Pos(0,       3), Colors.leftMole,  BLOCK_SIZE);
        window.write(rightPlayerName + ": " + rightMole.points,
                     new Pos(WIDTH/2, 3), Colors.rightMole, BLOCK_SIZE);
    }

    /** Reset a mole to its start position, direction, and zero points. (Given — do not modify.) */
    void resetMole(Mole mole, Pos startPos, int[] startDir) {
        mole.pos    = startPos;
        mole.dir    = startDir;
        mole.points = 0;
    }

    // ---- Game logic -----------------------------------------------------

    /**
     * Move mole one step: reverse at sky/edge boundaries; award a point for
     * fresh soil; draw new position before erasing old (flicker-free).
     *
     * <p>Hint — step by step:
     * <ol>
     *   <li>Compute {@code Pos next = mole.nextPos()}.</li>
     *   <li>Determine whether {@code next} is out-of-bounds or in the sky:
     *       <pre>
     *         boolean atEdge = next.x &lt; 0 || next.x &gt;= WIDTH
     *                       || next.y &lt; 0 || next.y &gt;= HEIGHT
     *                       || next.y &lt;= SKY_MAX;
     *       </pre></li>
     *   <li>If {@code atEdge}: call {@code mole.reverseDir()} (the mole bounces).</li>
     *   <li>Otherwise (the move is legal):
     *     <ul>
     *       <li>If {@code window.getBlock(next).equals(Colors.soil)}, increment
     *           {@code mole.points} by 1 (the mole digs a new tunnel block).</li>
     *       <li>Draw the mole at its new position:
     *           {@code window.setBlock(next, mole.color)}.</li>
     *       <li>Erase the old position (leave a tunnel):
     *           {@code window.setBlock(mole.pos, Colors.tunnel)}.</li>
     *       <li>Commit the move: {@code mole.move()}.</li>
     *     </ul>
     *   </li>
     * </ol>
     * Drawing the new block first and erasing the old one second avoids
     * a one-frame flicker.</p>
     */
    public void update(Mole mole) {
        throw new RuntimeException("TODO: compute next pos, bounce at edges, score soil, draw/erase, move");
    }

    /**
     * Runs one round. Returns {@code true} if the player pressed R to restart.
     *
     * <p>The "wait for restart" section after the main loop is already provided
     * — you only need to implement the {@code while (running)} loop body.
     *
     * <p>Hint — each iteration of the {@code while (running)} loop:
     * <ol>
     *   <li>Read the next event type:
     *       {@code int et = window.nextEventType(10);}</li>
     *   <li>If {@code et == BlockWindow.EVENT_KEY_PRESSED}:
     *     <ul>
     *       <li>Get the key: {@code String key = window.lastKey();}</li>
     *       <li>Forward it to both moles: {@code leftMole.setDir(key);
     *           rightMole.setDir(key);}</li>
     *     </ul>
     *   </li>
     *   <li>If {@code et == BlockWindow.EVENT_WINDOW_CLOSED}: set
     *       {@code running = false;}.</li>
     *   <li>If still running:
     *     <ul>
     *       <li>Call {@code update(leftMole)} and {@code update(rightMole)}.</li>
     *       <li>If either score changed since last frame, call
     *           {@code showPoints()} and save the new scores in
     *           {@code prevLeft}/{@code prevRight}.</li>
     *       <li>If {@code leftMole.points >= WIN_SCORE || rightMole.points >= WIN_SCORE}:
     *           set {@code running = false}, determine the winner
     *           ({@code leftMole.points >= rightMole.points ? leftPlayerName : rightPlayerName}),
     *           and display two lines of text using {@code window.write(...)}.</li>
     *     </ul>
     *   </li>
     *   <li>Call {@code BlockWindow.delay(150)} to pace the game.</li>
     * </ol>
     */
    boolean gameLoop() throws InterruptedException {
        boolean running   = true;
        int     prevLeft  = -1;
        int     prevRight = -1;

        while (running) {
            throw new RuntimeException("TODO: implement the game loop body (read event, setDir, update, showPoints, check win)");
        }

        // ---- Wait for R or window close (given — do not modify) ----
        boolean restart = false;
        boolean waiting = true;
        while (waiting) {
            int et = window.nextEventType(500);
            if (et == BlockWindow.EVENT_KEY_PRESSED
                    && window.lastKey().equalsIgnoreCase("r")) {
                restart = true;
                waiting = false;
            } else if (et == BlockWindow.EVENT_WINDOW_CLOSED) {
                waiting = false;
            }
        }
        return restart;
    }

    // ---- Entry point (given — do not modify) ----------------------------

    public void start() throws InterruptedException {
        System.out.println("Start digging!");
        System.out.println(leftPlayerName  + " " + leftMole.keyControl);
        System.out.println(rightPlayerName + " " + rightMole.keyControl);
        boolean keepPlaying = true;
        while (keepPlaying) {
            resetMole(leftMole,  new Pos(5,  20), new int[]{ 1, 0});
            resetMole(rightMole, new Pos(24, 20), new int[]{-1, 0});
            drawWorld();
            keepPlaying = gameLoop();
        }
    }
}
