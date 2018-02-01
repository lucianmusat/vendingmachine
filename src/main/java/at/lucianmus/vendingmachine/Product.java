package at.lucianmus.vendingmachine;

/**
 * @author Lucian on 31-1-18
 */

public class Product {
    final public String name;
    final public int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // TODO: this should not be necessary
    Product(String name, double price) {
        this(name, (int) Math.round(price * 100));
    }

    // TODO: this should not be necessary
    Double euroValue() {
        return this.price * 100.0;
    }

    @Override
    public String toString() {
        return this.name + ":" + (this.price / 100) + "." + (this.price) % 100;
    }

}
