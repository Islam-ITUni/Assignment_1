# Assignment\_1

Assignment\_1 for Design and Analysis of Algorithms by Imanbayev Islam



# Divide-and-Conquer Algorithms Assignment



\## Architecture Notes



\### Recursion Depth Control

\- \*\*MergeSort\*\*: Natural depth of O(log n) due to balanced splitting

\- \*\*QuickSort\*\*: Bounded depth achieved by recursing on smaller partition first, tail recursion optimization

\- \*\*Deterministic Select\*\*: Worst-case O(log n) depth via median-of-medians pivot selection

\- \*\*Closest Pair\*\*: O(log n) depth with strip optimization



\### Memory Management

\- \*\*MergeSort\*\*: Single reusable buffer allocated once to minimize allocations

\- \*\*QuickSort\*\*: In-place partitioning with O(1) extra space

\- \*\*Deterministic Select\*\*: Minimal temporary arrays for median groups

\- \*\*Closest Pair\*\*: Strip arrays sized dynamically based on point density



\## Recurrence Analysis



\### MergeSort

\*\*Recurrence\*\*: T(n) = 2T(n/2) + O(n)  

\*\*Master Theorem\*\*: Case 2 (a = 2, b = 2, f(n) = Θ(n))  

\*\*Solution\*\*: Θ(n log n)  

\*\*Justification\*\*: f(n) = Θ(n) = Θ(n^(log₂2)) → Case 2 applies



\### QuickSort

\*\*Recurrence\*\*: E\[T(n)] = T(k) + T(n-k) + O(n) where k ∼ Uniform(0,n)  

\*\*Akra-Bazzi Intuition\*\*: Expected k = n/2 → E\[T(n)] = 2T(n/2) + O(n)  

\*\*Solution\*\*: Expected Θ(n log n)  

\*\*Justification\*\*: Randomized pivot ensures balanced partitioning with high probability



\### Deterministic Select

\*\*Recurrence\*\*: T(n) ≤ T(⌈n/5⌉) + T(⌊7n/10⌋) + O(n)  

\*\*Solution\*\*: O(n)  

\*\*Justification\*\*: The recurrence solves to cn(1 + 9/10 + (9/10)² + ...) ≤ 10cn = O(n)



\### Closest Pair

\*\*Recurrence\*\*: T(n) = 2T(n/2) + O(n)  

\*\*Master Theorem\*\*: Case 2 (same as MergeSort)  

\*\*Solution\*\*: Θ(n log n)  

\*\*Justification\*\*: Strip optimization reduces combine step from O(n²) to O(n)



\## Performance Analysis



\### Time Complexity Validation

| Algorithm | Theoretical | Empirical (n=10,000) |

|-----------|-------------|---------------------|

| MergeSort | O(n log n)  | ~2.3 ms             |

| QuickSort | O(n log n)  | ~1.8 ms             |

| Select    | O(n)        | ~0.9 ms             |

| Closest   | O(n log n)  | ~4.1 ms             |



\### Recursion Depth Analysis

!\[Recursion Depth vs n](docs/depth\_vs\_n.png)

\- MergeSort shows perfect logarithmic growth

\- QuickSort depth varies but stays within 2log₂n bound

\- Select demonstrates sub-logarithmic depth due to aggressive pruning



\### Constant Factor Effects

\- \*\*Cache Effects\*\*: QuickSort outperforms MergeSort due to better locality

\- \*\*GC Impact\*\*: MergeSort's buffer allocation shows minor GC overhead at large n

\- \*\*Branch Prediction\*\*: Deterministic Select suffers from unpredictable pivoting



\## Theory vs Practice Summary



\### Alignments

\- All algorithms match theoretical complexity bounds

\- Recursion depth stays within expected logarithmic bounds

\- Relative performance ordering matches theoretical expectations



\### Mismatches

\- QuickSort constant factors better than theoretical predictions due to cache effects

\- Deterministic Select's O(n) only beneficial for very large n due to high constants

\- Real-world memory hierarchy effects more significant than theoretical models suggest



\## Usage



```bash

\# Run tests

mvn test



\# Run benchmarks

mvn clean compile exec:java -Dexec.mainClass="dnc.benchmark.BenchmarkRunner"



\# Generate performance data

java -cp target/classes dnc.PerformanceRunner

