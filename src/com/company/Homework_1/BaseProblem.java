package com.company.Homework_1;

public class BaseProblem {
    public static double logisticMap(double x_0, double R) {
        return round(4 * R * x_0 * (1 - x_0), 10);
    }

    public static double logisticMap10(double x_0, double R) {
        return logisticMap(x_0, R) / 10 * 10;
    }

    public static double logisticMapDerivative(double x_0, double R) {
        return 4 * R * (1 - 2 * x_0);
    }

    public static double round(double num) {
        return round(num, 3);
    }

    public static double round(double num, int dec) {
        return Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
    }
}
