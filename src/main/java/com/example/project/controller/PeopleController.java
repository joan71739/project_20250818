package com.example.project.controller;

import com.example.project.model.People;
import com.example.project.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping("/people")
    public List<People> getPeople() {
        return peopleService.getAllPeople();
    }

    @PostMapping("/people")
    public ResponseEntity<People> addPeople(@RequestBody People people) {
        People created = peopleService.addPeople(people);
        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<People> updatePeople(@PathVariable String id, @RequestBody People people) {
        People updated = peopleService.updatePeople(id, people);
        if(!Objects.isNull(updated)){
            return ResponseEntity.ok(updated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/people")
    public ResponseEntity<Void> deletePeople(@RequestParam String id) {
        boolean deleted = peopleService.deletePeopleById(id);
        if(deleted) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
