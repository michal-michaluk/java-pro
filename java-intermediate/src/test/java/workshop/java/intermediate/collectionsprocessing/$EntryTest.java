package workshop.java.intermediate.collectionsprocessing;

import org.junit.Test;
import workshop.java.intermediate.boilerplatefree.ExampleMovies;
import workshop.java.intermediate.boilerplatefree.Movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class $EntryTest {

    // using ExampleMovies ...

    // Task 1.
    // find titles of movies with French language

    @Test
    public void frenchTitles() throws Exception {
        List<String> frenchTitles = new ArrayList<>();
        for (Movie.MovieBuilder builder : ExampleMovies.allMovies()) {
            Movie movie = builder.build();
            if (movie.getLanguage().contains("French")) {
                frenchTitles.add(movie.getTitle());
            }
        }

        assertThat(frenchTitles)
                .isNotEmpty()
                .contains(
                        "Kill Bill: Vol. 1",
                        "The Inglorious Bastards",
                        "What Just Happened"
                )
        ;
    }


    // Task 2.
    // find any movie from Drama genre
    // find any movie from Silent genre

    @Test
    public void anyDramaAnySilent() throws Exception {
        Movie drama = null;
        Movie silent = null;
        for (Movie.MovieBuilder builder : ExampleMovies.allMovies()) {
            Movie movie = builder.build();
            if (movie.getGenre().contains(Movie.Genre.Drama)) {
                drama = movie;
            }
            if (movie.getGenre().contains(Movie.Genre.Silent)) {
                silent = movie;
            }
            if (drama != null && silent != null) {
                break;
            }
        }

        assertThat(drama)
                .isNotNull();

        assertThat(silent)
                .isNull();
    }


    // Task 3.
    // calculate
    // over all movies duration
    // average imdbRating
    // total number of imdbVotes
    // largest crew number

    @Test
    public void aggregate() throws Exception {
        Duration totalDuration = Duration.ZERO;
        double ratingSum = 0;
        int ratingCount = 0;
        int totalVotes = 0;
        int largestCrew = 0;
        for (Movie.MovieBuilder builder : ExampleMovies.allMovies()) {
            Movie movie = builder.build();
            totalDuration = totalDuration.plus(movie.getRuntime());
            ratingSum += movie.getImdbRating();
            ratingCount++;
            totalVotes += movie.getImdbVotes();
            int crewCount = movie.getActors().size()
                    + movie.getWriter().size()
                    + movie.getDirector().size();
            largestCrew = Math.max(largestCrew, crewCount);
        }
        double ratingAvg = ratingCount > 0 ? ratingSum / ratingCount : 0.0;
        System.out.println("ratingAvg: " + ratingAvg);
        System.out.println("ratingSum: " + ratingSum);
        System.out.println("ratingCount: " + ratingCount);
        System.out.println("totalVotes: " + totalVotes);
        System.out.println("largestCrew: " + largestCrew);
    }


    // Task 4.
    // list distinctive all persons from database ordered by name


    // Task 5.
    // group by genre


    // Task 6.
    // calculate average duration by genre


}
