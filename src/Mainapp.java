import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mainapp implements ActionListener {

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

