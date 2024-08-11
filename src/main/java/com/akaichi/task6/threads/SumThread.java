package com.akaichi.task6.threads;

import java.util.List;


public class SumThread extends Thread {

    private List<Integer> list;
    private int sum;

    public SumThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
       for (int i = 0; i < list.size(); i++) {
           sum += list.get(i);
       }
    }

    public int getSum() {
        return sum;
    }
}
