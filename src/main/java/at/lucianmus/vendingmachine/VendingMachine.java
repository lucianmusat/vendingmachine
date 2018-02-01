package at.lucianmus.vendingmachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucian on 31-1-18
 */

public class VendingMachine {
    private Money vendingMoneyStack;
    private Money insertedMoney;
    private ProductHolder frame = new ProductHolder();
    private Integer credit;
    private Map<Integer, Integer> multiplicationMap = new HashMap<>();

    VendingMachine(Money vendingMoneyStack) {
        this.vendingMoneyStack = vendingMoneyStack;
        this.insertedMoney  = new Money(0,0,0,0,0,0,0,0);
        this.credit = 0;
        multiplicationMap.put(0, 1);
        multiplicationMap.put(1, 2);
        multiplicationMap.put(2, 5);
        multiplicationMap.put(3, 10);
        multiplicationMap.put(4, 20);
        multiplicationMap.put(5, 50);
        multiplicationMap.put(6, 100);
        multiplicationMap.put(7, 200);
    }

//    public void addInternalMoney(String type, Integer...values){
//        this.vendingMoneyStack.addCoins(type, values);
//    }

    public Integer getTotal(){
        return this.vendingMoneyStack.getTotal();
    }

    public void addProduct(Product product, Integer position, Integer amount){
        this.frame.addProduct(product, position, amount);
    }

    public void buyProduct(Integer position){
        // Do we have enough money?
        if (Math.round((this.frame.getPrice(position) * 100)) <= this.getCurrentCredit()) {
            this.credit -= (int)Math.round((this.frame.getPrice(position) * 100));
            this.frame.popProduct(position);
            // Move funds from inserted to internal
            this.swallowCoins();
            if (this.giveChange(this.vendingMoneyStack, (double)this.credit) == null)
                System.out.println("Unfortunately there are not enough funds in the machine to give you change!");
        } else
            System.out.println("Not enough funds to buy that!");
    }

    public void addCoins(String type, Integer...values){
        this.insertedMoney.addCoins(type, values);
        if (type.equals("cents"))
            for (Integer coin:values)
                this.credit += coin;
        else if (type.equals("euros"))
            for (Integer coin:values)
                this.credit += coin * 100;
    }

//    public Integer getInsertedMoney(){
//        return this.insertedMoney.getTotal();
//    }

    public Integer getCurrentCredit(){
        return this.credit;
    }

    private void swallowCoins(){
        // Copy coins to internal money stack
        for (int i=0; i<8; i++){
            if (this.insertedMoney.getAllCoins()[i] > 0)
                    this.vendingMoneyStack.addCoin(this.insertedMoney.getAllCoins()[i], i);
        }
        // Destroy inserted money stack
        this.insertedMoney  = new Money(0,0,0,0,0,0,0,0);
    }

    private Money giveChange(Money availableMoney, Double price){
        // Recursive function to give back the change
//        System.out.println("Need to give " + price + " change...");
        if (price > 0) {
            for (int i=7;i>= 0;i--) {
                Integer currentSlotCoins = availableMoney.getAllCoins()[i];
                if (currentSlotCoins > 0) {
                    if (this.multiplicationMap.get(i) <= price) {
                        if (i > 5) {
                            availableMoney.discardCoin("euro", this.multiplicationMap.get(i) / 100);
                            price -= this.multiplicationMap.get(i);
                            return giveChange(availableMoney, price);
                        } else {
                            availableMoney.discardCoin("cent", this.multiplicationMap.get(i));
                            price -= this.multiplicationMap.get(i);
                            return giveChange(availableMoney, price);
                        }
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


}
