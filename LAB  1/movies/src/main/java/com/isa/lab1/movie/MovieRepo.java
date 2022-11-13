package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.lab1.datastore.Datastore;
import com.isa.lab1.director.Director;
import com.isa.lab1.repository.Repository;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class MovieRepo  implements Repository<Movie, String> {

    private Datastore store;

    @Autowired
    public MovieRepo(Datastore store){
        this.store = store;
    }

    @Override
    public Optional<Movie> find(String name) {
        return store.findMovie(name);
    }

    public Optional<Movie> finddir(Director director) {
        return store.findMovie(director);
    }

    @Override
    public List<Movie> findAll() {
        return store.findAllMovies();
    }

    @Override
    public void create(Movie entity) {
        store.createMovie(entity);
    }

    @Override
    public void delete(Movie entity) {
        store.deleteMovie(entity.gettitle());
    }

    public void deletedir(Director director) {
        store.deleteMovie(director);
    }

    @Override
    public void update(Movie entity) {
        store.updateMovie(entity);
    }


}
