package com.example.project.controller;

import com.example.project.model.People;
import com.example.project.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class loginController {



    @GetMapping("/")
    public String index(){
        return "歡迎來到首頁";
    }

    @GetMapping("/list")
    public List<String> getList() {
        return Arrays.asList("item1", "item2", "item3");
    }


}
