package TestProject;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductFactory {

    //standard class to input the data from user
    public static Scanner scanner = new Scanner(System.in);

    public static boolean reset;
    public static double newCokePrice;
    public static double newPepsiPrice;
    public static double newSodaPrice;

    // return all the products under the vending list
    public TreeMap<String, Double> defaultProductList(){
        TreeMap<String, Double> productList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            productList.put("Coke" , (double) 25);
            productList.put("Pepsi", (double) 35);
            productList.put("Soda", (double) 45);
        return productList;
    }

    // reset the prices of the product
    public TreeMap<String, Double> updatedProductList(){
        TreeMap<String, Double> updatedProductList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        updatedProductList.put("Coke", newCokePrice);
        updatedProductList.put("Pepsi", newPepsiPrice);
        updatedProductList.put("Soda", newSodaPrice);
        return updatedProductList;
    }
// this function decides which function to call
// defaultProductList() where default price set for the product or,
// updatedProductList() where the updated prices of product listed
    public TreeMap<String, Double> ProductList(){
//        TreeMap<String, Double> productList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        if(reset){
            return updatedProductList();
        } else {
            return defaultProductList();
        }
    }
// reset the vending Machine product prices and asks user to enter the new prices for the products
    public void resetMachine(){

        System.out.println("Please enter YES to RESET the product Price, or enter NO to cancel the selection...");
        String userSelection = scanner.nextLine();

        if(userSelection.equalsIgnoreCase("YES")){
            System.out.println("Please update Product Prices::");
            updateProductPrices();
            reset = true;
        } else {
            System.out.println("Resetting machine cancelled.. ");
            reset = false;
        }
    }

// printing the product
    public void printProductList(TreeMap<String, Double> productList){
        System.out.println("Product list is as below: \n" +
                "Product" + "\t" + "Price");
        for(Map.Entry<String, Double> entry: productList.entrySet()){

            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
// taking user input for updated prices for the product
    private void updateProductPrices(){
        try {
            System.out.println("New Coke Price:");
            newCokePrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("New Pepsi Price:");
            newPepsiPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("New Soda Price:");
            newSodaPrice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter appropriate option to choose...");
        }


    }


}
