package org.example.visual.utils;

import org.example.model.MyTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class Creater {
    private Creater(){}
    public static JPopupMenu createPopUpM (String... menuItems){
        JPopupMenu jPopupMenu = new JPopupMenu();
        for (String item : menuItems){
            jPopupMenu.add(new JMenuItem(item));
        }
        return jPopupMenu;
    }
    public static JTable createTableForClass (MyTableModel myTableModel, ArrayList<MyTableModel> tableContent){
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < myTableModel.getTableSize(); i++)
            model.addColumn(myTableModel.getTitles().get(i));
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
    public static Box createSaveCancelBox(){
        Box buttonBox = Box.createHorizontalBox();

        buttonBox.add(Box.createHorizontalStrut(10));
        JButton saveButton = new JButton("Сохранить");
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(100));
        JButton cancelButton = new JButton("Отмена");
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        return buttonBox;
    }
}
