import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class add extends JFrame {
    private JTextField amountField, categoryField, descriptionField, dateField;
    private int userId;
    private Mainapp mainApp;
    private JLabel logout;

    private Font font1 = new Font("Arial", Font.BOLD, 16);
    private Font font2 = new Font("Arial", Font.BOLD, 20);
    private Font font4 = new Font("Arial", Font.BOLD, 17);
    
    public add(int userId, Mainapp mainApp) {
        this.userId = userId;
        this.mainApp = mainApp;
        
        setTitle("Add Expense");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#294752"));
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 650);
        add(panel);

        // App Icon
        ImageIcon img = new ImageIcon(getClass().getResource("/icon1.png"));
        setIconImage(img.getImage());

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
                new Login(); // Opens the Login window
                dispose(); // Closes the current window
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

        
        // Labels and input fields
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(45, 120, 150, 25);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(font2);
        panel.add(amountLabel);
        
        amountField = new JTextField();
        amountField.setBounds(45, 150, 300, 40);
        amountField.setFont(font4);
        panel.add(amountField);
        
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(45, 210, 100, 25);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(font2);
        panel.add(categoryLabel);
        
        categoryField = new JTextField();
        categoryField.setBounds(45, 240, 300, 40);
        categoryField.setFont(font4);
        panel.add(categoryField);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(45, 300, 150, 25);
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(font2);
        panel.add(descriptionLabel);
        
        descriptionField = new JTextField();
        descriptionField.setBounds(45, 330, 300, 40);
        descriptionField.setFont(font4);
        panel.add(descriptionField);
        
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(45, 390, 200, 25);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(font2);
        panel.add(dateLabel);
        
        dateField = new JTextField();
        dateField.setBounds(45, 420, 300, 40);
        dateField.setFont(font4);
        panel.add(dateField);
        
        // Submt button
        JButton addButton = new JButton("Save");
        addButton.setBounds(45, 500, 130, 40);
        addButton.setFont(font4);
        panel.add(addButton);
        
        // Clos button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(215, 500, 130, 40);
        cancelButton.setFont(font4);
        panel.add(cancelButton);
        
        // Action listener for adding expense
        addButton.addActionListener(e -> addExpense());
        
        cancelButton.addActionListener(e -> {
            new Mainapp(userId); 
            dispose(); 
        });
        
        setVisible(true);
    }
    
    private void addExpense() {
      // Get text from fields
    String amountText = amountField.getText().trim();
    String category = categoryField.getText().trim();
    String description = descriptionField.getText().trim();
    String date = dateField.getText().trim();

      // Validate inputs
    if (amountText.isEmpty() || category.isEmpty() || description.isEmpty() || date.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill out all fields.");
        return; // Stop execution if any field is empty
    }

    try {
        double amount = Double.parseDouble(amountText);

        boolean success = UserDatabase.addExpense(userId, amount, category, description, date);
        if (success) {
            JOptionPane.showMessageDialog(this, "Expense added successfully!");
            mainApp.loadExpenses(userId); // Refresh table in Mainapp
            new Mainapp(userId);
            dispose(); // Close window after adding expense
        } else {
            JOptionPane.showMessageDialog(this, "Error adding expense.");
        }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }
}
    