package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDbRepo extends JpaRepository<Image, Integer> {}
