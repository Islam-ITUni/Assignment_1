# Divide and Conquer Algorithms  

## Project Overview  
This project contains my implementation of four classic divide-and-conquer algorithms. Each one was built from scratch with careful recursion handling and performance tracking. The goal was not only correctness, but also understanding their behavior in practice.  

## Algorithms Implemented  

### MergeSort  
- Stable sorting algorithm.  
- Splits the array, recursively sorts, and merges.  
- Uses **insertion sort** for small arrays to improve efficiency.  
- Reuses a single buffer to reduce memory allocations.  

### QuickSort  
- In-place sorting with randomized pivot selection.  
- Always recurses into the **smaller partition first** to control recursion depth.  
- Expected recursion depth remains O(log n).  

### Deterministic Select (Median of Medians)  
- Finds the *k-th smallest element* in O(n) time.  
- Groups elements in fives and uses the **median-of-medians** pivot.  
- Recurses only into the side that contains the element, guaranteeing performance.  

### Closest Pair of Points (2D)  
- Solves the closest pair problem in O(n log n) time.  
- Sorts points by x, splits recursively, and checks a vertical strip ordered by y.  
- Uses the classic 7–8 neighbor scan for efficiency.  
  

### Recursion Management  

- **MergeSort**: balanced recursion depth O(log n).  
- **QuickSort**: smaller-first recursion + tail recursion optimization.  
- **Deterministic Select**: recursion limited by median-of-medians pivoting.  
- **Closest Pair**: logarithmic recursion depth with strip checks.  

### Performance Tracking  

A custom **metrics system** was added to measure:  
- Execution time  
- Number of comparisons  
- Memory allocations  
- Maximum recursion depth  

This made it possible to compare theoretical results with empirical ones.  

## Testing Strategy  

- Verified sorting algorithms against `Arrays.sort` on random and adversarial arrays.  
- Checked recursion depth bounds for QuickSort and MergeSort.  
- For Deterministic Select, compared results with sorted arrays across many trials.  
- For Closest Pair, validated against a brute-force implementation on small inputs.  
- Included edge-case tests: empty arrays, single-element arrays, minimal inputs.  


## Performance Analysis  

**Theoretical vs practical findings:**  
- **MergeSort**: stable O(n log n), predictable performance.  
- **QuickSort**: typically fastest in practice due to cache efficiency.  
- **Deterministic Select**: guarantees O(n), but higher constant factors make it slower on small n.  
- **Closest Pair**: efficient O(n log n), strip optimization works as expected.  

## Project Structure  

src/
├── main/java/dnc/
│ ├── mergesort/
│ ├── quicksort/
│ ├── select/
│ ├── closestpair/
│ ├── metrics/
│ └── util/
└── test/java/dnc/
└── AlgorithmTest.java


## Usage  

To compile and test the project:  
```bash
mvn compile
mvn test
