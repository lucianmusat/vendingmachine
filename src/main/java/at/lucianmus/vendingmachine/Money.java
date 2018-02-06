package at.lucianmus.vendingmachine;

import java.util.TreeMap;


/**
 * @author Lucian on 31-1-18
 */

public class Money {

    private TreeMap<Coin, Integer> coins;

    // TODO: bad API
    // 1) null is 'magic'
    // 2) taking in external references to internal maps
    // 3) (see also) this.getCoins(), giving out references to internal datastructures
    Money(TreeMap<Coin, Integer> coins) {
        if (coins != null)
            this.coins = coins;
        else
            this.coins = new TreeMap<>();
    }

    // We need a non verbose method for internal use
    // TODO: is this the best API? Do'nt we need to add/remove multiple instances of the same coin at once?
    // that is, have some method addCoin(Coin, int)?
    public void addCoins(Coin... coins){
        addCoins(false, coins);
    }

    // TODO: bad API, 'internal' is a bit magic, let caller do logging/stdout if needed
    public void addCoins(boolean internal, Coin...coins){
        for (Coin coin:coins){
            if (!internal) {
                if (coin.value< 100) {
                    System.out.println("Inserting " + coin.value + " cents");
                } else {
                    System.out.println("Inserting " + (coin.value / 100) + " euro");
                }
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

    // TODO: bad API, what happens if we do not have enough coins?
    public void removeCoins(boolean internal, Coin... coins){
        for (Coin coin:coins){
            if (this.coins.get(coin) > 0) {
                if (!internal) {
                    if (coin.value < 100)
                        System.out.println("Ding! a " + coin.value + " cents coin drops into the tray.");
                    else
                        System.out.println("Ding! a " + (coin.value / 100) + " euro coin drops into the tray.");
                }
                this.coins.put(coin, this.coins.get(coin) - 1);
            }
        }
    }

    public Integer getTotal() {
        int total = 0;
        for (Coin coin:this.coins.keySet()) {
            int numberOfCoins = this.getCoins().get(coin);
            for (int i = 0; i < numberOfCoins; i++) {
                total += coin.value;
            }
        }
        return total;
    }

    // TODO: see above, never hand out references to internal data structures
    public TreeMap<Coin, Integer> getCoins() {
        return this.coins;
    }

    @Override
    public String toString() {
        StringBuilder allCoins = new StringBuilder();
        for (Coin coin:this.getCoins().descendingKeySet()){
            int numberOfCoins = this.getCoins().get(coin);
            if (numberOfCoins > 0){
                if (coin.value > 50) {
                    allCoins.append(numberOfCoins).append(" ").append(coin.value / 100).append(" euro coin. ");
                } else {
                    allCoins.append(numberOfCoins).append(" ").append(coin.value).append(" cent coin. ");
                }
            }
        }
        return allCoins.toString();
    }

}
