public class Colors {
    public static final String CYAN = "\033[38;5;51m";   
    public static final String BLUE = "\033[38;5;39m";   
    public static final String RESET = "\033[0m";        
    
    public static void printCyan(String text) {
        System.out.print(CYAN + text + RESET);
    }
    
    public static void printBlue(String text) {
        System.out.print(BLUE + text + RESET);
    }
    
    public static void printlnCyan(String text) {
        System.out.println(CYAN + text + RESET);
    }
    
    public static void printlnBlue(String text) {
        System.out.println(BLUE + text + RESET);
    }
}