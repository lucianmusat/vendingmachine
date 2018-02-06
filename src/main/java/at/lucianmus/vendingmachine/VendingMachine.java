package at.lucianmus.vendingmachine;

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
        this.insertedMoney  = new Money(null);
        this.credit = 0;
    }

    public Integer getTotal(){
        return this.vendingMoneyStack.getTotal();
    }

    public void addProduct(Product product, Integer position, Integer amount){
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

    public void addCoins(Coin...coins){
        this.insertedMoney.addCoins(coins);
        for (Coin coin:coins)
            this.credit += coin.value;
    }

    public Integer getCurrentCredit(){
        return this.credit;
    }

    private void swallowCoins(){
        // Copy coins to internal money stack
        for(Coin coin:this.insertedMoney.getCoins().keySet()){
            int numberOfCoins = this.insertedMoney.getCoins().get(coin);
            for (int i=0; i<numberOfCoins; i++){
                this.vendingMoneyStack.addCoins(true, coin);
                this.insertedMoney.removeCoins(true, coin);
            }
        }
    }

    private Money giveChange(Money availableMoney, int price){
        // Recursive function to give back the change
//        System.out.println("Need to give " + price + " change...");
        if (price > 0) {
            for (Coin coin:availableMoney.getCoins().descendingKeySet()){
                // If we actually have a coin in that slot
                if (availableMoney.getCoins().get(coin) > 0) {
                    Integer currentSlotCoins = coin.value;
                    if (currentSlotCoins > 0) {
                        if (currentSlotCoins <= price) {
                            availableMoney.removeCoins(coin);
                            price -= coin.value;
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

    public Money getInternalCoinStack(){
        return this.vendingMoneyStack;
    }

}
