

public class Checkout {
    private static final double SHIPPING_COST_PER_KG = 30;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        double subtotal = cart.getSubtotal();
        //Returns a list of shippable items.and Adds the total weights of shippable items.
        double shippingWeight = cart.getShippableItems().stream().mapToDouble(ShippingItem::getWeight).sum();
        //shipping Service Cost
        double shippingCost = (shippingWeight / 1000) * SHIPPING_COST_PER_KG;
        //
        double totalAmount = subtotal + shippingCost;
        //check if totalAmount < balance of customer
        if (customer.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance for checkout.");
        }

        // Deduct customer balance and reduce product quantities
        customer.deductBalance(totalAmount);
        //Looping on All items in the cart .
        for (AddItemToCart item : cart.getItems()) {
                 //Reduces the available quantity of a product based on the quantity ordered in the cart.
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        // Shipping notice
        //If there are shippable items in the cart:
        if (!cart.getShippableItems().isEmpty()) {
            //ShippingService.shipItems() is called to show the shipping details
            ShippingService.shipItems(cart.getShippableItems());
        }

        //Print invoice
        System.out.println("** Checkout Details **");
        //For each item in the cart, print:
        for (AddItemToCart item : cart.getItems()) {
                                //Quantity                        //Product name.           //Total price (price x quantity).
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Cost without Shipping Service : " + subtotal);
        System.out.println("Shipping Service cost: " + shippingCost);
        System.out.println("Cost with Shipping Service: " + totalAmount);
        System.out.println("Remaining balance: " + customer.getBalance() + "\n");
    }

    public static void main(String[] args) {
        Product cheese = new ExpirProduct("Cheese", 200, 5, false, 400);
        Product biscuits = new ExpirProduct("Biscuits", 150, 10, false, 700);
        Product tv = new NontExpirProduct("TV", 5000, 3, 10000);
        Product scratchCard = new NontExpirProduct("Mobile Scratch Card", 50, 20, 0);

        Customer customer = new Customer("Ahmed Moftah", 10000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);

        checkout(customer, cart);
    }
}