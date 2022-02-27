package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import org.junit.Test;

import static org.junit.Assert.*;

public class addSugarStructural {

    @Test
    public void testAddSugar_normal() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addSugar("10");
        assertEquals(25, inv.getSugar());
    }

    @Test
    public void testAddSugar_String(){
        Inventory inv = new Inventory();
        assertThrows(InventoryException.class, () -> {
            inv.addSugar("Test");
        });
    }

    @Test
    public void testAddSugar_neg_number(){
        Inventory inv = new Inventory();
        assertThrows(InventoryException.class, () -> {
            inv.addSugar("-100");
        });
    }

}
