package at.lucianmus.vendingmachine;

/**
 * @author Lucian on 1-2-18
 */

public class Coin implements Comparable<Coin>{

    public final int value;

    private Coin(int value) {
        this.value = value;
    }

    static final Coin oneCent = new Coin(1);
    static final Coin twoCent = new Coin(2);
    static final Coin fiveCent = new Coin(5);
    static final Coin tenCent = new Coin(10);
    static final Coin twentyCent = new Coin(20);
    static final Coin fiftyCent = new Coin(50);
    static final Coin oneEuro = new Coin(100);
    static final Coin twoEuro = new Coin(200);

    @Override
    public int compareTo(Coin o) {
        return Integer.compare(this.value, o.value);
    }

}
