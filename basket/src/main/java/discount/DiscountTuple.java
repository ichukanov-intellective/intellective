package main.java.discount;

import main.java.model.BasketItem;

public class DiscountTuple {

    private final BasketItem item;
    private final int count;

    public DiscountTuple(BasketItem item, int count) {
        this.item = item;
        this.count = count;
    }

    public BasketItem getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }
}
