package workshop.java.fundamentals.basics;

/**
 *
 * Created by michal on 14.06.2016.
 */
public class InterfaceImplementation implements Interface {

    @Override
    public String method() {
        // class "implements Interface"
        // compiler is forcing us to implement that method
        // in other words class need fulfill the contract
        return "method implemented in InterfaceImplementation";
    }

}
