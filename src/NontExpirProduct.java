
public class NontExpirProduct extends Product {
    private double weight;

    public NontExpirProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return weight > 0;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
