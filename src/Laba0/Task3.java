package Laba0;

import java.util.Scanner;

public class Task3 {
    public static final String[] NAMES = {"кг мяса","шт хлеба","кг огурцов","кг помидоров" ,"л бензина"};
    public static final float[] PRICES = {285.5f, 25.4f, 40, 51, 43.6f};
    public static final int GUYS = 3;

    public static void main(String[] args) {
        System.out.printf("Нам надо скинуться по %.2f", count_and_divide_costs(get_amount()));
    }

    public static float[] get_amount() {
        float[] amount = {0, 0, 0, 0, 0};
        for (int i = 0; i != NAMES.length; i++) {
            System.out.printf("Сколько нам нужно купить %s?: ", NAMES[i]);
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextFloat()) {amount[i] = scan.nextFloat(); }
            else {System.out.println("Ошибка. Вы ввели не число."); i--;}
        }
        return amount;
    }

    public static float count_and_divide_costs(float[] costs) {
        float final_cost = 0;
        for (int j = 0; j != PRICES.length; j++) {
            final_cost += PRICES[j] * costs[j];
        }
        return final_cost / GUYS;
    }
}
