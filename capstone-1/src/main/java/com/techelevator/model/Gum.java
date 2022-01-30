package com.techelevator.model;

public class Gum extends Item {
    public Gum(String itemName, String itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    String getSound() {
        return "Chew Chew, Yum!";
    }
}