import java.util.Scanner;

public class BudgetWiseApp {
    
    private static void showMenu() {
        System.out.println();
        Colors.printlnCyan("\t+==========================================================+");
        Colors.printlnCyan("\t|                      MAIN MENU                           |");
        Colors.printlnCyan("\t|               Choose an option below                     |");
        Colors.printlnCyan("\t+==========================================================+");
        
        System.out.println("\t|   1. [INCOME]   Add Income                               |");
        System.out.println("\t|   2. [EXPENSE]  Add Expense                              |");
        System.out.println("\t|   3. [SUMMARY]  View Monthly Summary                     |");
        System.out.println("\t|   4. [ANALYSIS] View Expense Breakdown                   |");
        System.out.println("\t|   5. [RECORDS]  View All Records                         |");
        System.out.println("\t|   6. [EXIT]     Exit Application                         |");
        
        Colors.printlnCyan("\t+==========================================================+");
        System.out.println();
    }
    
    private static void printGoodbye() {
        System.out.println();
        Colors.printlnCyan("\t+==========================================================+");
        Colors.printlnCyan("\t|                    THANK YOU!                           |");
        Colors.printlnCyan("\t+==========================================================+");
        System.out.println("\t|  Thank you for using BudgetWise!                        |");
        System.out.println("\t|  Stay financially smart and secure!                     |");
        System.out.println("\t|  See you next time!                                     |");
        Colors.printlnCyan("\t+==========================================================+");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BannerDisplay banner = new BannerDisplay();
        BudgetTracker tracker = new BudgetTracker();

        banner.show();
        Colors.printCyan("\tPress Enter to continue...");
        input.nextLine();

        int choice;
        boolean continueInMenu = false;
        
        do {
            if (!continueInMenu) {
                banner.show(); 
                showMenu();
                Colors.printCyan("\tEnter your choice (1-6): ");
                choice = Integer.parseInt(input.nextLine());
            } else {
                showMenu();
                Colors.printCyan("\tEnter your choice (1-6): ");
                choice = Integer.parseInt(input.nextLine());
                continueInMenu = false;
            }

            switch (choice) {
                case 1:
                    tracker.addIncome();
                    continueInMenu = tracker.askToContinue();
                    break;
                case 2:
                    tracker.addExpense();
                    continueInMenu = tracker.askToContinue();
                    break;
                case 3:
                    tracker.viewSummary();
                    continueInMenu = tracker.askToContinue();
                    break;
                case 4:
                    tracker.viewBreakdown();
                    continueInMenu = tracker.askToContinue();
                    break;
                case 5:
                    tracker.viewAllRecords();
                    continueInMenu = tracker.askToContinue();
                    break;
                case 6:
                    printGoodbye();
                    break;
                default:
                    System.out.println();
                    Colors.printlnCyan("\tInvalid choice! Please enter a number between 1-6.");
                    System.out.println();
                    break;
            }
        } while (choice != 6);
        
        input.close();
    }
}