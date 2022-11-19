package com.isa.lab1.director;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepo  extends JpaRepository<Director, Long> {

}
