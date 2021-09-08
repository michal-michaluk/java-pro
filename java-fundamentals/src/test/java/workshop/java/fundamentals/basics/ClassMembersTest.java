package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ClassMembersTest {

    @Test
    public void instantiateObject() throws Exception {

        ClassMembers object = new ClassMembers();

        Assertions.assertThat(object).isNotNull();
    }

    @Test
    public void instantiateObjectAnotherConstructor() throws Exception {

        ClassMembers object = new ClassMembers("given value");

        Assertions.assertThat(object).isNotNull();
    }

    @Test
    public void staticMembersCanByAccessedWithoutInstance() throws Exception {

        Assertions.assertThat(ClassMembers.CONSTANT_FIELD).isEqualTo("constant!");

        Assertions.assertThat(ClassMembers.add(1, 2)).isEqualTo(3);
    }

    @Test
    public void accessStateOfObjectWithQueryLikeMethod() throws Exception {

        ClassMembers object = new ClassMembers("given value");

        Assertions.assertThat(object.getField()).isEqualTo("given value");
    }

    @Test
    public void changeStateOfObjectWithCommandLikeMethods() throws Exception {

        ClassMembers object = new ClassMembers("given value");
        Assertions.assertThat(object.getField()).isEqualTo("given value");

        // note there is no value returned - void cannot by assigned to variable
        object.doSomeChangeInside("new value");

        Assertions.assertThat(object.getField()).isEqualTo("new value");
    }

    @Test
    public void callMethodWithDifferentScopes() throws Exception {

        ClassMembers object = new ClassMembers("given value");

        // we can call it anywhere
        object.doPublicBehaviour();

        // we are in same package so we can access protected and default scope:
        object.doSomethingWithDefaultScope();
        object.doSomethingButProtected();

        // note you can not call private members
        // object.doSomethingInPrivate();
    }

    @Test
    public void callVarargMethod() throws Exception {

        ClassMembers object = new ClassMembers("given value");

        Assertions.assertThat(object.createListOfElements("one", "two"))
                .contains("one", "two");


        Assertions.assertThat(object.createListOfElements("one only"))
                .contains("one only");

    }
}
