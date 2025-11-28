public interface FinancialTip {
    String getTip();
}

class SavingTip implements FinancialTip {
    public String getTip() { return "Excellent! You're saving money. Consider investments!"; }
}

class NeutralTip implements FinancialTip {
    public String getTip() { return "You broke even. Track expenses closely next month!"; }
}

class OverspendTip implements FinancialTip {
    public String getTip() { return "Budget Alert! Review your expenses and cut costs!"; }
}