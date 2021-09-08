package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class InterfaceTest {

    @Test
    public void useObjectByItsInterface() throws Exception {
        Interface object = new InterfaceImplementation();

        String value = useInterface(object);

        System.out.println(value);
        Assertions.assertThat(value)
                .isEqualTo("method implemented in InterfaceImplementation");
    }

    private String useInterface(Interface object) {
        return object.method();
    }
}
