package main.java.discount;

import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountRule {
    BigDecimal apply(List<BasketItem> items, BigDecimal preTotal);
}
