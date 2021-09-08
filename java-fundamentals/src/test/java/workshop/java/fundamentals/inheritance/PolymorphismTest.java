package workshop.java.fundamentals.inheritance;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PolymorphismTest {

    @Test(dataProvider = "pets")
    public void weCanUseCatAndDogBehaviours(Pet pet) throws Exception {

        // we can use cat or dog behaviours
        // not knowing what kind of animal that is
        // it is called Polymorphism in object oriented languages
        System.out.println(pet.makeSound());

        if (pet instanceof Dog) { // to check what
            System.out.println("it is a dog !");
            Assertions.assertThat(pet.makeSound()).isEqualTo("Woof Woof");
        } else if (pet instanceof Cat) {
            System.out.println("it is a cat !");
            Assertions.assertThat(pet.makeSound()).isEqualTo("Meow");
        }
    }

    @Test(dataProvider = "pets")
    public void castToSubType(Pet pet) throws Exception {

        // if we are sure what type it is
        if (pet instanceof Cat) {
            // we can cast it to any subtype
            Cat cat = (Cat) pet;
            System.out.println("it is a cat and it makes " + cat.makeSound());
        }

    }

    @DataProvider
    public static Object[][] pets() {
        return new Object[][] {{new Cat("Bella")}, {new Dog("Cody")}, {new Dog("Buster")}};
    }
}
