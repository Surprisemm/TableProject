import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MyOptions extends JPanel {


    public MyOptions() {
        super();

        setLayout(new MigLayout("nogrid"));

        JLabel rowLable = new JLabel("Строк: ");
        JLabel colLable = new JLabel("Столбцов: ");
        JLabel roundingText = new JLabel(" знаков после запятой ");

        JCheckBox topHeader = new JCheckBox("Добавить строку заголовков");
        JCheckBox leftHeader = new JCheckBox("Добавить столбец заголовков");
        JCheckBox rightFooter = new JCheckBox("Добавить столбец итогов:");
        JCheckBox bottomFooter = new JCheckBox("Добавить строку итогов:");
        JCheckBox roundingCheck = new JCheckBox("Округлять результат с точностью до ");

        SpinnerModel spinnerModel = new SpinnerNumberModel(0,0,100,1);

        JSpinner rowValue = new JSpinner(spinnerModel);
        JSpinner colValue = new JSpinner(spinnerModel);
        JSpinner roundingValue = new JSpinner(spinnerModel);

        rowValue.setPreferredSize(new Dimension(200,rowValue.getPreferredSize().height));
        colValue.setPreferredSize(new Dimension(200,colValue.getPreferredSize().height));

        JComboBox rightFooterCombo = new JComboBox<>();
        JComboBox bottomFooterCombo = new JComboBox<>();

        rightFooterCombo.setPreferredSize(new Dimension(200, rightFooterCombo.getPreferredSize().height));
        bottomFooterCombo.setPreferredSize(new Dimension(200, bottomFooterCombo.getPreferredSize().height));

        //тут настроить внешний вид

        // 1 строка
        add(rowLable);
        add(rowValue);
        add(colLable);
        add(colValue);

        // 2 строка
        add(topHeader, "newline");
        add(leftHeader);

        // 3 str
        add(bottomFooter, "newline");
        add(bottomFooterCombo);

        // 4 str
        add(rightFooter, "newline");
        add(rightFooterCombo);

        //5 str
        add(roundingCheck, "newline");
        add(roundingValue);
        add(roundingText);



        setVisible(true);

    }
}
