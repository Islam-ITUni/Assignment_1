package dnc;
import dnc.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static double findClosestDistance(Point[] points) {
        if (points.length < 2) throw new IllegalArgumentException("At least 2 points required");
        Metrics.reset();
        Metrics.recordAllocation();

        Point[] pointsByX = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsByX);
        return findClosestDistance(pointsByX, 0, pointsByX.length - 1);
    }

    private static double findClosestDistance(Point[] pointsByX, int left, int right) {
        Metrics.enterRecursion();
        int n = right - left + 1;

        if (n <= 3) {
            double minDist = bruteForce(pointsByX, left, right);
            Metrics.exitRecursion();
            return minDist;
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pointsByX[mid];

        double leftMin = findClosestDistance(pointsByX, left, mid);
        double rightMin = findClosestDistance(pointsByX, mid + 1, right);
        double minDist = Math.min(leftMin, rightMin);

        Point[] strip = new Point[n];
        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pointsByX[i].x - midPoint.x) < minDist) strip[stripSize++] = pointsByX[i];
        }

        Arrays.sort(strip, 0, stripSize, Comparator.comparingDouble(p -> p.y));
        Metrics.recordAllocation();

        double stripMin = minDist;
        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < minDist; j++) {
                double dist = strip[i].distanceTo(strip[j]);
                Metrics.recordComparison();
                if (dist < stripMin) stripMin = dist;
            }
        }
        Metrics.exitRecursion();
        return Math.min(minDist, stripMin);
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double minDist = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = points[i].distanceTo(points[j]);
                Metrics.recordComparison();
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }
}
