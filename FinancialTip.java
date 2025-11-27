public interface FinancialTip {
    String getTip();
}

class SavingTip implements FinancialTip {
    public String getTip() { return "Great job! Consider saving or investing your remaining balance."; }
}

class NeutralTip implements FinancialTip {
    public String getTip() { return "You broke even this month. Try tracking categories more closely next time."; }
}

class OverspendTip implements FinancialTip {
    public String getTip() { return "Warning! You are overspending. Cut back on non-essential expenses."; }
}