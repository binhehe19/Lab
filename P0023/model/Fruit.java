package model;

public class Fruit {
    private final String id;
    private final String name;
    private final double price;
    private int quantity;
    private final String origin;

    // Lưu thông tin trái cây. / Save fruit information.
    public Fruit(String id, String name, double price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getOrigin() { return origin; }

    // Trừ tồn kho nếu đủ hàng. / Reduce stock if enough fruit is available.
    public boolean takeStock(int amount) {
        boolean taken = amount > 0 && amount <= quantity;
        if (taken) {
            quantity -= amount;
        }
        return taken;
    }
}
