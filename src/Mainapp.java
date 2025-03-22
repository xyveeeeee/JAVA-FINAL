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
    private Font font2 = new Font("Arial", Font.BOLD, 18);

    private Font font5 = new Font("Arial", Font.BOLD, 80);

    private Color defaultnav = Color.decode("#366273");
    private Color highlighttnav = Color.decode("#97ABC8");
    private JLabel all, daily, weekly, monthly, categories;


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
        bglabel1.setBounds(0, 0, 450, 73);
        bglabel1.setLayout(null);
        panel.add(bglabel1);

        JPanel bglabel2 = new JPanel();
        bglabel2.setBackground(Color.decode("#1C3138"));
        bglabel2.setBounds(0, 50, 450, 60);
        bglabel2.setLayout(null);
        panel.add(bglabel2);

        JPanel bglabel3 = new JPanel();
        bglabel3.setBackground(Color.decode("#37555E"));
        bglabel3.setBounds(0, 100, 450, 60);
        bglabel3.setLayout(null);
        panel.add(bglabel3);

        //all
        all = new JLabel("All");
        all.setBounds(25, 30, 50, 25);
        all.setFont(font1);
        all.setForeground(Color.decode("#366273"));   //#97ABC8 light colot

        all.setCursor(new Cursor(Cursor.HAND_CURSOR));
        all.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadExpenses(userId, "all");
                changingcolor(all);
            }
        });
        panel.add(all);
        bglabel2.add(all);

         //daily
        daily = new JLabel("Daily");
        daily.setBounds(85, 30, 50, 25);
        daily.setFont(font1);
        daily.setForeground(Color.decode("#366273"));   

        daily.setCursor(new Cursor(Cursor.HAND_CURSOR));
        daily.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadExpenses(userId, "daily");
                changingcolor(daily);
            }
        });
        panel.add(daily);
        bglabel2.add(daily);

        //weekly
        weekly = new JLabel("Weekly");
        weekly.setBounds(159, 30, 60, 25);
        weekly.setFont(font1);
        weekly.setForeground(Color.decode("#366273"));
    
        weekly.setCursor(new Cursor(Cursor.HAND_CURSOR));
        weekly.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadExpenses(userId, "weekly");
                changingcolor(weekly);

            }
        });
        panel.add(weekly);
        bglabel2.add(weekly);

         //monthly
        monthly = new JLabel("Monthly");
        monthly.setBounds(248, 30, 70, 25);
        monthly.setFont(font1);
        monthly.setForeground(Color.decode("#366273"));
    
        monthly.setCursor(new Cursor(Cursor.HAND_CURSOR));
        monthly.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadExpenses(userId, "monthly");
                changingcolor(monthly);

            }
        });
        panel.add(monthly);
        bglabel2.add(monthly);

        categories = new JLabel("Categories");
        categories.setBounds(338, 30, 90, 25);
        categories.setFont(font1);
        categories.setForeground(Color.decode("#366273"));
    
        categories.setCursor(new Cursor(Cursor.HAND_CURSOR));
        categories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadExpenses(userId, "category");
                changingcolor(categories);

                
            }
        });
        panel.add(categories);
        bglabel2.add(categories);

        // Logout
        logout = new JLabel("Log Out");
        logout.setBounds(345, 25, 150, 30);
        logout.setForeground(Color.white);
        logout.setFont(font2);
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
        lbl.setBounds(65, 25, 300, 30);
        lbl.setFont(font2);
        lbl.setForeground(Color.WHITE);
        bglabel1.add(lbl);

        model = new DefaultTableModel(new Object[]{"all","Date", "Category", "Description", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; 
            }
        };

        table = new JTable(model);
        table.setRowHeight(50);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 10));

        // Hidin headers
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 0));

        // Cell Renderer for custom styling
        DefaultTableCellRenderer centerAlign = new DefaultTableCellRenderer();
        centerAlign.setHorizontalAlignment(JLabel.CENTER);
        centerAlign.setFont(font1);
        
        // Apply it in table to make it centr
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerAlign);
        }

        // table css
        table.setBackground(Color.decode("#11232D"));
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(Color.decode("#467A8D"));
        table.setFont(font1);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 163, 450, 450);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.decode("#294752"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane);


        JLabel totalLabel = new JLabel("Total Expenses");
        totalValue = new JLabel("0.00");


        totalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        totalValue.setFont(new Font("Arial", Font.PLAIN, 14));


        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalValue.setFont(new Font("Arial", Font.PLAIN, 14));


        totalLabel.setForeground(Color.WHITE);
        totalValue.setForeground(new Color(150, 200, 220));


        totalLabel.setBounds(160, 20, 200, 20);
        totalValue.setBounds(210, 40, 200, 20);


        panel.add(totalLabel);
        bglabel3.add(totalLabel);
        panel.add(totalValue);
        bglabel3.add(totalValue);

        // if data no data 
        emptyLabel = new JLabel("Currently no existing data", SwingConstants.CENTER);
        emptyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emptyLabel.setForeground(Color.decode("#467A8D"));
        emptyLabel.setBounds(45, 80, 350, 340);
        scrollPane.add(emptyLabel);
        panel.add(emptyLabel);


    // Add button
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setBounds(0, 163, 450, 450); 
    panel.add(layeredPane); 

    // Add scrollPane inside layeredPane
    scrollPane.setBounds(0, 0, 450, 450);
    layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER); // added to scrollpane then make it bg .defaultlayer 

    
    JLabel add = new JLabel("+");
    add.setBounds(370, 360, 70, 70); 
    add.setFont(font5);
    add.setForeground(Color.decode("#467A8D"));
    add.setCursor(new Cursor(Cursor.HAND_CURSOR));

    add.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            new add(userId, Mainapp.this);
            frame.dispose();
        }
    });

    
    layeredPane.add(add, JLayeredPane.PALETTE_LAYER); //.pelett_layer to make it front or overly



        loadExpenses(userId, "all");
    
        changingcolor(all);


        frame.setSize(450, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);  
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // v===========color changing habndle===============v
    public void changingcolor(JLabel selectedLabel) {
        all.setForeground(defaultnav);
        daily.setForeground(defaultnav);
        weekly.setForeground(defaultnav);
        monthly.setForeground(defaultnav);
        categories.setForeground(defaultnav);

        selectedLabel.setForeground(highlighttnav); // Highlight clicked label
    }
    // ^ ==============================================^
    // v==============handl dats function====================v
    public void loadExpenses(int userId, String filterType) {
        model.setRowCount(0);  // clr dats
        List<String[]> expenses = UserDatabase.getExpenses(userId);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
    
        List<String[]> filteredExpenses = new ArrayList<>();
    
        for (String[] expense : expenses) {
            try {
                LocalDate expenseDate = LocalDate.parse(expense[0], formatter);
    
                switch (filterType) {
                    case "daily":
                        if (expenseDate.isEqual(today)) {
                            filteredExpenses.add(expense);
                        }
                        break;
                    case "weekly":
                        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
                        LocalDate endOfWeek = startOfWeek.plusDays(6);
                        if (!expenseDate.isBefore(startOfWeek) && !expenseDate.isAfter(endOfWeek)) {
                            filteredExpenses.add(expense);
                        }
                        break;
                    case "monthly":
                        if (expenseDate.getMonth() == today.getMonth() && expenseDate.getYear() == today.getYear()) {
                            filteredExpenses.add(expense);
                        }
                        break;
                    case "category":
                        filteredExpenses.add(expense); 
                        break;
                    case  "all":
                        filteredExpenses.add(expense); 
                }
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date: " + expense[0] + " - " + e.getMessage());
            }
        }
        
        // If the user clicks "Categories" mag sosort into categories 
        if (filterType.equals("category")) {
            // Sort by date bago sa luma
            filteredExpenses.sort(Comparator
                .comparing((String[] e) -> LocalDate.parse(e[0], formatter), Comparator.reverseOrder()) // sort sa bago
                .thenComparing(e -> e[1].toLowerCase())); // tas sort by category ng naka alphabetical order
        } else {
            // naka sort sa bago
            filteredExpenses.sort(Comparator
                .comparing((String[] e) -> LocalDate.parse(e[0], formatter))
                .reversed());
        }             
        
        // Adding expenss to thetable
        for (String[] expense : filteredExpenses) {
            model.addRow(expense);
        }
    
        // total values
        double totalAmount = filteredExpenses.stream()
            .mapToDouble(expense -> Double.parseDouble(expense[3])) 
            .sum();
    
        totalValue.setText(String.format("%.2f", totalAmount));
    
        
        emptyLabel.setVisible(filteredExpenses.isEmpty());
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
