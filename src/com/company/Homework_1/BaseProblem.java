package com.company.Homework_1;

public class BaseProblem {
    static double logisticMap(double x_0, double R) {
        return 4 * R * x_0 * (1 - x_0);
    }

    static double logisticMap10(double x_0, double R) {
        return logisticMap(x_0, R) / 10 * 10;
    }
}
