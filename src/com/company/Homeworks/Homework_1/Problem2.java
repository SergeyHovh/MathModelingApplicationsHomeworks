package com.company.Homeworks.Homework_1;

import java.util.Vector;

public class Problem2 extends BaseProblem {
    private static final double ID_NUMBER = 4;
    private static final double x_0 = ID_NUMBER / 10;
    public static double r = 0.2;
    public static double R = 0.92;
    private static double dR = 0.01;

    private static double getNthElement(int n, double r) {
        double Xn = x_0;
        for (int i = 0; i < n; ++i) {
            Xn = logisticMap(Xn, r);
        }
        return Xn;
    }

    private static double getNthElement10(int n, double r) {
        double Xn = x_0;
        for (int i = 0; i < n; ++i) {
            Xn = logisticMap10(Xn, r);
        }
        return Xn;
    }

    public static Vector<Double> compute(int n, double r, double R) {
        Vector<Double> result = new Vector<>();
        while (r <= R) {
            double i = getNthElement(n, r);
            result.add(i);
            r += dR;
            System.out.println("r = [" + r + "], Nth element = [" + i + "]");
        }
        return result;
    }

    public static Vector<Double> compute10(int n, double r, double R) {
        Vector<Double> result = new Vector<>();
        while (r <= R) {
            double i = getNthElement10(n, r);
            result.add(i);
            r += dR;
            System.out.println("r = [" + r + "], Nth element = [" + i + "]");
        }
        return result;
    }
}
