import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyTable_v1 extends JTable {

    private final int cellMinHeight = 20;
    private boolean upHearder;
    private boolean leftHeader;
    private boolean rightResult;
    private boolean downResult;


    public MyTable_v1(DefaultTableModel model){

        super();

        setModel(model);

        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Настройка рендерера ячеек
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Отступы внутри ячейки
                }
                return component;
            }
        };

        setDefaultRenderer(Object.class, cellRenderer);

        // Установка высоты ячеек
        setRowHeight(1); // Сбросим высоту ячейки, чтобы она формировалась автоматически

        // Настройка высоты ячеек с учетом минимального размера
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int rowCount = getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    int rowHeight = Math.max(getRowHeight(), cellMinHeight);
                    setRowHeight(i, rowHeight);
                }
            }
        });



    }
}
