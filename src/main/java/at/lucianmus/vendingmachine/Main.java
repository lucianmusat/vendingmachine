package at.lucianmus.vendingmachine;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        Money vendingMoneyStack = new Money(new TreeMap<Coin, Integer>(){{
            put(Coin.oneCent, 10);
            put(Coin.twoCent, 10);
            put(Coin.fiveCent, 10);
            put(Coin.tenCent, 10);
            put(Coin.twentyCent, 10);
            put(Coin.fiftyCent, 10);
            put(Coin.oneEuro, 10);
            put(Coin.twoEuro, 10);
        }});
//        Money vendingMoneyStack = new Money(null);

        VendingMachine vendingMachine = new VendingMachine(vendingMoneyStack);

        System.out.println("Vending machine has " + vendingMachine.getTotal() + " cents");
        System.out.println("Coins currently inside the vending machine: " + vendingMachine.getInternalCoinStack());

        // Supply vending machine
        Product coke = new Product("Coke", 205);
        Product snickers = new Product("Snickers", 250);
        vendingMachine.addProduct(coke,0,2);
        vendingMachine.addProduct(snickers,1,1);
        // Try to add a product in a slot which already contains another product
        vendingMachine.addProduct(coke,1,1);

        // Try to get a product with no credit
        vendingMachine.buyProduct(0);

        // Add coins to the vending machine
        vendingMachine.addCoins(Coin.oneEuro, Coin.oneEuro, Coin.twoEuro);

        // Try again to buy
        vendingMachine.buyProduct(1);
        System.out.println("Credit in the vending machine: " + vendingMachine.getCurrentCredit());
        System.out.println("Coins currently inside the vending machine: " + vendingMachine.getInternalCoinStack());

        vendingMachine.addCoins(Coin.oneEuro, Coin.fiftyCent, Coin.twentyCent, Coin.fiveCent);
        vendingMachine.buyProduct(0);

        // Buy a sold out product
        vendingMachine.addCoins(Coin.twoEuro, Coin.oneEuro);
        vendingMachine.buyProduct(1);
    }

}
