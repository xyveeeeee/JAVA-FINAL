import java.awt.*;
import javax.swing.*;

public class splash extends JFrame{
    
    static JLabel lbl;
    static Font font1 = new Font("Arial", Font.BOLD, 18);

    public static void main(String[] args) {
        
        JWindow splash = new JWindow();

        // windw background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null); 

        //img
        ImageIcon splashIcon = new ImageIcon(Mainapp.class.getResource("/logo.png")); 
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

        // size
        splash.setSize(400, 650);
        splash.setLocationRelativeTo(null);

        splash.setVisible(true);

        try {
            // screen time
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close splash 
        splash.setVisible(false);
        splash.dispose();

        // starting in logn
        SwingUtilities.invokeLater(() -> new Login());
    }
}
