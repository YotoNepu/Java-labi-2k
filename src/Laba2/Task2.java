package Laba2;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        int[] intArray = createArray();
        System.out.print("Массив из 20 целых чисел: ");
        printArray(intArray);

        int maxEven = findMaxEven(intArray);

        if (maxEven != -1) {System.out.printf("Максимальное четное число в массиве: %d", maxEven);}
        else {System.out.print("В массиве нет четных чисел!");}
    }

    public static int[] createArray() {
        Random random = new Random();
        int[] newArray = new int[20];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = random.nextInt(1001);
        }
        return newArray;
    }

    public static void printArray(int[] toPrint) {
        for (int x: toPrint) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }

    public static int findMaxEven(int[] array) {
        int maxEven = -1;
        for (int number: array) {
            if (number % 2 == 0 && number > maxEven) {
                    maxEven = number;
            }
        }
        return maxEven;
    }
}