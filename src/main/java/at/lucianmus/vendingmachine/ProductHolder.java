package at.lucianmus.vendingmachine;


/**
 * @author Lucian on 31-1-18
 */

public class ProductHolder {
    // Three dimensional array to hold products
    private Product[][][] frame = new Product[5][5][5];

    ProductHolder() {}

    public void addProduct(Product product, Integer position, Integer amount){
        Integer row = position / 5;
        Integer column = position - (5 * row);
        if (this.frame[row][column][0] != null){
            if (!this.frame[row][column][0].getName().equals(product.getName())) {
                System.out.println("Cannot add " + product.getName() + " here, it already contains " + this.frame[row][column][0].getName() + "!");
                return;
            }
        }
        for (Integer depth = 0; depth < amount; depth++) {
            System.out.println("Adding " + product.getName() + " in slot " + position);
            this.frame[row][column][depth] = product;
        }
    }

    public void popProduct(Integer position){
        Integer row = position / 5;
        Integer column = position - (5 * row);
        if (this.frame[row][column][0] != null){
            for (Integer depth = 0; depth < 5; depth++){
                if (this.frame[row][column][depth] == null) {
                    System.out.println("Bong! " + this.frame[row][column][depth - 1].getName() + " drops into the tray.");
                    this.frame[row][column][depth -1] = null;
                    return;
                }
                // The slot is full until the back
                if (depth == 4 && this.frame[row][column][depth] != null) {
                    System.out.println("Ding! " + this.frame[row][column][depth].getName() + " drops into the tray.");
                    this.frame[row][column][depth] = null;
                    return;
                }
            }
        }
        System.out.println("The tray spins but nothing falls out!");
    }

    public int getPrice(Integer position){
        Integer row = position / 5;
        Integer column = position - (5 * row);
        if (this.frame[row][column][0] != null)
            return this.frame[row][column][0].getPrice();
        else
            return 0;
    }

}
