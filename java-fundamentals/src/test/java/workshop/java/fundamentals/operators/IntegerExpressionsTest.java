package workshop.java.fundamentals.operators;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class IntegerExpressionsTest {

    @Test
    public void unaryOperators() throws Exception {
        int a = 0;

        // a++ is called suffix increment
        // current value is passed to assertThat method
        // value of variable a is incremented
        Assertions.assertThat(a++).isEqualTo(0);
        Assertions.assertThat(a).isEqualTo(1);

        // similar for suffix decrement operator
        Assertions.assertThat(a--).isEqualTo(1);
        Assertions.assertThat(a).isEqualTo(0);

        // ++a is different
        // first value of variable a is incremented
        // then new value is passed to assertThat method
        Assertions.assertThat(++a).isEqualTo(1);

        // and similar for increment operator
        Assertions.assertThat(--a).isEqualTo(0);

        a = 1;
        Assertions.assertThat(-a).isEqualTo(-1);
        // value of variable a is not change with that - (minus) operator
        Assertions.assertThat(-a).isEqualTo(-1);
    }


    @Test
    public void binaryOperators() throws Exception {
        final int a = 2;
        final int b = 3;

        // standard arithmetic operators

        Assertions.assertThat(a + b).isEqualTo(5);

        Assertions.assertThat(a - b).isEqualTo(-1);

        Assertions.assertThat(a * b).isEqualTo(6);

        Assertions.assertThat(b / a).isEqualTo(1);

        Assertions.assertThat(b % a).isEqualTo(1);
    }

    @Test
    public void moreOfModulusOperator() throws Exception {
        Assertions.assertThat(0 % 2).isEqualTo(0);
        Assertions.assertThat(1 % 2).isEqualTo(1);
        Assertions.assertThat(2 % 2).isEqualTo(0);
        Assertions.assertThat(3 % 2).isEqualTo(1);
        Assertions.assertThat(4 % 2).isEqualTo(0);
        Assertions.assertThat(5 % 2).isEqualTo(1);

        Assertions.assertThat(0 % 3).isEqualTo(0);
        Assertions.assertThat(1 % 3).isEqualTo(1);
        Assertions.assertThat(2 % 3).isEqualTo(2);
        Assertions.assertThat(3 % 3).isEqualTo(0);
    }

    @Test
    public void operatorPriorities() throws Exception {
        Assertions.assertThat(12 + 4 * 2).isEqualTo(20);

        // 0. parentheses are always strongest
        Assertions.assertThat((12 + 4) * 2).isEqualTo(32);

        // 1. suffix unary
        int a = 2;
        Assertions.assertThat(12 + 4 * a--).isEqualTo(20);

        // 2. unary operators
        int b = 2;
        Assertions.assertThat(12 + (4 * --b)).isEqualTo(16);

        // 3. * % /
        Assertions.assertThat(12 + 4 * 2).isEqualTo(20);

        // 4. + -
        Assertions.assertThat(12 + 4 * 2).isEqualTo(20);

        // 5. ternary operator

        // 6. assignments are last

        int c = 12 + 4 * 2;
        Assertions.assertThat(c).isEqualTo(20);
    }
}
