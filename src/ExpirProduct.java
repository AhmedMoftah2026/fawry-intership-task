
public class ExpirProduct extends Product{
    private boolean expired;
    private double weight;

    public ExpirProduct(String name, double price, int quantity, boolean expired, double weight) {
        super(name, price, quantity);
        this.expired = expired;
        this.weight = weight;
    }


    @Override
    public boolean isExpired() {
        return expired;
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
