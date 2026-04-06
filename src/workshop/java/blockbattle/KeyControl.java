package blockbattle;

/** Maps keyboard key names to movement directions for one player. */
public class KeyControl {
    public final String left, right, up, down;

    public KeyControl(String left, String right, String up, String down) {
        this.left = left; this.right = right; this.up = up; this.down = down;
    }

    /**
     * Returns the direction vector {dx, dy} for the given key.
     * Returns {0, 0} if key is not one of this control's keys.
     *
     * <p>Hint: Compare {@code key} with each of the four field strings using
     * {@code key.equals(...)}. Return a two-element {@code int[]} array:
     * <ul>
     *   <li>{@code left}  → {@code {-1,  0}} (move left: decrease x)</li>
     *   <li>{@code right} → {@code { 1,  0}} (move right: increase x)</li>
     *   <li>{@code up}    → {@code { 0, -1}} (move up: decrease y)</li>
     *   <li>{@code down}  → {@code { 0,  1}} (move down: increase y)</li>
     *   <li>any other key → {@code { 0,  0}} (no movement)</li>
     * </ul>
     * Use a chain of {@code if} statements, one for each direction.</p>
     */
    public int[] direction(String key) {
        throw new RuntimeException("TODO: return the {dx, dy} vector for key, or {0,0} if not matched");
    }

    /**
     * Returns true iff key is one of this control's four keys.
     *
     * <p>Hint: Return {@code true} when {@code key.equals(left)},
     * {@code key.equals(right)}, {@code key.equals(up)}, or
     * {@code key.equals(down)}. Combine the four tests with {@code ||}.
     * You may also call {@code direction(key)} and check whether the result
     * is non-zero, but the direct approach is clearer here.</p>
     */
    public boolean has(String key) {
        throw new RuntimeException("TODO: return true iff key matches one of left/right/up/down");
    }

    @Override public String toString() {
        return "KeyControl(left=" + left + ", right=" + right
             + ", up=" + up + ", down=" + down + ")";
    }
}
