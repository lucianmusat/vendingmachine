package at.lucianmus.vendingmachine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Lucian on 31-1-18
 */

public class Money {

    private Integer oneCent, twoCents, fiveCents, tenCents, twentyCents, fiftyCents, oneEuro, twoEuro;
    private Integer[] valid_cents = {1, 2, 5, 10, 20, 50};
    private Integer[] valid_euros = {1, 2};
    private Map<Integer, Integer> multiplicationMap = new HashMap<>();

    Money(Integer oneCent, Integer twoCents, Integer fiveCents, Integer tenCents, Integer twentyCents, Integer fiftyCents, Integer oneEuro, Integer twoEuro) {
        this.oneCent = oneCent;
        this.twoCents = twoCents;
        this.fiveCents = fiveCents;
        this.tenCents = tenCents;
        this.twentyCents = twentyCents;
        this.fiftyCents = fiftyCents;
        this.oneEuro = oneEuro;
        this.twoEuro = twoEuro;
        multiplicationMap.put(1, 0);
        multiplicationMap.put(2, 1);
        multiplicationMap.put(5, 2);
        multiplicationMap.put(10, 3);
        multiplicationMap.put(20, 4);
        multiplicationMap.put(50, 5);
        multiplicationMap.put(100, 6);
        multiplicationMap.put(200, 7);
    }

    private void addCoin(Integer value, String type){
        if (type.equals("cent")){
            if (Arrays.asList(valid_cents).contains(value)){
                this.addCoin(value, this.multiplicationMap.get(value));
            } else
                System.out.println("Unrecognized coin!");
        } else if (type.equals("euro")) {
            if (Arrays.asList(valid_euros).contains(value)){
                this.addCoin(value, this.multiplicationMap.get(value * 100));
            } else
                System.out.println("Unrecognized coin!");
        }
    }

    public void addCoin(Integer value, Integer position){
        switch (position){
            case 0:
                this.oneCent += value;
                break;
            case 1:
                this.twoCents += value;
                break;
            case 2:
                this.fiveCents += value;
                break;
            case 3:
                this.tenCents += value;
                break;
            case 4:
                this.twentyCents += value;
                break;
            case 5:
                this.fiftyCents += value;
                break;
            case 6:
                this.oneEuro += value;
                break;
            case 7:
                this.twoEuro += value;
                break;
        }
    }

    private void removeCoin(Integer value, Integer position){
        switch (position){
            case 0:
                if (this.oneCent - value >= 0)
                    this.oneCent -= value;
                break;
            case 1:
                if (this.twoCents - value >= 0)
                    this.twoCents -= value;
                break;
            case 2:
                if (this.fiveCents - value >= 0)
                    this.fiveCents -= value;
                break;
            case 3:
                if (this.tenCents - value >= 0)
                    this.tenCents -= value;
                break;
            case 4:
                if (this.twentyCents - value >= 0)
                    this.twentyCents -= value;
                break;
            case 5:
                if (this.fiftyCents - value >= 0)
                    this.fiftyCents -= value;
                break;
            case 6:
                if (this.oneEuro - value >= 0)
                    this.oneEuro -= value;
                break;
            case 7:
                if (this.twoEuro - value >= 0)
                    this.twoEuro -= value;
                break;
        }
    }

    private void addCoins(String type, boolean isInternal, Integer...values){
        if (values.length > 0 ){
            for (Integer coin:values){
                if (type.equals("cents")) {
                    if (Arrays.asList(valid_cents).contains(coin)) {
                        if (!isInternal)
                            System.out.println("Inserting " + coin + " cents");
                        addCoin(coin, "cent");
                    } else System.out.println("Have the " + coin + " cents back, it is not recognized");
                }
                else if (type.equals("euros")){
                    if (Arrays.asList(valid_euros).contains(coin)) {
                        if (!isInternal)
                            System.out.println("Inserting " + coin + " euros");
                        addCoin(coin, "euro");
                    } else System.out.println("Have the " + coin + " euros back, it is not recognized");
                }
            }
        }
    }

    public void addCoins(String type, Integer...values){
        this.addCoins(type, false, values);
    }

    public void discardCoin(String type, Integer value) {
        if (type.equals("cent") && Arrays.asList(valid_cents).contains(value)) {
            this.removeCoin(value, this.multiplicationMap.get(value));
            System.out.println("Ding! A " + value + " cent coin dropped in the socket!");
        } else if (type.equals("euro") && Arrays.asList(valid_euros).contains(value)) {
            this.removeCoin(value, this.multiplicationMap.get(value * 100));
            System.out.println("Ding! A " + value + " euro coin dropped in the socket!");
        }
    }

    public Integer getTotal(){
        return this.oneCent + this.twoCents * 2 + this.fiveCents * 5 + this.tenCents * 10 + this.twentyCents * 20 + this.fiftyCents * 50 + this.oneEuro * 100 + this.twoEuro * 200;
    }

    public Integer[] getAllCoins(){
        return new Integer[]{this.oneCent, this.twoCents, this.fiveCents, this.tenCents, this.twentyCents, this.fiftyCents, this.oneEuro, this.twoEuro};
    }

}
