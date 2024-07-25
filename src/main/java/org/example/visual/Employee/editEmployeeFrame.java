package org.example.visual.Employee;

import com.toedter.calendar.JDateChooser;
import org.example.dateOperations.IndividualsOperations;
import org.example.dateOperations.positionsOperation;
import org.example.exceptions.NonexistingEmployeeException;
import org.example.model.Employe;
import org.example.model.Individual;
import org.example.model.position;
import org.example.utils.Constants;
import org.example.visual.SuccessFrame;
import org.example.visual.utils.cancelButtonCreater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import static org.example.dateOperations.employeeOperations.saveEmployee;
import static org.example.visual.ErrorFrame.errorFrame;

public class editEmployeeFrame extends JFrame {
    public editEmployeeFrame(Employe employe) {
        JPanel mainPanel = new JPanel();
        setName("Редактирование информации о сотруднике");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Box empBox = new Box(BoxLayout.Y_AXIS);
        empBox.add(Box.createVerticalStrut(10));
        JLabel nameLabel = new JLabel("Имя сотрудника:");
        empBox.add(nameLabel);
        empBox.add(Box.createVerticalStrut(10));
        JComboBox<Individual> individualComboBox = addIndividualUnemployedComboBox(employe);
        empBox.add(individualComboBox);
        Checkbox useAlreadyEmployedCheckBox = new Checkbox("   Показывать уже трудоустроеных");
        useAlreadyEmployedCheckBox.addItemListener(alreadyEmployedListener(employe, individualComboBox, empBox));
        empBox.add(useAlreadyEmployedCheckBox);
        empBox.add(Box.createHorizontalStrut(10));
        empBox.add(Box.createVerticalStrut(10));


        JLabel positionLabel = new JLabel("Должность:");
        empBox.add(positionLabel);
        empBox.add(Box.createVerticalStrut(10));
        empBox.add(Box.createHorizontalStrut(10));
        JComboBox<position> positionJComboBox = addPositionComboBox(employe);
        empBox.add(positionJComboBox);
        empBox.add(Box.createHorizontalStrut(10));
        empBox.add(Box.createVerticalStrut(10));

        JLabel startDateLabel = new JLabel("Дата приема на работу:");
        empBox.add(startDateLabel);
        empBox.add(Box.createVerticalStrut(10));
        empBox.add(Box.createHorizontalStrut(10));
        JDateChooser startDateChooser = addStartDateChooser(employe);
        empBox.add(startDateChooser);
        empBox.add(Box.createHorizontalStrut(10));
        empBox.add(Box.createVerticalStrut(10));

        JLabel endDateLabel = new JLabel("Дата увольнения:");
        empBox.add(endDateLabel);
        empBox.add(Box.createVerticalStrut(10));
        empBox.add(Box.createHorizontalStrut(10));
        JDateChooser endDateChooser = addEndDateChooser(employe);
        empBox.add(endDateChooser);
        empBox.add(Box.createHorizontalStrut(10));
        empBox.add(Box.createVerticalStrut(10));

        mainPanel.add(empBox);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalStrut(10));

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(saveButtonListener(individualComboBox, positionJComboBox,
                startDateChooser, endDateChooser));
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(100));

        buttonBox.add(new cancelButtonCreater().createCancelButton());
        buttonBox.add(Box.createHorizontalStrut(10));

        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();
    }
    private ActionListener saveButtonListener(JComboBox<Individual> ind, JComboBox<position> position,
                                              JDateChooser startDate, JDateChooser endDate){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ind.getSelectedItem() == null || position.getSelectedItem() == null || startDate.getDate() == null){
                    errorFrame("Одно из полей не заполнено");
                    return;
                }
                if (endDate.getDate() != null && endDate.getDate().before(startDate.getDate())){
                    errorFrame("Дата приема на работу установлена позже даты увольнения");
                    return;
                }
                try {
                    saveEmployee(((Individual) ind.getSelectedItem()).getName(), ((position) position.getSelectedItem()).getPositionName() ,
                            startDate.getDate(), Optional.ofNullable(endDate.getDate()));
                    new SuccessFrame();
                    dispose();
                } catch (NonexistingEmployeeException ex){
                    errorFrame("Заполнена дата увольнения, однако работник с таким именем и должностью на работу не принимался");
                }
            }
        };
    }
    private ItemListener alreadyEmployedListener (Employe employe, JComboBox<Individual> individualComboBox, Box empBox){
        final JComboBox<Individual>[] comboBoxWrapper = new JComboBox[]{individualComboBox};
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBoxWrapper[0].removeAllItems();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ArrayList<Individual> individuals = IndividualsOperations.getList();
                    for (Individual individual : individuals)
                        comboBoxWrapper[0].addItem(individual);
                    comboBoxWrapper[0].setSelectedItem(null);
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    ArrayList<Individual> individuals = new ArrayList<>(IndividualsOperations.searchUnemployed());
                    for (Individual individual : individuals)
                        comboBoxWrapper[0].addItem(individual);
                    comboBoxWrapper[0].setSelectedItem(individuals.stream().filter(individual -> employe.getName().equals(individual.getName())).findFirst());
                }
                empBox.revalidate();
                empBox.repaint();
            }
        };
    }

    private JComboBox<Individual> addIndividualUnemployedComboBox(Employe employe) {
        if (employe != null) {
            ArrayList<Individual> individuals = IndividualsOperations.getList();
            JComboBox<Individual> individualJComboBox = new JComboBox<>(individuals.toArray(new Individual[0]));
            individualJComboBox.setSelectedItem(individuals.stream().filter(individual -> employe.getName().equals(individual.getName())).findFirst());
            individualJComboBox.setBorder(new EmptyBorder(10, 10, 10, 10));
            return individualJComboBox;
        } else {
            JComboBox<Individual> individualJComboBox = new JComboBox<>(IndividualsOperations.searchUnemployed().toArray(new Individual[0]));
            individualJComboBox.setSelectedItem(null);
            individualJComboBox.setBorder(new EmptyBorder(10, 10, 10, 10));
            return individualJComboBox;
        }
    }


    private JComboBox<position> addPositionComboBox(Employe employe) {

        ArrayList<position> positions = positionsOperation.getList();
        JComboBox<position> positionJComboBox = new JComboBox<>(positions.toArray(new position[0]));
        if (employe != null)
            positionJComboBox.setSelectedItem(positions.stream().filter(position -> employe.getPositionName().equals(position.getPositionName())));
        else
            positionJComboBox.setSelectedItem(null);
        return positionJComboBox;
    }

    private JDateChooser addStartDateChooser(Employe employe) {
        JDateChooser startDate = new JDateChooser();
        startDate.setLocale(new Locale("ru", "RU"));
        startDate.setDateFormatString(Constants.DATE_FORMAT_FOR_DATA_FIELD);
        if (employe != null && employe.getStartDate() != null)
            startDate.setDate(employe.getStartDate());
        return startDate;
    }

    private JDateChooser addEndDateChooser(Employe employe) {
        JDateChooser endDate = new JDateChooser();
        endDate.setLocale(new Locale("ru", "RU"));
        endDate.setDateFormatString(Constants.DATE_FORMAT_FOR_DATA_FIELD);
        if (employe != null && employe.getEndDate() != null)
            endDate.setDate(employe.getEndDate());
        return endDate;
    }
}
