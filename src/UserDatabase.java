import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/expenses_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; 

    // logn credentials
    public static boolean validateLogin(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            
                String storedPassword = rs.getString("password"); // geting pasword from hash
                
                // Hash the input password and comparing alredy stored hash
                String hashedInputPassword = hashPassword(password);
                return storedPassword.equals(hashedInputPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hash password using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
