package com.example.demo.Factory;

// Giảm giá 10% cho khách hàng VIP 2
public class VIP2DiscountStrategy implements DiscountStrategy {
    @Override
    public float calculateDiscount(float total) {
        return total * 0.1f;
    }
}
