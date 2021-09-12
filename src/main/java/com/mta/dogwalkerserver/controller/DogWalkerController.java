package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Image;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequestMapping("/api/DogWalker")
@RestController
public class DogWalkerController {
    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


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


    @PostMapping(value = "/save")
    public DogWalker saveDogWalker(@RequestBody DogWalker dogWalker) throws Exception {

        DogOwner dogOwnercheck = dogOwnerRepo.findByUserName(dogWalker.getUserName());
        DogWalker dogWalkercheck = dogWalkerRepo.findByUserName(dogWalker.getUserName());
        if(dogOwnercheck != null || dogWalkercheck != null)
        {
            Exception excpt = new Exception("already have a user with this username! please choose a unique username");
            throw excpt;
        }

        dogWalker.setRoles("DOG_WALKER_ROLE");
        dogWalker.setActive(true);
        dogWalker.setPassword(passwordEncoder.encode(dogWalker.getPassword()));
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
    public DogWalker updateUser(@PathVariable int id, @RequestBody DogWalker dogWalker) throws Exception {

        DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();
        String updatedDogWalkerUserName = updateDogWalker.getUserName();

        DogOwner dogOwnercheck = dogOwnerRepo.findByUserName(dogWalker.getUserName());
        DogWalker dogWalkercheck = dogWalkerRepo.findByUserName(dogWalker.getUserName());

        if (dogOwnercheck != null || dogWalkercheck != null) {
            if (dogWalkercheck != null){
                if(dogWalkercheck.getId() != updateDogWalker.getId()) {
                    Exception excpt = new Exception("already have a user with this username! please choose a unique username");
                    throw excpt;
                }
            }
            else if (dogOwnercheck != null){
                Exception excpt = new Exception("already have a user with this username! please choose a unique username");
                throw excpt;
            }
        }

        //DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();

        updateDogWalker.setHourSalary(dogWalker.getHourSalary());
//        updateDogWalker.setContact(dogWalker.getContact());
        updateDogWalker.setAddress_Id(dogWalker.getAddress_Id());
        updateDogWalker.setImage(dogWalker.getImage());
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

    @GetMapping(value = "/getDogOwnerIdByUserName/userName/{userName}")
    public Integer getDogOwnerIdByUserName(@PathVariable String userName) {
        return dogOwnerRepo.findByUserName(userName).getId();
    }


    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] downloadImage(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.getById(id);
        byte[] image = dogWalker.getImage().getContent();

        return image;
    }


    @PostMapping("/uploadImage/id/{id}")
    public String uploadImage(@RequestBody Map<String, String> imageFile, @PathVariable int id) {
        byte[] decodedBytes = Base64.getDecoder().decode(imageFile.get("imageFile"));

        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        Image dbImage = new Image();

        dbImage.setName("imageFile");
        dbImage.setContent(decodedBytes);


        dogWalker.setImage(dbImage);
        dogWalkerRepo.save(dogWalker);
        return "save";
    }
}

