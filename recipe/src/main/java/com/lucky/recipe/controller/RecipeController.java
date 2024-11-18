package com.lucky.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucky.recipe.model.Recipe;
import com.lucky.recipe.model.User;
import com.lucky.recipe.service.RecipeService;
import com.lucky.recipe.service.UserService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Recipe creatRecipe = recipeService.createRecipe(recipe, user);
        return creatRecipe;
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe,
            @PathVariable Long recipeId) throws Exception {
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, recipeId);
        return updatedRecipe;
    }

    @GetMapping()
    public List<Recipe> findAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @DeleteMapping("/{recipeId}")
    public String deletRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "REcipe deleted successfully!";
    }

    @PutMapping("/{recipeId}/like/user/{userId}")
    public Recipe likeRecipes(@PathVariable Long recipeId, 
            @PathVariable Long userId) throws Exception{
                User user = userService.findUserById(userId);
        return recipeService.likeRecipe(recipeId,user);
    }
}
