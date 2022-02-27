package edu.ncsu.csc326.coffeemaker;

import org.junit.Test;

public class addRecipeStructural {

    @Test
    public void testAddRecipe_Twice(){
        RecipeBook rb = new RecipeBook();
        Recipe r = new Recipe();
        rb.addRecipe(r);
        rb.addRecipe(r);
    }
}
