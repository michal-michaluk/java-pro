package workshop.java.fundamentals.basics;

import java.util.Arrays;
import java.util.List;

/**
 * Classes are basic units in Java language.
 *
 * Created by michal on 13.06.2016.
 */
public class ClassMembers {

    // constants
    // static fields are not related to instance of object
    // you can access them without instance
    public final static String CONSTANT_FIELD = "constant!";
    private final static int PRIVATE_CONSTANT = 44;

    // instance fields
    private final String finalField = "unchangeable"; // final field initialised here or
    private final String finalField2; // need to by initialized in constructors
    private String variableField;

    /**
     * it is default constructor for that class without any argument
     */
    public ClassMembers() {
        finalField2 = "another unchangeable";
        this.variableField = "instance private non final field";
    }

    public ClassMembers(String variableField) {
        this.variableField = variableField;
        finalField2 = "fixed since now " + variableField
                + " but computed during construction";
    }

    /**
     * static methods are accessible without class instance.
     *
     * @param a first number
     * @param b second number
     * @return sum of given numbers
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * nothing in; nothing out
     * void method without arguments
     */
    public void doPublicBehaviour() {

    }

    /**
     * query like method
     * without arguments, returns internal state
     * no side effects of execution
     *
     * @return "some string" constant
     */
    public String getField() {
        return variableField;
    }

    /**
     * command like method
     * do some behaviour, potentially changing internal state
     * returns nothing
     *
     * @param parameter test added to instance field
     */
    public void doSomeChangeInside(String parameter) {
        variableField = parameter;
    }

    /**
     * Overloaded method - same name, different argument list
     * compiler can distinguish call based on arguments
     *
     */
    public void doSomeChangeInside(String parameter, String parameter2) {
        doSomethingInPrivate();
    }

    /**
     * private method
     * can be accessed only from within class
     */
    private void doSomethingInPrivate() {

    }

    /**
     * default scope for class members
     * can be accessed from classes in same package
     */
    void doSomethingWithDefaultScope() {

    }

    /**
     * protected method
     * can be accessed from classes in same package
     * and from class extending that class
     */
    protected void doSomethingButProtected() {

    }

    /**
     * Notice argument list "String... elements".
     * Vararg methods can by called with any number of arguments.
     * Inside method array of elements is passed.
     *
     * @param elements vararg parameter - accepts any number of elements
     * @return list containing all elements given to method
     */
    public List<String> createListOfElements(String... elements) {
        return Arrays.asList(elements);
    }

    @Override
    public String toString() {
        return "instance of ClassMembers class with " +
                "variableField='" + variableField + '\'';
    }
}
