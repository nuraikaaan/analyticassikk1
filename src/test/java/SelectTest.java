import algo.Select;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class SelectTest {
    @Test
    public void testSelectMatchesSort() {
        Random rand = new Random();
        for (int trial = 0; trial < 100; trial++) {
            int[] a = rand.ints(50, 0, 1000).toArray();
            int k = rand.nextInt(a.length);
            int expected = Arrays.stream(a).sorted().toArray()[k];
            int actual = Select.select(a.clone(), k);
            assertEquals(expected, actual);
        }
    }
}
