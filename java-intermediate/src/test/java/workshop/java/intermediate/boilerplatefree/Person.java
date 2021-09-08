package workshop.java.intermediate.boilerplatefree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Optional;

@Value
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
public class Person {

    String name;
    String involvement;

    public Person(String name) {
        this.name = name;
        involvement = null;
    }

    public Optional<String> getInvolvement() {
        return Optional.ofNullable(involvement);
    }
}
