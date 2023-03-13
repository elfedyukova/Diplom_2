package ru.praktikum.order;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderGenerator {

    public Order empty() {
        return new Order("");
    }

    public Order invalidOrder() {
        return new Order(RandomStringUtils.random(10));
    }


    public Order passOrder() {
        return new Order("61c0c5a71d1f82001bdaaa6d");
    }

    public Order passOrders() {
        return new Order("61c0c5a71d1f82001bdaaa6f");
    }
}