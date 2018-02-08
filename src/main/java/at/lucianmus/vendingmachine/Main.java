package at.lucianmus.vendingmachine;

public class Main {

    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine(new Money());

        System.out.println("Vending machine has " + vendingMachine.getTotal() + " cents");

        // Supply vending machine
        Product coke = new Product("Coke", 205);
        Product snickers = new Product("Snickers", 250);
        vendingMachine.addProduct(coke,0,2);
        vendingMachine.addProduct(snickers,1,1);

        // Try to add a product in a slot which already contains another product
        vendingMachine.addProduct(coke,1,1);

        // Try to add more products than the slots can hold
        vendingMachine.addProduct(snickers,1,7);

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

        // Give change
        vendingMachine.buyProduct(0);

    }

}
