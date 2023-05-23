import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private Object[][] data;

    public MyTableModel(Object[][] data) {
        this.data = data;
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
}
