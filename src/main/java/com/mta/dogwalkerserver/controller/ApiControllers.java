package com.mta.dogwalkerserver.controller;


import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.AddressRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//https://www.baeldung.com/spring-requestmapping
//@RequestMapping("/api")
public class ApiControllers {

    @Autowired
    private DogWalkerRepo dogWalkerRepo;
    private AddressRepo addressRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
    }

    @GetMapping(value = "/getDogWalkers")
    public List<DogWalker> getUsers(){
        return dogWalkerRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveDogWalker(@RequestBody DogWalker dogWalker){
        dogWalkerRepo.save(dogWalker);
        return "save...";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable int id,@RequestBody DogWalker dogWalker){
        DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();
        updateDogWalker.setAddress_Id(dogWalker.getAddress_Id());
        updateDogWalker.setFirstName(dogWalker.getFirstName());
        updateDogWalker.setLastName(dogWalker.getLastName());
        dogWalkerRepo.save(updateDogWalker);
        return "updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteDogWalker(@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        dogWalkerRepo.delete(dogWalker);
        return "deleted " + id;
    }
}
