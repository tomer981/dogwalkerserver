package com.mta.dogwalkerserver.repo;

import com.mta.dogwalkerserver.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
