import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {

    static JLabel lbl;
    static Font font1 = new Font("Arial", Font.BOLD, 18);

    public static void main(String[] args) {
        //splash wndow
        JWindow splash = new JWindow();

        // windw background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null); 

        // splash image from the classpath
        ImageIcon splashIcon = new ImageIcon(App.class.getResource("/logo.png")); 
        Image scaledImg = splashIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel splashLabel = new JLabel(new ImageIcon(scaledImg));
        splashLabel.setBounds(75, 170, 250, 250);

        //name
        lbl = new JLabel("Personal Expenses Tracker");
        lbl.setBounds(80, 360, 400, 30); 
        lbl.setFont(font1);
        lbl.setForeground(Color.WHITE);

        panel.add(splashLabel);
        panel.add(lbl);

        // visible the pcontent
        splash.setContentPane(panel);

        // window size
        splash.setSize(400, 650);
        splash.setLocationRelativeTo(null);

        // Make the splash screen visible
        splash.setVisible(true);

        try {
            // splash screen timer
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close splash screen
        splash.setVisible(false);
        splash.dispose();

        // Start the main app
        SwingUtilities.invokeLater(() -> new Login());
    }
}


class Login extends JFrame implements ActionListener{
    JTextField username;
    JPasswordField passwordField;
    JButton loginButton;
    JCheckBox showpassword;
    Font font1 = new Font("Arial", Font.BOLD, 17), font2 = new Font("Arial", Font.BOLD,18),
    font3 = new Font("Arial", Font.PLAIN,14), font4 = new Font("Arial", Font.BOLD,15);


    Login() {
        //------------- layout
        setTitle("Personal Expenses Tracker");
        setSize(400, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        Image logo = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        setIconImage(logo);

        // Logo
        ImageIcon icon = new ImageIcon(App.class.getResource("/logo.png"));
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

        //username
        JLabel userlabel = new JLabel("Username:");
        userlabel.setFont(new Font("Arial", Font.BOLD, 16));
        userlabel.setForeground(Color.WHITE);
        userlabel.setBounds(45, 232, 300, 20);
        panel.add(userlabel);

        username = new JTextField();
        username.setBounds(45, 255, 300, 40);
        username.setFont(font2);
        panel.add(username); 

        // lbl pass
        JLabel titleLabel = new JLabel("Password:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(45, 300, 300, 20);
        panel.add(titleLabel);

        // Pass field
        passwordField = new JPasswordField();
        passwordField.setBounds(45, 325, 300, 40);
        passwordField.setFont(font2);
        panel.add(passwordField);

        //check bx
        showpassword = new JCheckBox("Show Password");
        showpassword.setBounds(45, 370, 250, 40);
        showpassword.addActionListener(this);
        showpassword.setBackground(Color.decode("#294752"));
        showpassword.setFont(font3);
        showpassword.setForeground(Color.white);
        panel.add(showpassword);

        // login btn
        loginButton = new JButton("Login");
        loginButton.setBounds(120, 420, 150, 40);
        loginButton.setFont(font2);
        loginButton.addActionListener(this);

        JLabel signup = new JLabel("Don't have an account?");
        signup.setBounds(120, 460, 200, 50);
        signup.setFont(font3);
        signup.setForeground(Color.white);

        JLabel signup2 = new JLabel("Sign Up Here!");
        signup2.setBounds(145, 490, 200, 50);
        signup2.setFont(font4);
        signup2.setForeground(Color.white);
        signup2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(signup);
        panel.add(signup2);
        panel.add(loginButton);
        setContentPane(panel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent evt){
    if(evt.getSource()==showpassword){
        if(showpassword.isSelected()){
            passwordField.setEchoChar((char)0);
        }
        else{
            passwordField.setEchoChar('*');
        }
    }
    if(evt.getSource()==loginButton){
        String usertext = username.getText();
        String pwdtext = new String(passwordField.getPassword());
        if(usertext.equalsIgnoreCase("xyve") && pwdtext.equalsIgnoreCase("vince")){
            JOptionPane.showMessageDialog(this, "Log in successfuly!");
            this.dispose(); 
            new Mainapp(); 
        }else{
            JOptionPane.showMessageDialog(this, "Wrong password, Please try again!");
        }
    }

    }

}

class Mainapp implements ActionListener {

    JFrame frame;
    JButton button;

    Mainapp() {
        frame = new JFrame("Personal Expenses Tracker");

        // icon resourses  
        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        Image scaledImg = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        frame.setIconImage(scaledImg);

        button = new JButton("Log out");
        button.addActionListener(this);

        frame.setLayout(new FlowLayout());
        frame.add(button);

        frame.setSize(400, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Button Clicked!");
        frame.dispose(); 
        new Login();
    }
}
