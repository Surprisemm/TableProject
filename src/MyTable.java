import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;

import static com.sun.java.accessibility.util.SwingEventMonitor.addCellEditorListener;

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

    public MyTable(int rowDat, int colDat, int roundingDat, boolean isTopHeade, boolean isLeftHeade,
                   boolean isRightFoote, boolean isBottomFoote, boolean isRoundingChec,
                   String rightFooterDat, String bottomFooterDat) {

        this.rowData = rowDat;
        this.colData = colDat;
        this.roundingData = roundingDat;
        this.isTopHeader = isTopHeade;
        this.isLeftHeader = isLeftHeade;
        this.isRightFooter = isRightFoote;
        this.isBottomFooter = isBottomFoote;
        this.isRoundingCheck = isRoundingChec;
        this.rightFooterData = rightFooterDat;
        this.bottomFooterData = bottomFooterDat;

        tableEndRow = rowData;
        tableEndCol = colData;

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

        //Слушатель обновления ячеек таблицы
        for (int i = 0; i < this.rowData; i++) {
            for (int j = 0; j < this.colData; j++) {
                TableCellEditor cellEditor = getCellEditor(i, j);
                if (cellEditor != null) {
                    cellEditor.addCellEditorListener(new CellEditorListener() {
                        @Override
                        public void editingStopped(ChangeEvent e) {
                            for (int k = 0; k < tableEndRow; k++) {
                                for (int l = 0; l < tableEndCol; l++) {
                                    System.out.print(data[k][l] + " ");
                                }
                                System.out.println();
                            }
                            System.out.println("--------------");
                            System.out.println(tableStartRow + " " + tableStartCol);
                            System.out.println(tableEndRow + " " + tableEndCol);
                            //Обновление данных в таблице
                            setData(data);
                            //Обновление значений в итогах

                        }

                        @Override
                        public void editingCanceled(ChangeEvent e) {

                        }
                    });
                }
            }
        }


    }

    private void configureTable() {
        if (isTopHeader) {
            rowData++;
            tableStartRow = 1;
        }
        if (isBottomFooter) {
            rowData++;
            tableEndRow = rowData - 1;
        }
        if (isLeftHeader) {
            colData++;
            tableStartCol = 1;
        }
        if (isRightFooter) {
            colData++;
            tableEndCol = colData - 1;
        }
    }

    private void updateHeaderText(){
        if (isTopHeader){

                int n = 1;
                for (int i = tableStartCol; i < colData; i++) {
                    data[0][i] = "Столбец " + n;
                    n++;
                }
                tableModel.setRowEditable(0,false);
        }
        if (isLeftHeader){
            int n = 1;
            for (int i = tableStartRow; i < rowData ; i++) {
                data[i][0] = "Строка " + n;
                n++;
            }
            tableModel.setColumnEditable(0,false);
        }
        if(isBottomFooter){
            data[rowData - 1][0] = "";
            tableModel.setRowEditable(rowData - 1,false);
        }
        if(isRightFooter){
            data[0][colData - 1] = "";
            tableModel.setColumnEditable(colData - 1,false);
        }
    }

    public void setData(Object[][] newData){
        data = newData;
        tableModel.setData(newData);
        refreshTable();
    }

    public void refreshTable() {
        TableModel model = getModel();
        if(model instanceof DefaultTableModel){
            ((DefaultTableModel) model).fireTableDataChanged();
        }
    }





}
