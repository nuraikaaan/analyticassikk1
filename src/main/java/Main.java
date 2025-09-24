import algo.*;
import util.*;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 500, 1000, 5000, 10000};
        CSVWriter writer = new CSVWriter("metrics.csv");

        for (int n : sizes) {
            int[] a = new Random().ints(n, 0, 100000).toArray();

            Metrics.reset();
            int[] copy = a.clone();
            MergeSort.sort(copy);
            writer.write("MergeSort", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

            Metrics.reset();
            copy = a.clone();
            QuickSort.sort(copy);
            writer.write("QuickSort", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

            Metrics.reset();
            copy = a.clone();
            Select.select(copy, n / 2);
            writer.write("Select", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

            Metrics.reset();
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(rand.nextInt(100000), rand.nextInt(100000));
            }
            double minDist = ClosestPair.findClosestPair(pts);
            writer.write("ClosestPair", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);
        }

        writer.close();
    }
}
