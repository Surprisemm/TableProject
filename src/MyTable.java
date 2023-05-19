import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTable extends JTable {
    private boolean showTopHeaders;
    private boolean showLeftHeaders;
    private boolean showBottomFooters;
    private boolean showRightFooters;

    public MyTable(Object[][] data, String[] columnNames) {
        super(data, columnNames);
        setDefaultRenderer(Object.class, new MyTableCellRenderer());

        showTopHeaders = true;
        showLeftHeaders = true;
        showBottomFooters = true;
        showRightFooters = true;
    }

    public void setShowTopHeaders(boolean showTopHeaders) {
        this.showTopHeaders = showTopHeaders;
        repaint();
    }

    public void setShowLeftHeaders(boolean showLeftHeaders) {
        this.showLeftHeaders = showLeftHeaders;
        repaint();
    }

    public void setShowBottomFooters(boolean showBottomFooters) {
        this.showBottomFooters = showBottomFooters;
        repaint();
    }

    public void setShowRightFooters(boolean showRightFooters) {
        this.showRightFooters = showRightFooters;
        repaint();
    }

    private class MyTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if ((row == 0 && showTopHeaders) || (column == 0 && showLeftHeaders)) {
                setBackground(Color.LIGHT_GRAY);
                setHorizontalAlignment(SwingConstants.CENTER);
            } else if ((row == table.getRowCount() - 1 && showBottomFooters) ||
                    (column == table.getColumnCount() - 1 && showRightFooters)) {
                setBackground(Color.LIGHT_GRAY);
                setHorizontalAlignment(SwingConstants.RIGHT);
            } else {
                setBackground(table.getBackground());
                setHorizontalAlignment(SwingConstants.LEFT);
            }

            return this;
        }
    }
}
