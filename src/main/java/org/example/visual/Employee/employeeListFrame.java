package org.example.visual.Employee;

import org.example.dataMapper.EmployeMapper;
import org.example.dateOperations.employeeOperations;
import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.model.position;
import org.example.visual.Individual.addIndividualFrame;
import org.example.visual.Position.editPositionFrame;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static org.example.dateOperations.positionsOperation.getList;

public class employeeListFrame extends JFrame {
    private DefaultTableModel model;

    public employeeListFrame() {
        JPanel mainPanel = new JPanel();
        setName("Список Работников");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box emplPanel = new Box(BoxLayout.Y_AXIS);
        JTable emplTable = Creater.createTableForClass(new Employe(), employeeOperations.getList());
        model = (DefaultTableModel) emplTable.getModel();
        emplTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)){
                    int row = emplTable.rowAtPoint(e.getPoint());
                    if (row >= 0 &&row <emplTable.getRowCount()){
                        emplTable.setRowSelectionInterval(row, row);
                        JPopupMenu actionMenu = createPopupMenu(emplTable);
                        actionMenu.show(emplTable,e.getX(), e.getY());
                    }
                }
                super.mousePressed(e);
            }
        });

        emplPanel.add(new JScrollPane(emplTable));


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton addEmployeButton = new JButton("Новый прием на работу");
        buttonBox.add(addEmployeButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editEmployeButton = new JButton("Редактировать");
        buttonBox.add(editEmployeButton);

        emplPanel.add(buttonBox);

        mainPanel.add(emplPanel);
        getContentPane().add(mainPanel);
        pack();

        addEmployeButton.addActionListener(e -> createNewEmployee());
        editEmployeButton.addActionListener(e -> editSelectedItem(emplTable));

        setVisible(true);
    }

    private void createNewEmployee() {
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

    private void editSelectedItem(JTable employeeTable) {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0)
            return;
        editEmployeeFrame frame = new editEmployeeFrame(employeeOperations.sirchEmploye((String) employeeTable.getValueAt(selectedRow, 0),
                (String) employeeTable.getValueAt(selectedRow, 1)));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }

    private JPopupMenu createPopupMenu(JTable employeeTable) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Добавить");
        createItem.addActionListener(e -> {
            createNewEmployee();
        });

        JMenuItem editItem = new JMenuItem("Редактировать");
        editItem.addActionListener(e -> editSelectedItem(employeeTable));

        jPopupMenu.add(createItem);
        jPopupMenu.add(editItem);
        return jPopupMenu;
    }
}
