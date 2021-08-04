package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Image;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;

//import com.mta.dogwalkerserver.repo.FileSystemRepo;
//import com.mta.dogwalkerserver.repo.ImageDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/DogWalker")
@RestController
public class DogWalkerController {
    @Autowired
    private DogWalkerRepo dogWalkerRepo;

//    @Autowired
//    private ImageDbRepo imageDbRepo;
//
//    @Autowired
//    private FileSystemRepo fileSystemRepo;

    @GetMapping(value = "/id/{id}")
    public DogWalker getDogWalkerByIdV1(@PathVariable int id) {
        return dogWalkerRepo.findById(id).get();
    }

    @GetMapping(value = "/")
    public DogWalker getDogWalkerByIdV2(@RequestParam(name = "id") int id) {
        return dogWalkerRepo.findById(id).get();
    }

    @GetMapping(value = "/address/id/{id}")
    public Address getAddressV1(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getAddress_Id();
    }

    @GetMapping(value = "/address/")
    public Address getAddressV2(@RequestParam(name = "id") int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getAddress_Id();
    }

    @GetMapping(value = "/contact/id/{id}")
    public Set<DogOwner> getContactV1(@PathVariable int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getContact();
    }

    @GetMapping(value = "/contact/")
    public Set<DogOwner> getContactV2(@RequestParam(name = "id") int id){
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        return dogWalker.getContact();
    }

    @PostMapping(value = "/save")
    public DogWalker saveDogWalker(@RequestBody DogWalker dogWalker) {
        dogWalkerRepo.save(dogWalker);
        return dogWalker;
    }

    @DeleteMapping(value = "/id/{id}")
    public String deleteDogWalkerV1(@PathVariable int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        dogWalkerRepo.delete(dogWalker);
        return "deleted " + id;
    }

    @DeleteMapping(value = "/")
    public String deleteDogWalkerV2(@RequestParam(name = "id") int id) {
        DogWalker dogWalker = dogWalkerRepo.findById(id).get();
        dogWalkerRepo.delete(dogWalker);
        return "deleted " + id;
    }


    @PutMapping(value = "/update/id/{id}")
    public DogWalker updateUserV1(@PathVariable int id, @RequestBody DogWalker dogWalker) {
        DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();
        updateDogWalker.setHourSalary(dogWalker.getHourSalary());
//        updateDogWalker.setContact(dogWalker.getContact());
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


    @PutMapping(value = "/update/")
    public DogWalker updateUserV2(@RequestParam(name = "id") int id, @RequestBody DogWalker dogWalker) {
        DogWalker updateDogWalker = dogWalkerRepo.findById(id).get();
        updateDogWalker.setHourSalary(dogWalker.getHourSalary());
//        updateDogWalker.setContact(dogWalker.getContact());
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
    public List<DogWalker> findDogWalkersAroundMe(@RequestBody LinkedHashMap payload) {

        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations", null);
        List<DogWalker> dogWalkers = dogWalkerRepo.getDogWalkersInGeoHashLocations(geoHashLocations);

        return dogWalkers;
    }


//    @PostMapping("/uploadImage/id/{id}")
//    Long uploadImageV1(@RequestParam MultipartFile multipartImage) throws Exception {
//        Image dbImage = new Image();
//        dbImage.setName(multipartImage.getName());
//        dbImage.setContent(multipartImage.getBytes());
//
//        return imageDbRepo.save(dbImage).getImage_Id();
//    }
//
//
//    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public Resource downloadImage(@PathVariable int imageId) {
//        byte[] image = imageDbRepo.findById(imageId)
//                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
//                                  .getContent();
//
//        return (Resource) new ByteArrayResource(image);
//    }
//
//    public int save(byte[] bytes, String imageName) throws Exception {
//        String location = fileSystemRepo.save(bytes, imageName);
//
//        return fileSystemRepo.save(new Image(imageName, location)).getId();
//    }




}

