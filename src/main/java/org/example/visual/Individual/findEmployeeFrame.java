package org.example.visual.Individual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.example.dateOperations.IndividualsOperations.searchIndividualByName;

public class findEmployeeFrame extends JFrame {
    public findEmployeeFrame(){
        JPanel mainPanel = new JPanel();
        setName("Поиск сотрудника");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box searchPanel = new Box(BoxLayout.Y_AXIS);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(Box.createVerticalStrut(10));

        JLabel searchLabel = new JLabel("Что искать:");
        searchLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        searchPanel.add(searchLabel);

        searchPanel.add(Box.createVerticalStrut(10));

        JTextField searchField = new JTextField("Введите имя сотрудника или его часть");
        searchField.setColumns(35);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        searchPanel.add(searchField);

        searchPanel.add(Box.createVerticalStrut(10));

        JButton sirchButton = new JButton("Найти");
        sirchButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        searchPanel.add(sirchButton);

        mainPanel.add(searchPanel);
        getContentPane().add(mainPanel);
        pack();

        sirchButton.addActionListener(e -> {
            searchIndividualByName(searchField.getText());
            //TODO Сделать окно с таблицей сотрудников и прикрутить к нему редактирование по правой кнопке, по левой и по кнопке внизу
        });


        setVisible(true);

    }
}
