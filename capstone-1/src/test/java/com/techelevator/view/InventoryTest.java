package com.techelevator.view;

import com.techelevator.model.Item;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.HashMap;

public class InventoryTest extends TestCase {

    public void testFillInventoryList() {
        Inventory.fillInventoryList();
        HashMap<String, Item> list = Inventory.getInventoryList();

        Assert.assertEquals(list.size(), 16);
    }
}