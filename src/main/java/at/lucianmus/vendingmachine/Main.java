package at.lucianmus.vendingmachine;

public class Main {

    public static void main(String[] args) {
        Money vendingMoneyStack = new Money(10,10,10,10,10,10,10,10);
        VendingMachine vendingMachine = new VendingMachine(vendingMoneyStack);

        System.out.println("Vending machine has " + vendingMachine.getTotal() + " cents");

        // Add some products
        Product coke = new Product("Coke", 2.0);
        Product snickers = new Product("Snickers", 2.5);
        vendingMachine.addProduct(coke,0,2);
        vendingMachine.addProduct(snickers,1,1);
        vendingMachine.popProduct(0);
        vendingMachine.popProduct(0);
        vendingMachine.popProduct(0);
        vendingMachine.popProduct(1);
        vendingMachine.popProduct(1);

//        vendingMachine.addCoins("cents",1, 3, 5);
//        vendingMachine.addCoins("euros",1, 2, 5);
//        System.out.println("Vending machine has " + vendingMachine.getTotal() + " cents");


   }

}
