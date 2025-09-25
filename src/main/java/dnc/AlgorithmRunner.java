package dnc;
import dnc.metrics.Metrics;
import java.util.Arrays;

public class AlgorithmRunner {
    public static Metrics.Snapshot runMergeSort(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        MergeSort.sort(copy);
        long endTime = System.nanoTime();
        return new Metrics.Snapshot(endTime - startTime, Metrics.getMaxRecursionDepth(),
                Metrics.getComparisonCount(), Metrics.getAllocationCount());
    }

    public static Metrics.Snapshot runQuickSort(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        QuickSort.sort(copy);
        long endTime = System.nanoTime();
        return new Metrics.Snapshot(endTime - startTime, Metrics.getMaxRecursionDepth(),
                Metrics.getComparisonCount(), Metrics.getAllocationCount());
    }

    public static Metrics.Snapshot runSelect(int[] array, int k) {
        int[] copy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        int result = DeterministicSelect.select(copy, k);
        long endTime = System.nanoTime();

        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        if (result != sorted[k]) throw new AssertionError("Select returned wrong result");

        return new Metrics.Snapshot(endTime - startTime, Metrics.getMaxRecursionDepth(),
                Metrics.getComparisonCount(), Metrics.getAllocationCount());
    }

    public static Metrics.Snapshot runClosestPair(Point[] points) {
        Point[] copy = Arrays.copyOf(points, points.length);
        long startTime = System.nanoTime();
        double result = ClosestPair.findClosestDistance(copy);
        long endTime = System.nanoTime();

        if (points.length <= 2000) {
            double bruteForceResult = bruteForceClosest(points);
            if (Math.abs(result - bruteForceResult) > 1e-9) throw new AssertionError("Closest pair wrong");
        }

        return new Metrics.Snapshot(endTime - startTime, Metrics.getMaxRecursionDepth(),
                Metrics.getComparisonCount(), Metrics.getAllocationCount());
    }

    private static double bruteForceClosest(Point[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }
}