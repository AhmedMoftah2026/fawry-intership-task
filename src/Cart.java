import java.util.ArrayList;
import java.util.List;


class Cart {
    private List<AddItemToCart> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (product.isExpired()) {
            throw new IllegalArgumentException(product.getName() + " is expired.");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock for " + product.getName());
        }
        items.add(new AddItemToCart(product, quantity));
    }

    public List<AddItemToCart> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(AddItemToCart::getTotalPrice).sum();
    }

    public List<ShippingItem> getShippableItems() {
        List<ShippingItem> ShippingItem = new ArrayList<>();
        for (AddItemToCart item : items) {
            Product product = item.getProduct();
            if (product.requiresShipping()) {
                ShippingItem.add(new ShippingItem() {
                    public String getName() {
                        return product.getName();
                    }

                    public double getWeight() {
                        return product.getWeight() * item.getQuantity();
                    }
                });
            }
        }
        return ShippingItem;
    }
}
