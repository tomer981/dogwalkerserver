package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogWalkerRepo extends JpaRepository<DogWalker,Integer> {
}
