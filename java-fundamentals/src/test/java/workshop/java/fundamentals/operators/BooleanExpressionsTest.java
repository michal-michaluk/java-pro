package workshop.java.fundamentals.operators;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class BooleanExpressionsTest {

    @Test
    public void unaryOperators() throws Exception {
        boolean variable = true;

        // negations operator !
        Assertions.assertThat(! variable).isFalse();

        // negation is not changing value of variable
        Assertions.assertThat(variable).isTrue();
    }

    @Test
    public void binaryOperators() throws Exception {
        // logical and
        Assertions.assertThat(true && true).isTrue();
        Assertions.assertThat(true && false).isFalse();
        Assertions.assertThat(false && true).isFalse();
        Assertions.assertThat(false && false).isFalse();

        // logical or
        Assertions.assertThat(false || false).isFalse();
        Assertions.assertThat(true || true).isTrue();
        Assertions.assertThat(true || false).isTrue();
        Assertions.assertThat(false || true).isTrue();
    }

    @Test
    public void ternaryOperator() throws Exception {

        // condition ? in-case-of-true : in-case-of-false;

        int a = true ? 1 : 2;
        int b = false ? 1 : 2;

        Assertions.assertThat(a).isEqualTo(1);
        Assertions.assertThat(b).isEqualTo(2);
    }

    @Test
    public void ternaryOperatorWithComputation() throws Exception {
        int a = 2;
        int b = 5;

        int c = true ? (1 + 2) : a + b;

        // instead of
        // int c;
        // if (true) {
        //     c = (1 + 2);
        // } else {
        //     c = a + b;
        // }

        Assertions.assertThat(c).isEqualTo(3);
    }

    @Test
    public void operatorPriorities() throws Exception {

        // 0. parentheses are always strongest
        Assertions.assertThat((true || false) && true)
                .isEqualTo(true);

        // 1. negation
        Assertions.assertThat(!false && true)
                .isEqualTo((!false) && true);

        // 3. &&
        Assertions.assertThat(true || false && true)
                .isEqualTo(true || (false && true));

        // 4. ||
        Assertions.assertThat(true || false && true)
                .isEqualTo(true || (false && true));

        // 5. ternary operator :-)
        Assertions.assertThat(true || false && true ? true || false : false)
                .isEqualTo(true);

        // 6. assignments are last

        boolean c = true || false && true;
        Assertions.assertThat(c).isEqualTo((true || false && true));
    }
}
