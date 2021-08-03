package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "DogOwners")
public interface DogOwnerRepo extends JpaRepository<DogOwner,Integer> {
    @Query(value = "SELECT do FROM dog_owner do WHERE " +
            "SUBSTRING(do.address_Id.geoHashLocation,1,6) IN :geoHashLocations OR " +
            "SUBSTRING(do.address_Id.geoHashLocation,1,7) IN :geoHashLocations")
    List<DogOwner> getDogOwnersInGeoHashLocations(@Param("geoHashLocations") List<String> geoHashLocations);


}
//getDogOwnersAroundMe
