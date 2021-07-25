package com.mta.dogwalkerserver.controller;


import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

//https://www.baeldung.com/spring-requestmapping
//@RequestMapping("/api")
@RestController
public class ApiControllers {

    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

//    private DogWalker dogWalker1 = null;

    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
    }

    @GetMapping(value = "/getDogWalkers")
    public List<DogWalker> getDogWalkers(){
        return dogWalkerRepo.findAll();
    }

    @GetMapping(value = "/getDogOwners")
    public List<DogOwner> getDownerOwner(){
        return dogOwnerRepo.findAll();
    }

    @GetMapping(value = "/getDogWalkerById/{id}")
    public DogWalker getDogWalkerById(@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker;
    }

    @GetMapping(value = "/getAddress/{id}")
    public String getAddress(@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        Address address = dogWalker.getAddress_Id();
//        System.out.println(address.getGeoHash());
        return address.getGeoHashLocation();
    }


    @PostMapping(value = "/saveDogWalker")
    public String saveDogWalker(@RequestBody DogWalker dogWalker){
        dogWalkerRepo.save(dogWalker);
//        if (dogWalker1 == null){
//            dogWalker1 = dogWalker;
//        }
//        else{
//            dogWalker1.getContact().add(dogWalker1);
//        }
        return "save...";
    }

    @PostMapping(value = "/saveDogOwner")
    public String saveDogWalker(@RequestBody DogOwner dogOwner){
        dogOwnerRepo.save(dogOwner);
        return "save...";
    }

    @PutMapping(value = "/update/{id}")
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
