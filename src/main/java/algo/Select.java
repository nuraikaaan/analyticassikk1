package algo;

import util.Metrics;
import util.ArrayUtils;

public class Select {
    public static int select(int[] a, int k) {
        return select(a, 0, a.length - 1, k);
    }

    private static int select(int[] a, int lo, int hi, int k) {
        while (true) {
            if (lo == hi) return a[lo];
            int pivot = medianOfMedians(a, lo, hi);
            int pivotIndex = partition(a, lo, hi, pivot);
            int rank = pivotIndex - lo;

            Metrics.comparisons++;
            if (k == rank) return a[pivotIndex];
            else if (k < rank) hi = pivotIndex - 1;
            else {
                k -= rank + 1;
                lo = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(a, subLo, subHi);
            ArrayUtils.swap(a, lo + i, subLo + (subHi - subLo) / 2);
        }
        return select(a, lo, lo + numGroups - 1, numGroups / 2);
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                Metrics.comparisons++;
                a[j + 1] = a[j--];
            }
            a[j + 1] = key;
        }
    }

    public static int partition(int[] a, int lo, int hi, int pivot) {
        for (int i = lo; i <= hi; i++) {
            if (a[i] == pivot) {
                ArrayUtils.swap(a, i, hi);
                break;
            }
        }
        return QuickSort.partition(a, lo, hi);
    }
}
