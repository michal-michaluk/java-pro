package workshop.java.fundamentals.basics;

/**
 * Since java 8 interface can implement default behaviours and static methods.
 *
 * Created by michal on 14.06.2016.
 */
public interface InterfaceInJava8 {

    static String staticMethod() {
        return "defined in Interface";
    }

    String method();

    default String defaultMethod() {
        // there is no fields
        // but we can call declared methods
        return method() + " " + staticMethod();
    }
}
