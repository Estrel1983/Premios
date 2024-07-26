package org.example.visual;

import javax.swing.*;

public class ErrorFrame {
    public static void errorFrame(String message){
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
