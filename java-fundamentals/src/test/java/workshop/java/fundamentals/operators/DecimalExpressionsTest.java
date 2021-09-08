package workshop.java.fundamentals.operators;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;

public class DecimalExpressionsTest {

    @Test
    public void unaryOperators() throws Exception {
        double a = 1.2;

        Assertions.assertThat(-a).isEqualTo(-1.2);
    }

    @Test
    public void binaryOperators() throws Exception {
        double a = 2;
        double b = 3;

        Assertions.assertThat(a + b).isEqualTo(5);

        Assertions.assertThat(a - b).isEqualTo(-1);

        Assertions.assertThat(a * b).isEqualTo(6);

        Assertions.assertThat(b / a).isEqualTo(1.5);
    }

    @Test
    public void expressions() throws Exception {
        Assertions.assertThat(12.0 + 4.0 * 2.0).isEqualTo(20.0);

        // 0. parentheses are strongest
        Assertions.assertThat((12.0 + 4.0) * 2.0).isEqualTo(32.0);

        // 2. unary operators
        double b = 2.0;
        Assertions.assertThat(12.0 + (4.0 * -b)).isEqualTo(4.0);

        // 3. * % /
        Assertions.assertThat(12.0 + 4.0 * 2.0).isEqualTo(20.0);

        // 4. + -
        Assertions.assertThat(12.0 + 4.0 * 2.0).isEqualTo(20.0);

        // 5. ternary operator

        // 6. assignments
    }

    @Test
    public void differentTypesOfDecimals() throws Exception {
        // lowest precision
        float f = 2.2F;
        // better but still not enough for money calculations
        double d = 2.2;

        // only option in java to make money calculations is BigDecimal
        BigDecimal big = new BigDecimal("2.2", MathContext.DECIMAL32);

        System.out.println(d);
        System.out.println(f);
        System.out.println(big);
    }
}
