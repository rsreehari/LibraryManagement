import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Book {
    private Scanner scanner = new Scanner(System.in);
    
    public void display() {
        try {
            LibraryManagement.clearScreen();
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM bookrecord";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.println("==================================================");
                System.out.println("Book Code : " + rs.getString("bno"));
                System.out.println("Book Name : " + rs.getString("bname"));
                System.out.println("Author of Book : " + rs.getString("auth"));
                System.out.println("Price of Book : " + rs.getInt("price"));
                System.out.println("Publisher : " + rs.getString("pub"));
                System.out.println("Total Quantity in Hand : " + rs.getInt("qty"));
                System.out.println("Purchased On : " + rs.getDate("dop"));
                System.out.println("==================================================");
            }
            
            if (!hasRecords) {
                System.out.println("No book records found!");
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
    
    public void insertData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code : ");
            String bno = scanner.nextLine();
            System.out.print("Enter Book Name : ");
            String bname = scanner.nextLine();
            System.out.print("Enter Book Author's Name : ");
            String auth = scanner.nextLine();
            System.out.print("Enter Book Price : ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Publisher of Book : ");
            String pub = scanner.nextLine();
            System.out.print("Enter Quantity purchased : ");
            int qty = scanner.nextInt();
            
            System.out.println("Enter Date of Purchase (Date/Month and Year separately): ");
            System.out.print("Enter Date : ");
            int day = scanner.nextInt();
            System.out.print("Enter Month : ");
            int month = scanner.nextInt();
            System.out.print("Enter Year : ");
            int year = scanner.nextInt();
            scanner.nextLine();
            
            LocalDate dop = LocalDate.of(year, month, day);
            
            String query = "INSERT INTO bookrecord VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bno);
            pstmt.setString(2, bname);
            pstmt.setString(3, auth);
            pstmt.setInt(4, price);
            pstmt.setString(5, pub);
            pstmt.setInt(6, qty);
            pstmt.setDate(7, Date.valueOf(dop));
            
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            System.out.println("Record Inserted Successfully!");
            LibraryManagement.waitForEnter();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            LibraryManagement.waitForEnter();
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
            LibraryManagement.waitForEnter();
        }
    }
    
    public void deleteBook() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code of Book to be deleted: ");
            String bno = scanner.nextLine();
            
            String query = "DELETE FROM bookrecord WHERE bno = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bno);
            
            int rowsAffected = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " Record(s) Deleted Successfully!");
            } else {
                System.out.println("No record found with Book Code: " + bno);
            }
            LibraryManagement.waitForEnter();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            LibraryManagement.waitForEnter();
        }
    }
    
    public void searchBookRecord() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code to be Searched: ");
            String bno = scanner.nextLine();
            
            String query = "SELECT * FROM bookrecord WHERE bno = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bno);
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("==================================================");
                System.out.println("Book Code : " + rs.getString("bno"));
                System.out.println("Book Name : " + rs.getString("bname"));
                System.out.println("Author of Book : " + rs.getString("auth"));
                System.out.println("Price of Book : " + rs.getInt("price"));
                System.out.println("Publisher : " + rs.getString("pub"));
                System.out.println("Total Quantity in Hand : " + rs.getInt("qty"));
                System.out.println("Purchased On : " + rs.getDate("dop"));
                System.out.println("==================================================");
            }
            
            if (!found) {
                System.out.println("No records found for Book Code: " + bno);
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
    
    public void updateBook() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Book Code of Book to be Updated: ");
            String bno = scanner.nextLine();
            
            // Check if book exists
            String checkQuery = "SELECT * FROM bookrecord WHERE bno = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, bno);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("No book found with Book Code: " + bno);
                rs.close();
                checkStmt.close();
                conn.close();
                LibraryManagement.waitForEnter();
                return;
            }
            
            System.out.println("Enter new data:");
            System.out.print("Enter Book Name : ");
            String bname = scanner.nextLine();
            System.out.print("Enter Book Author's Name : ");
            String auth = scanner.nextLine();
            System.out.print("Enter Book Price : ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Publisher of Book : ");
            String pub = scanner.nextLine();
            System.out.print("Enter Quantity purchased : ");
            int qty = scanner.nextInt();
            
            System.out.println("Enter Date of Purchase (Date/Month and Year separately): ");
            System.out.print("Enter Date : ");
            int day = scanner.nextInt();
            System.out.print("Enter Month : ");
            int month = scanner.nextInt();
            System.out.print("Enter Year : ");
            int year = scanner.nextInt();
            scanner.nextLine();
            
            LocalDate dop = LocalDate.of(year, month, day);
            
            String updateQuery = "UPDATE bookrecord SET bname=?, auth=?, price=?, pub=?, qty=?, dop=? WHERE bno=?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, bname);
            pstmt.setString(2, auth);
            pstmt.setInt(3, price);
            pstmt.setString(4, pub);
            pstmt.setInt(5, qty);
            pstmt.setDate(6, Date.valueOf(dop));
            pstmt.setString(7, bno);
            
            int rowsAffected = pstmt.executeUpdate();
            
            rs.close();
            checkStmt.close();
            pstmt.close();
            conn.close();
            
            System.out.println(rowsAffected + " Record(s) Updated Successfully!");
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
