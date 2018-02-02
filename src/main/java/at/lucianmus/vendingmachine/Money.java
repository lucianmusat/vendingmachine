package at.lucianmus.vendingmachine;

import java.util.TreeMap;


/**
 * @author Lucian on 31-1-18
 */

public class Money {

    private TreeMap<Coin, Integer> coins;

    Money(TreeMap<Coin, Integer> coins) {
        if (coins != null)
            this.coins = coins;
        else
             this.coins = new TreeMap<>();
    }

    // We need a non verbose method for internal use
    public void addCoins(Coin... coins){
        addCoins(false, coins);
    }

    public void addCoins(boolean internal, Coin...coins){
        for (Coin coin:coins){
            if (!internal) {
                if (coin.getValue() < 100)
                    System.out.println("Inserting " + coin.getValue() + " cents");
                else
                    System.out.println("Inserting " + (coin.getValue() / 100) + " euro");
            }
            if (this.coins.containsKey(coin)) {
                this.coins.put(coin, this.coins.get(coin) + 1);
            } else {
                this.coins.put(coin, 1);
            }
        }
    }

    // We need a non verbose method for internal use
    public void removeCoins(Coin... coins){
        removeCoins(false, coins);
    }

    public void removeCoins(boolean internal, Coin... coins){
        for (Coin coin:coins){
            if (this.coins.get(coin) > 0) {
                if (!internal) {
                    if (coin.getValue() < 100)
                        System.out.println("Ding! a " + coin.getValue() + " cents coin drops into the tray.");
                    else
                        System.out.println("Ding! a " + (coin.getValue() / 100) + " euro coin drops into the tray.");
                }
                this.coins.put(coin, this.coins.get(coin) - 1);
            }
        }
    }

    public Integer getTotal(){
        int total = 0;
        for (Coin coin:this.coins.keySet()) {
            int numberOfCoins = this.getCoins().get(coin);
            for (int i = 0; i < numberOfCoins; i++) {
                total += coin.getValue();
            }
        }
        return total;
    }

    public TreeMap<Coin, Integer> getCoins() {
        return this.coins;
    }

    @Override
    public String toString(){
        StringBuilder allCoins = new StringBuilder();
        for (Coin coin:this.getCoins().descendingKeySet()){
            int numberOfCoins = this.getCoins().get(coin);
            if (numberOfCoins > 0){
                if (coin.getValue() > 50)
                    allCoins.append(numberOfCoins).append(" ").append(coin.getValue() / 100).append(" euro coin. ");
                else
                    allCoins.append(numberOfCoins).append(" ").append(coin.getValue()).append(" cent coin. ");
            }
        }
        return allCoins.toString();
    }

}
