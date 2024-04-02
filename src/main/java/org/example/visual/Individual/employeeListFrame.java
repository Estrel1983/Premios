package org.example.visual.Individual;

import org.example.model.Individual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static org.example.dateOperations.IndividualsOperations.getIndividualList;
import static org.example.dateOperations.IndividualsOperations.sirchIndividual;

public class employeeListFrame extends JFrame {
    private DefaultTableModel model;
    public employeeListFrame(){
        JPanel mainPanel = new JPanel();
        setName("Список Физических лиц");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box individPanel = new Box(BoxLayout.Y_AXIS);
        JTable individTable = createIndividualsTable();
        individPanel.add(new JScrollPane(individTable));
        individTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)){
                    int row = individTable.rowAtPoint(e.getPoint());
                    if (row >= 0 &&row <individTable.getRowCount()){
                        individTable.setRowSelectionInterval(row, row);
                        JPopupMenu actionMenu = createPopupMenu(individTable);
                        actionMenu.show(individTable,e.getX(), e.getY());
                    }
                }
                super.mousePressed(e);
            }
        });

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton addIndividButton = new JButton("Новое Физ Лицо");
        buttonBox.add(addIndividButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editIndividButton = new JButton("Редактировать");
        buttonBox.add(editIndividButton);

        individPanel.add(buttonBox);

        mainPanel.add(individPanel);
        getContentPane().add(mainPanel);
        pack();

        addIndividButton.addActionListener(e -> {
            addEmployeeFrame frame = new addEmployeeFrame();
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshTable();
                }
            });
        });

        editIndividButton.addActionListener(e -> editSelectedItem(individTable));

        setVisible(true);
    }
    private JPopupMenu createPopupMenu(JTable individTable) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Добавить нового");
        createItem.addActionListener(e -> {
            addEmployeeFrame frame = new addEmployeeFrame();
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshTable();
                }
            });
        });

        JMenuItem editItem = new JMenuItem("Редактировать");
        editItem.addActionListener(e -> editSelectedItem(individTable));

        jPopupMenu.add(createItem);
        jPopupMenu.add(editItem);
        return jPopupMenu;
    }

    private JTable createIndividualsTable (){
        JTable table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("Дата рождения");
        ArrayList<Individual> indList = getIndividualList();
        indList.stream().forEach(e -> model.addRow(e.toObject()));
        table.setModel(model);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        if (!indList.isEmpty()) {
            int defaultSelectedRow = 0;
            table.setRowSelectionInterval(defaultSelectedRow, defaultSelectedRow);
        }
        return table;
    }
    private void refreshTable() {
        ArrayList<Individual> indList = getIndividualList(); // Получаем обновленные данные
        model.setRowCount(0);
        indList.stream().forEach(e -> model.addRow(e.toObject()));
    }
    private void editSelectedItem (JTable individTable){
        int selectedRow = individTable.getSelectedRow();
        if (selectedRow < 0)
            return;
        editEmployeeFrame frame = new editEmployeeFrame(sirchIndividual((String) individTable.getValueAt(selectedRow, 0),
                (String) individTable.getValueAt(selectedRow, 1)));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }
}
