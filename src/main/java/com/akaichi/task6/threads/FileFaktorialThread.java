package com.akaichi.task6.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFaktorialThread extends Thread{
    private File fileName ;
    private List<Integer> faktorialsList;

    public FileFaktorialThread(File fileName) {
        this.fileName = fileName;
        this.faktorialsList = new ArrayList<>();
    }

    @Override
    public void run(){
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(fileName)));

            for (String line : lines) {
                String[] numbers = line.split("\\s+");

                for (String numberStr : numbers) {
                    try {
                        int number = Integer.parseInt(numberStr);
                        int faktorial = calculateFactorial(number);
                        faktorialsList.add(faktorial);

                    } catch (NumberFormatException e) {
                        System.err.println("Помилка при перетворенні рядка в число: " + e.getMessage());
                    }
                }
            }
            System.out.println("Факторіали з файлу " + fileName + ": " + faktorialsList);

            writeSimpleNumbersToFile("faktorials.txt", faktorialsList);
        } catch (IOException e) {
            System.err.println("Виникла помилка при зчитуванні файлу: " + e.getMessage());
        }
    }

    private void writeSimpleNumbersToFile(String fileName, List<Integer> numbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer number : numbers) {
                writer.write(number + " ");
            }
            writer.flush();
            System.out.println("Прості числа було успішно записано у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Виникла помилка при записі простих чисел до файлу: " + e.getMessage());
        }
    }

    public static int calculateFactorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

}
