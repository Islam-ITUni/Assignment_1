package dnc;
public class Point implements Comparable<Point> {
    public final double x, y;
    public Point(double x, double y) { this.x = x; this.y = y; }

    public double distanceTo(Point other) {
        double dx = this.x - other.x, dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override public int compareTo(Point other) { return Double.compare(this.x, other.x); }
    @Override public String toString() { return String.format("(%.2f, %.2f)", x, y); }
}