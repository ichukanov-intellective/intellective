package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public interface DiscountRule extends Predicate<List<BasketItem>> {
    BigDecimal apply(BigDecimal total);

    default boolean match(List<BasketItem> items, List<DiscountTuple> tuples) {
        Map<BasketItem, Long> itemsByType = items.stream().collect(groupingBy(Function.identity(), counting()));

        Predicate<DiscountTuple> predicate = (entry) ->
                itemsByType.containsKey(entry.getItem()) &&
                        itemsByType.get(entry.getItem()) >= entry.getCount();

        return tuples.stream().allMatch(predicate);
    }
}
