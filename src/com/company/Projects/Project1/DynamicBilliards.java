package com.company.Projects.Project1;


import javax.swing.*;

public class DynamicBilliards extends BaseFrame {
    DynamicBilliards(String name) {
        super(name, 800, 600);
        JPanel billiardsTable = new DrawTable(3, 4);
        setContentPane(billiardsTable);
    }
}

