package controller;

import java.util.ArrayList;
import model.Fruit;
import model.FruitManager;
import model.OrderItem;
import view.FruitView;

public class FruitController {
    private final FruitManager model;
    private final FruitView view;

    public FruitController(FruitManager model, FruitView view) {
        this.model = model;
        this.view = view;
    }

    // Chạy menu đến khi chọn thoát. / Run the menu until the user chooses exit.
    public void run() {
        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1: createFruit(); break;
                case 2: view.showOrders(model.getOrders()); break;
                case 3: shopping(); break;
                case 4: view.showMessage("Goodbye!"); break;
            }
        } while (choice != 4);
    }

    // Thêm trái cây và trả về số loại đã thêm. / Add fruit and return the number added.
    public int createFruit() {
        int added = 0;
        do {
            String id;
            boolean duplicate;
            do {
                id = view.getString("Fruit ID: ");
                duplicate = model.findById(id) != null;
                if (duplicate) {
                    view.showMessage("This ID already exists!");
                }
            } while (duplicate);
            String name = view.getString("Fruit name: ");
            double price = view.getPrice("Price: ");
            int quantity = view.getInt("Quantity: ", 1, Integer.MAX_VALUE);
            String origin = view.getString("Origin: ");
            if (model.add(new Fruit(id, name, price, quantity, origin))) {
                added++;
                view.showMessage("Fruit added successfully!");
            }
        } while (view.getYesNo("Do you want to continue"));
        view.showFruits(model.getAll());
        return added;
    }

    // Chọn hàng, xác nhận rồi lưu tên khách. / Select fruit, confirm the order and save the customer name.
    public void shopping() {
        ArrayList<Fruit> available = model.getAvailable();
        if (available.isEmpty()) {
            view.showMessage("No fruit is available for shopping.");
        } else {
            ArrayList<OrderItem> cart = new ArrayList<>();
            boolean orderNow;
            do {
                view.showFruits(available);
                int item = view.getInt("Select item: ", 1, available.size());
                Fruit fruit = available.get(item - 1);
                view.showMessage("You selected: " + fruit.getName());
                int quantity = view.getInt("Please input quantity: ", 1, fruit.getQuantity());
                model.buy(cart, fruit.getId(), quantity);
                available = model.getAvailable();
                if (available.isEmpty()) {
                    view.showMessage("All fruit is sold out. Please finish your order.");
                    orderNow = true;
                } else {
                    orderNow = view.getYesNo("Do you want to order now");
                }
            } while (!orderNow);
            view.showItems(cart);
            String customer = view.getString("Input your name: ");
            if (model.saveOrder(customer, cart)) {
                view.showMessage("Order saved successfully!");
            }
        }
    }
}
