package com.isa.lab1.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
       
    private final DirectorRepo repo;

    @Autowired
    public DirectorService(DirectorRepo repo) {
        this.repo = repo;
    }

    public Optional<Director> find(String name) {
        return repo.find(name);
    }

    public List<Director> findAll() {
        return repo.findAll();
    }

    public void create(Director director) {
        repo.create(director);
    }

    public  void delete(Director director){
        repo.delete(director);
    } 

    public  void update(Director director){
        repo.update(director);
    } 
}
