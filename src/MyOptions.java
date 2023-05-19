import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MyOptions extends JPanel {


    public MyOptions() {
        super();

        setLayout(new MigLayout("nogrid, gap 20"));
        Font font = new Font("Comic Sans MS", Font.PLAIN, 18);


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

        rowValue.setPreferredSize(new Dimension(200,rowValue.getPreferredSize().height + 5));
        colValue.setPreferredSize(new Dimension(200,colValue.getPreferredSize().height + 5));

        JComboBox rightFooterCombo = new JComboBox<>();
        JComboBox bottomFooterCombo = new JComboBox<>();

        rightFooterCombo.setPreferredSize(new Dimension(200, rightFooterCombo.getPreferredSize().height));
        bottomFooterCombo.setPreferredSize(new Dimension(200, bottomFooterCombo.getPreferredSize().height));

        //тут настроить внешний вид

        // Установка шрифта для компонентов с текстом
        rowLable.setFont(font);
        colLable.setFont(font);
        roundingText.setFont(font);
        topHeader.setFont(font);
        leftHeader.setFont(font);
        rightFooter.setFont(font);
        bottomFooter.setFont(font);
        roundingCheck.setFont(font);

        // 1 строка
        add(rowLable);
        add(rowValue, "gap 10");
        add(colLable, "gap 10");
        add(colValue, "gap 10");

        // 2 строка
        add(topHeader, "newline");
        add(leftHeader, "gap 10");

        // 3 str
        add(bottomFooter, "newline");
        add(bottomFooterCombo, "gap 10");

        // 4 str
        add(rightFooter, "newline");
        add(rightFooterCombo, "gap 10");

        //5 str
        add(roundingCheck, "newline");
        add(roundingValue, "gap 10");
        add(roundingText, "gap 10");



        setVisible(true);

    }
}
