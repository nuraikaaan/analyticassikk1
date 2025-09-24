package algo;

import util.Metrics;
import util.ArrayUtils;

import java.util.Random;

public class QuickSort {
    public static void sort(int[] a) {
        ArrayUtils.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        while (lo < hi) {
            Metrics.enter();
            int pivot = partition(a, lo, hi);
            if (pivot - lo < hi - pivot) {
                sort(a, lo, pivot - 1);
                lo = pivot + 1;
            } else {
                sort(a, pivot + 1, hi);
                hi = pivot - 1;
            }
            Metrics.exit();
        }
    }

    public static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            Metrics.comparisons++;
            if (a[j] < pivot) {
                ArrayUtils.swap(a, i, j);
                i++;
            }
        }
        ArrayUtils.swap(a, i, hi);
        return i;
    }
}
