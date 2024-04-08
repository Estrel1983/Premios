package org.example.visual.Individual;

import org.example.visual.utils.PlaceholderTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static org.example.dateOperations.IndividualsOperations.searchIndividualByName;

public class findIndividualFrame extends JFrame {
    public findIndividualFrame(){
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

        PlaceholderTextField searchField = new PlaceholderTextField("Введите имя сотрудника или его часть");
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
            truncatedIndividualFrame frm = new truncatedIndividualFrame(searchIndividualByName(searchField.getText()));
            findIndividualFrame.this.dispose();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                sirchButton.requestFocusInWindow();
            }
        });

        setVisible(true);

    }
}
