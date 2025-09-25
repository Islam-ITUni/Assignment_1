package dnc;
import dnc.metrics.Metrics;
import dnc.util.ArrayUtils;

public class DeterministicSelect {
    public static int select(int[] array, int k) {
        if (k < 0 || k >= array.length) throw new IllegalArgumentException("k out of bounds");
        Metrics.reset();
        return select(array, 0, array.length - 1, k);
    }

    private static int select(int[] array, int left, int right, int k) {
        Metrics.enterRecursion();
        while (true) {
            if (left == right) {
                Metrics.exitRecursion();
                return array[left];
            }

            int pivotIndex = medianOfMedians(array, left, right);
            pivotIndex = partition(array, left, right, pivotIndex);

            if (k == pivotIndex) {
                Metrics.exitRecursion();
                return array[k];
            } else if (k < pivotIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }
    }

    private static int medianOfMedians(int[] array, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) return medianOfFive(array, left, right);

        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];
        Metrics.recordAllocation();

        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);
            medians[i] = medianOfFive(array, groupLeft, groupRight);
        }
        return select(medians, 0, numGroups - 1, numGroups / 2);
    }

    private static int medianOfFive(int[] array, int left, int right) {
        ArrayUtils.insertionSort(array, left, right);
        return left + (right - left) / 2;
    }

    private static int partition(int[] array, int left, int right, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        ArrayUtils.swap(array, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            Metrics.recordComparison();
            if (array[i] < pivotValue) {
                ArrayUtils.swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        ArrayUtils.swap(array, storeIndex, right);
        return storeIndex;
    }
}