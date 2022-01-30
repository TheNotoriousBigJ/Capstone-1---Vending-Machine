package com.techelevator.view;

import com.techelevator.model.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Inventory {

    private static String filename = "vendingmachine.csv";
    private static File inventoryFile = new File(filename);
    private static HashMap<String, Item> inventoryList = new HashMap<>();

    public static HashMap<String, Item> getInventoryList() {
        return inventoryList;
    }

    public static HashMap<String, Item> fillInventoryList() {
        try {
            Scanner inventoryInput = new Scanner(inventoryFile);
            while (inventoryInput.hasNext()) {
                inventoryInput.useDelimiter("\\||\n");
                String productCode = inventoryInput.next();
                String itemName = inventoryInput.next();
                String itemPrice = inventoryInput.next();
                String itemType = inventoryInput.next();

                if (itemType.equals("Chip\r")) {
                    Chip chip = new Chip(itemName, itemPrice);
                    inventoryList.put(productCode, chip);
                }
                if (itemType.equals("Drink\r")) {
                    Drink drink = new Drink(itemName, itemPrice);
                    inventoryList.put(productCode, drink);
                }
                if (itemType.equals("Candy\r")) {
                    Candy candy = new Candy(itemName, itemPrice);
                    inventoryList.put(productCode, candy);
                }
                if (itemType.equals("Gum\r")) {
                    Gum gum = new Gum(itemName, itemPrice);
                    inventoryList.put(productCode, gum);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }

    public static void displayMenu() {
        ArrayList<String> productCodeList = new ArrayList(inventoryList.keySet());
        Collections.sort(productCodeList);
        for (String productCode : productCodeList) {
            Item item = inventoryList.get(productCode);
            if (item.getItemStock().equals(0)) {
                System.out.println(productCode + "| " + item.getItemName() + " |" +
                        " Price -> " + item.getItemPrice() + "$ " + "SOLD OUT");
            } else System.out.println(productCode + "| " + item.getItemName() + " |" +
                    " Price -> " + item.getItemPrice() + "$ " + "Qty: " + item.getItemStock());

        }
    }
}
