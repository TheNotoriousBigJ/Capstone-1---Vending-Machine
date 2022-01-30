package com.techelevator.view;

import com.techelevator.model.Accounting;
import junit.framework.TestCase;
import org.junit.Assert;

public class AccountingTest extends TestCase {

    public void testFeedMoney() {
        Double balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(0d));
        Accounting.feedMoney(5d);
        balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(5d));
        //getChange
        Accounting.getChange();
    }

    public void testMakePayment() {
        Double balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(0d));
        Accounting.feedMoney(5d);
        Accounting.makePayment(2d);
        balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(3));

        Double response = Accounting.makePayment(4d);
        balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(3));
        Assert.assertEquals(response, new Double(-1));
        Accounting.getChange();
    }

    public void testGetChange() {
        Double balance = Accounting.getBalance();
        Assert.assertEquals(balance, new Double(0d));
        Accounting.feedMoney(2.40);
        String change = Accounting.getChange();
        String expectedResult = "Please take your change: " + 9 + " Quarters " + 1 + " Dimes " + 1 + " Nickels";
        Assert.assertEquals(change, expectedResult);
        Accounting.getChange();
    }
}