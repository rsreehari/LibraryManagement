import java.util.Scanner;

public class MenuLib {
    private Scanner scanner = new Scanner(System.in);
    private Book book = new Book();
    private Member member = new Member();
    private Issue issue = new Issue();
    
    public void menuBook() {
        while (true) {
            LibraryManagement.clearScreen();
            System.out.println("\t\t\t Book Record Management\n");
            System.out.println("======================================================");
            System.out.println("1. Add Book Record");
            System.out.println("2. Display Book Records");
            System.out.println("3. Search Book Record");
            System.out.println("4. Delete Book Record");
            System.out.println("5. Update Book Record");
            System.out.println("6. Return to Main Menu");
            System.out.println("======================================================");
            
            System.out.print("Enter Choice between 1 to 6: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        book.insertData();
                        break;
                    case 2:
                        book.display();
                        break;
                    case 3:
                        book.searchBookRecord();
                        break;
                    case 4:
                        book.deleteBook();
                        break;
                    case 5:
                        book.updateBook();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Wrong Choice......Enter Your Choice again");
                        LibraryManagement.waitForEnter();
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                LibraryManagement.waitForEnter();
            }
        }
    }
    
    public void menuMember() {
        while (true) {
            LibraryManagement.clearScreen();
            System.out.println("\t\t\t Member Record Management\n");
            System.out.println("======================================================");
            System.out.println("1. Add Member Record");
            System.out.println("2. Display Member Records");
            System.out.println("3. Search Member Record");
            System.out.println("4. Delete Member Record");
            System.out.println("5. Update Member Record");
            System.out.println("6. Return to Main Menu");
            System.out.println("======================================================");
            
            System.out.print("Enter Choice between 1 to 6: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        member.insertMember();
                        break;
                    case 2:
                        member.display();
                        break;
                    case 3:
                        member.searchMember();
                        break;
                    case 4:
                        member.deleteMember();
                        break;
                    case 5:
                        member.updateMember();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Wrong Choice......Enter Your Choice again");
                        LibraryManagement.waitForEnter();
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                LibraryManagement.waitForEnter();
            }
        }
    }
    
    public void menuIssueReturn() {
        while (true) {
            LibraryManagement.clearScreen();
            System.out.println("\t\t\t Issue/Return Management\n");
            System.out.println("==================================================");
            System.out.println("1. Issue Book");
            System.out.println("2. Display Issued Book Records");
            System.out.println("3. Return Issued Book");
            System.out.println("4. Return to Main Menu");
            System.out.println("==================================================");
            
            System.out.print("Enter Choice between 1 to 4: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        issue.issueBookData();
                        break;
                    case 2:
                        issue.showIssuedBooks();
                        break;
                    case 3:
                        issue.returnBook();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Wrong Choice......Enter Your Choice again");
                        LibraryManagement.waitForEnter();
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                LibraryManagement.waitForEnter();
            }
        }
    }
}
