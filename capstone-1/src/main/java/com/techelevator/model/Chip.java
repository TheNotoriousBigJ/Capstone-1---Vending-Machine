package com.techelevator.model;

public class Chip extends Item {
    public Chip(String itemName, String itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    String getSound() {
        return "Crunch Crunch, Yum!";
    }
}