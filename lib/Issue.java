import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Issue {
    private Scanner scanner = new Scanner(System.in);
    
    public void showIssuedBooks() {
        try {
            LibraryManagement.clearScreen();
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT B.bno, B.bname, M.mno, M.mname, I.d_o_issue, I.d_o_ret " +
                          "FROM bookrecord B, issue I, member M " +
                          "WHERE B.bno = I.bno AND I.mno = M.mno";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.println("=====================================================");
                System.out.println("Book Code : " + rs.getString("bno"));
                System.out.println("Book Name : " + rs.getString("bname"));
                System.out.println("Member Code : " + rs.getInt("mno"));
                System.out.println("Member Name : " + rs.getString("mname"));
                System.out.println("Date of Issue : " + rs.getDate("d_o_issue"));
                Date returnDate = rs.getDate("d_o_ret");
                if (returnDate != null) {
                    System.out.println("Date of Return : " + returnDate);
                } else {
                    System.out.println("Date of Return : Not Returned Yet");
                }
                System.out.println("=====================================================");
            }
            
            if (!hasRecords) {
                System.out.println("No issued book records found!");
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            LibraryManagement.waitForEnter();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            LibraryManagement.waitForEnter();
        }
    }
    
    public void issueBookData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code to issue : ");
            String bno = scanner.nextLine();
            System.out.print("Enter Member Code : ");
            int mno = scanner.nextInt();
            
            // Check if book exists
            String bookQuery = "SELECT * FROM bookrecord WHERE bno = ?";
            PreparedStatement bookStmt = conn.prepareStatement(bookQuery);
            bookStmt.setString(1, bno);
            ResultSet bookRs = bookStmt.executeQuery();
            
            if (!bookRs.next()) {
                System.out.println("Book with code " + bno + " does not exist!");
                bookRs.close();
                bookStmt.close();
                conn.close();
                LibraryManagement.waitForEnter();
                return;
            }
            
            // Check if member exists
            String memberQuery = "SELECT * FROM member WHERE mno = ?";
            PreparedStatement memberStmt = conn.prepareStatement(memberQuery);
            memberStmt.setInt(1, mno);
            ResultSet memberRs = memberStmt.executeQuery();
            
            if (!memberRs.next()) {
                System.out.println("Member with code " + mno + " does not exist!");
                bookRs.close();
                bookStmt.close();
                memberRs.close();
                memberStmt.close();
                conn.close();
                LibraryManagement.waitForEnter();
                return;
            }
            
            System.out.println("Enter Date of Issue (Date/Month and Year separately): ");
            System.out.print("Enter Date : ");
            int day = scanner.nextInt();
            System.out.print("Enter Month : ");
            int month = scanner.nextInt();
            System.out.print("Enter Year : ");
            int year = scanner.nextInt();
            scanner.nextLine();
            
            LocalDate issueDate = LocalDate.of(year, month, day);
            
            String query = "INSERT INTO issue (bno, mno, d_o_issue) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bno);
            pstmt.setInt(2, mno);
            pstmt.setDate(3, Date.valueOf(issueDate));
            
            pstmt.executeUpdate();
            
            bookRs.close();
            bookStmt.close();
            memberRs.close();
            memberStmt.close();
            pstmt.close();
            conn.close();
            
            System.out.println("Book Issued Successfully!");
            LibraryManagement.waitForEnter();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            LibraryManagement.waitForEnter();
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
            LibraryManagement.waitForEnter();
        }
    }
    
    public void returnBook() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code of Book to be returned: ");
            String bno = scanner.nextLine();
            System.out.print("Enter Member Code of Member who is returning Book: ");
            int mno = scanner.nextInt();
            scanner.nextLine();
            
            // Check if book is issued to this member
            String checkQuery = "SELECT * FROM issue WHERE bno = ? AND mno = ? AND d_o_ret IS NULL";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, bno);
            checkStmt.setInt(2, mno);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("No active issue found for Book Code: " + bno + " and Member Code: " + mno);
                rs.close();
                checkStmt.close();
                conn.close();
                LibraryManagement.waitForEnter();
                return;
            }
            
            LocalDate returnDate = LocalDate.now();
            
            String query = "UPDATE issue SET d_o_ret = ? WHERE bno = ? AND mno = ? AND d_o_ret IS NULL";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(returnDate));
            pstmt.setString(2, bno);
            pstmt.setInt(3, mno);
            
            int rowsAffected = pstmt.executeUpdate();
            
            rs.close();
            checkStmt.close();
            pstmt.close();
            conn.close();
            
            if (rowsAffected > 0) {
                System.out.println("Book Returned Successfully!");
            } else {
                System.out.println("Failed to return book. Please check the details.");
            }
            LibraryManagement.waitForEnter();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            LibraryManagement.waitForEnter();
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
            LibraryManagement.waitForEnter();
        }
    }
}
