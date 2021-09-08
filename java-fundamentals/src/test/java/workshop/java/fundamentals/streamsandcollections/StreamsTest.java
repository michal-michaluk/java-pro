package workshop.java.fundamentals.streamsandcollections;

import workshop.java.fundamentals.inheritance.Cat;
import workshop.java.fundamentals.inheritance.Dog;
import workshop.java.fundamentals.inheritance.Pet;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTest {

    private List<Pet> pets = Arrays.asList(
            new Dog("Cody"), new Dog("Buster"), new Cat("Bella")
    );

    @Test
    public void simpleLoop() throws Exception {

        // simple loop to collect pet names
        String petNames = "";
        for (Pet pet : pets) {
            petNames += pet.getName() + ", ";
        }

        System.out.println(petNames);
        Assertions.assertThat(petNames).isEqualTo("Cody, Buster, Bella, ");

    }

    @Test
    public void simpleStream() throws Exception {

        // .stream() is a default method introduced to Collection interface
        String petNames = pets.stream()
                .map(pet -> pet.getName())
                .collect(Collectors.joining(", "));

        System.out.println(petNames);
        Assertions.assertThat(petNames).isEqualTo("Cody, Buster, Bella");
    }


    @Test
    public void otherStream() throws Exception {

        // more complex example:
        // take only dogs
        // let them make sound
        // print sounds

        pets.stream().parallel()
                .filter(pet -> pet instanceof Dog)
                .map(pet -> (Dog) pet) // stream of Dogs
                .forEach((Dog dog) -> {
                    String sound = dog.makeSound();
                    System.out.println(dog.getName() + " makes " + sound);
                });
    }
}
