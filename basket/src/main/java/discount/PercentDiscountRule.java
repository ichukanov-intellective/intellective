package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class PercentDiscountRule implements DiscountRule {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static PercentDiscountRule of(BigDecimal percent, DiscountTuple... items) {
        return new PercentDiscountRule(Arrays.asList(items), percent);
    }

    private final BigDecimal percent;
    private final List<DiscountTuple> items;

    private PercentDiscountRule(List<DiscountTuple> items, BigDecimal percent) {
        this.items = items;
        this.percent = percent;
    }

    @Override
    public BigDecimal apply(BigDecimal total) {
        return percent.multiply(total.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
    }

    @Override
    public boolean test(List<BasketItem> items) {
        return DiscountRuleMatcher.match(items, this.items);
    }
}
