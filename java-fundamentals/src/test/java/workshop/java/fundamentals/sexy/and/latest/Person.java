package workshop.java.fundamentals.sexy.and.latest;

import java.util.List;

public record Person(String name, int age, List<Person> friends) {
}
