package at.lucianmus.vendingmachine;

import java.util.Map;

/**
 * @author Lucian on 31-1-18
 */

public class VendingMachine {
    private Money vendingMoneyStack;
    private Money insertedMoney;
    private ProductHolder frame = new ProductHolder();
    private Integer credit;

    VendingMachine(Money vendingMoneyStack) {
        this.vendingMoneyStack = vendingMoneyStack;
        this.insertedMoney  = new Money();
        this.credit = 0;
    }

    public Integer getTotal() {
        return this.vendingMoneyStack.getTotal();
    }

    public void addProduct(Product product, Integer position, Integer amount) {
        this.frame.addProduct(product, position, amount);
    }

    public void buyProduct(Integer position){
        // Do we have enough money?
        if (this.frame.getPrice(position) <= this.getCurrentCredit()) {
            this.credit -= (this.frame.getPrice(position));
            this.frame.popProduct(position);
            // Move funds from inserted to internal
            this.swallowCoins();
            if (this.giveChange(this.vendingMoneyStack, this.credit) == null)
                System.out.println("Unfortunately there are not enough funds in the machine to give you change!");
        } else
            System.out.println("Not enough funds to buy that!");
    }

    public void addCoins(Coin...coins) {
        this.insertedMoney.addCoins(coins);
        for (Coin coin:coins) {
            if (coin.value > 50) {
                System.out.println("Inserted a " + coin.value/100 + " euro coin.");
            } else {
                System.out.println("Inserted a " + coin.value + " cents coin.");
            }
            this.credit += coin.value;
        }
    }

    public Integer getCurrentCredit(){
        return this.credit;
    }

    private void swallowCoins() {
        // Copy coins to internal money stack
        for (Map.Entry<Coin, Integer> c : this.insertedMoney) {
            for (int i=0; i<c.getValue(); i++) {
                this.vendingMoneyStack.addCoins(c.getKey());
                this.insertedMoney.removeCoins(c.getKey());
            }
        }
    }

    private Money giveChange(Money availableMoney, int price) {
        // Recursive function to give back the change
//        System.out.println("Need to give " + price + " change...");
        if (price > 0) {
            for (Map.Entry<Coin, Integer> c : availableMoney){
                // If we actually have a coin in that slot
                if (c.getValue() > 0) {
                    if (c.getKey().value <= price) {
                        if (c.getKey().value > 50) {
                            System.out.println("Ding! A " + c.getKey().value/100 + " euro coin drop into the tray!");
                        } else {
                            System.out.println("Ding! A " + c.getKey().value + " cents coin drop into the tray!");
                        }
                        availableMoney.removeCoins(c.getKey());
                        price -= c.getKey().value;
                        return giveChange(availableMoney, price);
                    }
                }
            }
            this.credit = 0;
            this.vendingMoneyStack = availableMoney;
            return null;
        }
        this.credit = 0;
        this.vendingMoneyStack = availableMoney;
        return availableMoney;
    }

    public Money getInternalCoinStack() {
        return this.vendingMoneyStack;
    }

}
