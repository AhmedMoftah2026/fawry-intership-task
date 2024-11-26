import java.util.List;

public class ShippingService {
    //method that Takes a list of shippable items as argument.
    public static void shipItems(List<ShippingItem> items) {
        System.out.println("** Checkout of shipping items Service **");
        double totalWeight = 0;
        //All shippable items are passed
        //and Print item name and weight
        for (ShippingItem item : items) {
            System.out.println(" - " + item.getName() + " " + item.getWeight() + "g");
            //The weight of each item is added up to totalWeight
            totalWeight += item.getWeight();
        }
        //print the totalWeight
        System.out.println("Total package weight : " + (totalWeight / 1000) + "kg\n");
    }
}
