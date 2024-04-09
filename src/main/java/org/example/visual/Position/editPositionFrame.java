package org.example.visual.Position;

import org.example.model.Position;
import org.example.visual.SuccessFrame;
import org.example.visual.utils.Creater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.dateOperations.positionsOperation.savePosition;


public class editPositionFrame extends JFrame{
    public editPositionFrame (Position position){
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
        mainPanel.add(indBox);
        mainPanel.add(Box.createVerticalStrut(10));

        Box buttonBox = Creater.createSaveCancelBox();
        mainPanel.add(buttonBox);

        getContentPane().add(mainPanel);
        pack();

        JButton cancelButton = (JButton) buttonBox.getComponent(3);
        JButton saveButton = (JButton) buttonBox.getComponent(1);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(cancelButton);
                window.dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (position != null)
                    savePosition(position, nameField.getText());
                else
                    savePosition(nameField.getText());
                new SuccessFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
