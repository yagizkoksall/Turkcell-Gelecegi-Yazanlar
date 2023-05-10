package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(3);

        System.out.println(findNumber(2, list));
        Scanner scanner;

        try {
            scanner = new Scanner(new File("C:\\Users\\yagiz\\Desktop\\denemetxt.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            integers.add(scanner.nextInt());
        }

        findMaxDistance(integers);

    }


    public static void findMaxDistance(List<Integer> integers) {
        int size = integers.size();
        double localDistance = -1;

        for (int i = 0; i < size; i += 2) {
            for (int j = i + 2; j < size; j += 2) {
                double distance = Math.sqrt(Math.pow((integers.get(i) - integers.get(j)), 2) +
                        Math.pow((integers.get(i + 1) - integers.get(j + 1)), 2));

                if (distance > localDistance) {
                    localDistance = distance;
                }
            }
        }

        System.out.println(localDistance);
    }


    public static int findNumber(int target, List<Integer> list) {
        int i = 0;
        int size = list.size();
        int counter = 0;
        while (i < size) {

            if (target == list.get(i)) {
                counter++;
                i += 2;
            } else {
                i += Math.abs(target - list.get(i));
            }

        }

        return counter;
    }

}