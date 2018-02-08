package at.lucianmus.vendingmachine;



/**
 * @author Lucian on 31-1-18
 */

public class ProductHolder {

    static public class Slot {

        public static final int POSITIONS = 5;

        // a slot has a number of POSITIONS, so it can contain
        // number of products [0..POSITIONS]. These are always stored in positions[0..numberOfProducts-1]
        private Product[] positions = new Product[POSITIONS];

        public boolean inUse() {
            return positions[0] != null;
        }

        /*
        public int numberOfProducts() {
            int res = 0;
            while (res < POSITIONS || positions[res] != null) {
                res++;
            }
            return res;
        }
        */

        public boolean isFull() {
            return positions[POSITIONS-1] != null;
        }

        public int getPrice() {
            if (!inUse()) throw new IllegalStateException("not in use");
            return positions[0].price;
        }

        public void addProduct(Product product) {
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

    // TODO: use one-dimensional array of slots
    private Product[][] frame = new Product[25][5];

    ProductHolder() {}

    public void addProduct(Product product, Integer slot, Integer amount) {
        if (this.frame[slot][0] != null) {
            if (!this.frame[slot][0].name.equals(product.name)) {
                System.out.println("Cannot add " + product.name + " here, it already contains " + this.frame[slot][0].name + "!");
                return;
            }
        }
        // TODO: bug: this overwrites existing stuff
        for (Integer depth = 0; depth < amount; depth++) {
            System.out.println("Adding " + product.name + " in slot " + slot);
            this.frame[slot][depth] = product;
        }
    }

    public void popProduct(Integer position) {
        if (this.frame[position][0] != null) {
            // TODO: this can be simplified: drop first one and shift the others
            for (Integer depth = 0; depth < 5; depth++){
                if (this.frame[position][depth] == null) {
                    System.out.println("Bong! " + this.frame[position][depth - 1].name + " drops into the tray.");
                    this.frame[position][depth -1] = null;
                    return;
                }
                // The slot is full until the back
                if (depth == 4 && this.frame[position][depth] != null) {
                    System.out.println("Ding! " + this.frame[position][depth].name + " drops into the tray.");
                    this.frame[position][depth] = null;
                    return;
                }
            }
        }
        System.out.println("The tray spins but nothing falls out!");
    }

    public int getPrice(Integer position) {
        if (this.frame[position][0] != null)
            return this.frame[position][0].price;
        else
            return 0; // Magical value!
    }

}
