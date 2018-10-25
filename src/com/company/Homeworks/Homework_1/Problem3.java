package com.company.Homeworks.Homework_1;

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

    public static Set<Double> getFixedPoints(double r, Function function) {
        Set<Double> result = new TreeSet<>();
        for (double i = 1; i < 100; i++) {
            for (double v : getFixedPoint(i / 1000, r, function)) {
                result.add(round(v));
            }
        }
        return result;
    }

    public static double[] getFixedPoint(double x_0, double r, Function function) {
        double result[] = null;
        int period = -1;
        int iter = 0;
        int chaos = 0;
        double val = -1;
        double point = x_0;
        while (true) {
            if (iter == period - 1) {
                return result;
            } else {
                if (iter == 128) { // picking random value
                    val = point;
                    iter = 0;
                    ++chaos;
                }
                ++iter;
                if (chaos == 10) { // if iterations are over 3000 data set is considered chaotic
                    return new double[]{Double.MIN_VALUE};
                }

                /*System.out.print("point = " + point);
                System.out.print(" val = " + val);
                System.out.print(" iter = " + iter);
                System.out.println(" chaos = " + chaos);*/

                point = round(function.action(point, r), 10); // working method
                if (val == point) { // determining the period
                    period = iter;
                    result = new double[period];
                    iter = 0;
                }
                if (iter < period && result != null) { // init the array
                    result[iter] = point;
                }
            }
        }
    }
}
