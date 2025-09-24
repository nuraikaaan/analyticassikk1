Assignment 1

# Architecture Notes

1. MergeSort uses a linear merge strategy with a reusable buffer allocated once and passed through recursive calls. For small arrays (n ≤ 16), it switches to insertion sort to reduce overhead. Recursion depth is tracked via Metrics.maxDepth.

2. QuickSort employs randomized pivot selection (via array shuffling) to avoid worst-case inputs. It recurses on the smaller partition and iterates over the larger one, keeping the call stack bounded to approximately O(log n). The algorithm is in-place, minimizing memory allocations.

3. Select follows the Median-of-Medians strategy: grouping elements by 5, selecting the median of medians as pivot, and partitioning in-place. It recurses only into the necessary side, preferring the smaller one to control depth and reduce comparisons.

4. Closest Pair uses a divide-and-conquer approach with sorting by x and y, recursive splitting, and a “strip” check sorted by y. The strip scan compares at most 7–8 neighbors, significantly reducing the number of distance calculations. The algorithm avoids extra allocations beyond sorting.



# Recurrence Analysis

# MergeSort

Recurrence: T(n) = 2T(n/2) + Θ(n)

Using Master Theorem Case 2:

a = 2, b = 2, f(n) = Θ(n) → T(n) = Θ(n log n) 
Measurements confirm logarithmic depth and linear comparisons per level.



# QuickSort

Average-case recurrence: T(n) = T(n/4) + T(3n/4) + Θ(n)

Master Theorem Case 2 applies:
T(n) = Θ(n log n)

Stack depth is bounded due to the smaller-first recursion strategy, typically staying below 20 for n = 10000.



# Select

Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n)

Solved via Akra–Bazzi: T(n) = Θ(n)

Despite linear asymptotics, constant factors are high due to grouping and recursive median selection. Metrics show stable comparison counts, but higher than sorting.



# Closest Pair

Recurrence: T(n) = 2T(n/2) + Θ(n)

Master Theorem Case 2:
T(n) = Θ(n log n)

Metrics confirm logarithmic depth and near-linear comparison growth. The strip scan effectively limits pairwise checks.



# Plots and Constant-Factor Effects

1. n vs comparisons — shows how operations scale with input size.

2. n vs maxDepth — illustrates recursion control.

3. n vs allocations — highlights memory usage, especially for MergeSort and Select. 


![Снимок экрана 2025-09-24 в 2.52.08 PM.png](../../../../../../var/folders/f7/ychrrnl9023ddnbh8kj2_ww00000gn/T/TemporaryItems/NSIRD_screencaptureui_hycrEX/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202025-09-24%20%D0%B2%202.52.08%E2%80%AFPM.png)

1. QuickSort shows deeper recursion but fewer allocations due to in-place sorting.

2. Select behaves linearly but with higher constants due to its deterministic pivot strategy.

3. MergeSort is stable in depth and allocations thanks to buffer reuse and cutoff.

4. Closest Pair scales logarithmically and maintains low comparison counts.

# Additional effects:

- Cache behavior benefits MergeSort and Closest Pair due to sequential memory access.
- Garbage collection may impact Select due to temporary array usage in grouping. 

#Summary:
- This project demonstrates the full lifecycle of algorithmic development: from theoretical design to practical implementation, testing, and performance analysis. Four classic divide-and-conquer algorithms were implemented—MergeSort, QuickSort, Deterministic Select, and Closest Pair of Points—each with careful attention to recursion safety, memory efficiency, and asymptotic behavior.
- Theoretical analysis using the Master Theorem and Akra–Bazzi method provided expected time complexities:
- MergeSort and Closest Pair follow Θ(n log n) growth.
- QuickSort achieves Θ(n log n) on average with bounded stack depth.
- Select operates in linear time Θ(n), though with higher constant factors.
- Metrics collected during execution—including comparisons, recursion depth, and memory allocations—confirmed these asymptotic predictions. Graphs showed logarithmic depth for MergeSort and Closest Pair, linear growth for Select, and efficient in-place behavior for QuickSort. Minor deviations were attributed to constant-factor effects such as cache locality, memory reuse, and garbage collection.
- All algorithms passed correctness tests, including adversarial inputs and randomized trials. The Git workflow was structured around feature branches and clean commit history, culminating in a stable release.
- Overall, the project validates that theoretical guarantees can be achieved in practice when algorithms are implemented with attention to detail and measured rigorously. The alignment between mathematical models and empirical data reinforces the reliability and efficiency of these classic strategies. 


# Test
- Used JUnit 5 (org.junit.jupiter.api.Test)
Each algorithm has its own test class:
1. MergeSortTest 
2. QuickSortTest 
3. SelectTest 
4. ClosestPairTest
 ![Снимок экрана 2025-09-24 в 3.27.00 PM.png](../../../../../../var/folders/f7/ychrrnl9023ddnbh8kj2_ww00000gn/T/TemporaryItems/NSIRD_screencaptureui_YZy3lp/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202025-09-24%20%D0%B2%203.27.00%E2%80%AFPM.png)