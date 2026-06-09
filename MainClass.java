package p1;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        LibraryManagmentSystem obj = new LibraryManagmentSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------");
            System.out.println("1 Add Book");
            System.out.println("2 Display Books");
            System.out.println("3 Search Book By ID");
            System.out.println("4 Search Book By Title");
            System.out.println("5 Issue Book");
            System.out.println("6 Return Book");
            System.out.println("7 Delete Book");
            System.out.println("8 Count Total Books");
            System.out.println("9 Show Issued Books");
            System.out.println("10 Exit");
            System.out.println("---------------------------");
            System.out.print("Enter your choice: ");
            
            int ch = sc.nextInt();

            if (ch == 1) {
                obj.addBook();
            } 
            else if (ch == 2) {
                obj.displayBooks();
            } 
            else if (ch == 3) {
                obj.searchBookById();
            } 
            else if (ch == 4) {
                obj.searchBookByTitle();
            } 
            else if (ch == 5) {
                obj.issueBook();
            } 
            else if (ch == 6) {
                obj.returnBook();
            } 
            else if (ch == 7) {
                obj.deleteBook();
            } 
            else if (ch == 8) {
                obj.countBooks();
            } 
            else if (ch == 9) {
                obj.showIssuedBooks();
            } 
            else if (ch == 10) {
                System.out.println("Exiting...");
                break; 
            } 
            else {
                System.out.println("Invalid Choice!");
            }
        }
    }
}
