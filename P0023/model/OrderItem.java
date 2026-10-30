package model;

public class OrderItem {
    private final String fruitId;
    private final String name;
    private final double price;
    private int quantity;

    // Giữ giá lúc mua để tính tiền đơn hàng. / Keep the price at the time of purchase.
    public OrderItem(Fruit fruit, int quantity) {
        fruitId = fruit.getId();
        name = fruit.getName();
        price = fruit.getPrice();
        this.quantity = quantity;
    }

    public String getFruitId() { return fruitId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getAmount() { return price * quantity; }

    // Gộp số lượng cùng trái cây trong giỏ. / Add more of the same fruit to the cart.
    public int addQuantity(int amount) {
        quantity += amount;
        return quantity;
    }
}
