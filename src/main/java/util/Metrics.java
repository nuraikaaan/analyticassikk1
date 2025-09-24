package util;

public class Metrics {
    public static int comparisons = 0;
    public static int allocations = 0;
    public static int maxDepth = 0;
    private static int currentDepth = 0;

    public static void enter() {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);
    }

    public static void exit() {
        currentDepth--;
    }

    public static void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
    }
}
