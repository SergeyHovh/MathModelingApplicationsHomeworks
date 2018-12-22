package com.company;

import java.util.Vector;

import static com.company.Homeworks.Homework_2.PiRandom.Random;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> vector = Random(0, 100);
        for (Integer integer : vector) {
            System.out.println(integer);
        }
    }
}
