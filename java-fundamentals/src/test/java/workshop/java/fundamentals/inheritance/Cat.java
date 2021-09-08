package workshop.java.fundamentals.inheritance;

/**
 * Cat extends Pet definition.
 * Cat inherits all members from Pet.
 * Cat can override some behaviours from Pet.
 *
 * Cat is subclass of Pet.
 * Pet is superclass of Cat.
 *
 * Created by michal on 14.06.2016.
 */
public class Cat extends Pet {

    public Cat(String name) {
        // call constructor of superclass
        super(name);
    }

    @Override
    public String makeSound() {
        return "Meow";
    }
}
