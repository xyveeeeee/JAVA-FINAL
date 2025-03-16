import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField passwordField;
    JButton loginButton;
    JCheckBox showPassword;

    Font font1 = new Font("Arial", Font.BOLD, 17);
    Font font2 = new Font("Arial", Font.BOLD, 18);
    Font font3 = new Font("Arial", Font.PLAIN, 14);
    Font font4 = new Font("Arial", Font.BOLD, 15);

    Login() {
        setTitle("Personal Expenses Tracker");
        setSize(400, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        // App Icon
        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        setIconImage(img.getImage());

        // Logo
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JLabel splashLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        splashLabel.setBounds(85, 40, 220, 220);
        panel.add(splashLabel);

        JLabel lbl = new JLabel("Personal Expenses Tracker");
        lbl.setBounds(80, 185, 400, 30);
        lbl.setFont(font1);
        lbl.setForeground(Color.WHITE);
        panel.add(lbl);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font1);
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(45, 232, 300, 20);
        panel.add(userLabel);

        username = new JTextField();
        username.setBounds(45, 255, 300, 40);
        username.setFont(font2);
        panel.add(username);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(font1);
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(45, 300, 300, 20);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(45, 325, 300, 40);
        passwordField.setFont(font2);
        panel.add(passwordField);

        // Show Password
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(45, 370, 250, 40);
        showPassword.setBackground(Color.decode("#294752"));
        showPassword.setForeground(Color.WHITE);
        showPassword.addActionListener(this);
        panel.add(showPassword);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(120, 420, 150, 40);
        loginButton.setFont(font2);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // Sign Up
        JLabel signUp = new JLabel("Don't have an account?");
        signUp.setBounds(120, 460, 200, 50);
        signUp.setFont(font3);
        signUp.setForeground(Color.white);
        panel.add(signUp);

        JLabel signUpHere = new JLabel("Sign Up Here!");
        signUpHere.setBounds(145, 490, 200, 50);
        signUpHere.setFont(font4);
        signUpHere.setForeground(Color.white);
        signUpHere.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpHere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
                dispose();
            }
        });
        panel.add(signUpHere);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    
        if (evt.getSource() == loginButton) {
            String userText = username.getText();
            String pwdText = new String(passwordField.getPassword());

            if (userText.isEmpty() || pwdText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields");
                return;
            }
    
            // Validate login before trying to get userId
            if (UserDatabase.validateLogin(userText, pwdText)) {
                int userId = UserDatabase.getUserId(userText);
                
                if (userId != -1) {
                    SessionManager.setSession(userId, userText); // Stor userId and username
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose();
                    new Mainapp(userId);
                } else {
                    JOptionPane.showMessageDialog(this, "Error retrieving user ID.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
    }
    

    public static void main(String[] args) {
        new Login();
    }
}