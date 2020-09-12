package main.java;

import main.java.discount.DiscountRule;
import main.java.discount.DiscountTuple;
import main.java.discount.PercentDiscountRule;
import main.java.discount.SumDiscountRule;
import main.java.model.Basket;
import main.java.model.BasketItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // -tool_1: 3 Х $7 = 21;
        // -tool_2: 2 X $10 = 20;
        // -tool_3: 100 X $0.01 = 1
        BasketItem tool1 = new BasketItem("tool_1", new BigDecimal("7.0"));
        BasketItem tool2 = new BasketItem("tool_2", new BigDecimal("10.0"));
        BasketItem tool3 = new BasketItem("tool_3", new BigDecimal("0.01"));

        ArrayList<DiscountRule> discounts = new ArrayList<>();

        //create discount system 10% (there are 1 X tool_1 and 10 X tool_3)
        PercentDiscountRule percentDiscountRule = PercentDiscountRule.of(
                new BigDecimal(10),
                new DiscountTuple(tool1, 1),
                new DiscountTuple(tool3, 10)
        );
        //create discount system sum discount (there are 2 Х tool_2) -$5
        DiscountRule sumDiscount = SumDiscountRule.of(
                new BigDecimal(5),
                new DiscountTuple(tool2, 2)
        );

        discounts.add(percentDiscountRule);
        discounts.add(sumDiscount);

        Basket basket = new Basket().put(tool1, 3).put(tool2, 2).put(tool3, 100);

        System.out.println("Final price: " + basket.calculate(discounts));
    }
}
