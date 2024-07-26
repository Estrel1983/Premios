package org.example.visual.Position;

import org.example.dateOperations.PremiosIncomLowLimitOperations;
import org.example.dateOperations.PremiosPercentOperations;
import org.example.model.position;
import org.example.utils.Utils;
import org.example.visual.SuccessFrame;
import org.example.visual.utils.cancelButtonCreater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static org.example.dateOperations.positionsOperation.savePosition;
import static org.example.visual.ErrorFrame.errorFrame;


public class editPositionFrame extends JFrame{
    public editPositionFrame (position position){
        JPanel mainPanel = new JPanel();
        setName("Редактирование данных о должности");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Box indBox = new Box(BoxLayout.Y_AXIS);
        JLabel nameLabel = new JLabel("Название должности:");
        indBox.add(nameLabel);
        JTextField nameField = new JTextField();
        if (position != null)
            nameField.setText(position.getPositionName());
        nameField.setBorder(new EmptyBorder(10, 10, 10, 10));
        indBox.add(nameField);

        indBox.add(Box.createHorizontalStrut(10));

        indBox.add(Box.createHorizontalStrut(10));
        indBox.add(Box.createVerticalStrut(10));

        JLabel percentLabel = new JLabel("% чаевых");
        indBox.add(percentLabel);
        JTextField percentField =  new JTextField();
        if (position != null)
            percentField.setText(PremiosPercentOperations.getPercentByPosition(position).toString());
        percentField.setBorder(new EmptyBorder(10, 10, 10, 10));
        indBox.add(percentField);

        indBox.add(Box.createHorizontalStrut(10));

        indBox.add(Box.createHorizontalStrut(10));
        indBox.add(Box.createVerticalStrut(10));

        JLabel sumLabel = new JLabel ("От какой суммы чаевые:");
        indBox.add(sumLabel);
        JTextField sumField =  new JTextField();
        if (position != null)
            sumField.setText(PremiosIncomLowLimitOperations.getLimitByPosition(position).toString());
        sumField.setBorder(new EmptyBorder(10, 10, 10, 10));
        indBox.add(sumField);

        indBox.add(Box.createHorizontalStrut(10));

        indBox.add(Box.createHorizontalStrut(10));
        indBox.add(Box.createVerticalStrut(10));

        mainPanel.add(indBox);
        mainPanel.add(Box.createVerticalStrut(10));

        Box buttonBox = Box.createHorizontalBox();

        buttonBox.add(Box.createHorizontalStrut(10));
        JButton saveButton = new JButton("Сохранить");
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(100));
        buttonBox.add(new cancelButtonCreater().createCancelButton());
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.setBorder(new EmptyBorder(10, 10, 10, 10));


        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty()){
                    Optional<Double> percent = Optional.empty();
                    Optional<Integer> lowSum = Optional.empty();
                    if (!percentField.getText().isEmpty() && Utils.checkDouble(percentField.getText()))
                        percent = Optional.of(Double.parseDouble(percentField.getText()));
                    else if (!percentField.getText().isEmpty()) {
                        errorFrame("Поле \"" + percentLabel.getText() + "\" заполнено не корректно");
                        return;
                    }
                    if (!sumField.getText().isEmpty() && Utils.checkInt(sumField.getText()))
                        lowSum = Optional.of(Integer.parseInt(sumField.getText()));
                    else if (!sumField.getText().isEmpty()) {
                        errorFrame("Поле \"" + sumLabel.getText() + "\" заполнено не корректно");
                        return;
                    }
                    if (position != null)
                        savePosition(position, nameField.getText(), percent, lowSum);
                    else {
                        savePosition(nameField.getText(), percent, lowSum);
                    }
                    new SuccessFrame();
                    dispose();
                }
                else {
                    errorFrame("Поле " + nameLabel.getText() + " не заполнено");
                }

            }
        });

        setVisible(true);
    }
}
