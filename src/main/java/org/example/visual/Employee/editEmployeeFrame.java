package org.example.visual.Employee;

import com.toedter.calendar.JDateChooser;
import org.example.dateOperations.IndividualsOperations;
import org.example.dateOperations.positionsOperation;
import org.example.model.Employe;
import org.example.model.Individual;
import org.example.model.position;
import org.example.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Locale;

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
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(100));

        JButton cancelButton = new JButton("Отмена");
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createHorizontalStrut(10));

        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();
    }
    private ItemListener alreadyEmployedListener (Employe employe, JComboBox<Individual> individualComboBox, Box empBox){
        final JComboBox<Individual>[] comboBoxWrapper = new JComboBox[]{individualComboBox};
        //test
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
                    comboBoxWrapper[0].setSelectedItem(null);
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
