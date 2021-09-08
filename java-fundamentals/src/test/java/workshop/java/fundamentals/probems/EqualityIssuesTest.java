package workshop.java.fundamentals.probems;

import workshop.java.fundamentals.inheritance.Dog;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class EqualityIssuesTest {

    @Test
    public void equalityForPrimitives() throws Exception {

        // for primitives operator == is doing what we expect

        Assertions.assertThat(2 == 2).isTrue();
        Assertions.assertThat(2 == 1).isFalse();
        Assertions.assertThat(2 != 1).isTrue();

    }

    @Test
    public void equalityForObjects() throws Exception {
        Object object1 = new Object();
        Object object2 = new Object();
        Object anotherVariablePointingToObject1 = object1;

        // for objects operator == is comparing references

        Assertions.assertThat(object1 == object1).isTrue();
        Assertions.assertThat(object1 == object2).isFalse();
        Assertions.assertThat(object1 == anotherVariablePointingToObject1).isTrue();

    }

    @Test
    public void equalityForStrings() throws Exception {

        // strings are objects in java
        // for objects operator == is comparing references
        // operator == is evaluated to true if both objects are the same object
        String string = "single string object";
        Assertions.assertThat(string == string).isTrue();

        // "a" == "a" only because compiler optimisation
        Assertions.assertThat("a" == "a").isTrue();

        // here we see the issue
        Assertions.assertThat(new String("a") == new String("a")).isFalse();

    }

    @Test
    public void solution() throws Exception {

        // we should always use equals method
        String string = "single string object";

        // is True as expected
        Assertions.assertThat(string.equals(string)).isTrue();

        // is True as expected
        Assertions.assertThat("a".equals("a")).isTrue();

        // is True as expected
        Assertions.assertThat(new String("a").equals(new String("a"))).isTrue();
    }

    @Test
    public void semanticOfEquals() throws Exception {

        // if our class does not implement equals method
        // default implementation from Object.equals still compare references
        Dog saba1 = new Dog("Saba");
        Dog saba2 = new Dog("Saba");

        Assertions.assertThat(saba1.equals(saba2)).isFalse();
    }
}
