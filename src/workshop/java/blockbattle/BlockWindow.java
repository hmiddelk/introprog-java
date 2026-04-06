package blockbattle;
import introprog.PixelWindow;
import java.awt.Color;

/** Wraps PixelWindow; translates block coordinates to pixel coordinates. */
public class BlockWindow {
    static final int EVENT_UNDEFINED     = 0;
    static final int EVENT_KEY_PRESSED   = 1;
    static final int EVENT_WINDOW_CLOSED = 5;

    public final int         blockSize;
    public final PixelWindow pixelWindow;

    public BlockWindow(int widthInBlocks, int heightInBlocks, String title, int blockSize) {
        this.blockSize   = blockSize;
        this.pixelWindow = new PixelWindow(
            widthInBlocks  * blockSize,
            heightInBlocks * blockSize,
            title, Color.BLACK, Color.BLACK);
    }

    /**
     * Fill the block at pos with color.
     *
     * <p>Hint: A block occupies a square of {@code blockSize × blockSize}
     * pixels. The top-left pixel of block {@code pos} is at pixel coordinates
     * {@code (pos.x * blockSize, pos.y * blockSize)}.
     * Call {@code pixelWindow.fill(x, y, width, height, color)} where
     * {@code width} and {@code height} are both {@code blockSize}.</p>
     */
    public void setBlock(Pos pos, Color color) {
        throw new RuntimeException("TODO: call pixelWindow.fill with the correct pixel coordinates");
    }

    /**
     * Return the colour of the centre pixel of the block at pos.
     *
     * <p>Hint: The centre pixel of block {@code pos} is at pixel coordinates
     * {@code (pos.x * blockSize + blockSize / 2,
     *          pos.y * blockSize + blockSize / 2)}.
     * Call {@code pixelWindow.getPixel(px, py)} with those values and return
     * the resulting {@code Color}.</p>
     */
    public Color getBlock(Pos pos) {
        throw new RuntimeException("TODO: call pixelWindow.getPixel at the centre of the block and return the Color");
    }

    /** Draw text anchored at block position pos. (Given — do not modify.) */
    public void write(String text, Pos pos, Color color, int textSize) {
        pixelWindow.drawText(text,
            pos.x * blockSize, pos.y * blockSize,
            color, textSize, 0, "monospaced");
    }

    /** Wait up to maxWaitMillis for the next event; return its type constant. (Given — do not modify.) */
    public int nextEventType(int maxWaitMillis) {
        pixelWindow.awaitEvent(maxWaitMillis);
        return pixelWindow.lastEventType();
    }

    /** Return the name of the last key pressed. (Given — do not modify.) */
    public String lastKey() { return pixelWindow.lastKey(); }

    /** Pause the current thread for millis milliseconds. (Given — do not modify.) */
    public static void delay(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
