import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUi extends JFrame {

    private int windowDim = 512;
    private int cellMinHeight = 20;

    private MyTable table;


    public MainUi(){

        super("ComplexTable");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

        this.setBounds(dimension.width / 2 - windowDim / 2, dimension.height / 2 - windowDim / 2, windowDim, windowDim);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

//-----------------------------------------------------------------------

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem menuItem = new JMenuItem("Старт");
        fileMenu.add(menuItem);


        JCheckBoxMenuItem topHeaderMenuItem = new JCheckBoxMenuItem("Заголовки сверху");
        JCheckBoxMenuItem leftHeaderMenuItem = new JCheckBoxMenuItem("Заголовки слева");
        JCheckBoxMenuItem bottomFooterMenuItem = new JCheckBoxMenuItem("Итоги снизу");
        JCheckBoxMenuItem rightFooterMenuItem = new JCheckBoxMenuItem("Итоги справа");

        topHeaderMenuItem.addActionListener(e -> table.setShowTopHeaders(topHeaderMenuItem.isSelected()));
        leftHeaderMenuItem.addActionListener(e -> table.setShowLeftHeaders(leftHeaderMenuItem.isSelected()));
        bottomFooterMenuItem.addActionListener(e -> table.setShowBottomFooters(bottomFooterMenuItem.isSelected()));
        rightFooterMenuItem.addActionListener(e -> table.setShowRightFooters(rightFooterMenuItem.isSelected()));

        fileMenu.add(topHeaderMenuItem);
        fileMenu.add(leftHeaderMenuItem);
        fileMenu.add(bottomFooterMenuItem);
        fileMenu.add(rightFooterMenuItem);


        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // создавать окно настройки таблицы
            }
        });






//------------------------------------------------------------------------
        JPanel tablePanel = new JPanel();
        Object[][] data = {
                {"Row 1", "John", 25, "Male"},
                {"Row 2", "Alice", 30, "Female"},
                {"Row 3", "Bob", 35, "Male"},
                {"Row 4", "Lisa", 28, "Female"}
        };
        String[] columnNames = {"", "Name", "Age", "Gender"};
        table = new MyTable(data, columnNames);

        // Настройка внешнего вида таблицы
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Установка ширины столбца заголовков слева
        table.getColumnModel().getColumn(0).setPreferredWidth(80);

        // Добавление таблицы на панель
        tablePanel.add(new JScrollPane(table));

        mainPanel.add(tablePanel);
//------------------------------------------------------------------------

        // тут будет класс с окном настройки панели, который вернет DefaultTableModel
        // который я отдам классу MyTable и он сформирует таблицу


//------------------------------------------------------------------------

        getContentPane().add(mainPanel);
        //pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        MainUi mu = new MainUi();
    }
}