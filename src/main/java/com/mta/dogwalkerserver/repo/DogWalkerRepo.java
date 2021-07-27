package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories

@RepositoryRestResource(path = "dogWalker")
public interface DogWalkerRepo extends JpaRepository<DogWalker,Integer> {
//    @Query("select * from dog_walker_app.dog_walker where dog_walker_app.GeoHashLocation = ?1")
//    List<DogWalker> findByEmailAddress(String geoHashLocation);
}
