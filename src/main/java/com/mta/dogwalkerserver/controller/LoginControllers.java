package com.mta.dogwalkerserver.controller;



import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//https://www.baeldung.com/spring-requestmapping
//https://www.baeldung.com/spring-security-login
@RequestMapping("/api")
@RestController
public class LoginControllers {

    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

}
