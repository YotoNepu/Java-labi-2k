package Laba2;

import java.util.Random;

public class Task4 {
    public static void main(String[] args) {
        int[] intArray = createArray();
        System.out.print("Массив из 20 целых чисел: ");
        printArray(intArray);

        int[] filteredArray = filterByThree(intArray);
        if (filteredArray.length > 0) {
            sortDescending(filteredArray);
            System.out.print("Обработанный массив: ");
            printArray(filteredArray);
        } else {System.out.println("Чисел, оканчивающихся на 3, нет.");}
    }

    public static int[] createArray() {
        Random random = new Random();
        int[] newArray = new int[20];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = random.nextInt(1000) + 1;
        }
        return newArray;
    }

    public static void printArray(int[] toPrint) {
        for (int x: toPrint) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }

    public static int[] filterByThree(int[] array) {
        int count = 0;
        for (int number: array) {
            if (number % 10 == 3) {
                count++;
            }
        }
        int[] result = new int[count];

        int index = 0;
        for (int number: array) {
            if (number % 10 == 3) {
                result[index] = number;
                index++;
            }
        }
        return result;
    }

    public static void sortDescending(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}