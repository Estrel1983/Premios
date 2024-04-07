package org.example.visual.Employee;

import org.example.dateOperations.employeeOperations;
import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class employeeListFrame extends JFrame {
    public employeeListFrame(){
        JPanel mainPanel = new JPanel();
        setName("Список Должностей");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box emplPanel = new Box(BoxLayout.Y_AXIS);
        JTable emplTable = Creater.createTableForClass(new Employe(), employeeOperations.getList());
        emplPanel.add(new JScrollPane(emplTable));

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton addPosButton = new JButton("Новый прием на работу");
        buttonBox.add(addPosButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editPosButton = new JButton("Редактировать");
        buttonBox.add(editPosButton);

        emplPanel.add(buttonBox);

        mainPanel.add(emplPanel);
        getContentPane().add(mainPanel);
        pack();

        setVisible(true);
    }
}
