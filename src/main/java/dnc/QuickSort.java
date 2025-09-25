package dnc;
import dnc.metrics.Metrics;
import dnc.util.ArrayUtils;
import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();
    private static final int INSERTION_SORT_CUTOFF = 10;

    public static void sort(int[] array) {
        Metrics.reset();
        ArrayUtils.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right) {
        Metrics.enterRecursion();
        while (right > left) {
            if (right - left <= INSERTION_SORT_CUTOFF) {
                ArrayUtils.insertionSort(array, left, right);
                Metrics.exitRecursion();
                return;
            }

            int pivotIndex = partition(array, left, right);
            if (pivotIndex - left < right - pivotIndex) {
                sort(array, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                sort(array, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
        Metrics.exitRecursion();
    }

    private static int partition(int[] array, int left, int right) {
        int pivotIndex = left + RANDOM.nextInt(right - left + 1);
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