package com.mta.dogwalkerserver.controller;


import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Image;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

//https://www.baeldung.com/spring-requestmapping
//@RequestMapping("/api")
@RestController
public class ApiControllers {

    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private DogOwnerRepo dogOwnerRepo;


    private DogWalker dogWalker1 = null;

    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
    }

    @GetMapping(value = "/getDogWalkers")
    public List<DogWalker> getDogWalkers(){
        return dogWalkerRepo.findAll();
    }

//    TODO: getContact of DogWalker/DogOwner by DogOwner/DogWalker id

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
        return "save...";
    }

    @PostMapping(value = "/saveDogOwner")
    public String saveDogWalker(@RequestBody DogOwner dogOwner){
        dogOwnerRepo.save(dogOwner);
        DogWalker updateDogWalker = dogWalkerRepo.findById(1).get();
        updateDogWalker.getContact().add(dogOwner);
        dogWalkerRepo.save(updateDogWalker);
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



    @PostMapping("/uploadImage/{id}")
    public String uploadDogWalkerImage(@RequestParam("imageFile")MultipartFile imageFile,@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        Image dbImage = new Image();

        dbImage.setName(imageFile.getName());
        try {
            dbImage.setContent(imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dogWalker.setImage_Id(dbImage);
        dogWalkerRepo.save(dogWalker);
        return "save";
    }

    @GetMapping(path = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadImage(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.getById(id);
        byte[] image = dogWalker.getImage_Id().getContent();

        return image;
    }

    @PostMapping(path = "/findDogWalkerAroundMe")
    public List<DogWalker> findDogWalkerAroundMe(@RequestBody LinkedHashMap payload){
        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations",null);



        return null;
    }
}
