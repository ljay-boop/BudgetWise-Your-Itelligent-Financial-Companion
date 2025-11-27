public class Transaction {
    private String category;
    private double amount;
    private boolean isIncome;

    public Transaction(String category, double amount, boolean isIncome) {
        this.category = category;
        this.amount = amount;
        this.isIncome = isIncome;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public boolean isIncome() { return isIncome; }
}