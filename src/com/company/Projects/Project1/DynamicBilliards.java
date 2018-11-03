package com.company.Projects.Project1;


class DynamicBilliards extends BaseFrame {
    DynamicBilliards(String name) {
        super(name, 600, 600);
//        setLayout(new GridLayout(2, 2));
//        add(new DrawTable(0, 3, 500, 5, true));
//        add(new DrawTable(1, 3, 500, 5, true));
        add(new DrawTable(2, 3, 2, 10, true));
//        add(new DrawTable(3, 3, 500, 5, true));
    }
}

