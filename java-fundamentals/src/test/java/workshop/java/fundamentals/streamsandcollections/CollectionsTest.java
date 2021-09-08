package workshop.java.fundamentals.streamsandcollections;

import workshop.java.fundamentals.inheritance.Cat;
import workshop.java.fundamentals.inheritance.Dog;
import workshop.java.fundamentals.inheritance.Pet;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.*;

public class CollectionsTest {

    // List is most commonly used collection
    // ArrayList is most commonly used implementation
    private List<Pet> arraylist = new ArrayList<>();

    // List implemented as linked elements - random access is slow
    private List<Pet> linkedlist = new LinkedList<>();

    // Sets are optimised for contains operation
    // hash set has random order when looping
    // equals and hashCode methods are very important in that case
    private Set<String> hashSet = new HashSet<>();

    // tree set has looping order over elements
    // related to implementation of compareTo() in stored Classes
    private SortedSet<String> treeSet = new TreeSet<>();

    //
    private Map<String, Pet> hashMap = new HashMap<>();

    private Map<String, Pet> treeMap = new TreeMap<>();

    // lets make usage of few pets
    private Dog cody = new Dog("Cody");
    private Dog buster = new Dog("Buster");
    private Cat bella = new Cat("Bella");

    @Test
    public void usageOfListAccumulatingObjects() {

        // typical usage:
        // if we want to store all pets we have
        arraylist.add(cody);
        arraylist.add(bella);

        // benefit of list - we can access element at any index
        Assertions.assertThat(arraylist.get(0)).isSameAs(cody);
        Assertions.assertThat(arraylist.get(1)).isSameAs(bella);

        // order of adding elements is preserved on the list
        for (Pet element : arraylist) {
            System.out.println(element.getName());
        }
    }

    @Test
    public void usageOfListEditingElementAtIndex() {
        arraylist.add(cody);

        // benefit of list - we can edit element at any index
        Assertions.assertThat(arraylist.get(0)).isSameAs(cody);
        arraylist.set(0, bella);
        Assertions.assertThat(arraylist.get(0))
                .isNotSameAs(cody)
                .isSameAs(bella);

        for (Pet element : arraylist) {
            System.out.println(element.getName());
        }
    }

    @Test
    public void usageOfListRemovingAtIndex() {
        arraylist.add(cody);
        arraylist.add(buster);
        arraylist.add(bella);

        // benefit of list - we can remove element at any index
        arraylist.remove(1); // buster is under index 1

        for (Pet element : arraylist) {
            System.out.println(element.getName());
        }

        // list reindex elements, to keep consecutive indexes
        Assertions.assertThat(arraylist.get(0)).isSameAs(cody);
        Assertions.assertThat(arraylist.get(1)).isSameAs(bella);
        Assertions.assertThat(arraylist).doesNotContain(buster);

    }

    @Test
    public void usageOfSet() {

        // typical usage:
        // if we want to keep track of all names used already for pets
        // to prevent duplicates
        hashSet.add("Bella");
        hashSet.add("Cody");
        hashSet.add("Buster");

        if (hashSet.contains("Buster")) {
            System.out.println("Buster is already in set");
        }
        Assertions.assertThat(hashSet).contains("Buster");

        if (! hashSet.contains("Max")) {
            System.out.println("Max is not in set");
        }
        Assertions.assertThat(hashSet).doesNotContain("Max");

        // but in hashSet order of elements is unpredictable
        for (String element : hashSet) {
            System.out.println(element);
        }
    }

    @Test
    public void usageOfSetRemovingFromSet() {

        // we can add and remove from set
        hashSet.add("Cody");
        hashSet.add("Buster");
        hashSet.add("Bella");

        if (hashSet.contains("Buster")) {
            System.out.println("Buster is in set");
        }
        Assertions.assertThat(hashSet).contains("Buster");

        hashSet.remove("Buster");

        if (! hashSet.contains("Buster")) {
            System.out.println("Buster is no more in set");
        }
        Assertions.assertThat(hashSet).doesNotContain("Buster");

        for (String element : hashSet) {
            System.out.println(element);
        }
    }

    @Test
    public void usageOfTreeSet() {

        // TreeSet keeps elements ordered
        treeSet.add("Bella");
        treeSet.add("Cody");
        treeSet.add("Buster");


        for (String element : treeSet) {
            System.out.println(element);
        }

        // but in natural order of entries, not in order we added elements
        // in case of Strings - lexicographic order
        Assertions.assertThat(treeSet)
                .containsExactly("Bella", "Buster", "Cody");
    }

    @Test
    public void usageOfMap() {

        // typical usage:
        // if we want find values based on keys
        hashMap.put("Cody", cody);
        hashMap.put("Buster", buster);
        hashMap.put("Bella", bella);

        // we can find our pet by name
        Pet pet = hashMap.get("Buster");

        Assertions.assertThat(pet.getName())
                .isEqualTo("Buster");
        Assertions.assertThat(pet).isSameAs(buster);
    }

    @Test
    public void usageOfMapIteratingOverMap() throws Exception {
        hashMap.put("Cody", cody);
        hashMap.put("Buster", buster);
        hashMap.put("Bella", bella);

        // we can iterate on keys
        for (String name : hashMap.keySet()) {
            System.out.println(name + " => " + hashMap.get(name));
        }

        for (Pet pet : hashMap.values()) {
            System.out.println(pet.getName() + " makes " + pet.makeSound());
        }

        for (Map.Entry<String, Pet> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    // in TestNG fields are preserved between run of tests methods
    // so we need to reset them after each method
    @AfterMethod
    public void tearDown() throws Exception {
        arraylist.clear();
        linkedlist.clear();
        hashSet.clear();
        treeSet.clear();
        hashMap.clear();
        treeMap.clear();
    }
}
