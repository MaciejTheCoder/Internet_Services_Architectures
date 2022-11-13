package com.isa.lab1.director;
import org.springframework.beans.factory.annotation.Autowired;

import com.isa.lab1.datastore.Datastore;
import com.isa.lab1.repository.Repository;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class DirectorRepo implements Repository<Director, String> {
   
    private final Datastore store;

    @Autowired
    public DirectorRepo(Datastore store) {
        this.store = store;
    }

    @Override
    public Optional<Director> find(String name) {
        return store.findDirector(name);
    }

    @Override
    public List<Director> findAll() {
        return store.findAllDirectors();
    }

    @Override
    public void create(Director entity) {
        store.createDirector(entity);
    }

    @Override
    public void delete(Director entity) {
        store.deleteDirector(entity.getname());
    }

    @Override
    public void update(Director entity) {
        store.updateDirector(entity);
    }


}
