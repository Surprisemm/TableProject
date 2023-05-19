import com.sun.applet2.preloader.event.ConfigEvent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class MyOptions extends JPanel {

    private int maxElSize = 30;

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

        roundingValue.setEnabled(false);

        rowValue.setPreferredSize(new Dimension(200, maxElSize));
        colValue.setPreferredSize(new Dimension(200, maxElSize));
        roundingValue.setPreferredSize(new Dimension(20, maxElSize));

        JComboBox rightFooterCombo = new JComboBox<>();
        JComboBox bottomFooterCombo = new JComboBox<>();

        rightFooterCombo.setEnabled(false);
        bottomFooterCombo.setEnabled(false);

        // Заполнение JComboBox элементами
        DefaultComboBoxModel<String> footerModel = new DefaultComboBoxModel<>();
        footerModel.addElement("Сумма");
        footerModel.addElement("Количество");
        footerModel.addElement("Среднее");
        footerModel.addElement("Максимум");
        footerModel.addElement("Минимум");
        footerModel.addElement("Сумма квадратов");

        DefaultComboBoxModel<String> footerModel_2 = new DefaultComboBoxModel<>();
        for (int i = 0; i < footerModel.getSize(); i++) {
            footerModel_2.addElement(footerModel.getElementAt(i));
        }

        rightFooterCombo.setModel(footerModel);
        bottomFooterCombo.setModel(footerModel_2);

        rightFooterCombo.setPreferredSize(new Dimension(200, maxElSize));
        bottomFooterCombo.setPreferredSize(new Dimension(200, maxElSize));

        // Панель с кнопками ---------------------------------------
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new MigLayout("nogrid, gap 20"));

        JButton insButton = new JButton("Вставить");
        JButton cancelButton = new JButton("Отмена");

        buttonsPanel.add(insButton);
        buttonsPanel.add(cancelButton);
        //----------------------------------------------------------

        // Взаимодействие элементов
        roundingCheck.addItemListener(e -> {
            boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
            roundingValue.setEnabled(isSelected);
        });

        rightFooter.addItemListener(e -> {
            boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
            rightFooterCombo.setEnabled(isSelected);
        });

        bottomFooter.addItemListener(e -> {
            boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
            bottomFooterCombo.setEnabled(isSelected);
        });

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
        rightFooterCombo.setFont(font);
        bottomFooterCombo.setFont(font);
        insButton.setFont(font);
        cancelButton.setFont(font);

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

        // 5 str
        add(roundingCheck, "newline");
        add(roundingValue, "gap 10");
        add(roundingText, "gap 10");

        // 6 str
        add(buttonsPanel, "newline, span, alignx right");


        setVisible(true);

    }


}
