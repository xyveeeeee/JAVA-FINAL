import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

class DeleteButtonRenderer extends JButton implements TableCellRenderer {
    public DeleteButtonRenderer() {
        setText("Delete");
        setForeground(Color.WHITE);
        setBackground(Color.RED);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this; 
    }
}