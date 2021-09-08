package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import workshop.java.fundamentals.inheritance.Cat;
import workshop.java.fundamentals.inheritance.Dog;
import workshop.java.fundamentals.inheritance.Pet;

import java.util.*;

public class EqualsAndHashCode {

    // rules for implementing equals and hashCode methods:

    // equals is a
    // reflexive relation:
    //  a is equal to a
    // symmetric relation:
    //  a is equal to b, so b is equal to a
    // transitive relation:
    //  a is equal to b, b is equal to c so a is equal to c

    // hashcode is consistent with equal method
    // if a is equal to b then a.hashcode is equal to b.hashcode

    // it is very important to have correct equals and hashcode
    // for classes used in Set and as keys in Map

    @Test
    public void equalsByValue() throws Exception {

        // String have value equality semantic

        Set<String> pets = new HashSet<>();
        pets.add(new String("Bella"));
        pets.add(new String("Cody"));
        pets.add(new String("Cody")); // another dog called Cody

        for (String pet : pets) {
            System.out.println(pet);
        }
        Assertions.assertThat(pets).hasSize(2);
    }

    @Test
    public void defaultImplementations() throws Exception {

        // Default implementation from Object class
        // have reference equality semantic
        // object is equal only to itself

        Set<Pet> pets = new HashSet<>();
        pets.add(new Cat("Bella"));
        pets.add(new Dog("Cody"));
        pets.add(new Dog("Cody")); // another dog called Cody

        for (Pet pet : pets) {
            System.out.println(pet.getName());
        }
        Assertions.assertThat(pets).hasSize(3);
    }

    // other important thing is to make those classes immutable
    @Test
    public void mutableMapKeysProblem() throws Exception {

        // old mutable java.util.Date representation
        Date date20160615 = new Date(2016 - 1900, 6 - 1, 15);

        Map<Date, Pet> birthdays = new HashMap<>();
        birthdays.put(date20160615, new Dog("Spark"));

        // obviously it is there
        Assertions.assertThat(birthdays).containsKey(date20160615);

        // change value of year
        Date duplicate = date20160615;
        date20160615.setYear(2020 - 1900);
        // same date instance but internal state is changed
        Assertions.assertThat(date20160615).isSameAs(duplicate);

        // but we will not find it in map
        //Assertions.assertThat(birthdays).containsKey(date20160615);

        birthdays.put(date20160615, new Dog("Spark"));
        Assertions.assertThat(birthdays).containsKey(date20160615);
        Assertions.assertThat(birthdays).hasSize(2);
        System.out.println(birthdays);
    }

    public class A {
        public void print(String text) {
            System.out.println(text + " z A");
        }
    }

    public class AA extends A {
        public void print(String text) {
            System.out.println(text + " z AA");
        }
    }

    public class AAA extends A {
        public void print(String text, String prefix) {
            super.print(prefix + text);
        }
    }

    @Test
    public void m2() throws Exception {
        new A().print("cześć");
        new AA().print("cześć");
        new AAA().print("cześć");

        A a = new AA();
        a.print("cześć");
    }
}
