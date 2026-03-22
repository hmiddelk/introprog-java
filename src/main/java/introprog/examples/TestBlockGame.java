package introprog.examples;

import introprog.BlockGame;
import introprog.PixelWindow;
import java.awt.Color;
import java.util.Random;

/**
 * Java translation of introprog.examples.TestBlockGame (Scala original).
 *
 * Key Scala-to-Java mappings used here:
 *
 *   - Tuples: Scala's (Int,Int) becomes scala.Tuple2<Object,Object> in
 *     bytecode (primitives are boxed).  Create with new scala.Tuple2<>(x,y).
 *     Access elements with ._1() / ._2() and cast to Integer.
 *
 *   - By-name parameter: BlockGame.gameLoop takes scala.Function0<Object>
 *     (a SAM interface).  We pass an anonymous AbstractFunction0 whose
 *     apply() returns a boxed Boolean.
 *
 *   - Default parameters: methods like drawCenteredText and
 *     drawTextInMessageArea expose their defaults through generated methods
 *     named <method>$default$<n>().  We call those to keep parity with the
 *     Scala defaults rather than hard-coding values.
 *
 *   - onMouseDown / onMouseUp override the BlockGame hooks that receive a
 *     scala.Tuple2<Object,Object> position.
 */
public class TestBlockGame {

    public static void main(String[] args) {
        System.out.println("Press Enter to toggle random blocks. Close window to continue.");
        new RandomBlocks().play();
        System.out.println("Opening MovingBlock. Press Ctrl+C to exit.");
        new MovingBlock().start();
        System.out.println("MovingBlock has ended.");
    }

    // -----------------------------------------------------------------------
    // RandomBlocks
    // -----------------------------------------------------------------------
    static class RandomBlocks extends BlockGame {

        enum State { STARTING, PLAYING, GAME_OVER }

        volatile State state = State.STARTING;
        boolean isDrawingRandomBlocks = false;
        final Random random = new Random();

        RandomBlocks() {
            super(
                "RandomBlocks",
                new scala.Tuple2<>(50, 50),
                15,
                Color.BLACK,
                50,
                2,
                Color.GRAY.darker().darker()
            );
        }

        void showEnterMessage() {
            drawTextInMessageArea(
                "Press Enter to toggle random blocks.", 0, 0,
                drawTextInMessageArea$default$4(),
                drawTextInMessageArea$default$5()
            );
        }

        void showEscapeMessage() {
            drawTextInMessageArea(
                "Press Esc to clear window.", 25, 0,
                drawTextInMessageArea$default$4(),
                drawTextInMessageArea$default$5()
            );
        }

        @Override
        public void onKeyDown(String key) {
            System.out.print(" Key down: " + key);
            switch (key) {
                case "Esc" -> {
                    clearWindow();
                    drawCenteredText(
                        "ESCAPED TO BLACK SPACE!",
                        drawCenteredText$default$2(),
                        drawCenteredText$default$3()
                    );
                    showEnterMessage();
                }
                case "Enter" -> {
                    isDrawingRandomBlocks = !isDrawingRandomBlocks;
                    showEnterMessage();
                    showEscapeMessage();
                }
            }
        }

        @Override
        public void onKeyUp(String key) {
            System.out.print(" Key up: " + key);
        }

        @Override
        public void onMouseDown(scala.Tuple2<Object, Object> pos) {
            System.out.print(" Mouse down: " + pos);
        }

        @Override
        public void onMouseUp(scala.Tuple2<Object, Object> pos) {
            System.out.print(" Mouse up: " + pos);
        }

        @Override
        public void onClose() {
            System.out.print(" Window Closed.");
            state = State.GAME_OVER;
        }

        @Override
        public void gameLoopAction() {
            int dimX = (Integer) dim()._1();
            int dimY = (Integer) dim()._2();
            System.out.print(".");
            if (isDrawingRandomBlocks) {
                drawBlock(
                    random.nextInt(dimX),
                    random.nextInt(dimY),
                    new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
                );
            }
        }

        void play() {
            state = State.PLAYING;
            System.out.println("framesPerSecond == " + framesPerSecond());
            showEnterMessage();
            gameLoop(new scala.runtime.AbstractFunction0<Object>() {
                @Override
                public Object apply() {
                    return state == State.GAME_OVER;
                }
            });
            System.out.println("Goodbye!");
        }
    }

    // -----------------------------------------------------------------------
    // MovingBlock
    // -----------------------------------------------------------------------
    static class MovingBlock extends BlockGame {

        double movesPerSecond = 2.0;
        long   timestampLastMove = System.currentTimeMillis();
        int    x = 0;
        int    y = 0;
        int    loopCounter = 0;

        MovingBlock() {
            super(
                "MovingBlock",
                new scala.Tuple2<>(10, 5),
                40,
                Color.BLACK,
                50,
                1,
                Color.DARK_GRAY
            );
        }

        int millisBetweenMoves() {
            return Math.max(1, (int) Math.round(1000.0 / movesPerSecond));
        }

        int dimX() { return (Integer) dim()._1(); }
        int dimY() { return (Integer) dim()._2(); }

        void move() {
            if (x == dimX() - 1) {
                x = -1;
                y += 1;
            }
            x = x + 1;
        }

        void erase() { drawBlock(x, y, Color.BLACK); }
        void draw()  { drawBlock(x, y, Color.CYAN);  }

        void update() {
            if (System.currentTimeMillis() > timestampLastMove + millisBetweenMoves()) {
                move();
                timestampLastMove = System.currentTimeMillis();
            }
        }

        @Override
        public void gameLoopAction() {
            erase();
            update();
            draw();
            clearMessageArea(
                clearMessageArea$default$1(),
                clearMessageArea$default$2(),
                clearMessageArea$default$3(),
                clearMessageArea$default$4()
            );
            drawTextInMessageArea("Loop number: " + loopCounter, 1, 0, Color.PINK, 30);
            loopCounter++;
        }

        void start() {
            pixelWindow().show();
            gameLoop(new scala.runtime.AbstractFunction0<Object>() {
                @Override
                public Object apply() {
                    return x == dimX() - 1 && y == dimY() - 1;
                }
            });
        }
    }
}
