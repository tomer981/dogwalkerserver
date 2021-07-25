package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;




@RepositoryRestResource(path = "dogOwner")
public interface DogOwnerRepo extends JpaRepository<DogOwner,Integer> {
}
