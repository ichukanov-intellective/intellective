package main.java.model;

import main.java.discount.DiscountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<BasketItem> items = new ArrayList<>();

    public Basket put(BasketItem item, int count) {
        for (int i = 0; i < count; i++) {
            items.add(item);
        }

        return this;
    }

    public BigDecimal calculate(ArrayList<DiscountRule> rules) {
        BigDecimal total = items.stream().map(BasketItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.subtract(getDiscount(rules, total));
    }

    private BigDecimal getDiscount(ArrayList<DiscountRule> rules, BigDecimal total) {
        return rules.stream().reduce(
                BigDecimal.ZERO,
                (acc, rule) -> acc.add(rule.apply(items, total)),
                BigDecimal::add
        );
    }
}
