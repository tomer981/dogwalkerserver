package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.Dog;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/DogOwner")
@RestController
public class DogOwnerController {
    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @GetMapping(value = "/id/{id}")
    public DogOwner getDogOwnerById(@PathVariable int id) {
        return dogOwnerRepo.findById(id).get();
    }

    @GetMapping(value = "/address/id/{id}")
    public Address getAddress(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        return dogOwner.getAddress_Id();
    }

    @GetMapping(value = "/contact/id/{id}")
    public Set<DogWalker> getContact(@PathVariable int id){
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        return dogOwner.getContact();
    }

    @GetMapping(value = "/id/{id}/dog")
    public Dog getDog(@PathVariable int id){
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        return dogOwner.getDog_Id();
    }

    //    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody
//    byte[] downloadImage(@PathVariable int id) {
//        DogOwner dogOwner = dogOwnerRepo.getById(id);
//        byte[] image = dogOwner.getImage_Id().getContent();
//
//        return image;
//    }
//
//    @PostMapping("/uploadImage/id/{id}")
//    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable int id) {
//        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
//        Image dbImage = new Image();
//
//        dbImage.setName(imageFile.getName());
//        try {
//            dbImage.setContent(imageFile.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        dogOwner.setImage_Id(dbImage);
//        dogOwnerRepo.save(dogOwner);
//        return "save";
//    }

    @PostMapping(value = "/save")
    public DogOwner saveDogOwner(@RequestBody DogOwner dogOwner){
        dogOwnerRepo.save(dogOwner);
        return dogOwner;
    }

    @DeleteMapping(value = "/id/{id}")
    public String deleteDogWalker(@PathVariable int id){
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        dogOwnerRepo.delete(dogOwner);
        return "deleted " + id;
    }

    @PutMapping(value = "/update/id/{id}")
    public DogOwner updateUser(@PathVariable int id,@RequestBody DogOwner dogOwner){
        DogOwner updateDogOwner = dogOwnerRepo.findById(id).get();
        updateDogOwner.setContact(dogOwner.getContact());
        updateDogOwner.setAddress_Id(dogOwner.getAddress_Id());
//        updateDogOwner.setImage_Id(dogOwner.getImage_Id());
        updateDogOwner.setFirstName(dogOwner.getFirstName());
        updateDogOwner.setLastName(dogOwner.getLastName());
        updateDogOwner.setUserName(dogOwner.getUserName());
        updateDogOwner.setEmail(dogOwner.getEmail());
        updateDogOwner.setAboutMyself(dogOwner.getAboutMyself());
        updateDogOwner.setBirthDay(dogOwner.getBirthDay());
        updateDogOwner.setPhone(dogOwner.getPhone());
        updateDogOwner.setGender(dogOwner.getGender());
        dogOwnerRepo.save(updateDogOwner);
        return updateDogOwner;
    }

    @PostMapping(path = "/DogOwnersAroundMe")
    public List<DogOwner> findDogWalkerAroundMe(@RequestBody LinkedHashMap payload) {

        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations", null);
        List<DogOwner> dogOwner = dogOwnerRepo.getDogOwnersInGeoHashLocations(geoHashLocations);

        return dogOwner;
    }
}
