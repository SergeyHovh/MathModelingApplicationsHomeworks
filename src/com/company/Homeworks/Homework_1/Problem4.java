package com.company.Homeworks.Homework_1;

import java.util.Set;

public class Problem4 extends BaseProblem {
    public static void compute(Function function) {
        for (double i = 0; i < 1000; i++) {
            double iter = i / 1000;
            Set<Double> set = Problem3.getFixedPoints(iter, function);
            if (set.contains(0.5)) {
                if (set.size() == 2) {
                    System.out.println(set + " r: " + iter);
                    for (Double aDouble : set) {
                        System.out.println("derivative at point: " + aDouble + " is " + round(logisticMapDerivative(aDouble, aDouble)));
                    }
                }
            }
        }
    }
}
