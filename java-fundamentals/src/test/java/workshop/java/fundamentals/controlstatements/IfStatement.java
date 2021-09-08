package workshop.java.fundamentals.controlstatements;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;

public class IfStatement {

    @Test
    public void simpleIfStatement() {

        // if-statement is a basic structure for executing conditional behaviour
        // code will be executed only if condition is true
        // look on some examples:

        if (name.equals("Michal")) {
            System.out.println("Hi, my name is Michal.");
        }

        if (true) { // with constants it is not very useful
            System.out.println("do that each time");
        }

        if (alwaysFalse()) {
            System.out.println("never do that");
        }

        if ("not empty".isEmpty()) {
            System.out.println("it will be not executed");
        }

        if (2 + 2 == 4) {
            System.out.println("always true");
        }
    }

    @Test
    public void ifElseStatement() {

        // we can define alternative behaviour
        // executed if condition is evaluated to false
        // as we saw in previous examples else-clause is optional

        if (anField) {
            System.out.println("executed if anField is true");
        } else {
            System.out.println("executed if anField is false");
        }
    }

    @Test(dataProvider = "intValues")
    public void chainedIfElseStatement(int value) {

        // we can have more variants then two
        // to achieve that lets chain multiple if-else

        if (value == 0) {
            System.out.println(value + " is zero");
        } else if (value > 0) {
            System.out.println(value + " is positive");
        } else {
            System.out.println(value + " is negative");
        }
    }

    @Test
    public void singleStatementWithoutBrackets() {

        // for single statements we can omit brackets
        // but it is not recommended

        if (anField)
            System.out.println("executed if anField is true");
        else
            System.out.println("executed if anField is false");
    }

    public String returnFromIfStatement(boolean argument) {

        // you can use return statement inside if-else

        if (argument) {
            return "case one";
        } else {
            return "case two";
        }
    }

    @Test
    public void callReturnFromIfStatement() {

        Assertions.assertThat(returnFromIfStatement(true)).isEqualTo("case one");

        Assertions.assertThat(returnFromIfStatement(false)).isEqualTo("case two");
    }

    public int howBigItIs(Object argument) {
        if (argument instanceof String s) {
            return s.length();
        } else if (argument instanceof Number n) {
            return n.intValue();
        } else if (argument instanceof Collection c && c.isEmpty()) {
            return c.size();
        } else return -1;
    }

    @Test
    public void testHowBigItIs() {
        Assertions.assertThat(howBigItIs("short string")).isEqualTo(12);
        Assertions.assertThat(howBigItIs(12)).isEqualTo(12);
    }

    ////////////////////////////////
    // utility members - ignore them

    @DataProvider
    public static Object[][] intValues() {
        return new Object[][]{{1}, {0}, {-3}};
    }

    private boolean anField = false;
    private String name = "Michal";

    private boolean alwaysFalse() {
        return false;
    }

}
