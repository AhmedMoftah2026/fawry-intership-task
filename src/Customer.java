public class Customer {
    private String name;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;

    }

    public void deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("The balance is not enough");
        }
    }

}
