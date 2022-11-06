package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isa.lab1.director.Director;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    
    private final MovieRepo repo;

    @Autowired
    public MovieService(MovieRepo repo) {
        this.repo = repo;
    }

    public Optional<Movie> find(String name) {
        return repo.find(name);
    }

    public Optional<Movie> finddir(Director director) {
        return repo.finddir(director);
    }

    public List<Movie> findAll() {
        return repo.findAll();
    }

    public void create(Movie movie) {
        repo.create(movie);
    }

    public void delete(Movie movie) {
        repo.delete(movie);
    }

    public void delete(Director director) {
        repo.deletedir(director);
    }

    public void update(Movie movie) {
        repo.update(movie);
    }
}
