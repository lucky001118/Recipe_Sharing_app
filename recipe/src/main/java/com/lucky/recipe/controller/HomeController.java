package com.lucky.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController(){
        return "well come";
    }

    // @PostMapping  -- is userd for the posting the data in the rest api
    // @PutMapping -- it is used for updating the existing data in the rest api
    // @DeleteMapping -- it is used to delete the partincular record in the rest api
}
