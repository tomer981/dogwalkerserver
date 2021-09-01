package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;


//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories

@RepositoryRestResource(path = "DogWalkers")
public interface DogWalkerRepo extends JpaRepository<DogWalker, Integer> {
    @Query(value = "SELECT dw FROM dog_walker dw WHERE " +
            "SUBSTRING(dw.address_Id.geoHashLocation,1,6) IN :geoHashLocations OR " +
            "SUBSTRING(dw.address_Id.geoHashLocation,1,7) IN :geoHashLocations")
    List<DogWalker> getDogWalkersInGeoHashLocations(@Param("geoHashLocations") List<String> geoHashLocations);


}


