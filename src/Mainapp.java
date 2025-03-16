import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Mainapp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JLabel logout;
    private Font font1 = new Font("Arial", Font.BOLD, 16);
    private Font font4 = new Font("Arial", Font.BOLD, 17);
    private Font font5 = new Font("Arial", Font.BOLD, 80);

    Mainapp(int userId) {
        frame = new JFrame("Personal Expenses Tracker");
        
        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        frame.setIconImage(img.getImage());
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);

        JPanel bglabel1 = new JPanel();
        bglabel1.setBackground(Color.decode("#0B1316"));
        bglabel1.setBounds(0, 0, 650, 73);
        bglabel1.setLayout(null);
        bglabel1.setVisible(true);
        panel.add(bglabel1);

        logout = new JLabel("Log Out");
        logout.setBounds(295, 20, 150, 30);
        logout.setForeground(Color.white);
        logout.setFont(font4);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login(); 
                frame.dispose();
            }
        });
        panel.add(logout);
        bglabel1.add(logout);

        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JLabel splashLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        splashLabel.setBounds(5, 5, 70, 70);
        panel.add(splashLabel);
        bglabel1.add(splashLabel);

        JLabel lbl = new JLabel("Personal Expenses Tracker");
        lbl.setBounds(65, 20, 300, 30);
        lbl.setFont(font1);
        lbl.setForeground(Color.WHITE);
        panel.add(lbl);
        bglabel1.add(lbl);

        // Table setup
        model = new DefaultTableModel(new String[]{"Amount", "Category", "Description", "Date"}, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setFont(font1);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 350, 450);
        panel.add(scrollPane);

        JLabel add = new JLabel("+");
        add.setBounds(320, 500, 70, 100);
        add.setFont(font5);
        add.setForeground(Color.decode("#467A8D"));
        panel.add(add);

        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new add(userId, Mainapp.this); 
                frame.dispose();
            }
        });
        panel.add(add);
        
        
        // Load expenses into the table
        loadExpenses(userId);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        // Add Expense Button
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setFont(font4);
        addExpenseButton.addActionListener(e -> new add(userId, this));
        buttonPanel.add(addExpenseButton);
        bglabel1.add(addExpenseButton);

        frame.setSize(400, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void loadExpenses(int userId) {
        model.setRowCount(0);
        List<String[]> expenses = UserDatabase.getExpenses(userId);
        for (String[] expense : expenses) {
            model.addRow(expense);
        }
    }
    

    public static void main(String[] args) {
        int userId = UserDatabase.getUserId("testuser"); // Example username
        if (userId != -1) {
            new Mainapp(userId);
        } else {
            System.out.println("User not found");
        }
    }
}