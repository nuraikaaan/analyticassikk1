import algo.ClosestPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class ClosestPairTest {
    @Test
    public void testAgainstBruteForce() {
        Random rand = new Random();
        ClosestPair.Point[] pts = new ClosestPair.Point[100];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(rand.nextInt(1000), rand.nextInt(1000));
        }

        double fast = ClosestPair.findClosestPair(pts);
        double slow = bruteForce(pts);
        assertEquals(slow, fast, 1e-6);
    }

    private double bruteForce(ClosestPair.Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double d = Math.hypot(pts[i].x - pts[j].x, pts[i].y - pts[j].y);
                if (d < min) min = d;
            }
        }
        return min;
    }
}
