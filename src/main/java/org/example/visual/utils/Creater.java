package org.example.visual.utils;

import org.example.model.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class Creater {
    public static JPopupMenu createPopUpM (String... menuItems){
        JPopupMenu jPopupMenu = new JPopupMenu();
        for (String item : menuItems){
            jPopupMenu.add(new JMenuItem(item));
        }
        return jPopupMenu;
    }
    public static JTable createTableForClass (TableModel tableModel, ArrayList<TableModel> tableContent){
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < tableModel.getTableSize(); i++)
            model.addColumn(tableModel.getTitles().get(i));
        tableContent.stream().forEach(e -> model.addRow(e.toObject()));
        table.setModel(model);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        if (!tableContent.isEmpty()) {
            int defaultSelectedRow = 0;
            table.setRowSelectionInterval(defaultSelectedRow, defaultSelectedRow);
        }
        return table;
    }
}
