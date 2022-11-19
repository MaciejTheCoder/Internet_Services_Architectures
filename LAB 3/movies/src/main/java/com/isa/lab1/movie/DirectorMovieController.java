package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;

import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;
import com.isa.lab1.dto.getmovieresp;
import com.isa.lab1.dto.getmoviesresp;
import com.isa.lab1.dto.postmoviereq;
import com.isa.lab1.dto.putmoviereq;

@RestController
@RequestMapping("api/directors/{id_dir}/movies")
public class DirectorMovieController {
    private MovieService movieservice;
    private DirectorService directorservice;

    @Autowired
    public DirectorMovieController(MovieService movieservice, DirectorService directorservice) {
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    @GetMapping
    public ResponseEntity<getmoviesresp> getmovies(@PathVariable("id_dir") Long id) {
        Optional<Director> director = directorservice.find(id);
        return director.map(value -> ResponseEntity.ok(getmoviesresp.entityToDtoMapper().apply(movieservice.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<getmovieresp> getmovie(@PathVariable("id_dir") Long id_dir, @PathVariable("id") Long id) {
        return  movieservice.find(id_dir,id)
                .map(value -> ResponseEntity.ok(getmovieresp.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postCharacter(@PathVariable("id_dir") Long id_dir, @RequestBody postmoviereq request,UriComponentsBuilder builder){
        Optional<Director> director = directorservice.find(id_dir);
        if (director.isPresent()) {
            Movie movie = postmoviereq
                    .dtoToEntityMapper(director::get)
                    .apply(request);
                    movie = movieservice.create(movie);
            return ResponseEntity.created(builder.pathSegment("api", "directors", "{id_dir}", "movies", "{id}")
                    .buildAndExpand(director.get().getId(), movie.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletemovie(@PathVariable("id_dir") Long id_dir, @PathVariable("id") long id) {
        Optional<Movie> movie = movieservice.find(id_dir, id);
        if (movie.isPresent()) {
            movieservice.delete(movie.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putmovie(@PathVariable("id_dir") Long id_dir, @RequestBody putmoviereq request, @PathVariable("id") long id) {
        Optional<Movie> movie = movieservice.find(id_dir, id);
        if (movie.isPresent()) {
            putmoviereq.dtoToEntityUpdater().apply(movie.get(), request);
            movieservice.update(movie.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
