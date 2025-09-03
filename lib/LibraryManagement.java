import java.util.Scanner;

public class LibraryManagement {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Test database connection first
        System.out.println("Testing database connection...");
        if (!DatabaseConnection.testConnection()) {
            System.out.println("Failed to connect to database. Please check your MySQL setup.");
            return;
        }
        
        MenuLib menuLib = new MenuLib();
        
        System.out.println("\nWelcome to Library Management System!\n");
        
        while (true) {
            clearScreen();
            displayMainMenu();
            
            try {
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        menuLib.menuBook();
                        break;
                    case 2:
                        menuLib.menuMember();
                        break;
                    case 3:
                        menuLib.menuIssueReturn();
                        break;
                    case 4:
                        System.out.println("\nThank you for using Library Management System!");
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1-4.");
                        waitForEnter();
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
                waitForEnter();
            }
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("========================================================");
        System.out.println("              LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================================");
        System.out.println("  1. Book Management");
        System.out.println("  2. Member Management");
        System.out.println("  3. Issue/Return Book");
        System.out.println("  4. Exit");
        System.out.println("========================================================");
    }
    
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    public static void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // ignore
        }
    }
}
