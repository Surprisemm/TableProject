import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MyTable extends JTable {

    public Object[][] data;
    private int tableStartCol = 0;
    private int tableStartRow = 0;
    private int tableEndCol;
    private int tableEndRow;

    private int rowData;
    private int colData;
    private int roundingData;
    private boolean isTopHeader;
    private boolean isBottomFooter;
    private boolean isLeftHeader;
    private boolean isRightFooter;
    private boolean isRoundingCheck;
    private String rightFooterData;
    private String bottomFooterData;
    private MyTableModel tableModel;

    public MyTable(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                   boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                   String rightFooterData, String bottomFooterData) {

        this.rowData = rowData;
        this.colData = colData;
        this.roundingData = roundingData;
        this.isTopHeader = isTopHeader;
        this.isLeftHeader = isLeftHeader;
        this.isRightFooter = isRightFooter;
        this.isBottomFooter = isBottomFooter;
        this.isRoundingCheck = isRoundingCheck;
        this.rightFooterData = rightFooterData;
        this.bottomFooterData = bottomFooterData;

        // Обработка заголовков и итогов
        configureTable();

        // Создание таблицы с моделью данных
        data = new Object[this.rowData][this.colData];

        //Добавление своей модели
        tableModel = new MyTableModel(data);

        //Обновление текста в заголовках
        updateHeaderText();

        //Удаление стандартных заголовков
        setTableHeader(null);

        //Установка модели для таблицы
        setModel(tableModel);
        setFillsViewportHeight(true);
    }

    private void configureTable() {
        if (isTopHeader) {
            rowData++;
            tableStartRow = 1;
        }
        if (isBottomFooter) {
            rowData++;
            tableEndRow = rowData - 2;
        }
        if (isLeftHeader) {
            colData++;
            tableStartCol = 1;
        }
        if (isRightFooter) {
            colData++;
            tableEndCol = colData - 2;
        }
    }

    private void updateHeaderText(){
        if (isTopHeader){

                int n = 1;
                for (int i = tableStartCol; i < colData; i++) {
                    data[0][i] = "Столбец " + n;
                    n++;
                }
                //tableModel.setRowEditable(0,false);
        }
        if (isLeftHeader){
            int n = 1;
            for (int i = tableStartRow; i < rowData ; i++) {
                data[i][0] = "Строка " + n;
                n++;
            }
        }
        if(isBottomFooter){
            data[rowData - 1][0] = "";
            //tableModel.setRowEditable(rowData - 1,false);
        }
        if(isRightFooter){
            data[0][colData - 1] = "";
        }
    }

}
