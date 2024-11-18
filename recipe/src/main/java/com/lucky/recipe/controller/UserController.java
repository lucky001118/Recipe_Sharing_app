package com.lucky.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.lucky.recipe.model.User;
import com.lucky.recipe.repository.UserRepo;
import com.lucky.recipe.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception{
        User isUserExsit = userRepo.findByEmail(user.getEmail());
        if(isUserExsit!=null){
            throw new Exception("User is exist with this email "+user.getEmail());
        }
        User savedUser = userRepo.save(user);

        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userRepo.deleteById(userId); 
        return "User deleted successfully!";
    }

    @GetMapping("/users")
    public List<User> getAllUSers(){
        List<User> allUsers = userRepo.findAll();
        return allUsers;
    }

    // --------------------------------------------------------

    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);
        return user;
    }
}
