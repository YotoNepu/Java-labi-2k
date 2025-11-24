package Laba1;

import java.util.Scanner;

public class Task6 {
    public static int[] BANKNOTE = {1000, 100, 50};
    public static int[] BANKNOTE_AMOUNT = {6, 2, 4};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] banknotes = new int[BANKNOTE.length];
        int all_amount = get_number(scan);

        int[] given_money = {all_amount, 0};
        for (int n = 0; n != BANKNOTE.length; n++) {
            given_money = give_money(n, given_money[0]);
            all_amount = given_money[0];
            banknotes[n] = given_money[1];
        }

        if (all_amount != 0) {System.out.print("В банкомате недостаточно банкнот.");}
        else {
            System.out.println("Будет выдано: ");
            for (int n = 0; n != BANKNOTE.length; n++) {
                System.out.printf("%s-х купюр: %s", BANKNOTE[n], banknotes[n]);
                System.out.println();
            }
        }
    }

    public static int get_number(Scanner in) {
        int number;
        while (true) {
            System.out.print("Введите сколько хотите снять денег: ");
            if (in.hasNextInt()) {number = in.nextInt(); break;}
            in.next();
            System.out.println("Ошибка. Вы ввели не число.");
        }
        return number;
    }

    public static int[] give_money(int money_indx, int remain) {
        int banknote_amount;
        int given_amount = remain / BANKNOTE[money_indx];
        if (given_amount > BANKNOTE_AMOUNT[money_indx]) {
            remain -= BANKNOTE[money_indx] * BANKNOTE_AMOUNT[money_indx];
            banknote_amount = BANKNOTE_AMOUNT[money_indx];
        }
        else {
            remain -= BANKNOTE[money_indx] * given_amount;
            banknote_amount = given_amount;
        }
        return new int[]{remain, banknote_amount};
    }
}
