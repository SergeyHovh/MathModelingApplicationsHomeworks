package com.company.Homework_1;

import java.util.Set;

public class Problem4 extends BaseProblem {
    public static void foo() {
        for (double i = 0; i < 860; i++) { // tested, after 862 it stops
            Set<Double> set = Problem3.getFixedPoints(i / 1000);
            if (set.contains(0.5)) {
                System.out.println(set + " r: " + i / 1000);
            }
        }
    }
}
