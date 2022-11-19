package com.isa.lab1.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.isa.lab1.dto.getdirresp;
import com.isa.lab1.dto.getdirsresp;
import com.isa.lab1.dto.postdirreq;
import com.isa.lab1.dto.putdirreq;

@RestController
@RequestMapping("api/directors")
public class DirController {
    private DirectorService directorservice;

    @Autowired
    public DirController(DirectorService directorservice) {
        this.directorservice = directorservice;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<getdirsresp> getdirs() {
        List<Director> all = directorservice.findAll();
        Function<Collection<Director>, getdirsresp> mapper = getdirsresp.entityToDtoMapper();
        getdirsresp response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<getdirresp> getdir(@PathVariable("id") Long id) {
        return  directorservice.find(id)
                .map(value -> ResponseEntity.ok(getdirresp.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postmovie(@RequestBody postdirreq request, UriComponentsBuilder builder) {
        Director director = postdirreq
                .dtoToEntityMapper()
                .apply(request);
                director = directorservice.create(director);
        return ResponseEntity.created(builder.pathSegment("api", "directors", "{id}")
                .buildAndExpand(director.getid()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedirector(@PathVariable("id") long id) {
        Optional<Director> director = directorservice.find(id);
        if (director.isPresent()) {
            directorservice.delete(director.get().getid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putdirector(@RequestBody putdirreq request, @PathVariable("id") long id) {
        Optional<Director> director = directorservice.find(id);
        if (director.isPresent()) {
            putdirreq.dtoToEntityUpdater().apply(director.get(), request);
            directorservice.update(director.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
