
abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int qty) {
        if (quantity >= qty) {
            quantity -= qty;
        } else {
            throw new IllegalArgumentException("Quantity is not available " + name);
        }
    }

    //Check Is the product expired or not?
    public abstract boolean isExpired();
    //the product need shipping?
    public abstract boolean requiresShipping();
    //Product weight if it needs shipping
    public abstract double getWeight();
}
