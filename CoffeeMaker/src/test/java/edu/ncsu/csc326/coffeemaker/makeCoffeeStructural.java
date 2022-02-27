package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class makeCoffeeStructural {

    private CoffeeMaker cm;
    private Recipe r;

    @BeforeEach
    private void setup() throws Exception {
        cm = new CoffeeMaker();
        r = new Recipe();
        r.setPrice("100");
        r.setAmtChocolate("10");
        r.setAmtMilk("10");
        r.setAmtSugar("10");
        r.setAmtCoffee("10");
    }

    @Test
    public void testMakeCoffee_null_recipe(){

        assertEquals(100, cm.makeCoffee(1, 5));
    }

    @Test
    public void testMakeCoffee_normal() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setName("Boza");
        r.setPrice("50");
        cm.addRecipe(r);

        assertEquals(50, cm.makeCoffee(1, 100));
    }

    @Test
    public void testMakeCoffee_not_enough_inventory() throws RecipeException {
        CoffeeMaker cm = new CoffeeMaker();
        Recipe r = new Recipe();
        r.setName("Mishmash");
        r.setPrice("50");
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
        r.setName("Mishmash");
        r.setPrice("50");
        cm.addRecipe(r);

        assertEquals(25, cm.makeCoffee(1, 25));
    }

}
