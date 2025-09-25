package dnc.benchmark;
import org.openjdk.jmh.annotations.*;
import dnc.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BenchmarkRunner {
    private int[] array;
    private Point[] points;

    @Setup public void setup() {
        array = generateRandomArray(10000);
        points = generateRandomPoints(10000);
    }

    @Benchmark public void benchmarkMergeSort() { MergeSort.sort(array.clone()); }
    @Benchmark public void benchmarkQuickSort() { QuickSort.sort(array.clone()); }
    @Benchmark public void benchmarkSelect() { DeterministicSelect.select(array.clone(), 5000); }
    @Benchmark public void benchmarkClosestPair() { ClosestPair.findClosestDistance(points.clone()); }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
        return array;
    }

    private Point[] generateRandomPoints(int size) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) points[i] = new Point(Math.random() * 1000, Math.random() * 1000);
        return points;
    }
}