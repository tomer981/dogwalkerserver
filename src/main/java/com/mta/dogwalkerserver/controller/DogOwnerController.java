package com.mta.dogwalkerserver.controller;

import ch.hsr.geohash.GeoHash;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mta.dogwalkerserver.models.*;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;

import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RequestMapping("/api/DogOwner")
@RestController
public class DogOwnerController {
//GeoHash
    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //{{baseURL}}/api/DogOwner/id/1
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
    public Set<DogWalker> getContact(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        return dogOwner.getContact();
    }


    @GetMapping(value = "/getDogByDogOwner/id/{id}")
    public Dog getDog(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        return dogOwner.getDog();
    }


    @GetMapping(path = "/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] downloadImage(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.getById(id);
        byte[] image = dogOwner.getImage().getContent();

        return image;
    }


    @PostMapping("/uploadImage/id/{id}")
    public String uploadImage(@RequestBody Map<String, String> imageFile, @PathVariable int id) {//RequestBody
        byte[] decodedBytes = Base64.getDecoder().decode(imageFile.get("imageFile"));

        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        Image dbImage = new Image();

        dbImage.setName("imageFile");
        dbImage.setContent(decodedBytes);

        dogOwner.setImage(dbImage);
        dogOwnerRepo.save(dogOwner);
        return "save";
    }

    @GetMapping(path = "/dog/image/id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] downloadImageDog(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.getById(id);
        Dog dog = dogOwner.getDog();
        byte[] image = dog.getImage().getContent();

        return image;
    }

    @PostMapping("/dog/uploadImage/id/{id}")
    public String uploadImageDog(@RequestBody Map<String, String> imageFile, @PathVariable int id) {
        byte[] decodedBytes = Base64.getDecoder().decode(imageFile.get("imageFile"));

        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        Dog dog = dogOwner.getDog();
        Image dbImage = new Image();

        dbImage.setName("imageFile");
        dbImage.setContent(decodedBytes);

        dog.setImage(dbImage);
        dogOwner.setDog(dog);
        dogOwnerRepo.save(dogOwner);
        return "save";
    }


    @PostMapping(value = "/save")
    public DogOwner saveDogOwner(@RequestBody DogOwner dogOwner) throws Exception {

        DogOwner dogOwnercheck = dogOwnerRepo.findByUserName(dogOwner.getUserName());
        DogWalker dogWalkercheck = dogWalkerRepo.findByUserName(dogOwner.getUserName());
        if (dogOwnercheck != null || dogWalkercheck != null) {
            Exception excpt = new Exception("already have a user with this username! please choose a unique username");
            throw excpt;
        }

        dogOwner.setRoles("DOG_OWNER_ROLE");
        dogOwner.setActive(true);
        dogOwner.setPassword(passwordEncoder.encode(dogOwner.getPassword()));
        dogOwnerRepo.save(dogOwner);
        return dogOwner;
    }

    @GetMapping(value = "/getDogWalkerIdByUserName/userName/{userName}")
    public Integer getDogWalkerIdByUserName(@PathVariable String userName) {
        return dogWalkerRepo.findByUserName(userName).getId();
    }


    @DeleteMapping(value = "/id/{id}")
    public String deleteDogOwner(@PathVariable int id) {
        DogOwner dogOwner = dogOwnerRepo.findById(id).get();
        dogOwnerRepo.delete(dogOwner);
        return "deleted " + id;
    }


    @PutMapping(value = "/update/id/{id}")
    public DogOwner updateUser(@PathVariable int id, @RequestBody DogOwner dogOwner) throws Exception {

        DogOwner updateDogOwner = dogOwnerRepo.findById(id).get();
        String updatedDogOwnerUserName = updateDogOwner.getUserName();

        DogOwner dogOwnercheck = dogOwnerRepo.findByUserName(dogOwner.getUserName());
        DogWalker dogWalkercheck = dogWalkerRepo.findByUserName(dogOwner.getUserName());


        if (dogOwnercheck != null || dogWalkercheck != null) {
            if (dogOwnercheck != null) {
                if (dogOwnercheck.getId() != updateDogOwner.getId()) {
                    Exception excpt = new Exception("already have a user with this username! please choose a unique username");
                    throw excpt;
                }
            } else if (dogWalkercheck != null) {
                Exception excpt = new Exception("already have a user with this username! please choose a unique username");
                throw excpt;
            }
        }


        //DogOwner updateDogOwner = dogOwnerRepo.findById(id).get();

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
        updateDogOwner.setDog(dogOwner.getDog());
        dogOwnerRepo.save(updateDogOwner);
        return updateDogOwner;
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    @PostMapping(path = "/DogWalkersAroundMe/id/{id}")
    public String findDogWalkerAroundMe(@RequestBody LinkedHashMap payload, @PathVariable int id) {
        GeoHash dogOwnerGeoHash = GeoHash.fromGeohashString(dogOwnerRepo.findById(id).get().getAddress_Id().getGeoHashLocation());
        List<String> geoHashLocations = (List<String>) payload.getOrDefault("geoHashLocations", null);
        List<DogWalker> dogWalkers = dogWalkerRepo.getDogWalkersInGeoHashLocations(geoHashLocations);

        Map<DogWalker, Double> dogWalkersGeohash = new HashMap<>();

        for (DogWalker dogWalker : dogWalkers) {
            GeoHash dogWalkerGeoHash = GeoHash.fromGeohashString(dogWalker.getAddress_Id().getGeoHashLocation());
            Double distance = distance(dogWalkerGeoHash.getPoint().getLatitude(), dogWalkerGeoHash.getPoint().getLongitude(), dogOwnerGeoHash.getPoint().getLatitude(), dogOwnerGeoHash.getPoint().getLatitude());
            dogWalkersGeohash.put(dogWalker, distance);
        }

        List<Map.Entry<DogWalker, Double>> list = new LinkedList<Map.Entry<DogWalker, Double>>(dogWalkersGeohash.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<DogWalker, Double>>() {
            public int compare(Map.Entry<DogWalker, Double> o1, Map.Entry<DogWalker, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<DogWalker, Double> temp = new LinkedHashMap<DogWalker, Double>();
        for (Map.Entry<DogWalker, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        Gson gson = new Gson();
        JsonArray back = new JsonArray();

        for (Map.Entry<DogWalker, Double> entry : temp.entrySet()) {
            JsonObject info = new JsonObject();
            info.addProperty("dogWalker", gson.toJson(entry.getKey()));
            info.addProperty("distance", gson.toJson(entry.getValue()));
            back.add(info);
        }

        return back.toString();
    }

    @GetMapping(path = "/getDogWalkerByPrice")
    public List<DogWalker> getDogWalkerByPrice() {
        return dogWalkerRepo.findByOrderByHourSalaryAsc();
    }


}
