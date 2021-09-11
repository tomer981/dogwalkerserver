package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DogRepo extends JpaRepository<Dog, Integer> {
}
