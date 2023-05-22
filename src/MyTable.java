import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTable extends JTable {

    public MyTable(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                   boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                   String rightFooterData, String bottomFooterData) {

     /*   // Обработка заголовков и итогов
        if(isTopHeader){rowData++;}
        if(isBottomFooter){rowData++;}
        if(isLeftHeader){colData++;}
        if(isRightFooter){colData++;}*/

        // Массив с данными
        Object[][] data = new Object[rowData][colData];

        // Создание таблицы с моделью данных
        DefaultTableModel model = new DefaultTableModel(data, 0);
        this.setModel(model);

        if (isTopHeader) {
            model.addRow(createHeaderArray(colData));
        }
        if (isLeftHeader) {
            for (int i = 0; i < rowData; i++) {
                model.addColumn("", createHeaderArray(rowData));
            }
        }
        if (isBottomFooter) {
            model.addRow(createFooterArray(colData, bottomFooterData));
        }
        if (isRightFooter) {
            for (int i = 0; i < rowData; i++) {
                model.addColumn("", createFooterArray(rowData, rightFooterData));
            }
        }



    }

    // Создание массива заголовков или итогов
    private Object[] createHeaderArray(int length) {
        Object[] headerArray = new Object[length];
        for (int i = 0; i < length; i++) {
            headerArray[i] = "Header " + (i + 1);
        }
        return headerArray;
    }

    // Создание массива данных для итогов с заданным значением
    private Object[] createFooterArray(int length, String footerData) {
        Object[] footerArray = new Object[length];
        for (int i = 0; i < length; i++) {
            footerArray[i] = footerData;
        }
        return footerArray;
    }

}
