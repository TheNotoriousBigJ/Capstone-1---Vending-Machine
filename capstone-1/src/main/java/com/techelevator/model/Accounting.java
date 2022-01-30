package com.techelevator.model;

import java.text.DecimalFormat;

public class Accounting {
    public static Double balance = 0.00;

    public static Double getBalance() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.format(balance);
        return balance;
    }

    public static Double feedMoney(Double money) {
        DecimalFormat df = new DecimalFormat("#.##");
        balance = balance + money;
        df.format(balance);
        return balance;
        //add rounding to modify balance
    }

    public static Double makePayment(Double money) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (money <= balance) {
            balance = balance - money;
            df.format(balance);
            return balance;
            //add rounding to modify balance
        }
        else return -1.00;
    }

    public static String getChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int balanceAsInt = (int) (balance * 100);

        quarters = balanceAsInt / 25;
        balanceAsInt = balanceAsInt - (25 * quarters);

        dimes = balanceAsInt / 10;
        balanceAsInt = balanceAsInt - (10 * dimes);

        nickels = balanceAsInt / 5;
        balanceAsInt = balanceAsInt - (5 * nickels);
        balance = 0d;

        return "Please take your change: " + quarters + " Quarters " + dimes + " Dimes " + nickels + " Nickels";
    }

}