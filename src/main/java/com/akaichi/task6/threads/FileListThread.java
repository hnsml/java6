package com.akaichi.task6.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileListThread extends Thread{
    private File file;
    private List<Integer> resultList;

    public FileListThread(File file) {
        this.file = file;
        this.resultList = new ArrayList<>();
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            resultList.add(random.nextInt(10));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Integer number : resultList) {
                writer.write(number + " ");
            }
            writer.flush();
            System.out.println("Дані було успішно записано у файл: " + file.getName());
        } catch (IOException e) {
            System.out.println("Виникла помилка при записі до файлу: " + e.getMessage());
        }
    }
}
