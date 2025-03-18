import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    // Database connect info
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expenses_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Testing connection data
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Database connected successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    // Secutring hash password using SHA-256 
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    // validating user from login to data
    public static boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if user exists
            }

        } catch (SQLException e) {
            System.err.println("Error during login validation: " + e.getMessage());
            return false;
        }
    }

    // Getting user ID based on username
    public static int getUserId(String username) {
        String query = "SELECT id FROM users WHERE username = ?";
        int userId = -1;

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user ID: " + e.getMessage());
        }

        return userId;
    }

    // Add a new user to the sql database
    public static boolean addUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, hashPassword(password));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) { // Code for duplicate entry
                System.err.println("Username already exists: " + username);
            } else {
                System.err.println("Error adding user: " + e.getMessage());
            }
            return false;
        }
    }

    // Adding expense to the database
    public static boolean addExpense(int userId, String date, String category, String description, double amount) {
        String query = "INSERT INTO expenses (user_id, date, category, description, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, date);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.setDouble(5, amount);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error adding expense: " + e.getMessage());
            return false;
        }
    }

    // Getimg expenses from the database for a specific user
    public static List<String[]> getExpenses(int userId) {
        List<String[]> expenses = new ArrayList<>();
        String query = "SELECT amount, category, description, date FROM expenses WHERE user_id = ? ORDER BY date DESC";


        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    String amount = String.valueOf(rs.getDouble("amount"));
                    String category = rs.getString("category");
                    String description = rs.getString("description");
                    String date = rs.getString("date");

                    expenses.add(new String[]{date, category, description, amount});
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching expenses: " + e.getMessage());
        }

        return expenses;
    }

    // Update an expense by ID
    public static boolean updateExpense(int expenseId, double amount, String category, String description, String date) {
        String query = "UPDATE expenses SET amount = ?, category = ?, description = ?, date = ? WHERE id = ?";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, amount);
            stmt.setString(2, category);
            stmt.setString(3, description);
            stmt.setString(4, date);
            stmt.setInt(5, expenseId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating expense: " + e.getMessage());
            return false;
        }
    }


    // Main method to test connection
    public static void main(String[] args) {
        testConnection();
    }
}
