public abstract class Display {
    protected void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) 
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else 
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public abstract void show();
}

class BannerDisplay extends Display {
    public void show() {
        clearScreen();
        
        Colors.printlnCyan("\t══════════════════════════════════════════════════════════════════════════════════════════");
        Colors.printlnCyan("\t██████╗  ██    ██ ██████╗  ██████╗  ███████╗ ████████╗    ██╗    ██╗ ██╗ ███████╗ ██████╗ ");
        Colors.printlnCyan("\t██   ██╗ ██    ██ ██   ██  ██       ██          ██        ██║    ██║ ██║ ██╔════╝ ██╔══██╗");
        Colors.printlnCyan("\t██████╔╝ ██    ██ ██   ██╔╝██   ███╗█████╗      ██        ██║ █╗ ██║ ██║ ███████╗ ██████╔╝");
        Colors.printlnCyan("\t██   ██╗ ██    ██ ██   ██  ██    ██ ██          ██        ██║███╗██║ ██║      ██║ ██╔══██╗");
        Colors.printlnCyan("\t██████╔╝  ██████  ███████   ██████╔╝███████╗    ██        ╚███╔███╔╝ ██║ ███████║ ██████╔╝");
        Colors.printlnCyan("\t╚═════╝    ╚════╝  ╚═════╝   ╚═════╝ ╚══════╝    ╚═╝         ╚══╝╚══╝ ╚═╝╚══════╝╚═╝  ╚═╝ ");
        Colors.printlnCyan("\tSmart Personal Finance Tracker - Manage Your Income & Expenses Wisely!");
        Colors.printlnCyan("\t══════════════════════════════════════════════════════════════════════════════════════════");
    }
    
    public void showWithoutClear() {
        
        Colors.printlnCyan("\t══════════════════════════════════════════════════════════════════════════════════════════");
        Colors.printlnCyan("\t██████╗  ██    ██ ██████╗  ██████╗  ███████╗ ████████╗    ██╗    ██╗ ██╗ ███████╗ ██████╗ ");
        Colors.printlnCyan("\t██   ██╗ ██    ██ ██   ██  ██       ██          ██        ██║    ██║ ██║ ██╔════╝ ██╔══██╗");
        Colors.printlnCyan("\t██████╔╝ ██    ██ ██   ██╔╝██   ███╗█████╗      ██        ██║ █╗ ██║ ██║ ███████╗ ██████╔╝");
        Colors.printlnCyan("\t██   ██╗ ██    ██ ██   ██  ██    ██ ██          ██        ██║███╗██║ ██║      ██║ ██╔══██╗");
        Colors.printlnCyan("\t██████╔╝  ██████  ███████   ██████╔╝███████╗    ██        ╚███╔███╔╝ ██║ ███████║ ██████╔╝");
        Colors.printlnCyan("\t╚═════╝    ╚════╝  ╚═════╝   ╚═════╝ ╚══════╝    ╚═╝         ╚══╝╚══╝ ╚═╝╚══════╝╚═╝  ╚═╝ ");
        Colors.printlnCyan("\tSmart Personal Finance Tracker - Manage Your Income & Expenses Wisely!");
        Colors.printlnCyan("\t══════════════════════════════════════════════════════════════════════════════════════════");
    }
}

class IconDisplay extends Display {
    public void show() {
        clearScreen();
        Colors.printlnCyan("\n\n\t\t\t Your Financial Companion \n");
    }
}