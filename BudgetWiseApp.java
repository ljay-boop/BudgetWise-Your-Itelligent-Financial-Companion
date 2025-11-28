import java.util.Scanner;

public class BudgetWiseApp {
    
    private static void setColor(int color) { System.out.print("\033[38;5;" + color + "m"); }
    private static void resetColor() { System.out.print("\033[0m"); }
    
    private static void showMenu() {
        System.out.println();
        setColor(51);
        System.out.println("\t+==========================================================+");
        System.out.println("\t|                      MAIN MENU                           |");
        System.out.println("\t|               Choose an option below                     |");
        System.out.println("\t+==========================================================+");
        resetColor();
        
        System.out.println("\t|   1. [INCOME]   Add Income                               |");
        System.out.println("\t|   2. [EXPENSE]  Add Expense                              |");
        System.out.println("\t|   3. [SUMMARY]  View Monthly Summary                     |");
        System.out.println("\t|   4. [ANALYSIS] View Expense Breakdown                   |");
        System.out.println("\t|   5. [RECORDS]  View All Records                         |");
        System.out.println("\t|   6. [EXIT]     Exit Application                         |");
        
        setColor(51);
        System.out.println("\t+==========================================================+");
        resetColor();
        System.out.println();
    }
    
    private static void printGoodbye() {
        System.out.println();
        setColor(51);
        System.out.println("\t+==========================================================+");
        System.out.println("\t|                    THANK YOU!                           |");
        System.out.println("\t+==========================================================+");
        System.out.println("\t|  Thank you for using BudgetWise!                        |");
        System.out.println("\t|  Stay financially smart and secure!                     |");
        System.out.println("\t|  See you next time!                                     |");
        System.out.println("\t+==========================================================+");
        resetColor();
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BannerDisplay banner = new BannerDisplay();
        BudgetTracker tracker = new BudgetTracker();

        banner.show();
        setColor(51);
        System.out.print("\tPress Enter to continue...");
        resetColor();
        input.nextLine();

        int choice;
        boolean continueInMenu = false;
        
        do {
            if (!continueInMenu) {
                banner.show(); 
                showMenu();
                setColor(51);
                System.out.print("\tEnter your choice (1-6): ");
                resetColor();
                choice = Integer.parseInt(input.nextLine());
            } else {
                showMenu();
                setColor(51);
                System.out.print("\tEnter your choice (1-6): ");
                resetColor();
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
                    setColor(51);
                    System.out.println("\tInvalid choice! Please enter a number between 1-6.");
                    resetColor();
                    System.out.println();
                    break;
            }
        } while (choice != 6);
        
        input.close();
    }
}