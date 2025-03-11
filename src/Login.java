import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField passwordField;
    JButton loginButton;
    JCheckBox showpassword;
    Font font1 = new Font("Arial", Font.BOLD, 17);
    Font font2 = new Font("Arial", Font.BOLD, 18);
    Font font3 = new Font("Arial", Font.PLAIN, 14);
    Font font4 = new Font("Arial", Font.BOLD, 15);

    Login() {
        //------------- layout
        setTitle("Personal Expenses Tracker");
        setSize(400, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        // App icon
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

        //------------------ ^end layout^

        // Username label & field
        JLabel userlabel = new JLabel("Username:");
        userlabel.setFont(new Font("Arial", Font.BOLD, 16));
        userlabel.setForeground(Color.WHITE);
        userlabel.setBounds(45, 232, 300, 20);
        panel.add(userlabel);

        username = new JTextField();
        username.setBounds(45, 255, 300, 40);
        username.setFont(font2);
        panel.add(username);

        // Password label & field
        JLabel titleLabel = new JLabel("Password:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(45, 300, 300, 20);
        panel.add(titleLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(45, 325, 300, 40);
        passwordField.setFont(font2);
        panel.add(passwordField);

        // Show Password checkbox
        showpassword = new JCheckBox("Show Password");
        showpassword.setBounds(45, 370, 250, 40);
        showpassword.addActionListener(this);
        showpassword.setBackground(Color.decode("#294752"));
        showpassword.setFont(font3);
        showpassword.setForeground(Color.white);
        panel.add(showpassword);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(120, 420, 150, 40);
        loginButton.setFont(font2);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // Sign up option
        JLabel signup = new JLabel("Don't have an account?");
        signup.setBounds(120, 460, 200, 50);
        signup.setFont(font3);
        signup.setForeground(Color.white);
        panel.add(signup);

        JLabel signup2 = new JLabel("Sign Up Here!");
        signup2.setBounds(145, 490, 200, 50);
        signup2.setFont(font4);
        signup2.setForeground(Color.white);
        signup2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signup2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Redirecting to Sign Up!");
            }
        });
        panel.add(signup2);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == showpassword) {
            if (showpassword.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Hide password
            }
        }

        if (evt.getSource() == loginButton) {
            String usertext = username.getText();
            String pwdtext = new String(passwordField.getPassword());

            if (usertext.isEmpty() || pwdtext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill up the form");
            } else if (!usertext.equalsIgnoreCase("xyve")) {
                JOptionPane.showMessageDialog(this, "Username incorrect");
            } else if (!pwdtext.equalsIgnoreCase("vince")) {
                JOptionPane.showMessageDialog(this, "Wrong password, Please try again!");
            } else {
                JOptionPane.showMessageDialog(this, "Log in successfully!");
                this.dispose();
                new Mainapp(); 
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
