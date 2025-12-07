package Laba8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        task1(scanner);
        task2(scanner);
        task3(scanner);
        task5(scanner);
    }

    public static String getData(Scanner sc, String msg) {
        String inputText;
        while (true) {
            System.out.print(msg);
            inputText = sc.nextLine();
            if (!inputText.isEmpty()) {
                break;
            }
            System.out.println("Ошибка. Введите строку.");
        }
        return inputText;
    }

    // JaVaПроГрамМиРоваНиЕ
    public static void task1(Scanner scan) {
        String txt = getData(scan, "\nВведите слово: ");

        String result = txt.replaceAll("[A-ZА-Я]", "");

        System.out.printf("Результат: %s%n", result);
    }

    // yM eman si olleH !dlroW
    public static void task2(Scanner scan) {
        String txt = getData(scan, "\nВведите зашифрованный текст: ");

        String[] words = txt.split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String reversedWord = new StringBuilder(words[i]).reverse().toString();

            result.append(reversedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        System.out.printf("Расшифрованный текст: %s%n", result.toString());
    }

    // Вот что я скажу: я по лесу иду (не один), я "шутки шучу" и... деревья пилю!?
    public static void task3(Scanner scan) {
        String txt = getData(scan, "\nВведите предложение: ");

        txt = txt.replaceAll("[,.!?:()\"]+", "");
        String[] words = txt.split("\\s+");

        int count = 0;
        for (String word : words) {
            if (word.length() == 3) {
                count++;
            }
        }

        System.out.printf("Кол-во трёхбуквенных слов: %d%n", count);
    }

    // Я собрал за сегодня 10 закладок из 15 книг в библиотеке за 30 минут.
    public static void task5(Scanner scan) {
        int sum = 0;
        String txt = getData(scan, "\nВведите текст с целыми числами: ");

        String[] numbers = txt.split("[^0-9]+");

        for (String n : numbers) {
            if (!n.isEmpty()) {
                sum += Integer.parseInt(n);
            }
        }

        System.out.printf("Сумма чисел в предложении: %d%n", sum);
    }
}
