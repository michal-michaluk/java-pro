package workshop.java.intermediate.collections;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class $EntryTest {

    static final List<String> objects = unmodifiableList(
            new Random()
                    .doubles(10_000)
                    .mapToObj(d -> "next double is: " + d)
                    .collect(Collectors.toList())
    );

    // Task 1.
    // add 10 000 random strings to:
    // to known list, set, queue implementation
    // create ExampleCollections class providing instances of filled and unmodifiable collections

    @Test
    public void arrayListAdd() throws Exception {
        List<String> strings = new ArrayList<>();
        for (String object : objects) {
            strings.add(object);
        }

        Assertions.assertThat(strings)
                .hasSize(10_000);
    }

    @Test
    public void linkedListAdd() throws Exception {
        List<String> strings = new LinkedList<>();
        for (String object : objects) {
            strings.add(object);
        }

        Assertions.assertThat(strings)
                .hasSize(10_000);
    }

    @Test
    public void hashSetAdd() throws Exception {
        Set<String> strings = new HashSet<>();
        for (String object : objects) {
            strings.add(object);
        }

        Assertions.assertThat(strings)
                .hasSize(10_000);
    }

    @Test
    public void treeSetAdd() throws Exception {
        Set<String> strings = new TreeSet<>();
        for (String object : objects) {
            strings.add(object);
        }

        Assertions.assertThat(strings)
                .hasSize(10_000);
    }

    @Test
    public void linkedHashSetAdd() throws Exception {
        Set<String> strings = new LinkedHashSet<>();
        for (String object : objects) {
            strings.add(object);
        }

        Assertions.assertThat(strings)
                .hasSize(10_000);
    }

    // Task 2.
    // using example collections
    // iterate over all elements for known list, set, queue implementation

    // Task 3.
    // using example collections
    // remove 10 000 random selected elements from known list, set, queue implementation

    // Task 4.
    // using example collections
    // find by index selected elements
    // - 5 000 existing in collection
    // - 5 000 not existing in collection
    // from known list, set, queue implementation

    // Task 5.
    // using example collections
    // find by value selected elements
    // - 5 000 existing in collection
    // - 5 000 not existing in collection
    // from known list, set, queue implementation

    // Task 6.
    // using example collections
    // remove by index selected elements
    // - 5 000 existing in collection
    // - 5 000 not existing in collection
    // from known list, set, queue implementation

    // Task 7.
    // using example collections
    // remove by value selected elements
    // - 5 000 existing in collection
    // - 5 000 not existing in collection
    // from known list, set, queue implementation
}
