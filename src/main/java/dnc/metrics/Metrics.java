package dnc.metrics;
import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    private static final ThreadLocal<AtomicInteger> recursionDepth = ThreadLocal.withInitial(() -> new AtomicInteger(0));
    private static final ThreadLocal<Integer> maxRecursionDepth = ThreadLocal.withInitial(() -> 0);
    private static final ThreadLocal<Long> comparisonCount = ThreadLocal.withInitial(() -> 0L);
    private static final ThreadLocal<Long> allocationCount = ThreadLocal.withInitial(() -> 0L);

    public static void reset() {
        recursionDepth.get().set(0);
        maxRecursionDepth.set(0);
        comparisonCount.set(0L);
        allocationCount.set(0L);
    }

    public static void enterRecursion() {
        int depth = recursionDepth.get().incrementAndGet();
        if (depth > maxRecursionDepth.get()) {
            maxRecursionDepth.set(depth);
        }
    }

    public static void exitRecursion() {
        recursionDepth.get().decrementAndGet();
    }

    public static void recordComparison() {
        comparisonCount.set(comparisonCount.get() + 1);
    }

    public static void recordAllocation() {
        allocationCount.set(allocationCount.get() + 1);
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth.get();
    }

    public static long getComparisonCount() {
        return comparisonCount.get();
    }

    public static long getAllocationCount() {
        return allocationCount.get();
    }

    public static class Snapshot {
        public final long timeNanos;
        public final int maxDepth;
        public final long comparisons;
        public final long allocations;

        public Snapshot(long timeNanos, int maxDepth, long comparisons, long allocations) {
            this.timeNanos = timeNanos;
            this.maxDepth = maxDepth;
            this.comparisons = comparisons;
            this.allocations = allocations;
        }
    }
}