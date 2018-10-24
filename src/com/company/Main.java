package com.company;

import com.company.Homework_2.PiRandom;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> vector = PiRandom.Random(0, 100);
        for (Integer integer : vector) {
            System.out.println(integer);
        }
    }
}
