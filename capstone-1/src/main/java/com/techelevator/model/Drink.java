package com.techelevator.model;

public class Drink extends Item {
    public Drink(String itemName, String itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    String getSound() {
        return "Glug Glug, Yum!";
    }
}