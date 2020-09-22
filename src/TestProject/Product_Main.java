package TestProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Product_Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Products products = new Products();
    private static ProductFactory productFactory = new ProductFactory();

    public static void main(String[] args) {

        System.out.println("Vending Machine Dispenses following items: \n");
        productFactory.printProductList(productFactory.defaultProductList());


        boolean quit = false;
        int choice = 0;
        while (!quit) {

            System.out.println("Press 0 to Print Instructions");

            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Please enter appropriate option to choose...");
            }

            while (choice < 0 || choice > 7) {
                System.out.print("Invalid value!: Choose the appropriate option... ");
                System.out.println("Press 0 to Print Instructions");
                choice = new Scanner(System.in).nextInt();
            }

            scanner.nextLine();
            switch (choice) {

                case 0:
                    printInstructions();
                    break;

                case 1:
                    productFactory.printProductList(productFactory.ProductList());
                    break;

                case 2:
                    selectItem();
                    break;

                case 3:
                    products.printSelections();
                    break;

                case 4:
                    makePayment();
                    break;

                case 5:
                    products.cancelSelection();
                    break;

                case 6:
                    productFactory.resetMachine();
                    break;

                case 7:
                    quit = true;
                    break;

            }

        }

    }

    public static void printInstructions() {

        System.out.println("\n Press");
        System.out.println("\t 0 - To print instruction.");
        System.out.println("\t 1 - Print Product List");
        System.out.println("\t 2 - To select item to purchase from Product List");
        System.out.println("\t 3 - To print selections");
        System.out.println("\t 4 - To make Payment");
        System.out.println("\t 5 - To Cancel item");
        System.out.println("\t 6 - To ResetMachine");
        System.out.println("\t 7 - Exit...");

    }
// selection items from vending machine
    public static void selectItem() {
        if (products.selectedItemCount()) {
            System.out.println("You can select only one item from Vending Machine at a time");
        } else {
            System.out.println("Enter the item to purchase");
            String selectedItem = scanner.nextLine();
            if (productFactory.ProductList().containsKey(selectedItem)) {
                System.out.println("Product item selected correctly, press: 0 for Main menu; 4 for Payment; or 6 to Cancel selection");
                products.addItem(selectedItem);
            } else {
                System.out.println("Product is not available in Vending Machine, " +
                        "Press 0 to go back to main menu or enter option 2 to choose the product.");
            }
        }
    }

    // make payment of the selected item

    public static void makePayment() {
        if (!products.selectedItemCount()) {
            System.out.println("You have not selected any item to purchase. Please press option 2 to select item to purchase, or..");
        } else {

            boolean flag = true;
            double totalAmt = 0;
            System.out.println("Vending Machine only accepts coins in the denomination of 1, 5, 10, 25 Cents");
            double payableAmt = products.payAmount();
            System.out.println(" Enter the coin in accepted denomination...");

            while (flag) {
                double coins =0;
                try {
                    coins = scanner.nextInt();
                    scanner.nextLine();
                    if (products.validCoins(coins)) {

                        totalAmt = totalAmt + coins;
                        if (totalAmt >= payableAmt) {

                            if (cancelPayment()) {
                                System.out.println("Payment cancelled," + totalAmt + " is refunded. Select 0 to go back to main menu");
                                products.cancelSelection();
                            } else {
                                System.out.println("Payment Successful... Please take you product");
                                double returnBalance = totalAmt - payableAmt;
                                System.out.println("Return Balance is:" + returnBalance);
                                totalAmt = 0;
                                products.clearSelection();
                            }
                            flag = false;

                        } else {
                            double additionalPayment = payableAmt - totalAmt;
                            if (cancelPayment()) {
                                System.out.println("Payment cancelled," + totalAmt + " is refunded");
                                products.cancelSelection();
                                flag = false;
                            } else {
                                System.out.println("Insufficient payment.. Please insert " + additionalPayment + " to purchase the product");
                                flag = true;
                            }
                        }
                    } else {
                        System.out.println("Enter coins in correct denominations");
                        flag = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter appropriate option to choose...");
                    flag = true;
                    break;


                }



            }

        }
    }

    // canceling the product
    private static boolean cancelPayment() {
        System.out.println("Please enter YES to cancel the payment, or enter NO to continue...");
        String userSelection = scanner.nextLine();
        if (userSelection.equalsIgnoreCase("YES")) {
            return true;
        } else if (userSelection.equalsIgnoreCase("NO")) {
            return false;
        } else {
            System.out.println("Please select either YES or NO");
        }
        return true;
    }


}
