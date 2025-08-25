package com.example.project.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class loginController {



    @GetMapping("/")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return "歡迎"+username+"來到首頁";
    }

    @GetMapping("/list")
    public List<String> getList() {
        return Arrays.asList("item1", "item2", "item3");
    }


}
