import java.sql.*;
import java.util.*;

public class Crudop {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/moviedb";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn = null;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Crudop obj = new Crudop();
        obj.start();
    }

    public Crudop() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful");
        } catch (SQLException sc) {
            sc.printStackTrace();
            System.err.println("Database connection failed");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("JDBC Driver not found. Make sure the connector is in your classpath");
        }
    }

    public void start() {
        if (conn == null) {
            System.err.println("Application cannot start");
            return;
        }

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Exiting demo application");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);

        closeResource();
    }

    private void displayMenu() {
        System.out.println("\n-- Simple CRUD Application --");
        System.out.println("1. Add Movie");
        System.out.println("2. View Movies");
        System.out.println("3. Update Movie Release Date by Director");
        System.out.println("4. Delete Movie by ID");
        System.out.println("5. Exit");
    }

    public void addProduct() {
        try {
            String insertSql = "INSERT INTO moviedetails (release_date, Actors, Movietype, Director, Rating, Language) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);

            System.out.print("Enter Release Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Enter Actors: ");
            String actors = scanner.nextLine();

            System.out.print("Enter Movie Type: ");
            String type = scanner.nextLine();

            System.out.print("Enter Director Name: ");
            String dir = scanner.nextLine();

            System.out.print("Enter Rating (1 to 10): ");
            int rate = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Language: ");
            String lang = scanner.nextLine();

            insertStmt.setString(1, date);
            insertStmt.setString(2, actors);
            insertStmt.setString(3, type);
            insertStmt.setString(4, dir);
            insertStmt.setInt(5, rate);
            insertStmt.setString(6, lang);

            int rowsAffected = insertStmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully");

            insertStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProduct() {
        String selectSql = "SELECT * FROM moviedetails";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery()) {

            System.out.println("\n-- Movie Data --");
            while (rs.next()) {
                String date = rs.getString("release_date");
                String actors = rs.getString("Actors");
                String type = rs.getString("Movietype");
                String dir = rs.getString("Director");
                int rate = rs.getInt("Rating");
                String lang = rs.getString("Language");

                System.out.printf("Release: %s | Actors: %s | Type: %s | Director: %s | Rating: %d | Language: %s%n",
                        date, actors, type, dir, rate, lang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct() {
        try {
            System.out.print("Enter Director Name (to update their movie's release date): ");
            String dir = scanner.nextLine();

            System.out.print("Enter New Release Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String updateSql = "UPDATE moviedetails SET release_date = ? WHERE Director = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, date);
            updateStmt.setString(2, dir);

            int rows = updateStmt.executeUpdate();
            System.out.println(rows + " row(s) updated.");

            updateStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct() {
        try {
            System.out.print("Enter ID of the movie to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String deleteSql = "DELETE FROM moviedetails WHERE id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);

            int rows = deleteStmt.executeUpdate();
            System.out.println(rows + " row(s) deleted.");

            deleteStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResource() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed");
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
