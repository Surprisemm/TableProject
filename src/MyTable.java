import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MyTable extends JTable {

    public Object[][] data;


    public MyTable(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                   boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                   String rightFooterData, String bottomFooterData) {

        // Обработка заголовков и итогов
        if(isTopHeader){rowData++;}
        if(isBottomFooter){rowData++;}
        if(isLeftHeader){colData++;}
        if(isRightFooter){colData++;}


        // Создание таблицы с моделью данных
        data = new Object[rowData][colData];

        MyTableModel tableModel = new MyTableModel(data);

        setModel(tableModel);
        setFillsViewportHeight(true);
    }



}
