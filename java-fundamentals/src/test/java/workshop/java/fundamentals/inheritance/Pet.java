package workshop.java.fundamentals.inheritance;

/**
 * Class declared as abstract,
 * can't be instantiated with "new" operator
 *
 * Created by michal on 14.06.2016.
 */
public abstract class Pet {

    private String name;

    // protected can be accessed by sub classes
    protected Pet(String name) {
        this.name = name;
    }

    // implemented behaviour
    public String getName() {
        return name;
    }

    // contract to implement, without implementation
    public abstract String makeSound();
}
