import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Member {
    private Scanner scanner = new Scanner(System.in);
    
    public void display() {
        try {
            LibraryManagement.clearScreen();
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM member";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.println("=========================================================");
                System.out.println("Member Code : " + rs.getInt("mno"));
                System.out.println("Member Name : " + rs.getString("mname"));
                System.out.println("Mobile No.of Member : " + rs.getString("mob"));
                System.out.println("Date of Membership : " + rs.getDate("dop"));
                System.out.println("Address : " + rs.getString("addr"));
                System.out.println("=========================================================");
            }
            
            if (!hasRecords) {
                System.out.println("No member records found!");
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
    
    public void insertMember() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Member Code : ");
            int mno = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Member Name : ");
            String mname = scanner.nextLine();
            System.out.print("Enter Member Mobile No. : ");
            String mob = scanner.nextLine();
            
            System.out.println("Enter Date of Membership (Date/Month and Year separately): ");
            System.out.print("Enter Date : ");
            int day = scanner.nextInt();
            System.out.print("Enter Month : ");
            int month = scanner.nextInt();
            System.out.print("Enter Year : ");
            int year = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter Member Address : ");
            String addr = scanner.nextLine();
            
            LocalDate dop = LocalDate.of(year, month, day);
            
            String query = "INSERT INTO member VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, mno);
            pstmt.setString(2, mname);
            pstmt.setString(3, mob);
            pstmt.setDate(4, Date.valueOf(dop));
            pstmt.setString(5, addr);
            
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
    
    public void deleteMember() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Member Code to be deleted: ");
            int mno = scanner.nextInt();
            scanner.nextLine();
            
            String query = "DELETE FROM member WHERE mno = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, mno);
            
            int rowsAffected = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " Record(s) Deleted Successfully!");
            } else {
                System.out.println("No record found with Member Code: " + mno);
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
    
    public void searchMember() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Member Name to be Searched: ");
            String mname = scanner.nextLine();
            
            String query = "SELECT * FROM member WHERE mname LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + mname + "%");
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("=========================================================");
                System.out.println("Member Code : " + rs.getInt("mno"));
                System.out.println("Member Name : " + rs.getString("mname"));
                System.out.println("Mobile No.of Member : " + rs.getString("mob"));
                System.out.println("Date of Membership : " + rs.getDate("dop"));
                System.out.println("Address : " + rs.getString("addr"));
                System.out.println("=========================================================");
            }
            
            if (!found) {
                System.out.println("No records found for Member Name: " + mname);
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
    
    public void updateMember() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            System.out.print("Enter Member Code to be Updated: ");
            int mno = scanner.nextInt();
            scanner.nextLine();
            
            // Check if member exists
            String checkQuery = "SELECT * FROM member WHERE mno = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, mno);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("No member found with Member Code: " + mno);
                rs.close();
                checkStmt.close();
                conn.close();
                LibraryManagement.waitForEnter();
                return;
            }
            
            System.out.println("Enter new data:");
            System.out.print("Enter Member Name : ");
            String mname = scanner.nextLine();
            System.out.print("Enter Member Mobile No. : ");
            String mob = scanner.nextLine();
            
            System.out.println("Enter Date of Membership (Date/Month and Year separately): ");
            System.out.print("Enter Date : ");
            int day = scanner.nextInt();
            System.out.print("Enter Month : ");
            int month = scanner.nextInt();
            System.out.print("Enter Year : ");
            int year = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter Member Address : ");
            String addr = scanner.nextLine();
            
            LocalDate dop = LocalDate.of(year, month, day);
            
            String updateQuery = "UPDATE member SET mname=?, mob=?, dop=?, addr=? WHERE mno=?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, mname);
            pstmt.setString(2, mob);
            pstmt.setDate(3, Date.valueOf(dop));
            pstmt.setString(4, addr);
            pstmt.setInt(5, mno);
            
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
