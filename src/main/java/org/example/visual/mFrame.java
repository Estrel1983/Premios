package org.example.visual;

import org.example.visual.Employee.employeeListFrame;
import org.example.visual.Individual.addIndividualFrame;
import org.example.visual.Individual.individualListFrame;
import org.example.visual.Individual.findIndividualFrame;
import org.example.visual.Position.editPositionFrame;
import org.example.visual.Position.positionListFrame;

import javax.swing.*;
import java.awt.*;

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
        menuBar.add(refMenu());
        menuBar.add(createRevenueMenu());
        return  menuBar;
    }
    private JMenu refMenu(){
        JMenu refMenu = new JMenu("Справочники");
        refMenu.add(createIndividMenu());
        refMenu.add(createPositionMenu());
        refMenu.add(createEmployeeMenu());
        return refMenu;
    }
    private JMenu createEmployeeMenu(){
        JMenu emplMenu = new JMenu("Сотрудники");
        emplMenu.add(employeeListMenu());
        return emplMenu;
    }
    private JMenu createIndividMenu() {
        JMenu individMenu = new JMenu("Физ.лица");
        individMenu.add(individListMenu());
        individMenu.add(addEmployeeMenu());
        individMenu.add(findEmployeeMenu());
        return individMenu;
    }
    private JMenu createPositionMenu() {
        JMenu positionMenu = new JMenu("Должности");
        positionMenu.add(positionListMenu());
        positionMenu.add(positionAddMenu());
        return positionMenu;
    }
    private JMenu createRevenueMenu() {
        JMenu revenueMenu = new JMenu("Выручка");
        return revenueMenu;
    }
    private JMenuItem individListMenu(){
        JMenuItem individList = new JMenuItem("Список физ. лиц");
        individList.addActionListener(e -> new individualListFrame());
        return individList;
    }
    private JMenuItem addEmployeeMenu(){
        JMenuItem addEmployee = new JMenuItem("Добавить физ. лицо");
        addEmployee.addActionListener(e -> new addIndividualFrame());
        return addEmployee;
    }
    private JMenuItem findEmployeeMenu(){
        JMenuItem findEmployee= new JMenuItem("Найти физ. лицо");
        findEmployee.addActionListener(e -> new findIndividualFrame());
        return findEmployee;
    }
    private JMenuItem positionListMenu(){
        JMenuItem positionList= new JMenuItem("Список Должностей");
        positionList.addActionListener(e -> new positionListFrame());
        return positionList;
    }
    private JMenuItem positionAddMenu(){
        JMenuItem addPosition= new JMenuItem("Добавить Должность");
        addPosition.addActionListener(e -> new editPositionFrame(null));
        return addPosition;
    }

    private JMenuItem employeeListMenu(){
        JMenuItem employeeList= new JMenuItem("Список сотрудников");
        employeeList.addActionListener(e -> new employeeListFrame());
        return employeeList;
    }
}
