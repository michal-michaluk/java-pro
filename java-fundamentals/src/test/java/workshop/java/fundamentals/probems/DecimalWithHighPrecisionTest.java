package workshop.java.fundamentals.probems;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DecimalWithHighPrecisionTest {

    @Test
    public void differentTypesOfDecimals() throws Exception {
        // lowest precision
        float f = 2.2F;
        // better but still not enough for money calculations
        double d = 2.2;

        // only option in java to make money calculations is BigDecimal
        BigDecimal big = new BigDecimal("2.2");

        System.out.println(d);
        System.out.println(f);
        System.out.println(big);
    }

//    @Test
//    public void expressionsWithBigDecimals() throws Exception {
//        BigDecimal a = new BigDecimal("2.4");
//        BigDecimal b = new BigDecimal("1.0");
//
//        // a.add(b) instead a + b
//        Assertions.assertThat(a.add(b))
//                .isCloseTo(new BigDecimal("3.4"), Offset.offset(new BigDecimal("0.01")));
//
//        // a.subtract(b) instead a - b
//        Assertions.assertThat(a.subtract(b))
//                .isCloseTo(new BigDecimal("1.4"), Offset.offset(new BigDecimal("0.01")));
//
//        // a.add(b.negate()) instead a + -b
//        Assertions.assertThat(a.add(b.negate()))
//                .isCloseTo(new BigDecimal("1.4"), Offset.offset(new BigDecimal("0.01")));
//
//        // a.multiply(b) instead a * b
//        Assertions.assertThat(a.multiply(b))
//                .isCloseTo(new BigDecimal("2.4"), Offset.offset(new BigDecimal("0.01")));
//
//        // a.divide(b) instead a / b
//        Assertions.assertThat(a.divide(b, BigDecimal.ROUND_HALF_DOWN))
//                .isCloseTo(new BigDecimal("2.4"), Offset.offset(new BigDecimal("0.01")));
//    }

    @Test
    public void bigDecimalMethodsAreImmutable() throws Exception {
        BigDecimal a = new BigDecimal("2.4");
        BigDecimal b = new BigDecimal("1.0");

        BigDecimal c = a.add(b);

        // so a is still 2.4
        Assertions.assertThat(a).isEqualTo(new BigDecimal("2.4"));

        // and c has sum of a + b
        Assertions.assertThat(c).isEqualTo(new BigDecimal("3.4"));
    }
}
