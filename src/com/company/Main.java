package com.company;

import com.company.Homework_1.Problem2;
import com.company.Homework_1.Problem3;
import com.company.Homework_1.Problem4;

public class Main {
    public static void main(String[] args) {
        // problem 2
        System.out.println("Problem 2");
        int n = 1000;
        Problem2.compute(n, Problem2.r, Problem2.R);
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        Problem2.compute10(n, Problem2.r, Problem2.R);

        // problem 3
        System.out.println("Problem 3");
        System.out.println(Problem3.getFixedPoints(Problem3.R));

        // problem 4
        System.out.println("Problem 4");
        Problem4.compute();
    }
}
