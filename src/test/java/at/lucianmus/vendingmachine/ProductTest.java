package at.lucianmus.vendingmachine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lucian on 8-2-18
 */

public class ProductTest {

    @Test
    public void testProduct() {
        Product testProduct = new Product("test", 100);
        assertTrue(testProduct.name.equals("test"));
        assertTrue(testProduct.price == 100);
        assertTrue(testProduct.toString().equals("test:1.0"));
    }
}
