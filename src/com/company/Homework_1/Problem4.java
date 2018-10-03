package com.company.Homework_1;

import java.util.Set;

public class Problem4 extends BaseProblem {
    public static void compute() {
        for (double i = 0; i < 860; i++) { // tested, after 862 it stops
            double iter = i / 1000;
            Set<Double> set = Problem3.getFixedPoints(iter);
            if (set.contains(0.5)) {
                System.out.println(set + " r: " + iter);
                System.out.println("derivative at point: " + iter + " is " + round(logisticMapDerivative(iter, iter)));
            }
        }
    }
}
