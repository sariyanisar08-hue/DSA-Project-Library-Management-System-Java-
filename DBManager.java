package p1;

import java.sql.*;

public class DBManager {
    Connection con;
    Statement stmt;

    public DBManager() {
        try {
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=DSAPROJECT;encrypt=false;trustServerCertificate=true";
            con = DriverManager.getConnection(url, "sa", "123456");
            stmt = con.createStatement();
            System.out.println("Database Connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private String escape(String s) {
        return s.replace("'", "''");
    }

    public void execute(String query) {
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public ResultSet select(String query) {
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Select Error: " + e.getMessage());
            return null;
        }
    }

    public void insertBook(int id, String title, String author, int year,
                           boolean isIssued, String name, String contact) {
        String sql = "INSERT INTO LibraryBooks(bookId, title, author, year, isIssued, name, contact) VALUES(" +
                id + ",'" + escape(title) + "','" + escape(author) + "'," + year + "," +
                (isIssued ? 1 : 0) + ",'" + escape(name) + "','" + escape(contact) + "')";
        execute(sql);
    }

    public void issueBook(int id, String name, String contact) {
        String sql = "UPDATE LibraryBooks SET isIssued=1, name='" + escape(name) +
                     "', contact='" + escape(contact) + "' WHERE bookId=" + id;
        execute(sql);
    }

    public void returnBook(int id) {
        String sql = "UPDATE LibraryBooks SET isIssued=0, name='', contact='' WHERE bookId=" + id;
        execute(sql);
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM LibraryBooks WHERE bookId=" + id;
        execute(sql);
    }

    public ResultSet selectAll() {
        return select("SELECT bookId, title, author, year, " +
                      "CASE WHEN isIssued=1 THEN 'Yes' ELSE 'No' END AS isIssued, name, contact " +
                      "FROM LibraryBooks");
    }

    public ResultSet selectByID(int id) {
        return select("SELECT bookId, title, author, year, " +
                      "CASE WHEN isIssued=1 THEN 'Yes' ELSE 'No' END AS isIssued, name, contact " +
                      "FROM LibraryBooks WHERE bookId=" + id);
    }

    public ResultSet selectByTitle(String title) {
        return select("SELECT bookId, title, author, year, " +
                      "CASE WHEN isIssued=1 THEN 'Yes' ELSE 'No' END AS isIssued, name, contact " +
                      "FROM LibraryBooks WHERE title='" + escape(title) + "'");
    }

    public ResultSet countBooks() {
        return select("SELECT COUNT(*) AS total FROM LibraryBooks");
    }

    public ResultSet showIssuedBooks() {
        return select("SELECT bookId, title, name, contact, " +
                      "CASE WHEN isIssued=1 THEN 'Yes' ELSE 'No' END AS isIssued " +
                      "FROM LibraryBooks WHERE isIssued=1");
    }
}
