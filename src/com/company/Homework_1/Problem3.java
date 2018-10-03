package com.company.Homework_1;

import java.util.Set;
import java.util.TreeSet;

public class Problem3 extends BaseProblem {
    public static final double R = 0.785;
    public static final double ID_NUMBER1 = 4;
    public static final double ID_NUMBER2 = 2;
    public static final double D1 = ID_NUMBER1 / 10;
    public static final double D2 = ID_NUMBER2 / 10;

    public static double nestedLogisticMap(double x_0, double r) {
        return logisticMap(logisticMap(x_0, r), r);
    }

    public static Set<Double> getFixedPoints(double r) {
        Set<Double> result = new TreeSet<>();
        for (double i = 1; i < 100; i++) {
            result.add(round(Problem3.getFixedPoint(i / 1000, r)));
        }
        return result;
    }

    static double getFixedPoint(double x_0, double r) {
        double point = x_0;
        double limit = 0.0000001;
        double res = nestedLogisticMap(x_0, r);
        while (true) {
//            System.out.println(point + " " + res);
            double difference = point - res;
            if (difference < limit && difference > -limit) {
                return point;
            } else {
                point = nestedLogisticMap(point, r);
                res = nestedLogisticMap(res, r);
            }
        }
    }
}
