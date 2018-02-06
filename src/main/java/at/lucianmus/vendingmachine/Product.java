package at.lucianmus.vendingmachine;

/**
 * @author Lucian on 31-1-18
 */

public class Product {
    public final String name;
    public final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + ":" + (this.price / 100) + "." + (this.price) % 100;
    }

}
