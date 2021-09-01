package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Image;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;

//import com.mta.dogwalkerserver.repo.FileSystemRepo;
//import com.mta.dogwalkerserver.repo.ImageDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/DogWalker")
@RestController
public class DogWalkerController {
    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private ImageDbRepo imageDbRepo;
//
//    @Autowired
//    private FileSystemRepo fileSystemRepo;

    @GetMapping(value = "/id/{id}")
    public DogWalker getDogWalkerByIdV1(@PathVariable int id) {
        return dogWalkerRepo.findById(id).get();
    }


    @GetMapping(value = "/address/id/{id}")
    public Address getAddressV1(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getAddress_Id();
    }


    @GetMapping(value = "/contact/id/{id}")
    public Set<DogOwner> getContactV1(@PathVariable int id){
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
    public String deleteDogWalkerV1(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        dogWalkerRepo.delete(dogWalker);
        return "deleted " + id;
    }



    @PutMapping(value = "/update/id/{id}")
    public DogWalker updateUserV1(@PathVariable int id, @RequestBody DogWalker dogWalker) throws Exception {

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



    @PostMapping(path = "/DogWalkersAroundMe")
    public List<DogWalker> findDogWalkersAroundMe(@RequestBody LinkedHashMap payload) {

        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations", null);
        List<DogWalker> dogWalkers = dogWalkerRepo.getDogWalkersInGeoHashLocations(geoHashLocations);

        return dogWalkers;
    }



    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] downloadImage(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.getById(id);
        byte[] image = dogWalker.getImage().getContent();

        return image;
    }


    @PostMapping("/uploadImage/id/{id}")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        Image dbImage = new Image();

        dbImage.setName(imageFile.getName());
        try {
            dbImage.setContent(imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dogWalker.setImage(dbImage);
        dogWalkerRepo.save(dogWalker);
        return "save";
    }


}

