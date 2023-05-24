import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private Object[][] data;
    private boolean[] rowEditable;
    private boolean[] columnEditable;

    public MyTableModel(Object[][] data) {
        this.data = data;
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
}
