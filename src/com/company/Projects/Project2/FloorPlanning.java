package com.company.Projects.Project2;

import com.company.Projects.BaseFrame;

public class FloorPlanning extends BaseFrame {
    public FloorPlanning(String name, int n, double f, double g) {
        super(name, 500, 400);
        Draw draw = new Draw(n, f, g);
        add(draw);
    }
}
