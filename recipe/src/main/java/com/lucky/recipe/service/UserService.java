package com.lucky.recipe.service;

import com.lucky.recipe.model.User;

public interface UserService {

    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;
}
