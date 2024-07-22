package com.example.demo.Factory;

public class DiscountStrategyFactory {
    public static DiscountStrategy getDiscountStrategy(int orderCount) {
        if (orderCount >= 3) {
            return new VIP2DiscountStrategy();
        } else if (orderCount > 0) {
            return new VIP1DiscountStrategy();
        } else {
            return new NoDiscountStrategy();
        }
    }
}
