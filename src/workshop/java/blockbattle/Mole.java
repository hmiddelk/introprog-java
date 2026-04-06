package blockbattle;
import java.awt.Color;

/** A digging mole with a mutable position, direction, and score. */
public class Mole {
    public final String     name;
    public       Pos        pos;
    public       int[]      dir;        // {dx, dy}
    public final Color      color;
    public final KeyControl keyControl;
    public       int        points;

    public Mole(String name, Pos pos, int[] dir, Color color, KeyControl keyControl) {
        this.name       = name;
        this.pos        = pos;
        this.dir        = dir;
        this.color      = color;
        this.keyControl = keyControl;
        this.points     = 0;
    }

    /**
     * If key belongs to this mole's keyControl, update dir accordingly.
     *
     * <p>Hint: First call {@code keyControl.has(key)} to check whether the
     * key is one of this mole's control keys. If so, call
     * {@code keyControl.direction(key)} to get the new direction vector and
     * assign it to {@code this.dir}. Do nothing if the key does not belong
     * to this mole (another player's key must not change this mole's
     * direction).</p>
     */
    public void setDir(String key) {
        throw new RuntimeException("TODO: if keyControl.has(key), set dir = keyControl.direction(key)");
    }

    /**
     * Reverse the current direction.
     *
     * <p>Hint: Assign a new {@code int[]} to {@code this.dir} whose elements
     * are the negatives of the current ones: {@code {-dir[0], -dir[1]}}.
     * This makes the mole bounce back when it hits a boundary.</p>
     */
    public void reverseDir() {
        throw new RuntimeException("TODO: set dir = new int[]{-dir[0], -dir[1]}");
    }

    /**
     * Move pos to nextPos().
     *
     * <p>Hint: Call {@code nextPos()} (which you implement below) and assign
     * its return value to {@code this.pos}. This advances the mole by one
     * step in the current direction.</p>
     */
    public void move() {
        throw new RuntimeException("TODO: set pos = nextPos()");
    }

    /**
     * The next position given the current dir, without updating pos.
     *
     * <p>Hint: Call {@code pos.moved(dir[0], dir[1])} and return the result.
     * {@code dir[0]} is the horizontal delta (dx) and {@code dir[1]} is the
     * vertical delta (dy). Do NOT assign to {@code this.pos} here — this
     * method is a pure query used to look ahead before committing the move.</p>
     */
    public Pos nextPos() {
        throw new RuntimeException("TODO: return pos.moved(dir[0], dir[1])");
    }

    @Override public String toString() {
        return "Mole[name=" + name + ", pos=" + pos
             + ", dir=(" + dir[0] + "," + dir[1] + "), points=" + points + "]";
    }
}
