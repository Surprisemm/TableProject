import javax.swing.table.AbstractTableModel;
import java.util.Arrays;

/**
 * Класс - модель таблицы, тут обрабатываются все указанные данные и заполняются ячейки таблицы сооответственно
 * Обрабатываются значения введенные в ячейки
 * Created by Nikita.Manzhukov on 01.06.2023
 */
public class MyTableModel extends AbstractTableModel {
    private final Object[][] data;
    private final boolean[] rowEditable;
    private final boolean[] columnEditable;

    private int rowData;
    private int colData;
    public int roundingData;
    private final boolean isTopHeader;
    private final boolean isLeftHeader;
    private final boolean isRightFooter;
    private final boolean isBottomFooter;
    public boolean isRoundingCheck;
    private final String rightFooterData;
    private final String bottomFooterData;
    private int tableStartCol = 0;
    private int tableStartRow = 0;
    private int tableEndCol;
    private int tableEndRow;
    private final Aggregator rowAggregator;
    private final Aggregator colAggregator;
    private final double[] rowResult;
    private final double[] colResult;

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

        rowAggregator = invokeAgg(rfd);
        colAggregator = invokeAgg(bfd);

        configureTable();

        data =  new Object[rowData][colData];

        rowResult = new double[colData + 1];
        colResult = new double[rowData + 1];

        tableEndRow = rowData;
        tableEndCol = colData;

        rowEditable = new boolean[getRowCount()];
        columnEditable = new boolean[getColumnCount()];
        // По умолчанию все строки и столбцы редактируемые
        Arrays.fill(rowEditable, true);
        Arrays.fill(columnEditable, true);

        updateHeaderText();
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

    /**
     * При изменении значения в ячейке
     * Ячейкам присваивается указанное значение
     * Обновляются итоги
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex){

        data[rowIndex][columnIndex] = value;

        updateFooters();

        fireTableDataChanged();

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return rowEditable[row] && columnEditable[column];
    }

    /**
     * Указывает редактируемость строки
     */
    public void setRowEditable(int rowIndex, boolean editable) {
        if (rowIndex >= 0 && rowIndex < rowEditable.length) {
            rowEditable[rowIndex] = editable;
        }
    }

    /**
     * Указывает редактируемость столбца
     */
    public void setColumnEditable(int columnIndex, boolean editable) {
        if (columnIndex >= 0 && columnIndex < columnEditable.length) {
            columnEditable[columnIndex] = editable;
        }
    }

    /**
     * Увеличивает размер таблицы в зависимости от наличия заголовков и итогов
     */
    public void configureTable() {
        if (isTopHeader) {
            rowData++;
            tableStartRow = 1;
        }
        if (isBottomFooter) {
            rowData++;
            tableEndRow = tableEndRow - 1;
        }
        if (isLeftHeader) {
            colData++;
            tableStartCol = 1;
        }
        if (isRightFooter) {
            colData++;
            tableEndCol = tableEndCol - 1;
        }
    }

    /**
     * Устанавливает текст в ячейки заголовков и итогов и делает их нередактируемыми
     */
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
            if(isLeftHeader) data[rowData - 1][0] = bottomFooterData;
            setRowEditable(rowData - 1,false);
        }
        if(isRightFooter){
            data[0][colData - 1] = "";
            if(isTopHeader) data[0][colData - 1] = rightFooterData;
            setColumnEditable(colData - 1,false);
        }
    }

    /**
     * Возвращает указанный агрегатор
     */
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

    /**
     * Передает в агрегатор все значения указанного столбца и возвращает результат
     */
    private double setColValInAgg(int columnIndex) {

        colAggregator.reset();

        for (int rowIndex = tableStartRow; rowIndex < rowData - 1; rowIndex++) {
            Object[] row = data[rowIndex];
            if (columnIndex < row.length && row[columnIndex] != null) {
                colAggregator.addValue(objToDbl(row[columnIndex]));
            }
        }

        return colAggregator.getResult();

    }

    /**
     * Передает в агрегатор все значения указанной строки и возвращает результат
     */
    private  double setRowValInAgg(int rowIndex) {

        rowAggregator.reset();

        for (int colIndex = tableStartCol; colIndex < colData - 1; colIndex++) {
            Object col = data[rowIndex][colIndex];
            if(col != null) {
                rowAggregator.addValue(objToDbl(col));
            }
        }

        return rowAggregator.getResult();

    }

    /**
     * Обновляет итоги значениями из агрегаторов
     * Округляет значения если нужно
     */
    private void updateFooters(){

        if(isBottomFooter) {

            for (int i = 0; i < rowResult.length; i++) {
                try {
                    rowResult[i] = setColValInAgg(i);
                }catch (Exception ignored){}
            }
            for (int i = tableStartCol; i < tableEndCol; i++) {
                data[tableEndRow - 1][i] = applyRounding(rowResult[i]);
            }

        }

        if(isRightFooter){

            for (int i = 0; i < colResult.length; i++) {
                try {
                    colResult[i] = setRowValInAgg(i);
                }catch (Exception ignored){}
                }
            for (int i = tableStartRow; i < tableEndRow; i++) {
                data[i][tableEndCol - 1] = applyRounding(colResult[i]);
            }

        }

        data[tableEndRow - 1][tableEndCol - 1] = " ";

    }

    /**
     * Конвертирует данные в число double если это возможно
     */
    private double objToDbl (Object obj) {

        if (obj == null) {
            return 0.0;
        }

        String str = obj.toString();

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0;
        }

    }

    /**
     * Применяет округление, если нужно
     * Если нет, то ничего не делает
     */
    private double applyRounding(double value) {
        if (isRoundingCheck) {
            return Math.round(value * Math.pow(10, roundingData)) / Math.pow(10, roundingData);
        } else {
            return value;
        }
    }

}
