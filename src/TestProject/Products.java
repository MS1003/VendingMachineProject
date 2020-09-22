package TestProject;

import java.util.ArrayList;
import java.util.Map;


public class Products extends ProductFactory {

    public final ArrayList<String> selectedItemList = new ArrayList<>();

    // this list all the selected products: currently only one item can be selected
    public void printSelections() {
        System.out.println("You have " + selectedItemList.size() + " item(s) in the list");
        for (int i = 0; i < selectedItemList.size(); i++) {
            System.out.println(i + 1 + " " + selectedItemList.get(i));
        }
    }
// this add items in the list
    public void addItem(String item) {
        selectedItemList.add(item);
    }

 //this gives queue of the selected items
    public boolean selectedItemCount() {
        if (selectedItemList.size() > 0) {
            return true;
        }
        return false;
    }

    // this function cancel the selected items
    public void cancelSelection() {
        if (selectedItemCount()) {
            selectedItemList.clear();
            System.out.println("Selection is cancelled, please select 0 to go back to main menu");
        } else {
            System.out.println("There is no items in the list of purchase. Please press 2 to select an item to purchase, or ..");
        }
    }

    // Clear the queue of selected items.
    public void clearSelection() {
        if (selectedItemCount()) {
            selectedItemList.clear();
        }
    }


    // this consist of array of valid/accepted coins by the Vending Machine
    public boolean validCoins(double coins) {
        double[] acceptedCoins = {1, 5, 10, 25};
        for (int i = 0; i < acceptedCoins.length; i++) {
            if (acceptedCoins[i] == coins) {
                System.out.println(+coins + "p accepted");
                return true;
            }
        }
        return false;
    }

    // Amount of the selected item to be paid
    public double payAmount() {
        if (selectedItemCount()) {
                for (Map.Entry<String, Double> entry : ProductList().entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(selectedItemList.get(0))) {
                        System.out.println("Price for selected product: " + entry.getKey() + " is: " + entry.getValue());
                        return entry.getValue();
                    }
                }
        }
        return 0;
    }

}