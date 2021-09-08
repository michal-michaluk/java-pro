package workshop.java.fundamentals.basics;

import java.util.List;

public record Person(String name, int age, List<Person> friends) {
}
