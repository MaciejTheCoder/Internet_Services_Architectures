package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.Optional;
import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorRepo;

@Service
public class MovieService {
    
    private MovieRepo repo;

    private DirectorRepo dirrepo; 

    @Autowired
    public MovieService(MovieRepo repo, DirectorRepo dirrepo) {
        this.repo = repo;
        this.dirrepo = dirrepo;
    }

    public Optional<Movie> find(Long id) {
        return repo.findById(id);
    }

    public Optional<Movie> find(Long dir_id, Long id) {
        Optional<Director> director = dirrepo.findById(dir_id);
        if (director.isPresent()) {
            return repo.findByIdAndDirector(id, director.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Movie> findAll() {
        return repo.findAll();
    }

    public List<Movie> findAll(Director director) {
        return repo.findAllByDirector(director);
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
