import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {
    private final List<Transaction> records = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);
    private double totalIncome = 0;
    private double totalExpenses = 0;
    
    private String centerText(String text, int totalWidth) {
        if (text.length() >= totalWidth) {
            return text.substring(0, totalWidth); 
        }
        int padding = (totalWidth - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(totalWidth - text.length() - padding);
    }
    
    private void printHeader(String title) {
        System.out.println();
        Colors.printBlue("");  // Set blue color for header

        Colors.printlnBlue("\t+==========================================================+"); 
        Colors.printlnBlue("\t|" + centerText(title, 58) + "|"); 
        Colors.printlnBlue("\t+==========================================================+"); 
        
        Colors.printCyan("");  // Reset to cyan for general text
        System.out.println();
    }
    
    private void printSuccess(String message) {
        Colors.printlnCyan("\t[SUCCESS] " + message);
    }
    
    private void printError(String message) {
        Colors.printlnCyan("\t[ERROR] " + message);
    }

    public void addIncome() {
        printHeader("ADD INCOME");
        
        Colors.printCyan("\tEnter income source: ");
        String category = input.nextLine();

        Colors.printCyan("\tEnter amount: PHP");
        double amount = Double.parseDouble(input.nextLine());

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
        
        Colors.printCyan("\tEnter expense category (Food, Transport, etc.): ");
        String category = input.nextLine();

        Colors.printCyan("\tEnter amount: PHP");
        double amount = Double.parseDouble(input.nextLine());

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
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|                    TOTAL INCOME                          |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        System.out.println("\t|                PHP " + String.format("%-38.2f", totalIncome) + "|");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|                   TOTAL EXPENSES                         |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        System.out.println("\t|                PHP " + String.format("%-38.2f", totalExpenses) + "|");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|                      BALANCE                             |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        System.out.println("\t|                PHP " + String.format("%-38.2f", balance) + "|");
        Colors.printlnCyan("\t+----------------------------------------------------------+");

        FinancialTip tip;
        if (balance > 0) {
            tip = new SavingTip();     
        } else if (balance == 0) {
            tip = new NeutralTip();     
        } else {
            tip = new OverspendTip();   
        }

        String tipMessage = tip.getTip();

        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|                    FINANCIAL TIP                         |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        System.out.println("\t|" + centerText(tipMessage, 58) + "|");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
    }

    public void viewBreakdown() {
        if (records.isEmpty()) {
            printHeader("EXPENSE BREAKDOWN ");
            Colors.printlnCyan("\tNo transactions recorded yet.");
            return;
        }

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Transaction t : records) {
            if (!t.isIncome()) {
                categoryTotals.merge(t.getCategory().toLowerCase(), t.getAmount(), Double::sum);
            }
        }

        printHeader("EXPENSE BREAKDOWN");
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|  Category          Amount            Percentage          |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() / totalExpenses) * 100;
            String category = capitalize(entry.getKey());
            String line = String.format("|  %-15s PHP%-12.2f     %6.2f%%             |", 
                                      category, entry.getValue(), percentage);
            System.out.println("\t" + line);
        }
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public void viewAllRecords() {
        if (records.isEmpty()) {
            printHeader("TRANSACTION HISTORY");
            Colors.printlnCyan("\tNo transactions recorded yet.");
            return;
        }

        printHeader("TRANSACTION HISTORY");
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        Colors.printlnCyan("\t|  Type       Category              Amount                 |");
        Colors.printlnCyan("\t+----------------------------------------------------------+");
        
        for (Transaction t : records) {
            String type = t.isIncome() ? "INCOME " : "EXPENSE";
            String category = t.getCategory();
            double amount = t.getAmount();
            
            String line = String.format("|  %-9s %-20s PHP%-10.2f            |", type, category, amount);
            System.out.println("\t" + line);
        }
        
        Colors.printlnCyan("\t+----------------------------------------------------------+");
    }
    
    public boolean askToContinue() {
        System.out.println();
        Colors.printCyan("\tContinue in this menu or back to main? (c/b): ");
        String response = input.nextLine().trim().toLowerCase();
        return response.equals("c") || response.equals("continue");
    }
}