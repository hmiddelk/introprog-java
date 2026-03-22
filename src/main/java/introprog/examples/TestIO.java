package introprog.examples;

import introprog.IO$;
import introprog.Image;
import introprog.PixelWindow;
import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Java translation of introprog.examples.TestIO (Scala original).
 *
 * Scala objects (IO, PixelWindow) are singletons accessed in Java via
 * the generated companion-object class: IO$.MODULE$ and PixelWindow.exit().
 *
 * The Scala case class Person is replaced by a Java record that implements
 * Serializable so that Java's object serialisation round-trip works.
 */
public class TestIO {

    record Person(String name) implements Serializable {}

    public static void main(String[] args) throws Exception {
        // --- object serialisation round-trip ---
        Map<Person, Integer> highscores = new HashMap<>();
        highscores.put(new Person("Sandra"), 42);
        highscores.put(new Person("Björn"),   5);

        IO$.MODULE$.saveObject(highscores, "highscores.ser");

        @SuppressWarnings("unchecked")
        Map<Person, Integer> highscores2 = IO$.MODULE$.loadObject("highscores.ser");

        boolean isSameContents = highscores2.equals(highscores);
        assert isSameContents : highscores + " != " + highscores2;
        System.out.println(highscores + " == " + highscores2);
        System.out.println(isSameContents ? "SUCCESS :)" : "FAILURE :(");

        testImageLoadAndDraw();
    }

    static void testImageLoadAndDraw() throws Exception {
        int wWidth  = 4 * 128;
        int wHeight = 3 * 128;
        PixelWindow w  = new PixelWindow(wWidth,             wHeight,              "DrawImage",     Color.BLACK, Color.WHITE);
        PixelWindow w2 = new PixelWindow(wWidth,             wHeight,              "DrawMatrix",    Color.BLACK, Color.WHITE);
        PixelWindow w3 = new PixelWindow((int)(wWidth*1.5),  (int)(wHeight*1.5),   "SaveLoadAsJpeg",Color.BLACK, Color.WHITE);

        Color[][] testMatrix = {
            { Color.BLUE,   Color.YELLOW, Color.BLUE   },
            { Color.YELLOW, Color.YELLOW, Color.YELLOW },
            { Color.BLUE,   Color.YELLOW, Color.BLUE   },
            { Color.BLUE,   Color.YELLOW, Color.BLUE   }
        };

        int flagX = 0, flagY = 0, flagW = 4, flagH = 3;
        w.drawMatrix(testMatrix, 0, 0);

        for (int i = 1; i <= 7; i++) {
            Image img = w.getImage(flagX, flagY, flagW, flagH);
            IO$.MODULE$.savePNG(img, "screenshot");
            w2.drawMatrix(img.toMatrix(), flagX, flagY);
            if (i != 7) {
                flagX += flagW;
                flagY += flagH;
                flagW *= 2;
                flagH *= 2;
                img = IO$.MODULE$.loadImage("screenshot.png");
                w.drawImage(img.scaled(img.width() * 2, img.height() * 2), flagX, flagY);
            }
        }

        Image im = w2.getImage();
        IO$.MODULE$.saveJPEG(im, "screenshot.jpg", 0.2);
        im = IO$.MODULE$.loadImage("screenshot.jpg");
        for (int i = 0; i <= 200; i++) {
            w3.clear();
            w3.drawImage(im, 0, 0, (int)(im.width()*0.5), (int)(im.height()*0.5), Math.toRadians(i * 2));
            Thread.sleep(100 / 6);
        }

        // Wait until the user closes the SaveLoadAsJpeg window.
        // This works in both interactive and non-interactive environments,
        // unlike System.in.read() which returns immediately when stdin is EOF.
        System.out.println("Close the SaveLoadAsJpeg window to exit.");
        int EVENT_WINDOW_CLOSED = 5;
        while (w3.lastEventType() != EVENT_WINDOW_CLOSED) {
            w3.awaitEvent(500);
        }
        IO$.MODULE$.delete("screenshot.png");
        IO$.MODULE$.delete("screenshot.jpg");
        PixelWindow.exit();
    }
}
