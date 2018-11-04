package com.company.Projects.Project1;

import javax.swing.*;

public class BaseFrame extends JFrame {
    BaseFrame(String name, int w, int h) {
        super(name);
        setVisible(true);
//        setLocationRelativeTo(null);
        setSize(w, h);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
