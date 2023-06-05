import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Класс - добавляет меню для открытия окна настройки таблицы,
 * добавляет пенель, на которой располагается таблица
 * Created by Nikita.Manzhukov on 01.06.2023
 */
public class MainUi extends JFrame implements MyOptions.OptionsCallback {

    private final MyOptions optionsWindow = new MyOptions();
    private final JPanel tablePanel;
    private final GridBagConstraints gbc;


    public MainUi(){

        super("ComplexTable");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

        int windowDimWidth = 600;
        int windowDimHeight = 500;

        this.setBounds(dimension.width / 2 - windowDimWidth / 2, dimension.height / 2 - windowDimHeight / 2, windowDimWidth, windowDimHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUIFont(new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 18));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem menuItem = new JMenuItem("Старт");
        fileMenu.add(menuItem);
        menuBar.setLayout(new BorderLayout());
        menuBar.setPreferredSize(new Dimension(windowDimWidth, 25));
        menuBar.add(fileMenu, BorderLayout.WEST);
        setJMenuBar(menuBar);

        menuItem.addActionListener(e -> {
            JFrame parentFrame = MainUi.this;
            MyOptions optionsWindow = new MyOptions();
            optionsWindow.setOptionsCallback(MainUi.this);
            JDialog dialog = new JDialog(parentFrame, "Options", true);
            dialog.setResizable(false);
            dialog.getContentPane().add(optionsWindow);
            dialog.pack();
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        });

        tablePanel = new JPanel(new GridBagLayout());

        int tablePadding = 10;
        tablePanel.setBorder(new EmptyBorder(tablePadding, tablePadding, tablePadding, tablePadding));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(tablePanel);
        mainPanel.setPreferredSize(new Dimension(500, 500));

        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        MainUi mu = new MainUi();
    }

    /**
     * Передает параметры для модели таблицы при нажатии кнопки "Вставить"
     * Создает таблицу, используя модель
     * Очищает панель с таблицей
     * Перерисовывает панель с таблицей
     */
    @Override
    public void onInsertButtonClicked(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                                      boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                                      String rightFooterData, String bottomFooterData) {
        tablePanel.removeAll();

        MyTableModel tableModel = new MyTableModel(rowData, colData, roundingData, isTopHeader, isLeftHeader,
                isRightFooter, isBottomFooter, isRoundingCheck, rightFooterData, bottomFooterData);

        JTable myTable = new JTable(tableModel);
        myTable.setTableHeader(null);

        tablePanel.add(new JScrollPane(myTable), gbc);

        tablePanel.revalidate();
        tablePanel.repaint();

    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource){
                UIManager.put(key, f);
            }
        }
    }
}