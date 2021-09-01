package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @Autowired
    private DogWalkerRepo dogWalkerRepo;


    private DogOwner findByDogOwnerName(String dogOwnerName) {
        return dogOwnerRepo.findByUserName(dogOwnerName);
    }

    private DogWalker findByDogWalkerName(String dogWalkerName) {
        return dogWalkerRepo.findByUserName(dogWalkerName);
    }



    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        DogOwner dogOwner = findByDogOwnerName(name);
        if (dogOwner != null) {

            MyUserDetails myUserDetails = new MyUserDetails(dogOwner);
            return myUserDetails;
        }


        DogWalker dogWalker = findByDogWalkerName(name);
        if (dogWalker != null) {

            MyUserDetails myUserDetails = new MyUserDetails(dogWalker);
            return myUserDetails;
        }
        else {
            throw new UsernameNotFoundException("No user Found");
        }
    }
}