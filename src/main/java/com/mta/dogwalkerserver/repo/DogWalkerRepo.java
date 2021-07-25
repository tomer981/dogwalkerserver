package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories

@RepositoryRestResource(path = "dogWalker")
public interface DogWalkerRepo extends JpaRepository<DogWalker,Integer> {
}
