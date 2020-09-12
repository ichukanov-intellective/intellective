package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
    public BigDecimal apply(List<BasketItem> items, BigDecimal total) {

        Map<BasketItem, Long> itemsByType = items.stream().collect(groupingBy(Function.identity(), counting()));

        Predicate<DiscountTuple> predicate = (entry) ->
                itemsByType.containsKey(entry.getItem()) &&
                        itemsByType.get(entry.getItem()) >= entry.getCount();

        boolean match = this.items.stream().allMatch(predicate);

        if (match) {
            return percent.multiply(total.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
        }

        return BigDecimal.ZERO;
    }
}
