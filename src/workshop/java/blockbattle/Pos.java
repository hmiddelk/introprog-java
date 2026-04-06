package blockbattle;

/** An immutable grid position in block coordinates. */
public class Pos {
    public final int x;
    public final int y;

    public Pos(int x, int y) { this.x = x; this.y = y; }

    /**
     * Returns a new Pos shifted by (dx, dy).
     *
     * <p>Hint: Create and return a new {@code Pos} whose x-coordinate is
     * {@code this.x + dx} and whose y-coordinate is {@code this.y + dy}.
     * Do not modify the current object — {@code Pos} is immutable.</p>
     */
    public Pos moved(int dx, int dy) {
        throw new RuntimeException("TODO: return a new Pos(x + dx, y + dy)");
    }

    @Override public String toString() { return "Pos(" + x + ", " + y + ")"; }
}
