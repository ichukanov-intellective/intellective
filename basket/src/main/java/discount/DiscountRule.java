package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public interface DiscountRule extends Predicate<List<BasketItem>> {
    BigDecimal apply(BigDecimal total);
}
