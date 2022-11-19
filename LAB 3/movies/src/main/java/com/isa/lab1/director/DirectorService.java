package com.isa.lab1.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
       
    private DirectorRepo repo;

    @Autowired
    public DirectorService(DirectorRepo repo) {
        this.repo = repo;
    }

    public Optional<Director> find(Long id) {
        return repo.findById(id);
    }

    public List<Director> findAll() {
        return repo.findAll();
    }
    
    @Transactional
    public Director create(Director director) {
        return repo.save(director);
    }

    @Transactional
    public void update(Director director) {
        repo.save(director);
    }

    @Transactional
    public void delete(Director director) {
        repo.delete(director);
    }
}
