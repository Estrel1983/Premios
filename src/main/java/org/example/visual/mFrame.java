package org.example.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mFrame extends JFrame {
    public mFrame() {
        setTitle("Расчет чаевых");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setJMenuBar(createMainMenuBar());
        getContentPane().add(mainPanel);
        JPanel workPanel = new JPanel(new GridBagLayout());
        getContentPane().add(mainPanel);
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMainMenuBar (){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createEmployeeMenu());
        menuBar.add(createPositionMenu());
        menuBar.add(createRevenueMenu());
        return  menuBar;
    }
    private JMenu createEmployeeMenu() {
        JMenu employeeMenu = new JMenu("Сотрудники");
        employeeMenu.add(employeeListMenu());
        employeeMenu.add(addEmployeeMenu());
        employeeMenu.add(findEmployeeMenu());
        return employeeMenu;
    }
    private JMenu createPositionMenu() {
        JMenu positionMenu = new JMenu("Должности");
        return positionMenu;
    }
    private JMenu createRevenueMenu() {
        JMenu revenueMenu = new JMenu("Выручка");
        return revenueMenu;
    }
    private JMenuItem employeeListMenu(){
        JMenuItem employeeList = new JMenuItem("Список сотрудников");
        employeeList.addActionListener(e -> new employeeListFrame());
        return employeeList;
    }
    private JMenuItem addEmployeeMenu(){
        JMenuItem addEmployee = new JMenuItem("Добавить сотрудника");
        addEmployee.addActionListener(e -> new addEmployeeFrame());
        return addEmployee;
    }
    private JMenuItem findEmployeeMenu(){
        JMenuItem findEmployee= new JMenuItem("Найти сотрудника");
        findEmployee.addActionListener(e -> new findEmployeeFrame());
        return findEmployee;
    }
}
