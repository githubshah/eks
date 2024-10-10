package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<Object> hello() {
        return restTemplate.getForEntity("https://abusiveexperiencereport.googleapis.com/$discovery/rest?version=v1", Object.class);
    }
}
