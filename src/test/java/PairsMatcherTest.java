import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PairsMatcherTest {
    private PairsMatcher pairsMatcher;
    private EditDistance editDistance;

    @BeforeEach
    void setUp() {
        editDistance = new LewensteinDistance();
        pairsMatcher = new PairsMatcher(editDistance);
    }


    /**
     * Asserts that editingDistance(str1, str2) <= editingDistance(str1, str i)
     */
    @Test
    void check_basic() {
        List<String> entries1 = List.of("distance", "life is a box of chocolates", "hello everybody");
        List<String> entries2 = List.of("chocolate gift box", "editing", "hello world", "never gonna give you up");

        List<Pair<String, String>> pairs = pairsMatcher.match(entries1, entries2);

        for (Pair<String, String> pair : pairs) {
            String first = pair.getKey(), second = pair.getValue();
            if (first == null)
                continue;
            int value = editDistance.calculateLength(first, second);

            long cnt = pairs.stream()
                    .filter(s -> s.getKey() != null)
                    .filter(s -> !s.getKey().equals(first))
                    .filter(s -> editDistance.calculateLength(first, s.getValue()) < value)
                    .count();

            assertEquals(0, cnt);
        }
    }

    @Test
    void check_n_less_than_m() {
        List<String> entries1 = List.of("distance", "life is a box of chocolates", "hello everybody");
        List<String> entries2 = List.of("life is a box of chocolates", "distance", "one more", "hello everybody");

        List<Pair<String, String>> pairs = pairsMatcher.match(entries1, entries2);

        assertAll(() -> {
            assertEquals(pairs.get(0).getKey(), pairs.get(0).getValue());
            assertEquals(pairs.get(1).getKey(), pairs.get(1).getValue());
            assertEquals(pairs.get(2).getKey(), pairs.get(2).getValue());
            assertNull(pairs.get(3).getKey());
            assertEquals(pairs.get(3).getValue(), "one more");
        });
    }

    @Test
    void check_n_greater_than_m() {
        List<String> entries1 = List.of("life is a box of chocolates", "distance", "one more", "hello everybody");
        List<String> entries2 = List.of("distance", "life is a box of chocolates", "hello everybody");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> pairsMatcher.match(entries1, entries2)
        );

        String expectedMessage = "Invalid arguments order";
        assertEquals(exception.getMessage(), expectedMessage);
    }
}
