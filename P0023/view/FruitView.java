package view;

import java.util.ArrayList;
import java.util.Hashtable;
import model.Fruit;
import model.OrderItem;

public class FruitView {
    private final Utility utility = new Utility();

    public int showMenu() {
        showMessage("\nFRUIT SHOP SYSTEM");
        showMessage("1. Create Fruit");
        showMessage("2. View orders");
        showMessage("3. Shopping (for buyer)");
        showMessage("4. Exit");
        return getInt("Please choose (1-4): ", 1, 4);
    }

    public String getString(String message) { return utility.getString(message); }
    public int getInt(String message, int min, int max) { return utility.getInt(message, min, max); }
    public double getPrice(String message) { return utility.getPrice(message); }
    public boolean getYesNo(String message) { return utility.getYesNo(message); }
    public void showMessage(String message) { System.out.println(message); }

    // Số Item là vị trí trên màn hình, không phải Fruit ID. / Item is the screen number, not the fruit ID.
    public void showFruits(ArrayList<Fruit> fruits) {
        showMessage("\nList of Fruit:");
        showMessage("Item | ID | Fruit Name | Origin | Price | Quantity");
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            System.out.printf("%d | %s | %s | %s | %.2f$ | %d%n",
                    i + 1, fruit.getId(), fruit.getName(), fruit.getOrigin(),
                    fruit.getPrice(), fruit.getQuantity());
        }
        if (fruits.isEmpty()) {
            showMessage("Fruit list is empty.");
        }
    }

    // In tiền từng dòng và tổng tiền. / Print each item amount and the total.
    public void showItems(ArrayList<OrderItem> items) {
        double total = 0;
        showMessage("Product | Quantity | Price | Amount");
        for (OrderItem item : items) {
            System.out.printf("%s | %d | %.2f$ | %.2f$%n",
                    item.getName(), item.getQuantity(), item.getPrice(), item.getAmount());
            total += item.getAmount();
        }
        System.out.printf("Total: %.2f$%n", total);
    }

    public void showOrders(Hashtable<String, ArrayList<OrderItem>> orders) {
        if (orders.isEmpty()) {
            showMessage("No orders yet.");
        }
        for (String customer : orders.keySet()) {
            showMessage("\nCustomer: " + customer);
            showItems(orders.get(customer));
        }
    }
}
