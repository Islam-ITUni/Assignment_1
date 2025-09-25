package dnc;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.Assertions; 
import dnc.metrics.Metrics;
import java.util.Random;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*; 

class AlgorithmTest {
    private final Random random = new Random(42);
    
    @Test 
    void testMergeSort() {
        int[] array = generateRandomArray(1000);
        int[] copy = array.clone();
        
        MergeSort.sort(array);
        assertTrue(isSorted(array));
        
        Arrays.sort(copy);
        assertArrayEquals(copy, array);
        
        assertTrue(Metrics.getMaxRecursionDepth() <= 2 * log2(1000) + 10);
    }
    
    @Test 
    void testQuickSort() {
        int[] array = generateRandomArray(1000);
        int[] copy = array.clone();
        
        QuickSort.sort(array);
        assertTrue(isSorted(array));
        
        Arrays.sort(copy);
        assertArrayEquals(copy, array);
        
        assertTrue(Metrics.getMaxRecursionDepth() <= 2 * log2(1000) + 10);
    }
    
    @Test 
    void testDeterministicSelect() {
        int[] array = generateRandomArray(1000);
        
        for (int k = 0; k < 100; k++) {
            int kth = random.nextInt(array.length);
            int result = DeterministicSelect.select(array.clone(), kth);
            
            int[] sorted = array.clone();
            Arrays.sort(sorted);
            assertEquals(sorted[kth], result);
        }
    }
    
    @Test 
    void testClosestPair() {
        Point[] points = generateRandomPoints(100);
        double result = ClosestPair.findClosestDistance(points);
        double expected = bruteForceClosest(points);
        
        assertEquals(expected, result, 1e-9);
    }
    
    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
    
    private Point[] generateRandomPoints(int size) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
        }
        return points;
    }
    
    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    private double bruteForceClosest(Point[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
    
    private int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}