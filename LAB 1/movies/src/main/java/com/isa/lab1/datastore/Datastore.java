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

    public synchronized Optional<Director> findDirector(String name) {
        return directors.stream()
                .filter(director -> director.getname().equals(name))
                .findFirst();
    }

    public synchronized void createDirector(Director director) throws IllegalArgumentException {
        findDirector( director.getname()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The director name \"%s\" is not unique", director.getname()));
                },
                () -> directors.add(director));
    }

    public synchronized void deleteDirector(String name) throws IllegalArgumentException {
        findDirector(name).ifPresentOrElse(
                directors::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with name \"%s\" does not exist", name));
                });
    }

    public synchronized void updateDirector(Director director) throws IllegalArgumentException {
        findDirector(director.getname()).ifPresentOrElse(
                original -> {
                    directors.remove(original);
                    directors.add(director);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with name \"%s\" does not exist", director.getname()));
                });
    }

    public synchronized List<Movie> findAllMovies() {
        return new ArrayList<>(movies);
    }

    public synchronized Optional<Movie> findMovie(String title) {
        return movies.stream()
                .filter(movies -> movies.gettitle().equals(title))
                .findFirst();

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
        findMovie(movie.gettitle()).ifPresentOrElse(
                original -> {
                    movies.remove(original);
                    movies.add(movie);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The movie with title \"%s\" does not exist", movie.gettitle()));
                });
    }

    public synchronized void deleteMovie(String title) throws IllegalArgumentException {
        findMovie(title).ifPresentOrElse(
                movies::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The movie with title \"%s\" does not exist", title));
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
