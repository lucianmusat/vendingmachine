package at.lucianmus.vendingmachine;

import java.util.Arrays;


/**
 * @author Lucian on 31-1-18
 */

public class Money {

    private Integer oneCent, twoCents, fiveCents, tenCents, twentyCents, fiftyCents, oneEuro, twoEuro;
    private Integer[] valid_cents = {1, 2, 5, 10, 20, 50};
    private Integer[] valid_euros = {1, 2};

    Money(Integer oneCent, Integer twoCents, Integer fiveCents, Integer tenCents, Integer twentyCents, Integer fiftyCents, Integer oneEuro, Integer twoEuro) {
        this.oneCent = oneCent;
        this.twoCents = twoCents;
        this.fiveCents = fiveCents;
        this.tenCents = tenCents;
        this.twentyCents = twentyCents;
        this.fiftyCents = fiftyCents;
        this.oneEuro = oneEuro;
        this.twoEuro = twoEuro;
    }

    private void addCoin(Integer value, String type){
        if (type.equals("cent")){
            if (Arrays.asList(valid_cents).contains(value)){
                switch (value){
                    case 1:
                        this.oneCent++;
                        break;
                    case 2:
                        this.twoCents++;
                        break;
                    case 5:
                        this.fiveCents++;
                        break;
                    case 10:
                        this.tenCents++;
                        break;
                    case 20:
                        this.twentyCents++;
                        break;
                    case 50:
                        this.fiftyCents++;
                        break;
                }
            } else
                System.out.println("Unrecognized coin!");
        } else if (type.equals("euro")) {
            if (Arrays.asList(valid_euros).contains(value)){
                switch (value){
                    case 1:
                        this.oneEuro++;
                        break;
                    case 2:
                        this.twoEuro++;
                        break;
                }
            } else
                System.out.println("Unrecognized coin!");
        }
    }

    public void addCoins(String type, Integer...values){
        if (values.length > 0 ){
            for (Integer coin:values){
                if (type.equals("cents")) {
                    if (Arrays.asList(valid_cents).contains(coin)) {
                        System.out.println("Inserting " + coin + " cents");
                        addCoin(coin, "cent");
                    } else System.out.println("Have the " + coin + " cents back, it is not recognized");
                }
                else if (type.equals("euros")){
                    if (Arrays.asList(valid_euros).contains(coin)) {
                        System.out.println("Inserting " + coin + " euros");
                        addCoin(coin, "euro");
                    } else System.out.println("Have the " + coin + " euros back, it is not recognized");
                }
            }
        }
    }

    public Integer getTotal(){
        return this.oneCent + this.twoCents * 2 + this.fiveCents * 5 + this.tenCents * 10 + this.twentyCents * 20 + this.fiftyCents * 50 + this.oneEuro * 100 + this.twoEuro * 200;
    }


}
