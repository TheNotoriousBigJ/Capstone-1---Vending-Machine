package com.techelevator.view;

import com.techelevator.model.*;
import junit.framework.TestCase;
import org.junit.Assert;


public class ItemTest extends TestCase {

    private Chip testChip;

    private void setup() {
        testChip = new Chip("Test Chip", "2.00");
    }

    public void testPurchaseItem() {
        setup();
        //testing one item, other items have same behaviors
        Integer previousChipStock = testChip.getItemStock();

        String chipResponse = testChip.purchaseItem();

        Assert.assertEquals((previousChipStock - 1) + "", testChip.getItemStock() + "");
        Assert.assertEquals("Crunch Crunch, Yum!", chipResponse);
    }
}