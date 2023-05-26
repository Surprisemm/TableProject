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

    public MyTable(MyTableModel tableModel) {

        //Удаление стандартных заголовков
        setTableHeader(null);

        //Обновление текста в заголовках
        tableModel.updateHeaderText();

        //Установка модели для таблицы
        setModel(tableModel);
        setFillsViewportHeight(true);

    }








}
