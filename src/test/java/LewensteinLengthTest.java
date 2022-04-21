import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LewensteinLengthTest {
    private EditDistance editDistance;

    @BeforeEach
    void setUp() {
        editDistance = new LewensteinDistance();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "DISTANCE,editing,distance,EDITING",
            "dIsTaNcE,EdItInG,DiStAnCe,eDiTiNg",
            "a,B,a,B",
            "a,a,a,A",
            "b,C,c,B"
    })
    void assert_equality_of_any_cases_check(String s1, String s2, String ss1, String ss2) {
        assertEquals(editDistance.calculateLength(s1, s2),
                editDistance.calculateLength(ss1, ss2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'', '', 0",
            "'', abcdef, 6",
            "abcdef, '', 6"
    })
    void calculate_with_empty_strings(String s1, String s2, int expected) {
        assertEquals(editDistance.calculateLength(s1, s2), expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "123   123\t123, 123  \t\t123  123, 0",
            "\tabcdef\t, \r\rABCDEF\t\t ,0",
            "a b c, a     bc, 1"
    })
    void check_many_spaces(String s1, String s2, int expected) {
        assertEquals(editDistance.calculateLength(s1, s2), expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Java is to JavaScript what car is to Carpet, Is Java and JavaScript same?, 31",
            "казнить нельзя помиловать, помиловать нельзя казнить, 22",
            "intention to repentance, execution of the sentence, 18"
    })
    void check_difficult_sentences(String s1, String s2, int expected) {
        assertEquals(editDistance.calculateLength(s1, s2), expected);
    }
}