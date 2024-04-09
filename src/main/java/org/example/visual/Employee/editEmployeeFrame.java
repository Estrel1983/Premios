package org.example.visual.Employee;

import com.toedter.calendar.JDateChooser;
import org.example.dateOperations.IndividualsOperations;
import org.example.dateOperations.employeeOperations;
import org.example.dateOperations.positionsOperation;
import org.example.model.Employe;
import org.example.model.Individual;
import org.example.model.Position;
import org.example.visual.SuccessFrame;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

import static org.example.visual.ErrorFrame.errorFrame;

public class editEmployeeFrame extends JFrame {
    public editEmployeeFrame(Employe employe){
        JPanel mainPanel = new JPanel();
        setName("Редактирование данных о приеме и увольнении");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box fieldBox = new Box(BoxLayout.Y_AXIS);
        JLabel nameLabel = new JLabel("Имя сотрудника:");
        fieldBox.add(nameLabel);

        JComboBox<String> nameBox;
        if (employe != null) {
            nameBox = new JComboBox<>(new String[]{employe.getName()});
        }
        else
            nameBox =  new JComboBox<>(IndividualsOperations.getList().stream().map(Individual::getName).toArray(String[]::new));
        nameBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        fieldBox.add(nameBox);
        fieldBox.add(Box.createHorizontalStrut(10));

        JLabel positionLabel = new JLabel("Должность:");
        fieldBox.add(positionLabel);

        JComboBox<String> positionBox;
        if (employe != null) {
            positionBox = new JComboBox<>(new String[]{employe.getPositionName()});
        }
        else
            positionBox =  new JComboBox<>(positionsOperation.getList().stream().map(Position::getPositionName).toArray(String[]::new));
        positionBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        fieldBox.add(positionBox);
        fieldBox.add(Box.createHorizontalStrut(10));

        JLabel hireLabel = new JLabel("Дата приема:");
        fieldBox.add(hireLabel);

        JDateChooser hireDate = new JDateChooser(employe!=null ? employe.getStartDate() : new Date());
        hireDate.setBorder(new EmptyBorder(10, 10, 10, 10));
        hireDate.setLocale(new Locale("ru", "RU"));
        fieldBox.add(hireDate);
        fieldBox.add(Box.createHorizontalStrut(10));

        JLabel terminLabel = new JLabel("Дата увольнения:");
        fieldBox.add(terminLabel);

        JDateChooser terminDate = new JDateChooser(employe!=null ? employe.getEndDate() : null);
        terminDate.setBorder(new EmptyBorder(10, 10, 10, 10));
        terminDate.setLocale(new Locale("ru", "RU"));
        fieldBox.add(terminDate);
        fieldBox.add(Box.createHorizontalStrut(10));
        fieldBox.add(Box.createVerticalStrut(10));


        mainPanel.add(fieldBox);
        mainPanel.add(Box.createVerticalStrut(10));

        Box buttonBox = Creater.createSaveCancelBox();
        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();

        JButton cancelButton = (JButton) buttonBox.getComponent(3);
        JButton saveButton = (JButton) buttonBox.getComponent(1);

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
                if (hireDate.getDate() == null){
                    errorFrame("Дата приема должна быть заполнена");
                    hireDate.requestFocus();
                    return;
                }
                if (hireDate.getDate() != null && terminDate.getDate() != null && terminDate.getDate().before(hireDate.getDate())){
                    errorFrame("Дата приема не может быть позднее чем дата увольнения");
                    hireDate.requestFocus();
                    return;
                }
                if (employe != null)
                    employeeOperations.saveEmploye(employe, hireDate.getDate(), terminDate.getDate());
                else
                    employeeOperations.saveEmploye((String) nameBox.getSelectedItem(), (String) positionBox.getSelectedItem(),
                            hireDate.getDate(), terminDate.getDate());
                new SuccessFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
