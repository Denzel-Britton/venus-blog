package com.example.venusblog.controller;

import com.example.venusblog.FakeDogFactGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogFactsController {

    @GetMapping("/dogo")

    public String fetchDogFact() {
        return "Puppies are a pain in the butt!";
    }

    @GetMapping("/dogo/{id}")

    public String fetchSingleDogFact(@PathVariable long id) {
        return FakeDogFactGateway.fetchDogFactById(id);
    }
}