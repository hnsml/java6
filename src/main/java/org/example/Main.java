package org.example;

import com.akaichi.task6.threads.AverageThread;
import com.akaichi.task6.threads.FileFaktorialThread;
import com.akaichi.task6.threads.FileListThread;
import com.akaichi.task6.threads.FileSimpleNumbersThread;
import com.akaichi.task6.threads.ListThread;
import com.akaichi.task6.threads.SumThread;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        List<Integer> resultList = new ArrayList<>();
        ListThread listThread = new ListThread(resultList);

        listThread.start();

        try {
            listThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int number : resultList) {
            System.out.print(number + " ");
        }

        SumThread sumThread = new SumThread(resultList);
        sumThread.start();
        AverageThread averageThread = new AverageThread(resultList);
        averageThread.start();

        try {
            sumThread.join();
            averageThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sum = sumThread.getSum();
        System.out.println("\nСума: " + sum);
        double average = averageThread.getAverage();
        System.out.println("\nСереднє арифметичне: " + average);

        File file;
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Введіть назву нового текстового файлу (.txt): ");
            String fileName = scanner.nextLine();

            if (!fileName.toLowerCase().endsWith(".txt")) {
                fileName += ".txt";
            }

            file = new File(fileName);

            try {
                if (file.createNewFile()) {
                    System.out.println("Файл " + fileName + " створено успішно.");
                } else {
                    System.out.println("Файл " + fileName + " вже існує.");
                }
            } catch (IOException e) {
                System.out.println("Сталася помилка при створенні файлу: " + e.getMessage());
            }
        }

        FileListThread fileListThread = new FileListThread(file);

        fileListThread.start();

        try {
            fileListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        FileSimpleNumbersThread fileSimpleNumbersThread = new FileSimpleNumbersThread(file);
        fileSimpleNumbersThread.start();
        FileFaktorialThread faktorialThread = new FileFaktorialThread(file);
        faktorialThread.start();

        try {
            fileSimpleNumbersThread.join();
            faktorialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}