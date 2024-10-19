package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<Object> hello() {
        // Fetch the response from the external service
        ResponseEntity<Object> response = restTemplate.getForEntity(
                "https://abusiveexperiencereport.googleapis.com/$discovery/rest?version=v1", Object.class);

        // Return a new ResponseEntity with the same body and status
        // Make sure to avoid copying headers if they are problematic
        return ResponseEntity.status(response.getStatusCode())
                .body(response.getBody());
    }

    @GetMapping("/apple")
    public String hello1() {
        // Fetch the response from the external service
        return "apple";
    }

    @GetMapping("/mango")
    public ResponseEntity<Object>  hello2() {
        // Fetch the response from the external service
        return ResponseEntity.status(HttpStatus.OK)
                .body("mango");
    }
}
