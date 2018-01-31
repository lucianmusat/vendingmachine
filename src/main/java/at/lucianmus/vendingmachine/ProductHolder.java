package at.lucianmus.vendingmachine;


/**
 * @author Lucian on 31-1-18
 */

public class ProductHolder {
    private Product[][][] frame = new Product[5][5][5];

    ProductHolder() {}

    public void addProduct(Product product, Integer position, Integer amount){
        Integer row = position / 5;
        Integer column = position - (5 * row);
        for (Integer depth = 0; depth < amount; depth++) {
            System.out.println("Adding " + product.name + " in slot " + position);
            this.frame[row][column][depth] = product;
        }
    }

    public boolean popProduct(Integer position){
        Integer row = position / 5;
        Integer column = position - (5 * row);
        if (this.frame[row][column][0] != null){
            for (Integer depth = 0; depth < 5; depth++){
                if (this.frame[row][column][depth] == null) {
                    System.out.println("Ding! " + this.frame[row][column][depth - 1].name + " drops into the tray.");
                    this.frame[row][column][depth -1] = null;
                    return true;
                }
                // The slot is full until the back
                if (depth == 4 && this.frame[row][column][depth] != null)
                    System.out.println("Ding! " + this.frame[row][column][depth].name + " drops into the tray.");
                    this.frame[row][column][depth] = null;
                    return true;
            }
        }
        return false;
    }

}
