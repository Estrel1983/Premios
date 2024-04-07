package org.example.visual.Individual;


import com.toedter.calendar.JDateChooser;
import org.example.visual.SuccessFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import static org.example.dateOperations.IndividualsOperations.createNewEmployee;
import static org.example.visual.ErrorFrame.errorFrame;

public class addIndividualFrame extends JFrame {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    public addIndividualFrame(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel addEmployeePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;
        constr.fill = GridBagConstraints.HORIZONTAL;

        constr.gridx = 0;
        constr.gridy = 0;
        JLabel nameLabel = new JLabel("ФИО:");
        constr.anchor = GridBagConstraints.WEST;
        addEmployeePanel.add(nameLabel, constr);

        constr.gridx = 0;
        constr.gridy = 1;
        JTextField nameField = new JTextField(40);
        addEmployeePanel.add(nameField, constr);

        constr.gridx = 0;
        constr.gridy = 3;
        JLabel dateLabel = new JLabel("Дата рождения:");
        constr.anchor = GridBagConstraints.WEST;
        addEmployeePanel.add(dateLabel, constr);

        constr.gridx = 0;
        constr.gridy = 4;
        JDateChooser birthDate = new JDateChooser();
        birthDate.setLocale(new Locale("ru", "RU"));
        birthDate.setDateFormatString(DATE_FORMAT);
        addEmployeePanel.add(birthDate, constr);

        constr.gridx = 0;
        constr.gridy = 5;
        JButton submitBut = new JButton("Сохранить");

        submitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty() && (birthDate.getDate() != null)) {
                    createNewEmployee(nameField.getText(), birthDate.getDate());
                    new SuccessFrame();
                    dispose();
                }
                else {
                    errorFrame("Одно из полей не заполнено");
                    nameField.setText("");
                    birthDate.setDate(null);
                }
            }
        });
        addEmployeePanel.add(submitBut, constr);



        mainPanel.add(addEmployeePanel);
        getContentPane().add(mainPanel);
        setSize(500, 300);
        setVisible(true);
    }
}
