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
import java.util.List;

import static org.example.dateOperations.IndividualsOperations.sirchIndividual;

public class truncatedIndividualFrame extends JFrame {
    private DefaultTableModel model;
    public truncatedIndividualFrame (List<Individual> individualList){
        JPanel mainPanel = new JPanel();
        setName("Список найденых лиц");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Box individPanel = new Box(BoxLayout.Y_AXIS);
        JTable individTable = createIndividualsTable(individualList);
        individPanel.add(new JScrollPane(individTable));

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setBorder(new EmptyBorder(10,10,10,10));

        JButton editIndividButton = new JButton("Редактировать");
        buttonBox.add(editIndividButton);

        individPanel.add(buttonBox);

        mainPanel.add(individPanel);
        getContentPane().add(mainPanel);
        pack();

        editIndividButton.addActionListener(e -> editSelectedItem(individTable));
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
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() ==2)
                    editSelectedItem(individTable);
                super.mousePressed(e);
            }
        });

        setVisible(true);
    }

    private JTable createIndividualsTable (List<Individual> indList){
        JTable table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("Дата рождения");
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
    private void editSelectedItem (JTable individTable){
        int selectedRow = individTable.getSelectedRow();
        if (selectedRow < 0)
            return;
        editIndividualFrame frame = new editIndividualFrame(sirchIndividual((String) individTable.getValueAt(selectedRow, 0),
                (String) individTable.getValueAt(selectedRow, 1)));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                truncatedIndividualFrame.this.dispose();
            }
        });
    }
    private JPopupMenu createPopupMenu(JTable individTable) {
        JPopupMenu jPopupMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Редактировать");
        editItem.addActionListener(e -> editSelectedItem(individTable));
        jPopupMenu.add(editItem);
        return jPopupMenu;
    }
}
