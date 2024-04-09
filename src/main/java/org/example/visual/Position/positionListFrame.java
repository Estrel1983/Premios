package org.example.visual.Position;

import org.example.model.Position;

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
import static org.example.dateOperations.positionsOperation.getList;
import static org.example.dateOperations.positionsOperation.sirchPosition;

public class positionListFrame extends JFrame {
    private DefaultTableModel model;

    public positionListFrame() {
        JPanel mainPanel = new JPanel();
        setName("Список Должностей");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box posPanel = new Box(BoxLayout.Y_AXIS);
        JTable posTable = createPosTable();
        posPanel.add(new JScrollPane(posTable));

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton addPosButton = new JButton("Новая Должность");
        buttonBox.add(addPosButton);
        buttonBox.add(Box.createHorizontalStrut(60));

        JButton editPosButton = new JButton("Редактировать");
        buttonBox.add(editPosButton);

        posPanel.add(buttonBox);

        mainPanel.add(posPanel);
        getContentPane().add(mainPanel);
        pack();

        posTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)){
                    int row = posTable.rowAtPoint(e.getPoint());
                    if (row >= 0 &&row < posTable.getRowCount()){
                        posTable.setRowSelectionInterval(row, row);
                        JPopupMenu actionMenu = createPopupMenu(posTable);
                        actionMenu.show(posTable,e.getX(), e.getY());
                    }
                }
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
                    editSelectedItem(posTable);
                super.mousePressed(e);
            }
        });

        editPosButton.addActionListener(e -> editSelectedItem(posTable));
        addPosButton.addActionListener(e -> createNewPosition());

        setVisible(true);
    }

    private JTable createPosTable() {
        JTable table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Должность");
        ArrayList<Position> posList = getList();
        posList.stream().forEach(e -> model.addRow(e.toObject()));
        table.setModel(model);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        if (!posList.isEmpty()) {
            int defaultSelectedRow = 0;
            table.setRowSelectionInterval(defaultSelectedRow, defaultSelectedRow);
        }
        return table;
    }
    private JPopupMenu createPopupMenu(JTable posTable) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Добавить");
        createItem.addActionListener(e -> createNewPosition());

        JMenuItem editItem = new JMenuItem("Редактировать");
        editItem.addActionListener(e -> editSelectedItem(posTable));

        jPopupMenu.add(createItem);
        jPopupMenu.add(editItem);
        return jPopupMenu;
    }
    private void editSelectedItem (JTable posTable){
        int selectedRow = posTable.getSelectedRow();
        if (selectedRow < 0)
            return;
        editPositionFrame frame = new editPositionFrame(sirchPosition((String) posTable.getValueAt(selectedRow, 0)));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }
    private void createNewPosition(){
        editPositionFrame frame = new editPositionFrame(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshTable();
            }
        });
    }

    private void refreshTable() {
        ArrayList<Position> posList = getList(); // Получаем обновленные данные
        model.setRowCount(0);
        posList.stream().forEach(e -> model.addRow(e.toObject()));
    }
}
