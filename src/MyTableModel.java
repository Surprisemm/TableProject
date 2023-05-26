import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel {
    private Object[][] data;
    private boolean[] rowEditable;
    private boolean[] columnEditable;

    private int rowData;
    private int colData;
    private int roundingData;
    private boolean isTopHeader;
    private boolean isLeftHeader;
    private boolean isRightFooter;
    private boolean isBottomFooter;
    private boolean isRoundingCheck;
    private String rightFooterData;
    private String bottomFooterData;

    private int tableStartCol = 0;
    private int tableStartRow = 0;
    private int tableEndCol;
    private int tableEndRow;

    public MyTableModel(int rd, int cd, int rod, boolean ith, boolean ilh,
                        boolean irf, boolean ibf, boolean irc,
                        String rfd, String bfd) {

        rowData = rd;
        colData = cd;
        roundingData = rod;
        isTopHeader = ith;
        isLeftHeader = ilh;
        isRightFooter = irf;
        isBottomFooter = ibf;
        isRoundingCheck = irc;
        rightFooterData = rfd;
        bottomFooterData = bfd;

        //Изменение размеров таблицы с учетом чекбоксов
        configureTable();

        data =  new Object[rowData][colData];

        tableEndRow = rowData;
        tableEndCol = colData;

        rowEditable = new boolean[getRowCount()];
        columnEditable = new boolean[getColumnCount()];
        // По умолчанию все строки и столбцы редактируемые
        for (int i = 0; i < rowEditable.length; i++) {
            rowEditable[i] = true;
        }
        for (int i = 0; i < columnEditable.length; i++) {
            columnEditable[i] = true;
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        if (data.length > 0) {
            return data[0].length;
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex){

        data[rowIndex][columnIndex] = value;

        //Тут обновляю значение в футере




        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println(value + " " + rowIndex + " " + columnIndex);
        System.out.println("------------------");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return rowEditable[row] && columnEditable[column];
    }

    public void setRowEditable(int rowIndex, boolean editable) {
        if (rowIndex >= 0 && rowIndex < rowEditable.length) {
            rowEditable[rowIndex] = editable;
        }
    }

    public void setColumnEditable(int columnIndex, boolean editable) {
        if (columnIndex >= 0 && columnIndex < columnEditable.length) {
            columnEditable[columnIndex] = editable;
        }
    }

    public void configureTable() {
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

    public void updateHeaderText(){
        if (isTopHeader){
            int n = 1;
            for (int i = tableStartCol; i < colData; i++) {
                data[0][i] = "Столбец " + n;
                n++;
            }
            setRowEditable(0,false);
        }
        if (isLeftHeader){
            int n = 1;
            for (int i = tableStartRow; i < rowData ; i++) {
                data[i][0] = "Строка " + n;
                n++;
            }
            setColumnEditable(0,false);
        }
        if(isBottomFooter){
            data[rowData - 1][0] = "";
            setRowEditable(rowData - 1,false);
        }
        if(isRightFooter){
            data[0][colData - 1] = "";
            setColumnEditable(colData - 1,false);
        }
    }



}
