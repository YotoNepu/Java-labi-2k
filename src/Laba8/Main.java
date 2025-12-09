package Laba8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        task1(scanner);
        task2(scanner);
        task3(scanner);
        task4(scanner);
        task5(scanner);
        task6(scanner);
        task7(scanner);
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

        System.out.printf("Расшифрованный текст: %s%n", result);
    }

    // Вот что я скажу: я по лесу иду (не один), я "шутки шучу" и... деревья пилю?!
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

    public static void task4(Scanner scan) {
        String password = getData(scan, "\nВведите пароль: ");

        if (password.length() < 8 || password.length() > 12) {
            System.out.println("Пароль не подходит: длина должна быть от 8 до 12 символов");
            return;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch) && Character.isLetter(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch) && Character.isLetter(ch)) {
                hasLowerCase = true;
            } else if ("#@$%\"&*<>,.!?;:".indexOf(ch) != -1) {
                hasSpecialChar = true;
            }
        }

        if (hasUpperCase && hasLowerCase && hasSpecialChar) {
            System.out.println("Пароль хороший!");
        } else {
            System.out.println("Пароль плохой по следующим причинам:");
            if (!hasUpperCase) System.out.println("- отсутствуют заглавные латинские буквы");
            if (!hasLowerCase) System.out.println("- отсутствуют прописные латинские буквы");
            if (!hasSpecialChar) System.out.println("- отсутствуют специальные символы или знаки препинания");
        }
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

    public static void task6(Scanner scan) {
        System.out.println("\nВведите текст (несколько предложений), для завершения ввода введите пустую строку:");

        StringBuilder textBuilder = new StringBuilder();
        while (true) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                break;
            }
            textBuilder.append(line).append(" ");
        }
        String text = textBuilder.toString().trim();
        String[] sentences = text.split("[.!?]\\s+");

        String keyword = getData(scan, "Введите ключевое слово: ");
        System.out.println("Предложения с ключевым словом:");
        for (String sentence: sentences) {
            if (sentence.matches(".*(^|[\\s\"()-])" + keyword + "($|[\\s,\"):;-]).*")) {
                System.out.println(sentence);
            }
        }
    }

    // Даров, ты вчера ел кукурузу, или ты просто не в теме
    public static void task7(Scanner scan) {
        String telegram = getData(scan, "\nВведите текст телеграммы: ");
        String comma;
        char firstSymbol = telegram.charAt(0);

        if ((firstSymbol >= 'А' && firstSymbol <= 'я') || firstSymbol == 'Ё' || firstSymbol == 'ё') {
            comma = " зпт";
        }
        else {comma = " comma";}
        telegram = telegram.replaceAll(",", comma);

        String[] words = telegram.split(" ");
        StringBuilder result = new StringBuilder();
        int money = 0;
        for (String word: words) {
            if (word.length() > 2) {
                result.append(" ").append(word);
                money += 10;
            }
        }

        System.out.printf("Телеграмма \"%s\" будет стоить - %d руб.", result.deleteCharAt(0), money);
    }
}
