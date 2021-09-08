package workshop.java.intermediate.collectionsprocessing;

import lombok.*;
import org.junit.Test;
import workshop.java.intermediate.boilerplatefree.ExampleMovies;
import workshop.java.intermediate.boilerplatefree.Movie;
import workshop.java.intermediate.boilerplatefree.Person;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class $ExitTest {
    public static final BinaryOperator<Aggregator> SINGLE_THREADED_AGGREGATION = (aggregator1, aggregator2) -> {
        throw new UnsupportedOperationException();
    };

    // using ExampleMovies ...

    // Task 1.
    // find titles of movies with French language

    // Task 2.
    // find any movie from Drama genre
    // find any movie from Silent genre

    // Task 3.
    // calculate
    // over all movies duration
    // average imdbRating
    // total number of imdbVotes
    // largest crew number

    @Value
    public static class ImmutableAggregator {
        private final Duration totalDuration;
        private final double ratingSum;
        private final int ratingCount;
        private final double totalVotes;
        private final int largestCrew;
    }


    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Aggregator {
        public Duration totalDuration = Duration.ZERO;
        public double ratingSum = 0.0;
        public int ratingCount = 0;
        public double totalVotes = 0.0;
        public int largestCrew = 0;
    }

    @Test
    public void aggregations() throws Exception {
        long sum = ExampleMovies.allMovies().stream()
                .map(Movie.MovieBuilder::build)
                .mapToLong((movie) -> movie.getRuntime().toMinutes())
                .sum();

        double average = ExampleMovies.allMovies().stream()
                .map(Movie.MovieBuilder::build)
                .mapToDouble(Movie::getImdbRating)
                .average().orElse(0);

        Optional<ImmutableAggregator> reduced = ExampleMovies.allMovies().parallelStream().map(Movie.MovieBuilder::build)
                .map(movie -> new ImmutableAggregator(
                        movie.getRuntime(),
                        movie.getImdbRating(),
                        1,
                        movie.getImdbVotes(),
                        movie.getActors().size()
                                + movie.getDirector().size()
                                + movie.getWriter().size()
                ))
                .reduce((agg1, agg2) -> new ImmutableAggregator(
                        agg1.totalDuration.plus(agg2.totalDuration),
                        agg1.ratingSum + agg2.ratingSum,
                        agg1.ratingCount + agg2.ratingCount,
                        agg1.totalVotes + agg2.totalVotes,
                        Math.max(agg1.largestCrew, agg2.largestCrew)
                ));
        System.out.println(reduced);

        Aggregator mutableReduce = ExampleMovies.allMovies().stream().map(Movie.MovieBuilder::build)
                .reduce(new Aggregator(), (agg, movie) -> {
                    agg.totalDuration = agg.totalDuration.plus(movie.getRuntime());
                    agg.ratingSum = agg.ratingSum + movie.getImdbRating();
                    agg.ratingCount++;
                    agg.totalVotes = agg.totalVotes + movie.getImdbVotes();
                    agg.largestCrew = Math.max(agg.largestCrew,
                            movie.getActors().size()
                                    + movie.getDirector().size()
                                    + movie.getWriter().size());
                    return agg;
                }, SINGLE_THREADED_AGGREGATION);

        System.out.println(mutableReduce);

    }

    // Task 4.
    // list distinctive all persons from database ordered by name

    @Test
    public void distinctPersons() throws Exception {
        String persons = ExampleMovies.allMovies().stream()
                .map(Movie.MovieBuilder::build)
                .flatMap((movie) ->
                        Stream.concat(movie.getDirector().stream(),
                                Stream.concat(
                                        movie.getActors().stream(),
                                        movie.getWriter().stream()
                                ))
                )
                .distinct()
                .sorted(Comparator.comparing(Person::getName))
                .map(Person::getName)
                .collect(Collectors.joining("\n"));

        System.out.println(persons);
    }

    // Task 5.
    // group by genre

    @AllArgsConstructor
    @Getter
    public static class Tuple {
        Movie.Genre genre;
        Movie movie;
    }

    @Test
    public void grouping() throws Exception {
        Map<Movie.Genre, List<Tuple>> index = ExampleMovies.allMovies().stream().map(Movie.MovieBuilder::build)
                .flatMap(movie -> movie.getGenre().stream()
                        .map(genre -> new Tuple(genre, movie)))
                .collect(Collectors.groupingBy(Tuple::getGenre));

        System.out.println(index);
    }


    // Task 6.
    // calculate average duration by genre

}
