package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whisky")
    public ResponseEntity<List<Whisky>> getAllWhiskys(@RequestParam(name = "year", required = false) Integer year) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYearEquals(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whisky/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

// http://localhost:8080/whiskys/distillery?name=Rosebank&age=12
    @GetMapping(value = "/whisky/distillery")
    public ResponseEntity<List<Whisky>> getWhiskyByDistillery(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "age", required = false) int age) {
        if (name != null && age != 0) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(name, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

// http://localhost:8080/whisky/region?region=Highland
    @GetMapping(value = "/whisky/region")
    public ResponseEntity<List<Whisky>> getWhiskyByRegion(@RequestParam(name = "region", required = false) String region) {
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}




