import algo.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {
    @Test
    public void testCorrectness() {
        int[] a = new Random().ints(100, 0, 1000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty);
        assertArrayEquals(new int[]{}, empty);

        int[] one = {42};
        MergeSort.sort(one);
        assertArrayEquals(new int[]{42}, one);
    }
}
