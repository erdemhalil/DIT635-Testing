package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class setPriceStructural {

    @Test
    public void testSetPrice_normal() throws RecipeException {
        Recipe r = new Recipe();
        r.setPrice("10");
        assertEquals(10, r.getPrice());
    }

    @Test
    public void testSetPrice_String(){
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> {
            r.setPrice("Test");
        });
    }

    @Test
    public void testSetPrice_neg_number(){
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> {
            r.setPrice("-100");
        });
    }
}
