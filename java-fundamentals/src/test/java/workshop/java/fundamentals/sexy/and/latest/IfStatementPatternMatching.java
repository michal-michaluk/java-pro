package workshop.java.fundamentals.sexy.and.latest;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Collection;

public class IfStatementPatternMatching {

    public int howBigItIs(Object argument) {
        if (argument instanceof String s) {
            return s.length();
        } else if (argument instanceof Number n) {
            return n.intValue();
        } else if (argument instanceof Collection c && c.isEmpty()) {
            return c.size();
        } else return -1;
    }

    @Test
    public void testHowBigItIs() {
        Assertions.assertThat(howBigItIs("short string")).isEqualTo(12);
        Assertions.assertThat(howBigItIs(12)).isEqualTo(12);
    }

}
