package Laba1;

import java.util.Scanner;

public class Task5 {
    public static int MAX_LENGTH = 6;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int min_number = get_number(scan, 0);
        int max_number = get_number(scan, 1);
        System.out.printf("Всего %d счастливых билетов!", count_tickets(min_number, max_number));
    }

    public static int get_number(Scanner in, int is_max) {
        String[] min_max = {"минимальный", "максимальный"};
        int number;
        while (true) {
            System.out.printf("Введите %s номер шестизначного билета через пробел: ", min_max[is_max]);
            if (in.hasNextInt()) {
                number = in.nextInt();
                if (String.valueOf(number).length() <= MAX_LENGTH) {break;}
            }
            in.next();
            System.out.println("Ошибка. Вы ввели не номер, или ввели его неверно.");
        }

        return number;
    }

    public static int count_tickets(int min_ticket, int max_ticket) {
        int count = 0;
        String ticket;
        for (int n = min_ticket; n <= max_ticket; n++) {
            ticket = String.format("%0" + MAX_LENGTH + "d", n);
            if ((int) ticket.charAt(0) + (int) ticket.charAt(1) + (int) ticket.charAt(2) ==
                    (int) ticket.charAt(3) + (int) ticket.charAt(4) + (int) ticket.charAt(5)) {count++;}
        }
        return count;
    }
}
