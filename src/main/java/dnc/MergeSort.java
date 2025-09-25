package dnc;
import dnc.metrics.Metrics;
import dnc.util.ArrayUtils;

public class MergeSort {
    private static final int INSERTION_SORT_CUTOFF = 16;
    private static int[] buffer;

    public static void sort(int[] array) {
        Metrics.reset();
        buffer = new int[array.length];
        Metrics.recordAllocation();
        sort(array, 0, array.length - 1);
        buffer = null;
    }

    private static void sort(int[] array, int left, int right) {
        Metrics.enterRecursion();
        if (right - left <= INSERTION_SORT_CUTOFF) {
            ArrayUtils.insertionSort(array, left, right);
            Metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
        Metrics.exitRecursion();
    }

    private static void merge(int[] array, int left, int mid, int right) {
        System.arraycopy(array, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            Metrics.recordComparison();
            if (buffer[i] <= buffer[j]) array[k++] = buffer[i++];
            else array[k++] = buffer[j++];
        }
        while (i <= mid) array[k++] = buffer[i++];
        while (j <= right) array[k++] = buffer[j++];
    }
}// Edge case handling: duplicates, empty arrays, single elements
