package com.akaichi.task6.threads;

import java.util.List;

public class AverageThread extends Thread {

    private List<Integer> list;
    private double average;

    public AverageThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int number : list) {
            sum += number;
        }
        if (!list.isEmpty()) {
            average = (double) sum / list.size();
        }
    }

    public double getAverage() {
        return average;
    }
}