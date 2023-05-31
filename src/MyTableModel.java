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
    private Aggregator rowAggregator;
    private Aggregator colAggregator;
    private double[] rowResult;
    private double[] colResult;

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

        rowAggregator = invokeAgg(rightFooterData);
        colAggregator = invokeAgg(bottomFooterData);

        System.out.println(tableStartRow + " " + tableEndRow);
        System.out.println(tableStartCol + " " + tableEndCol);

        //Изменение размеров таблицы с учетом чекбоксов
        configureTable();

        System.out.println("---------------------");
        System.out.println(tableStartRow + " " + tableEndRow);
        System.out.println(tableStartCol + " " + tableEndCol);

        data =  new Object[rowData][colData];

        rowResult = new double[rowData];
        colResult = new double[colData];

       /* for (int i = tableStartRow; i < tableEndRow; i++) {
            for (int j = tableStartCol; j < tableEndCol; j++) {
                data[i][j] = "Nikita " + i + " " + j;
            }
        }*/

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

        //Прохожу по всем ячейкам нижнего футера
       /* for (int i = tableStartCol; i < tableEndCol ; i++) {
            data[tableEndRow][i] =
        }

        for (int i = tableStartRow; i < tableEndRow; i++) {
            data[i][tableEndCol] =
        }*/

        if(isBottomFooter) {

            for (int i = 0; i < rowResult.length; i++) {
                rowResult[i] = setColValInAgg(i);
            }
            for (int i = tableStartCol; i < tableEndCol; i++) {
                data[tableEndRow - 1][i] = rowResult[i];
            }

        }

        if(isRightFooter){

            for (int i = 0; i < colResult.length; i++) {
                colResult[i] = setRowValInAgg(i);
            }
            for (int i = tableStartRow; i < tableEndRow-1; i++) {
                data[i][tableEndCol - 1] = colResult[i];
            }

        }


        fireTableDataChanged();

      /*  for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println(value + " " + rowIndex + " " + columnIndex);
        System.out.println("------------------");*/
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

    private Aggregator invokeAgg(String agg) {

        switch (agg) {
            case "Сумма":
                return new SumAgg();
            case "Количество":
                return new CounterAgg();
            case "Среднее":
                return new AverageAgg();
            case "Максимум":
                return new MaxAgg();
            case "Минимум":
                return new MinAgg();
            case "Сумма квадратов":
                return new SqdSumAgg();
        }

        return null;
    }

    private double setColValInAgg(int columnIndex) {

        colAggregator.reset();

        for (int rowIndex = tableStartRow; rowIndex < tableEndRow; rowIndex++) {
            Object[] row = data[rowIndex];
            if (columnIndex < row.length && row[columnIndex] != null) {
                colAggregator.addValue(row[columnIndex]);
            }
        }

        return colAggregator.getResult();

    }

    private  double setRowValInAgg(int rowIndex) {

        rowAggregator.reset();

        for (int colIndex = tableStartCol; colIndex < tableEndCol; colIndex++) {
            Object col = data[rowIndex][colIndex];
            if(col != null) {
                rowAggregator.addValue(col);
            }
        }

        return rowAggregator.getResult();

    }

    private double setValInAgg(int start, int end, int index, Aggregator aggregator) {
        aggregator.reset();
        for (int i = start; i < end; i++) {
            Object[] items = data[i];
            if (index < items.length && items[index] != null) {
                aggregator.addValue(items[index]);
            }
        }
        return aggregator.getResult();
    }


}
