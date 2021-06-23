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

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam(name = "year", required = false) Integer year) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYearEquals(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity<List<Whisky>> getWhiskyByDistillery(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "age", required = false) int age) {
        if (name != null && age != 0) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(name, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//        return new ResponseEntity<>(whiskyRepository.findByDistillery(distillery).getName(), HttpStatus.OK);
    }
}




