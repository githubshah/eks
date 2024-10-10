package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public ResponseEntity<Object> hello(@RequestParam("to") String name) {
        // Call Service B
        String serviceB = "http://" + name + ":8080/";
        System.out.println(serviceB);
        Object forObject = restTemplate.getForObject(serviceB, Object.class);
        Map<String, Object> stringObjectMap = Map.of("service-b-response", forObject);
        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }
}
