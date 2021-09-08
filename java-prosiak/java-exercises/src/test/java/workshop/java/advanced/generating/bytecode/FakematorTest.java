package workshop.java.advanced.generating.bytecode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * Created by michal on 10.01.2017.
 */
public class FakematorTest {

    public static class DooingSomething {

        public String doHardStuff() {
            return "hallo world";
        }
    }

    @Test
    public void testHardStuff() throws Exception {
        DooingSomething fake = new DooingSomething();

        Assertions.assertThat(fake.doHardStuff())
                .isEqualTo("hallo world");
    }

    @Test
    @Disabled
    public void testMockedStuff() throws Exception {
        DooingSomething fake = Fakemator.makeFake(DooingSomething.class);

        Assertions.assertThat(fake.doHardStuff())
                .isEqualTo("Fakemator rulezz");
    }
}