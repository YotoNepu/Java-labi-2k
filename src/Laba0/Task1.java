package Laba0;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        float cost;
        cost = get_cost();
        System.out.printf("Цена вашего товара со скидкой 13%%: %.2f руб.", discount_cost(cost));
    }

    public static float discount_cost(float c) {
        return c - c * 0.13f;
    }

    public static float get_cost() {
        float price;
        while (true) {
            System.out.print("Пожалуйста, введите цену товара: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextFloat()) {price = scan.nextFloat(); break;}
            else {System.out.println("Ошибка. Вы ввели не число.");}
        }
        return price;
    }
}
