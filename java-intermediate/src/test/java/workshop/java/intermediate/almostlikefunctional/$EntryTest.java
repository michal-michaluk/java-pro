package workshop.java.intermediate.almostlikefunctional;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleUnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.offset;

public class $EntryTest {

    // Task 1.
    // write VAT (Value Added Tax) calculator,
    // working for regions PL (23%), DE (19%) and TaxFree (0%)
    // no other rules need to be considered

    public static class TaxCalculator {

        private final Map<String, DoubleUnaryOperator> regions = new HashMap<>();

        public TaxCalculator() {
            regions.put("PL", price -> price * 1.23);
            regions.put("DE", price -> price * 1.19);
            regions.put("TaxFree", price -> price);
        }

        public DoubleUnaryOperator forRegion(String region) {
            return Optional.ofNullable(regions.get(region))
                    .orElseGet(() -> (d) -> {
                        throw new IllegalArgumentException("unsupported region: " + region);
                    });
        }
    }

    @Test
    public void testForPL() throws Exception {
        TaxCalculator tax = new TaxCalculator();

        Double price = tax.forRegion("PL").applyAsDouble(159.99);

        assertThat(price).isCloseTo(196.78, offset(0.01));
    }

    @Test
    public void notExistingRegion() throws Exception {
        TaxCalculator tax = new TaxCalculator();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> tax.forRegion("NotExistingOne")
                                .applyAsDouble(10.0));
    }

    // Task 2.
    // write custom comparator for sting,
    // handling properly polish diacritic characters
    //


}
