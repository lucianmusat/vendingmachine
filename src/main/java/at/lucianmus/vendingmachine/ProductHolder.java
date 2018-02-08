package at.lucianmus.vendingmachine;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Lucian on 31-1-18
 */

public class ProductHolder {

    static public class Slot {

        private static final int POSITIONS = 5;

        // a slot has a number of POSITIONS, so it can contain
        // number of products [0..POSITIONS]. These are always stored in positions[0..numberOfProducts-1]
        private Product[] positions = new Product[POSITIONS];

        boolean inUse() {
            return positions[0] != null;
        }

        int freeSlots(){
            return Math.toIntExact(Arrays.stream(positions).filter(Objects::isNull).count());
        }

        boolean isFull() {
             return positions[POSITIONS-1] != null;
        }

        int getPrice() throws IllegalStateException {
            if (!inUse()) throw new IllegalStateException("not in use");
            return positions[0].price;
        }

        void addProduct(Product product) throws IllegalStateException {
            if (inUse() && !positions[0].name.equals(product.name)) throw new IllegalStateException("in use by another product");
            if (isFull()) throw new IllegalStateException("full");
            int i = 0;
            while (positions[i] != null) {
                i++;
            }
            positions[i] = product;
        }

        void popProduct() {
            // just like a real vending machine, we happily sell from empty slots
            if (!inUse()) {
                System.out.println("The tray spins but nothing falls out! :-(");
            } else {
                System.out.println("Dropping a " + positions[0].name);
            }

            //noinspection ManualArrayCopy
            for (int i=0; i<POSITIONS-1; i++) {
                positions[i] = positions[i+1];
            }
            positions[POSITIONS-1] = null;
        }

    }

    private Slot[] frame = new Slot[25];

    ProductHolder() {
        for (int i=0; i<frame.length; i++){
            frame[i] = new Slot();
        }
    }

    public void addProduct(Product product, int slot, int amount) {
        if (slot<0 || slot>24) throw new IllegalStateException("No such slot!");
        if (amount<0) throw new IllegalStateException("Illegal amount of products!");
        if (!frame[slot].isFull() && amount <= frame[slot].freeSlots()){
            for (int i=0; i<amount; i++) {
                try {
                    frame[slot].addProduct(product);
                } catch (IllegalStateException e) {
                    System.out.println("Cannot add " + product.name + " to slot " + slot + ", it already contains something else!");
                    return;
                }
            }
            System.out.println("Added " + amount + " " + product.name + " to slot " + slot);
        } else {
            System.out.println("Not enough space to add products!");
        }
    }

    public void popProduct(int slot) {
        frame[slot].popProduct();
    }

    public int getPrice(int slot) {
        if (!frame[slot].inUse()) throw new IllegalStateException("No product in specified slot!");
        return frame[slot].getPrice();
    }

}
