package p1;

import java.util.*;
import java.sql.ResultSet;

public class LibraryManagmentSystem {
    Scanner sc = new Scanner(System.in);
    DBManager db = new DBManager();

    public void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.print("Is Issued? (true/false): ");
        boolean isIssued = sc.nextBoolean();
        sc.nextLine();
        String name = "";
        String contact = "";
        if (isIssued) {
            System.out.print("Enter Member Name: ");
            name = sc.nextLine();
            System.out.print("Enter Contact: ");
            contact = sc.nextLine();
        }

        db.insertBook(id, title, author, year, isIssued, name, contact);
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {
        try {
            ResultSet rs = db.selectAll();
            boolean found = false;
            while (rs != null && rs.next()) {
                found = true;
                System.out.println("------------------");
                System.out.println("Book ID: " + rs.getInt("bookId"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Year: " + rs.getInt("year"));
                System.out.println("Issued: " + rs.getString("isIssued"));
                if (rs.getString("isIssued").equals("Yes")) {
                    System.out.println("Issued To: " + rs.getString("name"));
                    System.out.println("Contact: " + rs.getString("contact"));
                }
            }
            if (!found) System.out.println("No books in the library yet!");
        } catch (Exception e) {
            System.out.println("Display failed: " + e.getMessage());
        }
    }

    public void searchBookById() {
        System.out.print("Enter ID to Search: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = db.selectByID(id);
            if (rs != null && rs.next()) {
                System.out.println("-----Book Found-----");
                System.out.println("Book ID: " + rs.getInt("bookId"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Year: " + rs.getInt("year"));
                System.out.println("Issued: " + rs.getString("isIssued"));
                if (rs.getString("isIssued").equals("Yes")) {
                    System.out.println("Issued To: " + rs.getString("name"));
                    System.out.println("Contact: " + rs.getString("contact"));
                }
            } else {
                System.out.println("Book Not Found");
            }
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    public void searchBookByTitle() {
        System.out.print("Enter Title to Search: ");
        String title = sc.nextLine();

        try {
            ResultSet rs = db.selectByTitle(title);
            if (rs != null && rs.next()) {
                System.out.println("-----Book Found-----");
                System.out.println("Book ID: " + rs.getInt("bookId"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Year: " + rs.getInt("year"));
                System.out.println("Issued: " + rs.getString("isIssued"));
                if (rs.getString("isIssued").equals("Yes")) {
                    System.out.println("Issued To: " + rs.getString("name"));
                    System.out.println("Contact: " + rs.getString("contact"));
                }
            } else {
                System.out.println("Book Not Found");
            }
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    public void issueBook() {
        System.out.print("Enter Book ID to Issue: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = db.selectByID(id);
            if (rs != null && rs.next()) {
                if (rs.getString("isIssued").equals("Yes")) {
                    System.out.println("Book is already issued!");
                    return;
                }
                System.out.print("Enter Member Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Contact: ");
                String contact = sc.nextLine();
                db.issueBook(id, name, contact);
                System.out.println("Book Issued!");
            } else {
                System.out.println("Book ID not found. Cannot issue.");
            }
        } catch (Exception e) {
            System.out.println("Issue failed: " + e.getMessage());
        }
    }

    public void returnBook() {
        System.out.print("Enter Book ID to Return: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = db.selectByID(id);
            if (rs != null && rs.next()) {
                if (rs.getString("isIssued").equals("No")) {
                    System.out.println("Book was not issued.");
                    return;
                }
                db.returnBook(id);
                System.out.println("Book Returned!");
            } else {
                System.out.println("Book ID not found. Cannot return.");
            }
        } catch (Exception e) {
            System.out.println("Return failed: " + e.getMessage());
        }
    }

    public void deleteBook() {
        System.out.print("Enter Book ID to Delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = db.selectByID(id);
            if (rs != null && rs.next()) {
                db.deleteBook(id);
                System.out.println("Book Deleted!");
            } else {
                System.out.println("Book ID not found. Cannot delete.");
            }
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    public void countBooks() {
        try {
            ResultSet rs = db.countBooks();
            if (rs != null && rs.next()) {
                int total = rs.getInt("total");
                if (total == 0) System.out.println("No books in the library!");
                else System.out.println("Total Books: " + total);
            }
        } catch (Exception e) {
            System.out.println("Count failed: " + e.getMessage());
        }
    }

    public void showIssuedBooks() {
        try {
            ResultSet rs = db.showIssuedBooks();
            boolean found = false;
            while (rs != null && rs.next()) {
                found = true;
                System.out.println("------------------");
                System.out.println("Book ID: " + rs.getInt("bookId"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Issued To: " + rs.getString("name"));
                System.out.println("Contact: " + rs.getString("contact"));
                System.out.println("Issued: " + rs.getString("isIssued"));
            }
            if (!found) System.out.println("No Issued Books");
        } catch (Exception e) {
            System.out.println("Show issued books failed: " + e.getMessage());
        }
    }
}
