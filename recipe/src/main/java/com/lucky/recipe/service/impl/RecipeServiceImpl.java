package com.lucky.recipe.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucky.recipe.model.Recipe;
import com.lucky.recipe.model.User;
import com.lucky.recipe.repository.RecipeRepo;
import com.lucky.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe creatRecipe = new Recipe();
        creatRecipe.setTitle(recipe.getTitle());
        creatRecipe.setImage(recipe.getImage());
        creatRecipe.setDescription(recipe.getDescription());
        creatRecipe.setUser(user);
        creatRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepo.save(creatRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        if (recipeOptional.isPresent()) {
            return recipeOptional.get();
        }
        throw new Exception("The recipe is not found with id "+id);
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
       findRecipeById(id);
       recipeRepo.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);

        if(recipe.getTitle()!=null){
            oldRecipe.setTitle(recipe.getTitle());
        }else if(recipe.getImage()!=null){
            oldRecipe.setImage(recipe.getImage());
        }if(recipe.getDescription()!=null){
            oldRecipe.setDescription(recipe.getDescription());
        }

        return recipeRepo.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);

        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        }else{
            recipe.getLikes().add(user.getId());
        }
        
        return recipeRepo.save(recipe);
    }

}
