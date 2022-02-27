package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class makeCoffeeStructural {

    /*private CoffeeMaker cm;
    private Recipe r;*/

    /*@BeforeAll
    private void setUp() throws Exception {
        cm = new CoffeeMaker();
        r = new Recipe();
        r.setPrice("100");
        r.setAmtChocolate("10");
        r.setAmtMilk("10");
        r.setAmtSugar("10");
        r.setAmtCoffee("10");
    }*/

    @Test
    public void testMakeCoffee_null_recipe() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setPrice("100");
        r.setAmtChocolate("10");
        r.setAmtMilk("10");
        r.setAmtSugar("10");
        r.setAmtCoffee("10");

        cm.addRecipe(r);
        cm.deleteRecipe(0);

        assertEquals(5, cm.makeCoffee(0, 5));

    }

    @Test
    public void testMakeCoffee_normal() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setPrice("50");
        cm.addRecipe(r);


        assertEquals(50, cm.makeCoffee(0, 100));
    }

    @Test
    public void testMakeCoffee_not_enough_inventory() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setAmtChocolate("100");
        r.setAmtCoffee("100");
        r.setAmtMilk("100");
        r.setAmtSugar("100");
        cm.addRecipe(r);

        assertEquals(100, cm.makeCoffee(1, 100));
    }

    @Test
    public void testMakeCoffee_not_enough_money() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setPrice("500");
        cm.addRecipe(r);
        Recipe[] r1 = cm.getRecipes();
        System.out.println(r1.length);
        assertEquals(25, cm.makeCoffee(3, 25));
    }

}
