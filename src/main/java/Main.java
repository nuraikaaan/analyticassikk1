import algo.*;
import util.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose mode:\n1 - Run predefined sizes and write to CSV\n2 - Input your own array and run interactively");
        int mode = scanner.nextInt();

        if (mode == 1) {
            int[] sizes = {100, 500, 1000, 5000, 10000};
            CSVWriter writer = new CSVWriter("metrics.csv");

            for (int n : sizes) {
                int[] a = new Random().ints(n, 0, 100000).toArray();

                Metrics.reset();
                MergeSort.sort(a.clone());
                writer.write("MergeSort", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

                Metrics.reset();
                QuickSort.sort(a.clone());
                writer.write("QuickSort", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

                Metrics.reset();
                Select.select(a.clone(), n / 2);
                writer.write("Select", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);

                Metrics.reset();
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                Random rand = new Random();
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rand.nextInt(100000), rand.nextInt(100000));
                }
                ClosestPair.findClosestPair(pts);
                writer.write("ClosestPair", n, Metrics.comparisons, Metrics.allocations, Metrics.maxDepth);
            }

            writer.close();
            System.out.println("Metrics written to metrics.csv");

        } else if (mode == 2) {
            System.out.print("Enter array size: ");
            int n = scanner.nextInt();
            int[] a = new int[n];
            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            System.out.println("Choose algorithm:\n1 - MergeSort\n2 - QuickSort\n3 - Select (k-th smallest)\n4 - Closest Pair (2D)");
            int choice = scanner.nextInt();

            Metrics.reset();

            switch (choice) {
                case 1:
                    MergeSort.sort(a);
                    System.out.println("Sorted array: " + Arrays.toString(a));
                    break;
                case 2:
                    QuickSort.sort(a);
                    System.out.println("Sorted array: " + Arrays.toString(a));
                    break;
                case 3:
                    System.out.print("Enter k (0-based index): ");
                    int k = scanner.nextInt();
                    int result = Select.select(a, k);
                    System.out.println("k-th smallest element: " + result);
                    break;
                case 4:
                    ClosestPair.Point[] pts = new ClosestPair.Point[n];
                    System.out.println("Enter " + n + " points as x y:");
                    for (int i = 0; i < n; i++) {
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        pts[i] = new ClosestPair.Point(x, y);
                    }
                    double minDist = ClosestPair.findClosestPair(pts);
                    System.out.println("Closest distance: " + minDist);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println("Comparisons: " + Metrics.comparisons);
            System.out.println("Allocations: " + Metrics.allocations);
            System.out.println("Max recursion depth: " + Metrics.maxDepth);
        } else {
            System.out.println("Invalid mode.");
        }

        scanner.close();
    }
}
