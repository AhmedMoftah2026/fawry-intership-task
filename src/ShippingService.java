import java.util.List;

public class ShippingService {
    public static void shipItems(List<ShippingItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (ShippingItem item : items) {
            System.out.println(" - " + item.getName() + " " + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight: " + (totalWeight / 1000) + "kg\n");
    }
}
