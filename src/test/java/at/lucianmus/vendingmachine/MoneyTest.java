package at.lucianmus.vendingmachine;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * @author Lucian on 8-2-18
 */

public class MoneyTest {

    @Test
    public void testAddCoins() {
        Money testMoney = new Money();
        testMoney.addCoin(Coin.fiftyCent);
        assertTrue(testMoney.getTotal() == 50);
        testMoney.addCoins(Coin.fiveCent, Coin.oneEuro);
        assertTrue(testMoney.getTotal() == 155);
    }

    @Test
    public void testRemoveCoins() {
        Money testMoney = new Money();
        testMoney.addCoins(Coin.fiveCent, Coin.oneEuro, Coin.tenCent);
        testMoney.removeCoin(Coin.fiveCent);
        assertTrue(testMoney.getTotal() == 110);
        testMoney.removeCoins(Coin.oneEuro, Coin.tenCent);
        assertTrue(testMoney.getTotal() == 0);
    }

    @Test
    public void testRemoveUnexistingCoin() {
        Money testMoney = new Money();
        testMoney.addCoins(Coin.tenCent);
        assertFalse(testMoney.removeCoin(Coin.fiveCent));
    }

    @Test
    public void testIterator() {
        Money testMoney = new Money();
        testMoney.addCoins(Coin.fiveCent, Coin.oneEuro, Coin.twentyCent, Coin.fiveCent);
        List<Integer> coins = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        for (Map.Entry<Coin, Integer> c : testMoney) {
            coins.add(c.getKey().value);
            amounts.add(c.getValue());
        }
        assertTrue(amounts.equals(Arrays.asList(1, 1, 2)));
        assertTrue(coins.equals(Arrays.asList(Coin.oneEuro.value, Coin.twentyCent.value, Coin.fiveCent.value)));
    }
}
