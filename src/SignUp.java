import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.*;

public class SignUp extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPassword;
    private JButton registerButton;

    Font font1 = new Font("Arial", Font.BOLD, 17);
    Font font2 = new Font("Arial", Font.BOLD, 18);
    Font font3 = new Font("Arial", Font.PLAIN, 14);
    Font font4 = new Font("Arial", Font.BOLD, 15);

    // Database connex info
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expenses_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; 

    public SignUp() {
        setTitle("Personal Expenses Tracker");
        setSize(400, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //bg color
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        // lg Icon
        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        Image logo = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        setIconImage(logo);

        // Logo
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        Image scaledImg = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel splashLabel = new JLabel(new ImageIcon(scaledImg));
        splashLabel.setBounds(85, 40, 220, 220);
        panel.add(splashLabel);

        JLabel lbl = new JLabel("Personal Expenses Tracker");
        lbl.setBounds(80, 185, 400, 30);
        lbl.setFont(font1);
        lbl.setForeground(Color.WHITE);
        panel.add(lbl);

        // Username Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(45, 232, 300, 20);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(45, 255, 300, 40);
        usernameField.setFont(font2);
        panel.add(usernameField);

        // Password Field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(45, 300, 300, 20);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(45, 325, 300, 40);
        passwordField.setFont(font2);
        panel.add(passwordField);

        // Confirm Password Field
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmLabel.setForeground(Color.WHITE);
        confirmLabel.setBounds(45, 370, 300, 20);
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(45, 395, 300, 40);
        confirmPasswordField.setFont(font2);
        panel.add(confirmPasswordField);

        //Checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(45, 440, 250, 30);
        showPassword.addActionListener(this);
        showPassword.setBackground(Color.decode("#294752"));
        showPassword.setFont(font3);
        showPassword.setForeground(Color.WHITE);
        panel.add(showPassword);

        // Registr Button
        registerButton = new JButton("Sign up");
        registerButton.setBounds(120, 480, 150, 40);
        registerButton.setFont(font2);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // Login lnk
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setBounds(110, 520, 200, 50);
        loginLabel.setFont(font3);
        loginLabel.setForeground(Color.WHITE);
        panel.add(loginLabel);

        JLabel loginLink = new JLabel("Login Here!");
        loginLink.setBounds(155, 550, 200, 50);
        loginLink.setFont(font4);
        loginLink.setForeground(Color.WHITE);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); 
                new Login();
            }
        });
        panel.add(loginLink);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                confirmPasswordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
                confirmPasswordField.setEchoChar('*');
            }
        }

        if (e.getSource() == registerButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) { // if field is checking empty
                JOptionPane.showMessageDialog(this, "Please fill out all fields");
            } else if (!password.equals(confirmPassword)) {             //if password equlas to pasword
                JOptionPane.showMessageDialog(this, "Passwords do not match"); 
            } else if (password.length() < 6) { // minimum lenbgth
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters");
            } else if (registerUser(username, password)) { // assigning dtat
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                dispose();
                new Login();
            } else {
                JOptionPane.showMessageDialog(this, "Username already taken");
            }
        }
    }

    // Hash password w SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // sha-256 is algorithm storing dats
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

    // Register user in MySQL database
    private boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.out.println("Username already taken.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
