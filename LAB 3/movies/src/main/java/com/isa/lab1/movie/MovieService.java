package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    
    private MovieRepo repo;

    @Autowired
    public MovieService(MovieRepo repo) {
        this.repo = repo;
    }

    public Optional<Movie> find(Long id) {
        return repo.findById(id);
    }

    public List<Movie> findAll() {
        return repo.findAll();
    }

    @Transactional
    public Movie create(Movie movie) {
        return repo.save(movie);
    }

    @Transactional
    public void update(Movie movie) {
        repo.save(movie);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
