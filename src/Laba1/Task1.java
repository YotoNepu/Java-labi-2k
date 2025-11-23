package Laba1;

import java.util.Scanner;

public class Task1 {
    public static final String[] ANIMALS = {"Крысы", "Коровы", "Тигра", "Зайца", "Дракона", "Змеи", "Лошади", "Овцы",
            "Обезьяны", "Курицы", "Собаки", "Свиньи"};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int year = get_year(scan);
        System.out.printf("%d г. - год %s", year, ANIMALS[define_year(year)]);
    }

    public static int get_year(Scanner in) {
        int year_in;
        while (true) {
            System.out.print("Напишите какой-нибудь год: ");
            if (in.hasNextInt()) {year_in = in.nextInt(); break;}
            System.out.println("Ошибка. Вы ввели не год.");
            in.next();
        }
        return year_in;
    }

    public static int define_year(int y) {
        int cycle = (y - 1996) % 12;
        if (cycle < 0) {
            cycle += 12;
        }
        return cycle;
    }
}
