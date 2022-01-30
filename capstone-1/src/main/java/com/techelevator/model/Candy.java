package com.techelevator.model;

public class Candy extends Item {
    public Candy(String itemName, String itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    String getSound() {
        return "Munch Munch, Yum!";
    }
}
