package com.isa.lab1.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo  extends JpaRepository<Movie, Long> {

}
