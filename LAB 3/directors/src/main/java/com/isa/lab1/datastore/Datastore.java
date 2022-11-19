package com.isa.lab1.datastore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.isa.lab1.director.Director;
import com.isa.lab1.movie.Movie;


@Component
public class Datastore {
    
    private final Set<Director> directors = new HashSet<>();
    
    private final Set<Movie> movies = new HashSet<>();

    public synchronized List<Director> findAllDirectors() {
        return new ArrayList<>(directors);
    }

    public synchronized Optional<Director> findDirector(Long id) {
        return directors.stream()
                .filter(director -> director.getid() == id)
                .findFirst();
    }

    public synchronized void createDirector(Director director) throws IllegalArgumentException {
        findDirector( director.getid()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The director id \"%s\" is not unique", director.getid()));
                },
                () -> directors.add(director));
    }

    public synchronized void deleteDirector(Long id) throws IllegalArgumentException {
        findDirector(id).ifPresentOrElse(
                directors::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%s\" does not exist", id));
                });
    }

    public synchronized void updateDirector(Director director) throws IllegalArgumentException {
        findDirector(director.getid()).ifPresentOrElse(
                original -> {
                    directors.remove(original);
                    directors.add(director);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%s\" does not exist", director.getid()));
                });
    }

    public synchronized List<Movie> findAllMovies() {
        return new ArrayList<>(movies);
    }

    public synchronized Optional<Movie> findMovie(Long id) {
        return movies.stream()
                .filter(movies -> (movies.getid() == id))
                .findAny();

    }

    public synchronized Optional<Movie> findMovie(Director director) {
        return movies.stream()
                .filter(movies -> movies.getdirector().equals(director))
                .findFirst();

    }

    public synchronized void createMovie(Movie movie) throws IllegalArgumentException {
        movies.add(movie);
    }


    public synchronized void updateMovie(Movie movie) throws IllegalArgumentException {
        findMovie(movie.getid()).ifPresentOrElse(
                original -> {
                    movies.remove(original);
                    movies.add(movie);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The movie with id \"%s\" does not exist", movie.getid()));
                });
    }

    public synchronized void deleteMovie(Long id) throws IllegalArgumentException {
        findMovie(id).ifPresentOrElse(
                movies::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The movie with id \"%s\" does not exist", id));
                });
    }

    public synchronized void deleteMovie(Director director) throws IllegalArgumentException {
        findMovie(director).ifPresentOrElse(
            movies::remove,
            () -> {
                throw new IllegalArgumentException(
                        String.format("Any movie with director \"%s\" does not exist anymore", director));
            });
    }
}
