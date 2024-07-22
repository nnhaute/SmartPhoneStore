package com.example.demo.Factory;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public float calculateDiscount(float total) {
        return 0;
    }
}

