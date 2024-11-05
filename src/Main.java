//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

public class Main {
    private static final double SHIPPING_COST_PER_KG = 30;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        double subtotal = cart.getSubtotal();
        double shippingWeight = cart.getShippableItems().stream().mapToDouble(ShippingItem::getWeight).sum();
        double shippingCost = (shippingWeight / 1000) * SHIPPING_COST_PER_KG;
        double totalAmount = subtotal + shippingCost;

        if (customer.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance for checkout.");
        }

        // Deduct customer balance and reduce product quantities
        customer.deductBalance(totalAmount);
        for (AddItemToCart item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        // Shipping notice
        if (!cart.getShippableItems().isEmpty()) {
            ShippingService.shipItems(cart.getShippableItems());
        }


        System.out.println("** Checkout receipt **");
        for (AddItemToCart item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping: " + shippingCost);
        System.out.println("Amount: " + totalAmount);
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