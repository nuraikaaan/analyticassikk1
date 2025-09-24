package algo;
import util.Metrics;
import java.util.*;

public class ClosestPair {
    public static class Point {
        public final int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosestPair(Point[] points) {
        Metrics.enter();
        Point[] sortedByX = points.clone();
        Point[] sortedByY = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingInt(p -> p.x));
        Arrays.sort(sortedByY, Comparator.comparingInt(p -> p.y));
        double result = closest(sortedByX, sortedByY, 0, points.length - 1);
        Metrics.exit();
        return result;
    }

    private static double closest(Point[] px, Point[] py, int lo, int hi) {
        if (hi - lo <= 3) {
            return bruteForce(px, lo, hi);
        }

        int mid = (lo + hi) / 2;
        int midX = px[mid].x;

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midX).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midX).toArray(Point[]::new);

        double dl = closest(px, pyl, lo, mid);
        double dr = closest(px, pyr, mid + 1, hi);
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (Point p : py) {
            if (Math.abs(p.x - midX) < d) {
                strip.add(p);
            }
        }

        double minStrip = stripClosest(strip, d);
        return Math.min(d, minStrip);
    }

    private static double bruteForce(Point[] points, int lo, int hi) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                Metrics.comparisons++;
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                Metrics.comparisons++;
                double dist = distance(strip.get(i), strip.get(j));
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    private static double distance(Point a, Point b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
