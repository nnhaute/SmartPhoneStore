package com.example.demo.Factory;

// Giảm giá 5% cho khách hàng VIP 1
public class VIP1DiscountStrategy implements DiscountStrategy {
    @Override
    public float calculateDiscount(float total) {
        return total * 0.05f;
    }
}
