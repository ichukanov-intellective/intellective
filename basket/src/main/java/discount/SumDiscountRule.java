package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SumDiscountRule implements DiscountRule {

    public static SumDiscountRule of(BigDecimal discount, DiscountTuple... items) {
        return new SumDiscountRule(Arrays.asList(items), discount);
    }

    private final List<DiscountTuple> items;

    private final BigDecimal discount;

    private SumDiscountRule(List<DiscountTuple> items, BigDecimal discount) {
        this.items = items;

        this.discount = discount;
    }

    @Override
    public BigDecimal apply(BigDecimal total) {
        return discount;
    }

    @Override
    public boolean test(List<BasketItem> items) {
        return DiscountRuleMatcher.match(items, this.items);
    }
}
