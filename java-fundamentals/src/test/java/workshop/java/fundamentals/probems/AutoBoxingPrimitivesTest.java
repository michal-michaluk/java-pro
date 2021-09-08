package workshop.java.fundamentals.probems;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AutoBoxingPrimitivesTest {

    public Object takeObject(Object object) {
        System.out.println(object);
        return object;
    }

    @Test
    public void primitivesUsedAsObjects() throws Exception {

        // primitive variables
        int i = 0;
        boolean b = true;
        double d = 1.0;
        float f = 1.0F;

        // primitives are not objects in java
        // but we can pass primitive to takeObject method
        // that "feature" is called autoboxing
        takeObject(i);
        takeObject(f);
        takeObject(d);
        takeObject(b);
    }

    @Test
    public void wrapperClassesForPrimitives() throws Exception {

        // autoboxing will wrap each primitive with wrapper object
        // like that:
        Integer iObject = new Integer(0);
        Boolean bObjecy = new Boolean(true);
        Double dObject = new Double(1.0);
        Float fObject = new Float(1.0F);

        // compiler is doing that for us
    }

    @Test
    public void testPassingByValue() {
        int value = 4;
        takePrimitive(value);
        Assertions.assertThat(value).isEqualTo(4);
    }

    public void takePrimitive(int value) {
        value = 2; // override local variable
    }

    @Test
    public void testPassingObject() throws Exception {
        Integer value = new Integer(4);
        takeObject(value);
        Assertions.assertThat(value).isEqualTo(new Integer(4));
    }

    @Test
    public void testPassCollection() throws Exception {
        List<Integer> empty = new ArrayList<>();
        takeListAndAddElement(empty);
        Assertions.assertThat(empty).isNotEmpty().contains(12);
    }

    private void takeListAndAddElement(List<Integer> list) {
        list.add(12);
    }
}
