package com.akaichi.task6.threads;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListThread extends Thread {

    private List<Integer> resultList;

    public ListThread(List<Integer> resultList) {
        this.resultList = resultList;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            resultList.add(random.nextInt(100));
        }
    }
}

