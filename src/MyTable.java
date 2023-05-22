import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTable extends JTable {

    private DefaultTableModel tableModel;

    public MyTable(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                   boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                   String rightFooterData, String bottomFooterData) {

        // Обработка заголовков и итогов
        if(isTopHeader){rowData++;}
        if(isBottomFooter){rowData++;}
        if(isLeftHeader){colData++;}
        if(isRightFooter){colData++;}


        // Создание таблицы с моделью данных
        Object[][] data = new Object[rowData][colData];


        // Заполняем заголовки и итоги
        if (isTopHeader) {
            for (int i = 0; i < colData; i++) {
                String tmpStr = "Столбец " + (i + 1);
                data[0][i] = tmpStr;
            }
        }



        tableModel = new DefaultTableModel(data, null);
        setModel(tableModel);
    }


}
