package at.lucianmus.vendingmachine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lucian on 8-2-18
 */

public class TestVendingMachine {

    @Test
    public void testInitialState() {
        Money money = new Money();
        money.addCoin(Coin.oneEuro);
        VendingMachine vendingMachine = new VendingMachine(money);
        assertTrue(vendingMachine.getTotal() == 100);
        assertTrue(vendingMachine.getCurrentCredit() == 0);
    }

    @Test
    public void testCredit() {
        VendingMachine vendingMachine = new VendingMachine(new Money());
        vendingMachine.addCoins(Coin.twentyCent);
        assertTrue(vendingMachine.getTotal() == 0);
        assertTrue(vendingMachine.getCurrentCredit() == 20);
    }

    @Test
    public void testBuyProduct() {
        VendingMachine vendingMachine = new VendingMachine(new Money());
        vendingMachine.addProduct(new Product("Fanta", 125), 0, 1);
        vendingMachine.addCoins(Coin.oneEuro);
        vendingMachine.buyProduct(0);
        // Not enough credit to buy the product, so nothing happens
        assertTrue(vendingMachine.getCurrentCredit() == 100);
        vendingMachine.addCoins(Coin.fiftyCent);
        // Now we actually have money to buy the product
        vendingMachine.buyProduct(0);
        assertTrue(vendingMachine.getCurrentCredit() == 0);
    }

    @Test
    public void testInternalMoney() {
        VendingMachine vendingMachine = new VendingMachine(new Money());
        vendingMachine.addProduct(new Product("Fanta", 120), 0, 1);
        vendingMachine.addCoins(Coin.oneEuro, Coin.fiftyCent, Coin.twentyCent);
        vendingMachine.buyProduct(0);
        // The 50 cent coin is dropped back as change
        assertTrue(vendingMachine.getInternalCoinStack().toString().equals("1 {1 euro coin} 1 {20 cent coin} "));
    }

    @Test
    public void testGiveChange() {
        VendingMachine vendingMachine = new VendingMachine(new Money());
        vendingMachine.addProduct(new Product("Fanta", 125), 0, 1);
        vendingMachine.addCoins(Coin.oneEuro, Coin.fiftyCent, Coin.twentyCent);
        vendingMachine.buyProduct(0);
        // The 20 cent coin is dropped back as change
        assertTrue(vendingMachine.getInternalCoinStack().toString().equals("1 {1 euro coin} 1 {50 cent coin} "));
    }

    @Test
    public void testBuyFromEmptySlot() {
        VendingMachine vendingMachine = new VendingMachine(new Money());
        vendingMachine.addCoins(Coin.twoEuro);
        vendingMachine.buyProduct(0);
        // Credit should still be there
        assertTrue(vendingMachine.getCurrentCredit() == 200);
    }



}
