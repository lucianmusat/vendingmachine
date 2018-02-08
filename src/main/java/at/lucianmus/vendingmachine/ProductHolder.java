package at.lucianmus.vendingmachine;


/**
 * @author Lucian on 31-1-18
 */

public class ProductHolder {

    private Product[][] frame = new Product[25][5];

    ProductHolder() {}

    public void addProduct(Product product, Integer position, Integer amount) {
        if (this.frame[position][0] != null) {
            if (!this.frame[position][0].name.equals(product.name)) {
                System.out.println("Cannot add " + product.name + " here, it already contains " + this.frame[position][0].name + "!");
                return;
            }
        }
        for (Integer depth = 0; depth < amount; depth++) {
            System.out.println("Adding " + product.name + " in slot " + position);
            this.frame[position][depth] = product;
        }
    }

    public void popProduct(Integer position) {
        if (this.frame[position][0] != null) {
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
            return 0;
    }

}
