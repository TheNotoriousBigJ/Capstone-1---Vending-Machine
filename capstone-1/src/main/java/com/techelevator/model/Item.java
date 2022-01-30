package com.techelevator.model;

public abstract class Item {

    //declare variables
    private String itemName;
    private String itemPrice;
    private Integer itemStock = 5;

    //getters
    public Double getItemPrice() {
        return Double.parseDouble(itemPrice);
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    //constructor
    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    abstract String getSound();

    public String purchaseItem() {
        //if product code exists but stock is empty
        if (itemStock.equals(0)){
            return "Sorry, that item is not available...";
        }
        //if product code exists and is purchased
        else {
            itemStock = itemStock - 1;
            return getSound();
        }
    }
}