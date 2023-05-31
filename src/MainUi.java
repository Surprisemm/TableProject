import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUi extends JFrame implements MyOptions.OptionsCallback {

    private MyTable myTable;
    private MyOptions optionsWindow = new MyOptions();
    private JPanel mainPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private MyTableModel tableModel;


    public MainUi(){

        super("ComplexTable");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();
      //  int windowDimWidth = tk.getScreenSize().width;
        //int windowDimHeight = tk.getScreenSize().height;
        int windowDimWidth = 800;
        int windowDimHeight = 600;

        this.setBounds(dimension.width / 2 - windowDimWidth / 2, dimension.height / 2 - windowDimHeight / 2, windowDimWidth, windowDimHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));



    //    System.out.println(optionsWindow.getRowValue());

//-----------------------------------------------------------------------

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem menuItem = new JMenuItem("Старт");
        fileMenu.add(menuItem);
        menuBar.setLayout(new BorderLayout());
        menuBar.setPreferredSize(new Dimension(windowDimWidth, 25));
        menuBar.add(fileMenu, BorderLayout.WEST);
        setJMenuBar(menuBar);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // создавать окно настройки таблицы
                JFrame parentFrame = MainUi.this; // Ссылка на родительское окно
                MyOptions optionsWindow = new MyOptions();
                optionsWindow.setOptionsCallback(MainUi.this);
                JDialog dialog = new JDialog(parentFrame, "Options", true);
                dialog.setResizable(false);
                dialog.getContentPane().add(optionsWindow);
                dialog.pack();
                dialog.setLocationRelativeTo(parentFrame);
                dialog.setVisible(true);
            }
        });

//------------------------------------------------------------------------

        tablePanel.setPreferredSize(new Dimension(300,600));



        mainPanel.add(tablePanel);

//------------------------------------------------------------------------

        getContentPane().add(tablePanel);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        MainUi mu = new MainUi();
    }

    @Override
    public void onInsertButtonClicked(int rowData, int colData, int roundingData, boolean isTopHeader, boolean isLeftHeader,
                                      boolean isRightFooter, boolean isBottomFooter, boolean isRoundingCheck,
                                      String rightFooterData, String bottomFooterData) {
        mainPanel.removeAll(); // очистить все

        // Заполняю tablePanel

        tableModel = new MyTableModel(rowData, colData, roundingData, isTopHeader, isLeftHeader,
                isRightFooter, isBottomFooter, isRoundingCheck, rightFooterData, bottomFooterData);
        myTable = new MyTable(tableModel);


        tablePanel.add(new JScrollPane(myTable));
        mainPanel.add(tablePanel);

        mainPanel.revalidate(); // Перерисовываем mainPanel
        mainPanel.repaint();

        getContentPane().add(tablePanel);
        pack();
        setVisible(true);

    }

}