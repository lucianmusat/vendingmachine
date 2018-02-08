package at.lucianmus.vendingmachine;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author Lucian on 31-1-18
 */

public class Money implements Iterable<Map.Entry<Coin, Integer>> {

    private TreeMap<Coin, Integer> coins;

    Money(Map<Coin, Integer> coins) {
        this.coins = new TreeMap<>();
        this.coins.putAll(coins);
    }

    Money() {
        this.coins = new TreeMap<>();
    }

    public void addCoins(Coin...coins) {
        for (Coin coin : coins) {
            addCoin(coin);
        }
    }

    public void addCoin(Coin coin) {
        if (this.coins.containsKey(coin)) {
            this.coins.put(coin, this.coins.get(coin) + 1);
        } else {
            this.coins.put(coin, 1);
        }
    }

    public boolean removeCoins(Coin... coins) {
        for (Coin coin:coins){
            if (!removeCoin(coin)) {
                return false;
            }
        }
        return true;
    }

    public boolean removeCoin(Coin coin) {
        if (this.coins.get(coin) > 0) {
            this.coins.put(coin, this.coins.get(coin) - 1);
            return true;
        } else return false;
    }

    public Integer getTotal() {
        int total = 0;
        for (Coin coin : this.coins.keySet()) {
            int numberOfCoins = this.coins.get(coin);
            for (int i = 0; i < numberOfCoins; i++) {
                total += coin.value;
            }
        }
        return total;
    }

    private class AmountIterator implements Iterator<Map.Entry<Coin, Integer>> {
        private final Iterator<Map.Entry<Coin, Integer>> tmi;

        AmountIterator() {
            tmi = Money.this.coins.descendingMap().entrySet().iterator();
        }

        public boolean hasNext() { return tmi.hasNext(); }
        public Map.Entry<Coin, Integer> next() { return tmi.next(); }
    }


    public Iterator<Map.Entry<Coin, Integer>> iterator() {
        return new AmountIterator();
    }


    @Override
    public String toString() {
        StringBuilder allCoins = new StringBuilder();
        for (Coin coin:this.coins.descendingKeySet()) {
            int numberOfCoins = this.coins.get(coin);
            if (numberOfCoins > 0) {
                if (coin.value > 50) {
                    allCoins.append(numberOfCoins).append(" {").append(coin.value / 100).append(" euro coin} ");
                } else {
                    allCoins.append(numberOfCoins).append(" {").append(coin.value).append(" cent coin} ");
                }
            }
        }
        return allCoins.toString();
    }

}
