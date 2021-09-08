package workshop.java.intermediate.collectionsprocessing;


import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class GroupingSimpleTest {

    @Test
    public void standardGrouping() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, List<String>> result = strings.stream()
                .collect(groupingBy(String::length));

        System.out.println(result);
    }

    @Test
    public void customMapImplementation() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        TreeMap<Integer, List<String>> result = strings.stream()
                .collect(groupingBy(String::length, TreeMap::new, toList()));

        System.out.println(result);
    }

    @Test
    public void customAggregateImplementation() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, TreeSet<String>> result = strings.stream()
                .collect(groupingBy(String::length, toCollection(TreeSet::new)));

        System.out.println(result);
    }

    @Test
    public void customAggregation_counting() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Long> result = strings.stream()
                .collect(groupingBy(String::length, counting()));

        System.out.println(result);
    }

    @Test
    public void customAggregation_filtering() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, List<String>> result = strings.stream()
                .collect(groupingBy(String::length, filtering(s -> !s.contains("c"), toList())));

        System.out.println(result);
    }

    @Test
    public void customAggregation_joining() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, String> result = strings.stream()
                .collect(groupingBy(String::length, joining(",", "[", "]")));

        System.out.println(result);
    }

    @Test
    public void customAggregation_averaging() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Double> result = strings.stream()
                .collect(groupingBy(String::length, averagingInt(String::hashCode)));

        System.out.println(result);
    }

    @Test
    public void customAggregation_summarizing() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, IntSummaryStatistics> result = strings.stream()
                .collect(groupingBy(String::length, summarizingInt(String::hashCode)));

        System.out.println(result);
    }

    @Test
    public void customAggregation_flatmapping() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, List<Character>> result = strings.stream()
                .map(toStringList())
                .collect(groupingBy(List::size, flatMapping(Collection::stream, Collectors.toList())));

        System.out.println(result);
    }

    @Test
    public void customAggregation_mapping() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, List<String>> result = strings.stream()
                .collect(groupingBy(String::length, Collectors.mapping(String::toUpperCase, Collectors.toList())));

        System.out.println(result);
    }

    @Test
    public void customAggregation_reducing() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, List<Character>> result = strings.stream()
                .map(toStringList())
                .collect(groupingBy(List::size, reducing(List.of(), (l1, l2) -> Stream.concat(l1.stream(), l2.stream())
                        .collect(Collectors.toList()))));

        System.out.println(result);
    }

    @Test
    public void customAggregation_reducing_optional() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Optional<List<Character>>> result = strings.stream()
                .map(toStringList())
                .collect(groupingBy(List::size, reducing((l1, l2) -> Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList()))));

        System.out.println(result);
    }

    @Test
    public void customAggregation_summing() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Integer> result = strings.stream()
                .collect(groupingBy(String::length, summingInt(String::hashCode)));

        System.out.println(result);
    }

    @Test
    public void customAggregation_max() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Optional<String>> result = strings.stream()
                .collect(groupingBy(String::length, Collectors.maxBy(Comparator.comparing(String::toUpperCase))));

        System.out.println(result);
    }

    @Test
    public void customAggregation_min() {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        Map<Integer, Optional<String>> result = strings.stream()
                .collect(groupingBy(String::length, Collectors.minBy(Comparator.comparing(String::toUpperCase))));

        System.out.println(result);
    }

    private static Function<String, List<Character>> toStringList() {
        return s -> s.chars().mapToObj(c -> (char) c).collect(toList());
    }
}
