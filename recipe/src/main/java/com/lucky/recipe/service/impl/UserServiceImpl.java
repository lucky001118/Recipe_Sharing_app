package com.lucky.recipe.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucky.recipe.config.JwtProvider;
import com.lucky.recipe.model.User;
import com.lucky.recipe.repository.UserRepo;
import com.lucky.recipe.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> optional = userRepo.findById(userId);

        if (optional.isPresent()) {
            return optional.get();
        }
        throw new Exception("User is not found with provided user id "+userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if (email==null) {
            throw new Exception("Provide a valid token ");
        }

        User user = userRepo.findByEmail(email);
        if (user==null) {
            throw new Exception("User not found with this email "+email);
        }
        return user;
    }

}
