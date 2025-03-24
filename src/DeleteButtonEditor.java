import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class DeleteButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private JTable table;
    private DefaultTableModel model;
    private int row;

    public DeleteButtonEditor(JTable table, DefaultTableModel model) {
        this.table = table;
        this.model = model;
        this.button = new JButton("Delete");
        
        

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this expense?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    String date = (String) model.getValueAt(row, 0);
                    String category = (String) model.getValueAt(row, 1);
                    String description = (String) model.getValueAt(row, 2);
                    double amount = Double.parseDouble(model.getValueAt(row, 3).toString());

                    // remove frm database
                    UserDatabase.deleteExpense(date, category, description, amount);
                    
                    

                    model.removeRow(row);
                    fireEditingStopped();
                }
            }
        });
        button.setPreferredSize(new Dimension(80, 30)); 

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Delete";
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}