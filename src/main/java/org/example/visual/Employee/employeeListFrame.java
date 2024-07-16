package org.example.visual.Employee;

import org.example.dateOperations.employeeOperations;
import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.model.position;
import org.example.visual.Position.editPositionFrame;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static org.example.dateOperations.positionsOperation.getList;

public class employeeListFrame extends JFrame {
    private DefaultTableModel model;
    public employeeListFrame(){
        JPanel mainPanel = new JPanel();
        setName("Список Работников");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box emplPanel = new Box(BoxLayout.Y_AXIS);
        JTable emplTable = Creater.createTableForClass(new Employe(), employeeOperations.getList());
        model = (DefaultTableModel) emplTable.getModel();
        emplPanel.add(new JScrollPane(emplTable));

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton addEmployeButton = new JButton("Новый прием на работу");
        buttonBox.add(addEmployeButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editPosButton = new JButton("Редактировать");
        buttonBox.add(editPosButton);

        emplPanel.add(buttonBox);

        mainPanel.add(emplPanel);
        getContentPane().add(mainPanel);
        pack();

        addEmployeButton.addActionListener(e -> createNewEmployee());

        setVisible(true);
    }

    private void createNewEmployee(){
        editEmployeeFrame frame = new editEmployeeFrame(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }
    private void refreshTable() {
        ArrayList<TableModel> empList = employeeOperations.getList(); // Получаем обновленные данные
        model.setRowCount(0);
        empList.forEach(e -> model.addRow(e.toObject()));
    }
}
