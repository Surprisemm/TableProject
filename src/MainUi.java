import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUi extends JFrame implements MyOptions.OptionsCallback {

    public int windowDimWidth = 800;
    public int windowDimHeight = 500;

    private MyTable table;
    private MyOptions optionsWindow = new MyOptions();


    public MainUi(){

        super("ComplexTable");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

        this.setBounds(dimension.width / 2 - windowDimWidth / 2, dimension.height / 2 - windowDimHeight / 2, windowDimWidth, windowDimHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
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

        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(windowDimWidth, windowDimHeight));

        //tmp
        JLabel tmpLable = new JLabel("Пусто");
        tablePanel.add(tmpLable);



        mainPanel.add(tablePanel);

//------------------------------------------------------------------------

        getContentPane().add(mainPanel);
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
        // Выполните нужные действия с переданными данными
        System.out.println("rowData: " + rowData);
        System.out.println("colData: " + colData);
        System.out.println("roundingData: " + roundingData);
        System.out.println("isTopHeader: " + isTopHeader);
        System.out.println("isLeftHeader: " + isLeftHeader);
        System.out.println("isRightFooter: " + isRightFooter);
        System.out.println("isBottomFooter: " + isBottomFooter);
        System.out.println("isRoundingCheck: " + isRoundingCheck);
        System.out.println("rightFooterData: " + rightFooterData);
        System.out.println("bottomFooterData: " + bottomFooterData);
        System.out.println("----------------------------------------");
    }

}