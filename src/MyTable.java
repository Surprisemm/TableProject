import javax.swing.*;

/**
 * Класс - расширяющий JTable, удаляются стандартные заголовки,
 * Обнровляется текст в Новых заголовках в модели таблицы, устанавливается модель
 * Created by Nikita.Manzhukov on 01.06.2023
 */
public class MyTable extends JTable {

    public MyTable(MyTableModel tableModel) {

        setTableHeader(null);

        tableModel.updateHeaderText();

        setModel(tableModel);
        setFillsViewportHeight(true);

    }








}
