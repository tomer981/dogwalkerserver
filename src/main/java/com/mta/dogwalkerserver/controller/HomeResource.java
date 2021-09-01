package com.mta.dogwalkerserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() { return ("<h1>Welcome</h1>"); }

    @GetMapping("/dogwalker")
    public String dogwalker() {
        return ("<h1>Welcome DogWalker</h1>");
    }

    @GetMapping("/dogowner")
    public String dogowner() {
        return ("<h1>Welcome DogOwner</h1>");
    }

}
