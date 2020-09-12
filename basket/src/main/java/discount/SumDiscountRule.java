package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SumDiscountRule implements DiscountRule {

    public static SumDiscountRule of(BigDecimal discount, DiscountTuple... items) {
        return new SumDiscountRule(Arrays.asList(items), discount);
    }

    private final List<DiscountTuple> items;
    private final BigDecimal discountCount;

    private SumDiscountRule(List<DiscountTuple> items, BigDecimal discountCount) {
        this.items = items;

        this.discountCount = discountCount;
    }

    @Override
    public BigDecimal apply(List<BasketItem> items, BigDecimal total) {

        Map<BasketItem, Long> itemsByType = items.stream().collect(groupingBy(Function.identity(), counting()));

        Predicate<DiscountTuple> predicate = (entry) ->
                itemsByType.containsKey(entry.getItem()) &&
                        itemsByType.get(entry.getItem()) >= entry.getCount();

        boolean match = this.items.stream().allMatch(predicate);

        if (match) {
            return discountCount;
        }

        return BigDecimal.ZERO;
    }
}
