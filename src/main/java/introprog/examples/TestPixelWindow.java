package introprog.examples;

import introprog.PixelWindow;
import java.awt.Color;

/**
 * Java translation of introprog.examples.TestPixelWindow (Scala original).
 *
 * Event-type constants are defined locally because the Scala nested object
 * PixelWindow$Event$ is awkward to use from Java; the integer values are
 * documented in the library source and never change.
 *
 * Notable API differences vs. the Scala original:
 *   - drawText has an extra int parameter for font style (0 = plain)
 *   - awaitEvent takes long (not Int)
 *   - line/fill have no default-parameter overloads in Java
 */
public class TestPixelWindow {

    // PixelWindow$Event$ integer constants
    static final int EVENT_UNDEFINED      = 0;
    static final int EVENT_KEY_PRESSED    = 1;
    static final int EVENT_KEY_RELEASED   = 2;
    static final int EVENT_MOUSE_PRESSED  = 3;
    static final int EVENT_MOUSE_RELEASED = 4;
    static final int EVENT_WINDOW_CLOSED  = 5;

    static PixelWindow w = new PixelWindow(400, 400, "Hello PixelWindow!", Color.BLACK, Color.GREEN);
    static Color color = Color.RED;

    static void square(int x, int y, int side) {
        w.line(x,        y,        x + side, y,        color, 1);
        w.line(x + side, y,        x + side, y + side, color, 1);
        w.line(x + side, y + side, x,        y + side, color, 1);
        w.line(x,        y + side, x,        y,        color, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Key and mouse events are printed. Close window to exit.");
        // drawText(text, x, y, color, size, style, font)  — style 0 = plain
        w.drawText("HELLO WORLD! 012345ÅÄÖ", 0, 0, Color.GREEN, 16, 0, "monospaced");
        square(200, 100, 50);
        w.fill(50, 100, 50, 50, Color.BLUE);
        color = Color.ORANGE;
        square(50, 100, 50);
        color = Color.GREEN;
        square(150, 200, 50);
        w.line(0, 0, w.width(), w.height(), Color.GREEN, 1);

        while (w.lastEventType() != EVENT_WINDOW_CLOSED) {
            w.awaitEvent(10);
            int et = w.lastEventType();
            if (et != EVENT_UNDEFINED) {
                System.out.println("lastEventType: " + et);
            }
            if (et == EVENT_KEY_PRESSED) {
                System.out.println("lastKey == " + w.lastKey());
            } else if (et == EVENT_KEY_RELEASED) {
                System.out.println("lastKey == " + w.lastKey());
            } else if (et == EVENT_MOUSE_PRESSED) {
                System.out.println("lastMousePos == " + w.lastMousePos());
            } else if (et == EVENT_MOUSE_RELEASED) {
                System.out.println("lastMousePos == " + w.lastMousePos());
            } else if (et == EVENT_WINDOW_CLOSED) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            Thread.sleep(100);
        }
    }
}
