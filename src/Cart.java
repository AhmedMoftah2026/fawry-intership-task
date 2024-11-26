import java.util.ArrayList;
import java.util.List;


class Cart {
    //List containing products added to the cart.
    private List<AddItemToCart> items = new ArrayList<AddItemToCart>();

    public void add(Product product, int quantity) {
        if (product.isExpired()) {
            throw new IllegalArgumentException(product.getName() + " is expired.");
        }
        else if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("The requested quantity is not available " + product.getName());
        }
        else {
            items.add(new AddItemToCart(product, quantity));
        }
    }

    public List<AddItemToCart> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
    //To calculate the subtotal of the products in the Cart
    public double getSubtotal() {
        //apply method getTotalPrice on all items in this list
        //and Collect all values produced from getTotalPrice
        return items.stream().mapToDouble(AddItemToCart::getTotalPrice).sum();
    }

//Collect all the products that need to be shipped
// and convert them to a list containing objects of type ShippingItem
    public List<ShippingItem> getShippableItems() {
        //first created An empty list is  to store all shippable items
        List<ShippingItem> ShippingItem = new ArrayList<>();
        //Looping on All items in the cart are available in this list
        for (AddItemToCart item : items) {
            Product product = item.getProduct();
            //Check that the product needs shipping or not
            if (product.requiresShipping()) {
                //using Anonymous inner class to implement two methods in the ShippingItem interface
                ShippingItem.add(new ShippingItem() {
                    public String getName() {
                        return product.getName();
                    }
                    //calculate Total weight is  based on product weight and quantity of product.
                    public double getWeight() {
                        return product.getWeight() * item.getQuantity();
                    }
                });
            }
        }
        return ShippingItem;
    }
}
