import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {
    private final List<Transaction> records = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private double totalIncome = 0;
    private double totalExpenses = 0;

    private void setColor(int c) { System.out.print("\033[38;5;" + c + "m"); }
    private void resetColor() { System.out.print("\033[0m"); }
    
    private String centerText(String text, int totalWidth) {
        if (text.length() >= totalWidth) {
            return text.substring(0, totalWidth); 
        }
        int padding = (totalWidth - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(totalWidth - text.length() - padding);
    }
    
    private void printHeader(String title) {
        System.out.println();
        setColor(39); 

        System.out.println("\t+==========================================================+"); 
        System.out.println("\t|" + centerText(title, 58) + "|"); 
        System.out.println("\t+==========================================================+"); 
        
        resetColor();
        System.out.println();
    }
    
    private void printSuccess(String message) {
        setColor(51); 
        System.out.println("\t[SUCCESS] " + message);
        resetColor();
    }
    
    private void printError(String message) {
        setColor(51); 
        System.out.println("\t[ERROR] " + message);
        resetColor();
    }

    public void addIncome() {
        printHeader("ADD INCOME");
        
        setColor(51); 
        System.out.print("\tEnter income source: ");
        resetColor();
        String category = sc.nextLine();

        setColor(51); 
        System.out.print("\tEnter amount: PHP");
        resetColor();
        double amount = Double.parseDouble(sc.nextLine());

        if (amount < 0) {
            printError("Amount cannot be negative!");
            return;
        }

        records.add(new Transaction(category, amount, true));
        totalIncome += amount;
        printSuccess("Income successfully added!");
    }

    public void addExpense() {
        printHeader("ADD EXPENSE");
        
        setColor(51); 
        System.out.print("\tEnter expense category (Food, Transport, etc.): ");
        resetColor();
        String category = sc.nextLine();

        setColor(51); 
        System.out.print("\tEnter amount: PHP");
        resetColor();
        double amount = Double.parseDouble(sc.nextLine());

        if (amount < 0) {
            printError("Amount cannot be negative!");
            return;
        }

        records.add(new Transaction(category, amount, false));
        totalExpenses += amount;
        printSuccess("Expense successfully added!");
    }

    public void viewSummary() {
        double balance = totalIncome - totalExpenses;

        printHeader("FINANCIAL SUMMARY ");
        
        setColor(51);
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|                    TOTAL INCOME                          |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        System.out.println("\t|                PHP " + String.format("%-38.2f", totalIncome) + "|");
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|                   TOTAL EXPENSES                         |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        System.out.println("\t|                PHP " + String.format("%-38.2f", totalExpenses) + "|");
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|                      BALANCE                             |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        System.out.println("\t|                PHP " + String.format("%-38.2f", balance) + "|");
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();

        String tip;
        if (balance > 0) {
            tip = "Excellent! You're saving money. Consider investments!";
        } else if (balance == 0) {
            tip = "You broke even. Track expenses closely next month!";
        } else {
            tip = "Budget Alert! Review your expenses and cut costs!";
        }

        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|                    FINANCIAL TIP                         |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        System.out.println("\t|" + centerText(tip, 58) + "|");
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
    }

    public void viewBreakdown() {
        if (records.isEmpty()) {
            printHeader("EXPENSE BREAKDOWN ");
            setColor(51); 
            System.out.println("\tNo transactions recorded yet.");
            resetColor();
            return;
        }

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Transaction t : records) {
            if (!t.isIncome()) {
                categoryTotals.merge(t.getCategory().toLowerCase(), t.getAmount(), Double::sum);
            }
        }

        printHeader("EXPENSE BREAKDOWN");
        
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|  Category          Amount            Percentage          |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() / totalExpenses) * 100;
            String category = capitalize(entry.getKey());
            String line = String.format("|  %-15s PHP%-12.2f     %6.2f%%             |", 
                                      category, entry.getValue(), percentage);
            System.out.println("\t" + line);
        }
        
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public void viewAllRecords() {
        if (records.isEmpty()) {
            printHeader("TRANSACTION HISTORY");
            setColor(51); 
            System.out.println("\tNo transactions recorded yet.");
            resetColor();
            return;
        }

        printHeader("TRANSACTION HISTORY");
        
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        System.out.println("\t|  Type       Category              Amount                 |");
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
        
        for (Transaction t : records) {
            String type = t.isIncome() ? "INCOME " : "EXPENSE";
            String category = t.getCategory();
            double amount = t.getAmount();
            
            String line = String.format("|  %-9s %-20s PHP%-10.2f            |", type, category, amount);
            System.out.println("\t" + line);
        }
        
        setColor(51); 
        System.out.println("\t+----------------------------------------------------------+");
        resetColor();
    }
    
    public boolean askToContinue() {
        System.out.println();
        setColor(51); 
        System.out.print("\tContinue in this menu or back to main? (c/b): ");
        resetColor();
        String response = sc.nextLine().trim().toLowerCase();
        return response.equals("c") || response.equals("continue");
    }
}