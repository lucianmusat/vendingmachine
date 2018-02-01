package at.lucianmus.vendingmachine;

public class Main {

    public static void main(String[] args) {
//        Money vendingMoneyStack = new Money(10,10,10,10,10,10,10,10);
        Money vendingMoneyStack = new Money(0,0,0,0,0,0,0,0);
        VendingMachine vendingMachine = new VendingMachine(vendingMoneyStack);

        System.out.println("Vending machine has " + vendingMachine.getTotal() + " cents");

        // Supply vending machine
        Product coke = new Product("Coke", 2.05);
        Product snickers = new Product("Snickers", 2.5);
        vendingMachine.addProduct(coke,0,2);
        vendingMachine.addProduct(snickers,1,1);
        vendingMachine.addProduct(coke,1,1);

        // Try to get a product with no credit
        vendingMachine.buyProduct(0);

        // Add coins to the vending machine
        vendingMachine.addCoins("euros", 1, 1, 2);

        // Try again to buy
        vendingMachine.buyProduct(1);
        System.out.println("Credit in the vending machine: " + vendingMachine.getCurrentCredit());

        vendingMachine.addCoins("euros",  2);
        vendingMachine.addCoins("cents", 50, 20, 5);
        System.out.println("Credit in the vending machine: " + vendingMachine.getCurrentCredit());
        vendingMachine.buyProduct(0);

        // Buy a sold out product
        vendingMachine.addCoins("euros",  2, 1);
        vendingMachine.buyProduct(1);

        // Add a fake coin
        vendingMachine.addCoins("euros",  5);

    }

}
