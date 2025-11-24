package Laba0;

import java.util.Scanner;

public class Task2 {
    public static final String[] FIRMS = {"Витражи","Стекольщик","Мастер"};
    public static final int[][] PRICES = {{420, 75}, {440, 65}, {470, 55}};

    public static void main(String[] args) {
        float amount = get_amount();
        float first_firm = count_cost(amount, 1, PRICES);
        float second_firm = count_cost(amount, 2, PRICES);
        float third_firm = count_cost(amount, 3, PRICES);
        float[] best_firm = compare_cost(first_firm, second_firm, third_firm);
        System.out.printf("Самый дешёвый заказ будет у фирмы %s со стоимостью: %.2f", FIRMS[(int)best_firm[0] - 1], best_firm[1]);
    }

    public static float get_amount() {
        float area;
        while (true) {
            System.out.print("Пожалуйста, введите сколько вам нужно стекла (м^2): ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextFloat()) {area = scan.nextFloat(); break;}
            else {System.out.println("Ошибка. Вы ввели не число.");}
        }
        if (area % 0.25f != 0) {area += (0.25f - area % 0.25f);}
        return area;
    }

    public static float count_cost(float windows, int firm_n, int[][] prices) {
        int a = prices[firm_n - 1][0];
        int b = prices[firm_n - 1][1];
        return windows * a + windows * 4 * b;
    }

    public static float[] compare_cost(float f_firm, float s_firm, float t_firm) {
        float firm, cost;
        if (Math.min(f_firm, s_firm) == f_firm) {firm = 1; cost = f_firm;}
        else {firm = 2; cost = s_firm;}
        if (Math.min(cost, t_firm) == t_firm) {firm = 3; cost = t_firm;}
        return new float[]{firm, cost};
    }
}
