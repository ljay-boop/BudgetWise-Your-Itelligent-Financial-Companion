public abstract class Display {
    protected void setColor(int color) { System.out.print("\033[38;5;" + color + "m"); }
    protected void resetColor() { System.out.print("\033[0m"); }

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
        setColor(51); 
        System.out.println("\t══════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("\t██████╗  ██    ██ ██████╗  ██████╗  ███████╗ ████████╗    ██╗    ██╗ ██╗ ███████╗ ██████╗ ");
        System.out.println("\t██   ██╗ ██    ██ ██   ██  ██       ██          ██        ██║    ██║ ██║ ██╔════╝ ██╔══");
        System.out.println("\t██████╔╝ ██    ██ ██   ██╔╝██   ███╗█████╗      ██        ██║ █╗ ██║ ██║ ███████╗ ██████");
        System.out.println("\t██   ██╗ ██    ██ ██   ██  ██    ██ ██          ██        ██║███╗██║ ██║      ██║ ██╔══");
        System.out.println("\t██████╔╝  ██████  ███████   ██████╔╝███████╗    ██        ╚███╔███╔╝ ██║ ███████║ ██████");
        System.out.println("\t╚═════╝    ╚════╝  ╚═════╝   ╚═════╝ ╚══════╝    ╚═╝         ╚══╝╚══╝ ╚═╝╚══════╝╚═╝  ╚═╝ ");
        System.out.println("\tSmart Personal Finance Tracker - Manage Your Income & Expenses Wisely!");
        System.out.println("\t══════════════════════════════════════════════════════════════════════════════════════════");
        resetColor();
        System.out.println();
    }
    
    public void showWithoutClear() {
        setColor(51); 
        System.out.println("\t══════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("\t██████╗  ██    ██ ██████╗  ██████╗  ███████╗ ████████╗    ██╗    ██╗ ██╗ ███████╗ ██████╗ ");
        System.out.println("\t██   ██╗ ██    ██ ██   ██  ██       ██          ██        ██║    ██║ ██║ ██╔════╝ ██╔══");
        System.out.println("\t██████╔╝ ██    ██ ██   ██╔╝██   ███╗█████╗      ██        ██║ █╗ ██║ ██║ ███████╗ ██████");
        System.out.println("\t██   ██╗ ██    ██ ██   ██  ██    ██ ██          ██        ██║███╗██║ ██║      ██║ ██╔══");
        System.out.println("\t██████╔╝  ██████  ███████   ██████╔╝███████╗    ██        ╚███╔███╔╝ ██║ ███████║ ██████");
        System.out.println("\t╚═════╝    ╚════╝  ╚═════╝   ╚═════╝ ╚══════╝    ╚═╝         ╚══╝╚══╝ ╚═╝╚══════╝╚═╝  ╚═╝ ");
        System.out.println("\tSmart Personal Finance Tracker - Manage Your Income & Expenses Wisely!");
        System.out.println("\t══════════════════════════════════════════════════════════════════════════════════════════");
        resetColor();
        System.out.println();
    }
}

class IconDisplay extends Display {
    public void show() {
        clearScreen();
        setColor(51);
        System.out.println("\n\n\t\t\t Your Financial Companion \n");
        resetColor();
    }
}