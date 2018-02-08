package at.lucianmus.vendingmachine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lucian on 8-2-18
 */

public class CoinTest {

    @Test
    public void testCoin() {
        Coin oneEuro = Coin.oneEuro;
        Coin twoEuro = Coin.twoEuro;
        assertTrue("Wrong value for coin", oneEuro.value == 100);
        assertTrue("Wrong value for coins", oneEuro.compareTo(twoEuro) < 0);
    }
}
