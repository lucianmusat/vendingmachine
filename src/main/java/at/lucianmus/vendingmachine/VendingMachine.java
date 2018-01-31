package at.lucianmus.vendingmachine;

/**
 * @author Lucian on 31-1-18
 */

public class VendingMachine {
    private Money vendingMoneyStack;
    private ProductHolder frame = new ProductHolder();

    public VendingMachine(Money vendingMoneyStack) {
        this.vendingMoneyStack = vendingMoneyStack;
    }

    public void addCoins(String type, Integer...values){
        this.vendingMoneyStack.addCoins(type, values);
    }

    // TODO: make private
    public Integer getTotal(){
        return this.vendingMoneyStack.getTotal();
    }

    public void addProduct(Product product, Integer position, Integer amount){
        this.frame.addProduct(product, position, amount);
    }

    public void popProduct(Integer position){
        this.frame.popProduct(position);
    }

}
