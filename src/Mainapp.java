import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mainapp implements ActionListener {

    JFrame frame;
    JLabel logout;
    Font font4 = new Font("Arial", Font.BOLD, 17);

    Mainapp() {
        frame = new JFrame("Personal Expenses Tracker");


        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        frame.setIconImage(img.getImage());


        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        logout = new JLabel("Log Out");
        logout.setBounds(295, 15, 150, 30); // Adjusted position to fit within frame
        logout.setForeground(Color.white);
        logout.setFont(font4);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login(); // Opens the SignUp window
                frame.dispose(); // Closes the current window
            }
        });
        panel.add(logout);

        // Button setup (Optional example if needed)


        // Frame setup
        frame.setSize(400, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel); // Fix here
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Button Clicked!");
        frame.dispose(); 
        new Login(); // Opens the Login window
    }

    public static void main(String[] args) {
        new Mainapp();
    }
}
