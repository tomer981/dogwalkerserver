package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/DogWalker")
@RestController
public class DogWalkerController {
    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @GetMapping(value = "/id/{id}")
    public DogWalker getDogWalkerById(@PathVariable int id) {
        return dogWalkerRepo.findById(id).get();
    }

    @GetMapping(value = "/address/id/{id}")
    public Address getAddress(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getAddress_Id();
    }

    @GetMapping(value = "/contact/id/{id}")
    public Set<DogOwner> getContact(@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getContact();
    }

//    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] downloadImage(@PathVariable int id) {
//        DogWalker dogWalker = dogWalkerRepo.getById(id);
//        byte[] image = dogWalker.getImage_Id().getContent();
//
//        return image;
//    }

//
//    @PostMapping("/uploadImage/id/{id}")
//    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable int id){
//        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
//        Image dbImage = new Image();
//
//        dbImage.setName(imageFile.getName());
//        try {
//            dbImage.setContent(imageFile.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        dogWalker.setImage_Id(dbImage);
//        dogWalkerRepo.save(dogWalker);
//        return "save";
//    }

    @PostMapping(value = "/save")
    public DogWalker saveDogWalker(@RequestBody DogWalker dogWalker) {
        dogWalkerRepo.save(dogWalker);
        return dogWalker;
    }

    @DeleteMapping(value = "/id/{id}")
    public String deleteDogWalker(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        dogWalkerRepo.delete(dogWalker);
        return "deleted " + id;
    }

    @PutMapping(value = "/update/id/{id}")
    public DogWalker updateUser(@PathVariable int id, @RequestBody DogWalker dogWalker) {
        DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();
        updateDogWalker.setHourSalary(dogWalker.getHourSalary());
        updateDogWalker.setContact(dogWalker.getContact());
        updateDogWalker.setAddress_Id(dogWalker.getAddress_Id());
        updateDogWalker.setImage_Id(dogWalker.getImage_Id());
        updateDogWalker.setFirstName(dogWalker.getFirstName());
        updateDogWalker.setLastName(dogWalker.getLastName());
        updateDogWalker.setUserName(dogWalker.getUserName());
        updateDogWalker.setEmail(dogWalker.getEmail());
        updateDogWalker.setAboutMyself(dogWalker.getAboutMyself());
        updateDogWalker.setBirthDay(dogWalker.getBirthDay());
        updateDogWalker.setPhone(dogWalker.getPhone());
        updateDogWalker.setGender(dogWalker.getGender());
        dogWalkerRepo.save(updateDogWalker);
        return updateDogWalker;
    }

    @PostMapping(path = "/DogWalkersAroundMe")
    public List<DogWalker> findDogWalkerAroundMe(@RequestBody LinkedHashMap payload) {
        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations", null);
        List<DogWalker> dogWalkers = dogWalkerRepo.getDogWalkersInGeoHashLocations(geoHashLocations);

        return dogWalkers;
    }
}

