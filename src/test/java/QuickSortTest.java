import algo.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import util.Metrics;

public class QuickSortTest {
    @Test
    public void testCorrectness() {
        int[] a = new Random().ints(100, 0, 1000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);

        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testRecursionDepth() {
        int[] a = new Random().ints(1000, 0, 10000).toArray();
        Metrics.reset();
        QuickSort.sort(a);
        int expectedMax = (int)(2 * Math.log(a.length) / Math.log(2)) + 5;
        assertTrue(Metrics.maxDepth <= expectedMax);
    }
}
