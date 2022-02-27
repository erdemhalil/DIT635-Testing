package test.java.edu.ncsu.csc326;

import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.function.Try;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	@Before
	public void setUp() throws Exception {
		cm = new CoffeeMaker();

		// Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		cm.addRecipe(r1);

		// Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		cm.addRecipe(r2);

		// Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		cm.addRecipe(r3);

		// Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		cm.addRecipe(r4);
	}

	@org.junit.jupiter.api.Test
	public void testCheckInventory() {
		String inventory = cm.checkInventory();
		String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		// We start with 15 in inventory.
		assertEquals(expected, inventory);
	}

	@org.junit.jupiter.api.Test
	public void testMakeCoffee() {
		assertEquals(20, cm.makeCoffee(2, 120)); // Make a coffee and check the change
		String inventory = cm.checkInventory();
		String expected = "Coffee: 12\nMilk: 12\nSugar: 14\nChocolate: 15\n";
		assertEquals(expected, inventory); // Check if the remaining ingrediants are correct
	}

	@org.junit.jupiter.api.Test
	public void testAddInventory_Normal() {
		try {
			cm.addInventory("4", "7", "0", "9"); // Coffee, Milk, Sugar, Chocolate
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
		String inventory = cm.checkInventory();
		String expected = "Coffee: 16\nMilk: 19\nSugar: 14\nChocolate: 24\n";
		// We add some inventory
		assertEquals(expected, inventory);
	}

	@org.junit.jupiter.api.Test
	public void testAddInventoryException() {
		Throwable exception = assertThrows(
				InventoryException.class, () -> {
					cm.addInventory("4", "-1", "asdf", "3"); // Should throw an InventoryException
				});
	}

	@org.junit.jupiter.api.Test
	public void testAddRecepi() {
		Recipe r5 = new Recipe();
		try {
			r5.setName("Milk");
			r5.setAmtChocolate("0");
			r5.setAmtCoffee("0");
			r5.setAmtMilk("4");
			r5.setAmtSugar("1");
			r5.setPrice("40");
			cm.addRecipe(r5); // We try to add a normal recepi
		} catch (RecipeException e) {
			fail("RecipeException should not be throun.");
		}
		Recipe[] recipes = cm.getRecipes();
		assertEquals(4, recipes.length); // We check if the number of recipes is correct
		assertEquals(r5, recipes[4]); // We check if the recipe is correct
	}

	@org.junit.jupiter.api.Test
	public void testAddRecepiException() {
		Recipe r5 = new Recipe();
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					r5.setName("Milk");
					r5.setAmtChocolate("aswd");
					r5.setAmtCoffee("fgd");
					r5.setAmtMilk("4");
					r5.setAmtSugar(null);
					r5.setPrice("99999999999999999999999999");
					cm.addRecipe(r5);
				}); // Should throw an Exception
	}

	@org.junit.jupiter.api.Test
	public void testEditRecepi() {
		Recipe[] recipes = cm.getRecipes();
		Recipe newRecipe = new Recipe();
		String recipeEdited;
		try {
			newRecipe.setName("Milk");
			newRecipe.setPrice("20");
			newRecipe.setAmtCoffee("1");
			newRecipe.setAmtMilk("4");
			newRecipe.setAmtSugar("2");
			newRecipe.setAmtChocolate("0");
			recipeEdited = cm.editRecipe(0, newRecipe); // We try to edit the first recepi
		} catch (RecipeException e) {
			fail("RecipeException should not be throun.");
		}
		assertEquals(recipeEdited, newRecipe); // Check if they are the same
	}

	@org.junit.jupiter.api.Test
	public void testEditRecepiException() {
		Recipe[] recipes = cm.getRecipes();
		Recipe newRecipe = new Recipe();
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					newRecipe.setName("123");
					newRecipe.setPrice(null);
					newRecipe.setAmtCoffee("a");
					newRecipe.setAmtMilk("b");
					newRecipe.setAmtSugar("2");
					newRecipe.setAmtChocolate("x");
					cm.editRecipe(0, newRecipe);
				}); // Should throw an Exception
	}

	@org.junit.jupiter.api.Test
	public void testDeleteARecepi() {
		try {
			Recipe[] recipes = cm.getRecipes();
			for (int i = 0; i < recipes.length; i++) {
				if (recipes[i] != null) {
					cm.deleteRecipe(i); // Delete all the recepis
				}
			}
		} catch (RecipeException e) {
			fail("RecipeException should not be throun.");
		}
		assertTrue(cm.getRecipes() == null); // Check if there are any recepies left
	}

}
