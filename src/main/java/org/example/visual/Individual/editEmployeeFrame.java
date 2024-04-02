package org.example.visual.Individual;

import com.toedter.calendar.JDateChooser;
import org.example.model.Individual;
import org.example.visual.SuccessFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import static org.example.dateOperations.IndividualsOperations.saveIndividual;

public class editEmployeeFrame extends JFrame {
    public editEmployeeFrame (Individual individual){
        JPanel mainPanel = new JPanel();
        setName("Редактирование данных о физ. лице");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Box indBox = new Box(BoxLayout.Y_AXIS);
        JLabel nameLabel = new JLabel("Имя:");
        indBox.add(nameLabel);

        JTextField nameField = new JTextField(individual.getName());
        nameField.setBorder(new EmptyBorder(10, 10, 10, 10));
        indBox.add(nameField);

        indBox.add(Box.createHorizontalStrut(10));

        JLabel dateLabel = new JLabel("Дата рождения:");
        indBox.add(dateLabel);

        JDateChooser birthDate = new JDateChooser(individual.getBirthDate());
        birthDate.setBorder(new EmptyBorder(10, 10, 10, 10));
        birthDate.setLocale(new Locale("ru", "RU"));
        indBox.add(birthDate);
        indBox.add(Box.createHorizontalStrut(10));
        indBox.add(Box.createVerticalStrut(10));
        mainPanel.add(indBox);
        mainPanel.add(Box.createVerticalStrut(10));

        Box buttonBox = Box.createHorizontalBox();

        buttonBox.add(Box.createHorizontalStrut(10));
        JButton saveButton = new JButton("Сохранить");
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(100));
        JButton cancelButton = new JButton("Отмена");
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.setBorder(new EmptyBorder(10, 10, 10, 10));


        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(cancelButton);
                window.dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveIndividual(individual, nameField.getText(), birthDate.getDate());
                new SuccessFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
