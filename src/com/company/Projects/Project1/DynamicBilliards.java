package com.company.Projects.Project1;


import java.awt.*;

class DynamicBilliards extends BaseFrame {
    DynamicBilliards(String name) {
        super(name, 500, 400);
        setLayout(new GridLayout(2, 2));
        DrawTable table1 = new DrawTable(0);
        DrawTable table2 = new DrawTable(1);
        DrawTable table3 = new DrawTable(2);
        DrawTable table4 = new DrawTable(3);

        // reversibility test
        int n = 100;
        table1.setReverse(n);
        table2.setReverse(n);

//        table3.setN(50);
//        table3.addHole(true);
        table3.addParticles(200, 5, false);
        table4.addParticles(200, 5, true);

        add(table1);
        add(table3);
        add(table2);
        add(table4);
    }
}

