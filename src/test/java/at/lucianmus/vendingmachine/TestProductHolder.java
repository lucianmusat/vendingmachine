package at.lucianmus.vendingmachine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lucian on 8-2-18
 */

public class TestProductHolder {

    @Test
    public void testAdd() {
        ProductHolder productHolder = new ProductHolder();
        productHolder.addProduct(new Product("Sprite", 100), 0, 1);
        assertTrue(productHolder.getPrice(0) == 100);
    }

    @Test(expected = IllegalStateException.class)
    public void testPop() {
        ProductHolder productHolder = new ProductHolder();
        productHolder.addProduct(new Product("Sprite", 100), 0, 1);
        assertTrue(productHolder.getPrice(0) == 100);
        productHolder.popProduct(0);
        assertFalse(productHolder.getPrice(0) == 100);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddToNonExistingSlot() {
        ProductHolder productHolder = new ProductHolder();
        productHolder.addProduct(new Product("Sprite", 100), 30, 1);
        assertFalse(productHolder.getPrice(30) == 100);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddNegativeAmount() {
        ProductHolder productHolder = new ProductHolder();
        productHolder.addProduct(new Product("Sprite", 100), 5, -5);
        // Should be no products in the slot
        assertFalse(productHolder.getPrice(5) == 100);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddTooManyProducts() {
        ProductHolder productHolder = new ProductHolder();
        productHolder.addProduct(new Product("Sprite", 100), 0, 10);
        // Should be no products in the slot
        assertFalse(productHolder.getPrice(0) == 100);
    }


}
