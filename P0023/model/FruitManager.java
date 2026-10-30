package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class FruitManager {
    private final ArrayList<Fruit> fruits = new ArrayList<>();
    private final Hashtable<String, ArrayList<OrderItem>> orders = new Hashtable<>();

    // Tìm ID không phân biệt hoa thường. / Find an ID and ignore letter case.
    public Fruit findById(String id) {
        Fruit found = null;
        for (Fruit fruit : fruits) {
            if (fruit.getId().equalsIgnoreCase(id)) {
                found = fruit;
                break;
            }
        }
        return found;
    }

    // Không thêm ID đã có. / Do not add an ID that already exists.
    public boolean add(Fruit fruit) {
        boolean added = false;
        if (findById(fruit.getId()) == null && fruit.getPrice() > 0
                && Double.isFinite(fruit.getPrice()) && fruit.getQuantity() > 0) {
            added = fruits.add(fruit);
        }
        return added;
    }

    public ArrayList<Fruit> getAll() {
        return new ArrayList<>(fruits);
    }

    // Chỉ bán trái cây còn hàng. / Only show fruit that is in stock.
    public ArrayList<Fruit> getAvailable() {
        ArrayList<Fruit> available = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() > 0) {
                available.add(fruit);
            }
        }
        return available;
    }

    // Kiểm tra tồn kho, thêm vào giỏ rồi giảm hàng. / Check stock, add to the cart and reduce stock.
    public boolean buy(ArrayList<OrderItem> cart, String id, int quantity) {
        Fruit fruit = findById(id);
        boolean bought = fruit != null && fruit.takeStock(quantity);
        if (bought) {
            OrderItem found = null;
            for (OrderItem item : cart) {
                if (item.getFruitId().equalsIgnoreCase(id)) {
                    found = item;
                    break;
                }
            }
            if (found == null) {
                cart.add(new OrderItem(fruit, quantity));
            } else {
                found.addQuantity(quantity);
            }
        }
        return bought;
    }

    // Cùng tên khách thì giữ cả các lần mua trước. / Keep earlier items when a customer buys again.
    public boolean saveOrder(String customer, ArrayList<OrderItem> cart) {
        boolean saved = !customer.trim().isEmpty() && !cart.isEmpty();
        if (saved) {
            ArrayList<OrderItem> items = orders.get(customer);
            if (items == null) {
                items = new ArrayList<>();
                orders.put(customer, items);
            }
            items.addAll(cart);
        }
        return saved;
    }

    public Hashtable<String, ArrayList<OrderItem>> getOrders() {
        Hashtable<String, ArrayList<OrderItem>> result = new Hashtable<>();
        for (String customer : orders.keySet()) {
            result.put(customer, new ArrayList<>(orders.get(customer)));
        }
        return result;
    }
}
