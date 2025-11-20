package Laba1;

import java.util.Scanner;

public class Task1 {
    public static final String[] ANIMALS = {"Крысы", "Коровы", "Тигра", "Зайца", "Дракона", "Змеи", "Лошади", "Овцы",
            "Обезьяны", "Курицы", "Собаки", "Свиньи"};

    public static void main(String[] args) {
        int year = get_year();
        System.out.printf("%d г. - год %s", year, ANIMALS[define_year(year)]);
    }

    public static int get_year() {
        int year_in;
        while (true) {
            System.out.print("Напишите какой-нибудь год: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {year_in = scan.nextInt(); break;}
            else {System.out.println("Ошибка. Вы ввели не год.");}
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
