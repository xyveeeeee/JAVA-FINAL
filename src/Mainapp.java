import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Mainapp {
    private JFrame frame;
    private JTable table;
    private JLabel emptyLabel;
    private DefaultTableModel model;
    private JLabel logout;
    private Font font1 = new Font("Arial", Font.BOLD, 16);
    private Font font4 = new Font("Arial", Font.BOLD, 17);
    private Font font5 = new Font("Arial", Font.BOLD, 80);

    private JLabel expenseValue;
    private JLabel totalValue;

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
        panel.add(bglabel1);

        JPanel bglabel2 = new JPanel();
        bglabel2.setBackground(Color.decode("#1C3138"));
        bglabel2.setBounds(0, 50, 650, 60);
        bglabel2.setLayout(null);
        panel.add(bglabel2);

        JPanel bglabel3 = new JPanel();
        bglabel3.setBackground(Color.decode("#37555E"));
        bglabel3.setBounds(0, 100, 650, 60);
        bglabel3.setLayout(null);
        panel.add(bglabel3);

         //daily
        JLabel daily = new JLabel("Daily");
        daily.setBounds(25, 30, 50, 25);
        daily.setFont(font1);
        daily.setForeground(Color.decode("#366273"));   //#97ABC8 light colot
        daily.setCursor(new Cursor(Cursor.HAND_CURSOR));
        daily.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
        
        
            }
        });
        panel.add(daily);
        bglabel2.add(daily);

        //weekly
        JLabel weekly = new JLabel("Weekly");
        weekly.setBounds(99, 30, 60, 25);
        weekly.setFont(font1);
        weekly.setForeground(Color.decode("#366273"));
    
        weekly.setCursor(new Cursor(Cursor.HAND_CURSOR));
        weekly.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
        
            }
        });
        panel.add(weekly);
        bglabel2.add(weekly);

         //monthly
        JLabel monthly = new JLabel("Monthly");
        monthly.setBounds(188, 30, 70, 25);
        monthly.setFont(font1);
        monthly.setForeground(Color.decode("#366273"));
    
        monthly.setCursor(new Cursor(Cursor.HAND_CURSOR));
        monthly.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
        
            }
        });
        panel.add(monthly);
        bglabel2.add(monthly);

        JLabel categories = new JLabel("Categories");
        categories.setBounds(278, 30, 90, 25);
        categories.setFont(font1);
        categories.setForeground(Color.decode("#366273"));
    
        categories.setCursor(new Cursor(Cursor.HAND_CURSOR));
        categories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
            }
        });
        panel.add(categories);
        bglabel2.add(categories);

        // Logout
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
        bglabel1.add(logout);

        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JLabel splashLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        splashLabel.setBounds(5, 5, 70, 70);
        bglabel1.add(splashLabel);

        JLabel lbl = new JLabel("Personal Expenses Tracker");
        lbl.setBounds(65, 20, 300, 30);
        lbl.setFont(font1);
        lbl.setForeground(Color.WHITE);
        bglabel1.add(lbl);

        // Table setup with delete button
        model = new DefaultTableModel(new Object[]{"Date", "Category", "Description", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; //
            }
        };

        table = new JTable(model);
        table.setRowHeight(50);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 10));

        // Hide headers
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 0));

        // Cell Renderer for custom styling
        DefaultTableCellRenderer centerAlign = new DefaultTableCellRenderer();
        centerAlign.setHorizontalAlignment(JLabel.CENTER);
        centerAlign.setFont(font1);

        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.CENTER);
        rightAlign.setForeground(new Color(100, 200, 255));
        rightAlign.setFont(font1);

        // Apply render
        for (int i = 0; i < 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerAlign);
        }

        // table styles
        table.setBackground(Color.decode("#11232D"));
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(Color.decode("#467A8D"));
        table.setFont(font1);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 163, 400, 450);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.decode("#294752"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane);

        JLabel expenseLabel = new JLabel("Expenses");
        JLabel totalLabel = new JLabel("Total");
        expenseValue = new JLabel("0.00");
        totalValue = new JLabel("0.00");

        expenseLabel.setFont(new Font("Arial", Font.BOLD, 15));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        expenseValue.setFont(new Font("Arial", Font.PLAIN, 14));
        totalValue.setFont(new Font("Arial", Font.PLAIN, 14));

        expenseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        expenseValue.setFont(new Font("Arial", Font.PLAIN, 14));
        totalValue.setFont(new Font("Arial", Font.PLAIN, 14));

        expenseLabel.setForeground(Color.WHITE);
        totalLabel.setForeground(Color.WHITE);
        expenseValue.setForeground(new Color(150, 200, 220));
        totalValue.setForeground(new Color(150, 200, 220));

        expenseLabel.setBounds(70, 20, 100, 20);
        totalLabel.setBounds(270, 20, 100, 20);
        expenseValue.setBounds(93, 40, 100, 20);
        totalValue.setBounds(278, 40, 100, 20);

        panel.add(expenseLabel);
        bglabel3.add(expenseLabel);
        panel.add(totalLabel);
        bglabel3.add(totalLabel);
        panel.add(expenseValue);
        bglabel3.add(expenseValue);
        panel.add(totalValue);
        bglabel3.add(totalValue);

        // Empty data message
        emptyLabel = new JLabel("Currently no existing data", SwingConstants.CENTER);
        emptyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emptyLabel.setForeground(Color.decode("#467A8D"));
        emptyLabel.setBounds(20, 80, 350, 340);
        scrollPane.add(emptyLabel);
        panel.add(emptyLabel);


        // Add button
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
        panel.add(scrollPane);

        // Load expenses into table
        loadExpenses(userId);

        frame.setSize(400, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void loadExpenses(int userId) {
        model.setRowCount(0);  // Clear previous data
        List<String[]> expenses = UserDatabase.getExpenses(userId);

        double totalAmount = 0.0;
        
        if (expenses.isEmpty()) {
            emptyLabel.setVisible(true);
        } else {
            emptyLabel.setVisible(false);

            for (String[] expense : expenses) {
                totalAmount += Double.parseDouble(expense[3]); 
            }
    
            
            expenseValue.setText(String.format("%.2f", totalAmount));
            totalValue.setText(String.format("%.2f", totalAmount));
        

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<String[]> validExpenses = new ArrayList<>();
    
            for (String[] expense : expenses) {
                try {
                    // Check if the date is valid before parsing
                    if (expense[0].matches("\\d{4}-\\d{2}-\\d{2}")) {  
                        LocalDate.parse(expense[0], formatter); // parsing
                        validExpenses.add(expense);  // If succes, add to list
                    } else {
                        System.err.println("Invalid date format found: " + expense[0]); // for Debugging 
                    }
                } catch (DateTimeParseException ex) {
                    System.err.println("Error parsing date: " + expense[0] + " - " + ex.getMessage());
                }
            }
    
            // Sort valid expenses
            validExpenses.sort(Comparator.comparing(e -> LocalDate.parse(e[0], formatter)));
    
            // Add sorted dat to the table
            for (String[] expense : validExpenses) {
                model.addRow(expense);
            }
        }
    }
    

    public static void main(String[] args) {
        int userId = UserDatabase.getUserId("testuser");
        if (userId != -1) {
            new Mainapp(userId);
        } else {
            System.out.println("User not found");
            new Mainapp(userId);
        }
    }
}
