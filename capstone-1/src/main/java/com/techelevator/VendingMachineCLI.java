package com.techelevator;

import com.techelevator.model.Accounting;
import com.techelevator.model.Item;
import com.techelevator.view.Inventory;
import com.techelevator.view.Log;
import com.techelevator.view.Menu;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String MAIN_SELECT_PRODUCT = "Select Product";
	private static final String MAIN_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] PURCHASE_MENU_OPTIONS = {MAIN_MENU_OPTION_FEED_MONEY, MAIN_SELECT_PRODUCT, MAIN_FINISH_TRANSACTION};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		Inventory.fillInventoryList();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, null);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Inventory.displayMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					// do purchase
					String currentBalance = "Current Money Provided: $" + String.format("%.2f", Accounting.getBalance());
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, currentBalance);
					if (purchaseChoice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Please insert whole dollar amount: ");
						Scanner moneyInput = new Scanner(System.in);
						String money = moneyInput.nextLine();
						Double previousBalance = Accounting.getBalance();
						try {
							double d = Double.parseDouble(money);
							DecimalFormat df = new DecimalFormat("#.##");
							Accounting.feedMoney(d);
							Log.logging("FEED MONEY: " + "$" + df.format(previousBalance) + " $" + df.format(Accounting.getBalance()));
						} catch (NumberFormatException nfe) {
							System.out.println("Not whole dollar amount");
						}
					}
					else if (purchaseChoice.equals(MAIN_SELECT_PRODUCT)) {
						Inventory.displayMenu();
						System.out.println("Please select product code: ");
						Scanner productCodeSelection = new Scanner(System.in);
						String productCode = productCodeSelection.nextLine().toUpperCase(Locale.ROOT);
						if (Inventory.getInventoryList().containsKey(productCode)) {
							Item item = Inventory.getInventoryList().get(productCode);
//                   if (Accounting.getBalance() >= item.getItemPrice())
							if (item.getItemStock() > 0) {
								Double previousBalance = Accounting.getBalance();
								if (Accounting.makePayment(item.getItemPrice()) >= 0) {
									DecimalFormat df = new DecimalFormat("#.##");
									System.out.println(item.purchaseItem());
									Log.logging(item.getItemName() + " " + productCode + " $" + df.format(previousBalance) + " $" + df.format(Accounting.getBalance()));
								} else System.out.println("NOT ENOUGH MONEY AVAILABLE.  PLEASE FEED MONEY.");
							}
							else System.out.println("Sorry, that item is not available...");
						}
						else System.out.println("Product does not exist!");

					}
					else if (purchaseChoice.equals(MAIN_FINISH_TRANSACTION)) {
						DecimalFormat df = new DecimalFormat("#.##");
						Double previousBalance = Accounting.getBalance();
						System.out.println(Accounting.getChange());
						Log.logging("GIVE CHANGE: " + "$" + df.format(previousBalance) + " $" + df.format(Accounting.getBalance()));
						//exit purchase menu
						break;
					}
				}
			} else {
				System.out.println("Thank you for your money!");
				//exits program
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
