package Laba2;

import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        int[] intArray = createArray();
        System.out.print("Массив из 20 целых чисел: ");
        printArray(intArray);

        int[] countAndSum = countAndSumSymmetric(intArray);

        System.out.printf("Кол-во трехзначных симметричных чисел: %d%n", countAndSum[0]);
        System.out.printf("Сумма трехзначных симметричных чисел: %d%n", countAndSum[1]);
    }

    public static int[] createArray() {
        Random random = new Random();
        int[] newArray = new int[20];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = random.nextInt(1001);
        }
        return newArray;
    }

    public static void printArray(int[] toPrint){
        for(int x: toPrint)
            System.out.printf("%d ",x);
        System.out.println();
    }

    public static int[] countAndSumSymmetric(int[] array) {
        int count = 0;
        int sum = 0;
        for (int number: array) {
            if (isSymmetric(number)) {
                count++;
                sum += number;
            }
        }
        return new int[]{count, sum};
    }

    public static boolean isSymmetric(int number) {
        if (number < 100 || number > 999) {
            return false;
        }

        int firstDigit = number / 100;
        int lastDigit = number % 10;

        return firstDigit == lastDigit;
    }
}