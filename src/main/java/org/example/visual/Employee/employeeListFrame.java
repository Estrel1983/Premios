package org.example.visual.Employee;

import org.example.dateOperations.employeeOperations;
import org.example.model.Employe;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static org.example.dateOperations.employeeOperations.searchEmploye;

public class employeeListFrame extends JFrame {
    DefaultTableModel model;

    public employeeListFrame(){
        JPanel mainPanel = new JPanel();
        setName("Список Должностей");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box emplPanel = new Box(BoxLayout.Y_AXIS);
        JTable emplTable = Creater.createTableForClass(new Employe(), employeeOperations.getListForTable());
        emplPanel.add(new JScrollPane(emplTable));
        model = (DefaultTableModel) emplTable.getModel();

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton addEmpButton = new JButton("Новый прием на работу");
        buttonBox.add(addEmpButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editPosButton = new JButton("Редактировать");
        buttonBox.add(editPosButton);

        emplPanel.add(buttonBox);

        mainPanel.add(emplPanel);
        getContentPane().add(mainPanel);
        pack();

        //TODO Сделать лисенер на кнопку редактировать. Добавить меню на правую кнопку на таблицу. Добавить действие на дабл клик

        addEmpButton.addActionListener(e -> createNewEmp());

        setVisible(true);
    }
    private void editSelectedItem (JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow < 0)
            return;
        editEmployeeFrame frame = new editEmployeeFrame(searchEmploye (((String) jTable.getValueAt(selectedRow, 0)),
                (String) jTable.getValueAt(selectedRow, 1),
                (String) jTable.getValueAt(selectedRow, 2),
                (String) jTable.getValueAt(selectedRow, 3)));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }
    private void refreshTable() {
        ArrayList<Employe> empList = employeeOperations.getList(); // Получаем обновленные данные
        model.setRowCount(0);
        empList.stream().forEach(e -> model.addRow(e.toObject()));
    }

    private void createNewEmp(){
        editEmployeeFrame frame = new editEmployeeFrame(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }
}
